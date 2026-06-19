package com.example.app.service;

import com.example.app.dto.LegacyBeneficiaryDTO;
import com.example.app.entity.LegacyBeneficiary;
import com.example.app.repository.LegacyBeneficiaryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LegacyBeneficiaryService {

    private final LegacyBeneficiaryRepository legacyBeneficiaryRepository;
    private final ValidationService validationService;

    private static final BigDecimal HUNDRED = new BigDecimal("100");

    public LegacyBeneficiaryService(LegacyBeneficiaryRepository legacyBeneficiaryRepository,
                                     ValidationService validationService) {
        this.legacyBeneficiaryRepository = legacyBeneficiaryRepository;
        this.validationService = validationService;
    }

    public List<LegacyBeneficiaryDTO> findByAsset(String presentationYear, String taxType,
                                                   String presentationCode, String assetSequence) {
        List<LegacyBeneficiary> beneficiaries = legacyBeneficiaryRepository
                .findByPresentationYearAndTaxTypeAndPresentationCodeAndAssetSequence(
                        presentationYear, taxType, presentationCode, assetSequence);
        
        List<LegacyBeneficiaryDTO> dtos = new ArrayList<LegacyBeneficiaryDTO>();
        for (LegacyBeneficiary beneficiary : beneficiaries) {
            dtos.add(convertToDTO(beneficiary));
        }
        return dtos;
    }

    @Transactional
    public LegacyBeneficiaryDTO create(LegacyBeneficiaryDTO dto) {
        validateLegacyBeneficiary(dto);
        
        String formattedBeneficiaryNif = validationService.validateAndFormatNif(dto.getBeneficiaryNif());
        dto.setBeneficiaryNif(formattedBeneficiaryNif);
        
        BigDecimal currentTotal = legacyBeneficiaryRepository.sumLegacyPercentageForAsset(
                dto.getPresentationYear(), dto.getTaxType(), dto.getPresentationCode(),
                dto.getAssetSequence(), dto.getCausantNif(), dto.getCausantSubcode());
        
        if (currentTotal == null) {
            currentTotal = BigDecimal.ZERO;
        }
        
        BigDecimal newTotal = currentTotal.add(dto.getLegacyPercentage() != null ? dto.getLegacyPercentage() : BigDecimal.ZERO);
        if (newTotal.compareTo(HUNDRED) > 0) {
            throw new IllegalArgumentException("Total legacy percentage cannot exceed 100%");
        }
        
        LegacyBeneficiary entity = convertToEntity(dto);
        LegacyBeneficiary saved = legacyBeneficiaryRepository.save(entity);
        
        return convertToDTO(saved);
    }

    @Transactional
    public LegacyBeneficiaryDTO update(LegacyBeneficiaryDTO dto) {
        validateLegacyBeneficiary(dto);
        
        LegacyBeneficiary.LegacyBeneficiaryId id = new LegacyBeneficiary.LegacyBeneficiaryId();
        id.setPresentationYear(dto.getPresentationYear());
        id.setTaxType(dto.getTaxType());
        id.setPresentationCode(dto.getPresentationCode());
        id.setAssetSequence(dto.getAssetSequence());
        id.setCausantNif(dto.getCausantNif());
        id.setCausantSubcode(dto.getCausantSubcode());
        id.setBeneficiaryNif(dto.getBeneficiaryNif());
        id.setBeneficiarySubcode(dto.getBeneficiarySubcode());
        
        LegacyBeneficiary existing = legacyBeneficiaryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Legacy beneficiary not found"));
        
        BigDecimal currentTotal = legacyBeneficiaryRepository.sumLegacyPercentageForAsset(
                dto.getPresentationYear(), dto.getTaxType(), dto.getPresentationCode(),
                dto.getAssetSequence(), dto.getCausantNif(), dto.getCausantSubcode());
        
        if (currentTotal == null) {
            currentTotal = BigDecimal.ZERO;
        }
        
        BigDecimal existingPercentage = existing.getLegacyPercentage() != null ? existing.getLegacyPercentage() : BigDecimal.ZERO;
        BigDecimal newPercentage = dto.getLegacyPercentage() != null ? dto.getLegacyPercentage() : BigDecimal.ZERO;
        BigDecimal newTotal = currentTotal.subtract(existingPercentage).add(newPercentage);
        
        if (newTotal.compareTo(HUNDRED) > 0) {
            throw new IllegalArgumentException("Total legacy percentage cannot exceed 100%");
        }
        
        existing.setLegacyIndicator(dto.getLegacyIndicator());
        existing.setLegacyPercentage(dto.getLegacyPercentage());
        existing.setAcquisitionType(dto.getAcquisitionType());
        
        LegacyBeneficiary saved = legacyBeneficiaryRepository.save(existing);
        
        return convertToDTO(saved);
    }

    @Transactional
    public void delete(String presentationYear, String taxType, String presentationCode,
                       String assetSequence, String causantNif, String causantSubcode,
                       String beneficiaryNif, String beneficiarySubcode) {
        LegacyBeneficiary.LegacyBeneficiaryId id = new LegacyBeneficiary.LegacyBeneficiaryId();
        id.setPresentationYear(presentationYear);
        id.setTaxType(taxType);
        id.setPresentationCode(presentationCode);
        id.setAssetSequence(assetSequence);
        id.setCausantNif(causantNif);
        id.setCausantSubcode(causantSubcode);
        id.setBeneficiaryNif(beneficiaryNif);
        id.setBeneficiarySubcode(beneficiarySubcode);
        
        legacyBeneficiaryRepository.deleteById(id);
    }

    private void validateLegacyBeneficiary(LegacyBeneficiaryDTO dto) {
        validationService.validateIndicator(dto.getLegacyIndicator(), "Legacy indicator");
        validationService.validateLegacyPercentage(dto.getLegacyPercentage());
        validationService.validateAcquisitionType(dto.getAcquisitionType());
        
        if ("S".equals(dto.getLegacyIndicator())) {
            if (dto.getLegacyPercentage() == null) {
                throw new IllegalArgumentException("Legacy percentage is required when legacy indicator is S");
            }
            if (dto.getAcquisitionType() == null) {
                throw new IllegalArgumentException("Acquisition type is required when legacy indicator is S");
            }
        }
    }

    private LegacyBeneficiaryDTO convertToDTO(LegacyBeneficiary entity) {
        LegacyBeneficiaryDTO dto = new LegacyBeneficiaryDTO();
        dto.setPresentationYear(entity.getPresentationYear());
        dto.setTaxType(entity.getTaxType());
        dto.setPresentationCode(entity.getPresentationCode());
        dto.setAssetSequence(entity.getAssetSequence());
        dto.setCausantNif(entity.getCausantNif());
        dto.setCausantSubcode(entity.getCausantSubcode());
        dto.setBeneficiaryNif(entity.getBeneficiaryNif());
        dto.setBeneficiarySubcode(entity.getBeneficiarySubcode());
        dto.setLegacyIndicator(entity.getLegacyIndicator());
        dto.setLegacyPercentage(entity.getLegacyPercentage());
        dto.setAcquisitionType(entity.getAcquisitionType());
        return dto;
    }

    private LegacyBeneficiary convertToEntity(LegacyBeneficiaryDTO dto) {
        LegacyBeneficiary entity = new LegacyBeneficiary();
        entity.setPresentationYear(dto.getPresentationYear());
        entity.setTaxType(dto.getTaxType());
        entity.setPresentationCode(dto.getPresentationCode());
        entity.setAssetSequence(dto.getAssetSequence());
        entity.setCausantNif(dto.getCausantNif());
        entity.setCausantSubcode(dto.getCausantSubcode());
        entity.setBeneficiaryNif(dto.getBeneficiaryNif());
        entity.setBeneficiarySubcode(dto.getBeneficiarySubcode());
        entity.setLegacyIndicator(dto.getLegacyIndicator());
        entity.setLegacyPercentage(dto.getLegacyPercentage());
        entity.setAcquisitionType(dto.getAcquisitionType());
        return entity;
    }
}