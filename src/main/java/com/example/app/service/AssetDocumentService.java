package com.example.app.service;

import com.example.app.dto.AssetDocumentDTO;
import com.example.app.entity.AssetDocument;
import com.example.app.entity.AssetDocumentId;
import com.example.app.exception.ResourceNotFoundException;
import com.example.app.exception.ValidationException;
import com.example.app.repository.AssetDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AssetDocumentService {

    private final AssetDocumentRepository assetDocumentRepository;
    private final AssetValidationService validationService;
    private final AssetCalculationService calculationService;

    @Autowired
    public AssetDocumentService(AssetDocumentRepository assetDocumentRepository,
                                 AssetValidationService validationService,
                                 AssetCalculationService calculationService) {
        this.assetDocumentRepository = assetDocumentRepository;
        this.validationService = validationService;
        this.calculationService = calculationService;
    }

    public List<AssetDocumentDTO> findByDocument(String presentationYear, String taxType, String presentationCode) {
        List<AssetDocument> assets = assetDocumentRepository
                .findByPresentationYearAndTaxTypeAndPresentationCode(presentationYear, taxType, presentationCode);
        return assets.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AssetDocumentDTO findById(String presentationYear, String taxType, 
                                      String presentationCode, String assetSequence) {
        AssetDocumentId id = new AssetDocumentId(presentationYear, taxType, presentationCode, assetSequence);
        AssetDocument asset = assetDocumentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
        return convertToDTO(asset);
    }

    public AssetDocumentDTO create(AssetDocumentDTO dto) {
        validateForCreate(dto);
        
        String paddedSequence = calculationService.padAssetSequence(dto.getAssetSequence());
        dto.setAssetSequence(paddedSequence);
        
        Long count = assetDocumentRepository.countByKey(
                dto.getPresentationYear(), dto.getTaxType(), 
                dto.getPresentationCode(), dto.getAssetSequence());
        
        if (count > 0) {
            throw new ValidationException("Asset already exists. Use modify instead.");
        }
        
        AssetDocument asset = convertToEntity(dto);
        asset = assetDocumentRepository.save(asset);
        return convertToDTO(asset);
    }

    public AssetDocumentDTO update(AssetDocumentDTO dto) {
        validateForUpdate(dto);
        
        AssetDocumentId id = new AssetDocumentId(
                dto.getPresentationYear(), dto.getTaxType(),
                dto.getPresentationCode(), dto.getAssetSequence());
        
        AssetDocument existing = assetDocumentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
        
        Optional<AssetDocument> verified = assetDocumentRepository.findVerifiedAsset(
                dto.getPresentationYear(), dto.getTaxType(),
                dto.getPresentationCode(), dto.getAssetSequence());
        
        if (verified.isPresent()) {
            updateVerifiedAsset(existing, dto);
        } else {
            updateAsset(existing, dto);
        }
        
        existing = assetDocumentRepository.save(existing);
        return convertToDTO(existing);
    }

    public void delete(String presentationYear, String taxType, 
                       String presentationCode, String assetSequence) {
        AssetDocumentId id = new AssetDocumentId(presentationYear, taxType, presentationCode, assetSequence);
        
        if (!assetDocumentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Asset not found");
        }
        
        assetDocumentRepository.deleteById(id);
    }

    public String getNextAssetSequence(String presentationYear, String taxType, String presentationCode) {
        Integer next = assetDocumentRepository.findNextAssetSequence(presentationYear, taxType, presentationCode);
        return String.format("%03d", next);
    }

    public boolean isAssetVerified(String presentationYear, String taxType, 
                                    String presentationCode, String assetSequence) {
        return assetDocumentRepository.findVerifiedAsset(
                presentationYear, taxType, presentationCode, assetSequence).isPresent();
    }

    private void validateForCreate(AssetDocumentDTO dto) {
        if (dto.getAssetSequence() == null || dto.getAssetSequence().isEmpty()) {
            throw new ValidationException("Asset sequence is required");
        }
        validationService.validateAssetNature(dto.getAssetNature(), null);
        validationService.validateAssetPosition(dto.getAssetPosition());
        validationService.validateTransmissionPercentage(dto.getTransmissionPercentage());
        validationService.validateAmount(dto.getDeclaredAmount(), "Declared amount");
        validationService.validateAmount(dto.getVerifiedAmount(), "Verified amount");
    }

    private void validateForUpdate(AssetDocumentDTO dto) {
        validationService.validateTransmissionPercentage(dto.getTransmissionPercentage());
        validationService.validateAmount(dto.getDeclaredAmount(), "Declared amount");
        validationService.validateAmount(dto.getVerifiedAmount(), "Verified amount");
        validationService.validateVerifiedAmountNotZero(dto.getVerifiedAmount());
    }

    private void updateAsset(AssetDocument existing, AssetDocumentDTO dto) {
        if (dto.getAssetNature() != null) {
            existing.setAssetNature(dto.getAssetNature());
        }
        if (dto.getAssetPosition() != null) {
            existing.setAssetPosition(dto.getAssetPosition());
        }
        if (dto.getTransmissionPercentage() != null) {
            existing.setTransmissionPercentage(dto.getTransmissionPercentage());
        }
        if (dto.getDeclaredAmount() != null) {
            existing.setDeclaredAmount(dto.getDeclaredAmount());
        }
        if (dto.getVerifiedAmount() != null) {
            existing.setVerifiedAmount(dto.getVerifiedAmount());
        }
        existing.setReferenceValueStatus(dto.getReferenceValueStatus());
        existing.setHasReferenceValue(dto.getHasReferenceValue());
        existing.setReferenceValueValid(dto.getReferenceValueValid());
        existing.setReferenceValue(dto.getReferenceValue());
    }

    private void updateVerifiedAsset(AssetDocument existing, AssetDocumentDTO dto) {
        if (dto.getDeclaredAmount() != null) {
            existing.setDeclaredAmount(dto.getDeclaredAmount());
        }
    }

    private AssetDocumentDTO convertToDTO(AssetDocument entity) {
        AssetDocumentDTO dto = new AssetDocumentDTO();
        dto.setPresentationYear(entity.getPresentationYear());
        dto.setTaxType(entity.getTaxType());
        dto.setPresentationCode(entity.getPresentationCode());
        dto.setAssetSequence(entity.getAssetSequence());
        dto.setAssetNature(entity.getAssetNature());
        dto.setAssetPosition(entity.getAssetPosition());
        dto.setTransmissionPercentage(entity.getTransmissionPercentage());
        dto.setDeclaredAmount(entity.getDeclaredAmount());
        dto.setVerifiedAmount(entity.getVerifiedAmount());
        dto.setVerificationDate(entity.getVerificationDate());
        dto.setValuationNumber(entity.getValuationNumber());
        dto.setReferenceValueStatus(entity.getReferenceValueStatus());
        dto.setHasReferenceValue(entity.getHasReferenceValue());
        dto.setReferenceValueValid(entity.getReferenceValueValid());
        dto.setReferenceValue(entity.getReferenceValue());
        
        if (entity.getVerifiedAmount() != null && entity.getTransmissionPercentage() != null) {
            dto.setProportionalVerifiedAmount(
                    calculationService.calculateProportionalVerifiedAmount(
                            entity.getVerifiedAmount(), entity.getTransmissionPercentage()));
        }
        
        dto.setAssetNatureDescription(getAssetNatureDescription(entity.getAssetNature()));
        
        return dto;
    }

    private AssetDocument convertToEntity(AssetDocumentDTO dto) {
        AssetDocument entity = new AssetDocument();
        entity.setPresentationYear(dto.getPresentationYear());
        entity.setTaxType(dto.getTaxType());
        entity.setPresentationCode(dto.getPresentationCode());
        entity.setAssetSequence(dto.getAssetSequence());
        entity.setAssetNature(dto.getAssetNature());
        entity.setAssetPosition(dto.getAssetPosition());
        entity.setTransmissionPercentage(dto.getTransmissionPercentage() != null ? 
                dto.getTransmissionPercentage() : new BigDecimal("100"));
        entity.setDeclaredAmount(dto.getDeclaredAmount());
        entity.setVerifiedAmount(dto.getVerifiedAmount());
        entity.setReferenceValueStatus(dto.getReferenceValueStatus());
        entity.setHasReferenceValue(dto.getHasReferenceValue());
        entity.setReferenceValueValid(dto.getReferenceValueValid());
        entity.setReferenceValue(dto.getReferenceValue());
        return entity;
    }

    private String getAssetNatureDescription(String nature) {
        if (nature == null) {
            return null;
        }
        switch (nature) {
            case "U": return "Urban Property";
            case "R": return "Rustic Property";
            case "V": return "Vehicle";
            case "E": return "Listed Securities";
            case "N": return "Unlisted Securities";
            case "C": return "Bank Account";
            case "A": return "Business Asset";
            case "T":
            case "O": return "Other Asset";
            default: return "Unknown";
        }
    }
}