package com.example.app.service;

import com.example.app.dto.LegacyAssetDTO;
import com.example.app.entity.LegacyAsset;
import com.example.app.entity.LegacyAssetId;
import com.example.app.exception.ResourceNotFoundException;
import com.example.app.exception.ValidationException;
import com.example.app.repository.LegacyAssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LegacyAssetService {

    private final LegacyAssetRepository legacyAssetRepository;
    private final AssetValidationService validationService;

    @Autowired
    public LegacyAssetService(LegacyAssetRepository legacyAssetRepository,
                               AssetValidationService validationService) {
        this.legacyAssetRepository = legacyAssetRepository;
        this.validationService = validationService;
    }

    public List<LegacyAssetDTO> findByAsset(String presentationYear, String taxType, 
                                             String presentationCode, String assetSequence) {
        List<LegacyAsset> legacies = legacyAssetRepository
                .findByPresentationYearAndTaxTypeAndPresentationCodeAndAssetSequence(
                        presentationYear, taxType, presentationCode, assetSequence);
        return legacies.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public LegacyAssetDTO create(LegacyAssetDTO dto) {
        validateLegacyAsset(dto);
        
        if ("S".equals(dto.getIsLegacy())) {
            BigDecimal currentTotal = legacyAssetRepository.sumLegacyPercentageForAsset(
                    dto.getPresentationYear(), dto.getTaxType(),
                    dto.getPresentationCode(), dto.getAssetSequence(),
                    dto.getDeceasedNif(), dto.getDeceasedSubCode());
            
            validationService.validateTotalLegacyPercentage(
                    currentTotal != null ? currentTotal : BigDecimal.ZERO, 
                    dto.getLegacyPercentage());
        }
        
        LegacyAsset legacy = convertToEntity(dto);
        legacy = legacyAssetRepository.save(legacy);
        return convertToDTO(legacy);
    }

    public LegacyAssetDTO update(LegacyAssetDTO dto) {
        LegacyAssetId id = createId(dto);
        
        LegacyAsset existing = legacyAssetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Legacy asset not found"));
        
        validateLegacyAsset(dto);
        
        if ("S".equals(dto.getIsLegacy())) {
            BigDecimal currentTotal = legacyAssetRepository.sumLegacyPercentageForAsset(
                    dto.getPresentationYear(), dto.getTaxType(),
                    dto.getPresentationCode(), dto.getAssetSequence(),
                    dto.getDeceasedNif(), dto.getDeceasedSubCode());
            
            BigDecimal adjustedTotal = currentTotal.subtract(
                    existing.getLegacyPercentage() != null ? existing.getLegacyPercentage() : BigDecimal.ZERO);
            
            validationService.validateTotalLegacyPercentage(adjustedTotal, dto.getLegacyPercentage());
        }
        
        existing.setLegacyPercentage(dto.getLegacyPercentage());
        existing.setAcquisitionType(dto.getAcquisitionType());
        
        existing = legacyAssetRepository.save(existing);
        return convertToDTO(existing);
    }

    public void delete(LegacyAssetDTO dto) {
        LegacyAssetId id = createId(dto);
        
        if (!legacyAssetRepository.existsById(id)) {
            throw new ResourceNotFoundException("Legacy asset not found");
        }
        
        legacyAssetRepository.deleteById(id);
    }

    private void validateLegacyAsset(LegacyAssetDTO dto) {
        if ("S".equals(dto.getIsLegacy())) {
            if (dto.getBeneficiaryNif() == null || dto.getBeneficiaryNif().isEmpty()) {
                throw new ValidationException("Beneficiary NIF is required for legacy assets");
            }
            if (dto.getLegacyPercentage() == null) {
                throw new ValidationException("Legacy percentage is required");
            }
            validationService.validateLegacyPercentage(dto.getLegacyPercentage());
            
            if (dto.getAcquisitionType() == null || dto.getAcquisitionType().isEmpty()) {
                throw new ValidationException("Acquisition type is required");
            }
            validationService.validateAcquisitionType(dto.getAcquisitionType());
        }
    }

    private LegacyAssetId createId(LegacyAssetDTO dto) {
        LegacyAssetId id = new LegacyAssetId();
        id.setPresentationYear(dto.getPresentationYear());
        id.setTaxType(dto.getTaxType());
        id.setPresentationCode(dto.getPresentationCode());
        id.setDeceasedNif(dto.getDeceasedNif());
        id.setDeceasedSubCode(dto.getDeceasedSubCode());
        id.setAssetSequence(dto.getAssetSequence());
        id.setBeneficiaryNif(dto.getBeneficiaryNif());
        id.setBeneficiarySubCode(dto.getBeneficiarySubCode());
        return id;
    }

    private LegacyAssetDTO convertToDTO(LegacyAsset entity) {
        LegacyAssetDTO dto = new LegacyAssetDTO();
        dto.setPresentationYear(entity.getPresentationYear());
        dto.setTaxType(entity.getTaxType());
        dto.setPresentationCode(entity.getPresentationCode());
        dto.setDeceasedNif(entity.getDeceasedNif());
        dto.setDeceasedSubCode(entity.getDeceasedSubCode());
        dto.setAssetSequence(entity.getAssetSequence());
        dto.setBeneficiaryNif(entity.getBeneficiaryNif());
        dto.setBeneficiarySubCode(entity.getBeneficiarySubCode());
        dto.setLegacyPercentage(entity.getLegacyPercentage());
        dto.setAcquisitionType(entity.getAcquisitionType());
        dto.setIsLegacy("S");
        return dto;
    }

    private LegacyAsset convertToEntity(LegacyAssetDTO dto) {
        LegacyAsset entity = new LegacyAsset();
        entity.setPresentationYear(dto.getPresentationYear());
        entity.setTaxType(dto.getTaxType());
        entity.setPresentationCode(dto.getPresentationCode());
        entity.setDeceasedNif(dto.getDeceasedNif());
        entity.setDeceasedSubCode(dto.getDeceasedSubCode() != null ? dto.getDeceasedSubCode() : "00");
        entity.setAssetSequence(dto.getAssetSequence());
        entity.setBeneficiaryNif(dto.getBeneficiaryNif());
        entity.setBeneficiarySubCode(dto.getBeneficiarySubCode() != null ? dto.getBeneficiarySubCode() : "00");
        entity.setLegacyPercentage(dto.getLegacyPercentage());
        entity.setAcquisitionType(dto.getAcquisitionType());
        return entity;
    }
}