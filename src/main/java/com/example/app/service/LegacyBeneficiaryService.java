package com.example.app.service;

import com.example.app.dto.LegacyBeneficiaryDTO;
import com.example.app.entity.LegacyBeneficiary;
import com.example.app.entity.LegacyBeneficiaryId;
import com.example.app.exception.BusinessValidationException;
import com.example.app.exception.ResourceNotFoundException;
import com.example.app.repository.LegacyBeneficiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LegacyBeneficiaryService {

    private final LegacyBeneficiaryRepository legacyBeneficiaryRepository;
    private final ValidationService validationService;

    @Autowired
    public LegacyBeneficiaryService(LegacyBeneficiaryRepository legacyBeneficiaryRepository, ValidationService validationService) {
        this.legacyBeneficiaryRepository = legacyBeneficiaryRepository;
        this.validationService = validationService;
    }

    public List<LegacyBeneficiaryDTO> findByAsset(String aapresenta, String vftipoimpu, String cdpresenta, String cdsecubien) {
        List<LegacyBeneficiary> entities = legacyBeneficiaryRepository.findByAapresentaAndVftipoimuAndCdpresentaAndCdsecubien(aapresenta, vftipoimpu, cdpresenta, cdsecubien);
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public LegacyBeneficiaryDTO create(LegacyBeneficiaryDTO dto) {
        LegacyBeneficiary beneficiary = toEntity(dto);
        validateLegacyBeneficiary(beneficiary);

        BigDecimal currentTotal = legacyBeneficiaryRepository.sumLegacyPercentage(
                beneficiary.getAapresenta(), beneficiary.getVftipoimpu(), beneficiary.getCdpresenta(),
                beneficiary.getCdsecubien(), beneficiary.getCdnifcausa(), beneficiary.getCdsubcausa());

        BigDecimal newTotal = (currentTotal != null ? currentTotal : BigDecimal.ZERO)
                .add(beneficiary.getPclegadosp() != null ? beneficiary.getPclegadosp() : BigDecimal.ZERO);
        if (newTotal.compareTo(new BigDecimal("100")) > 0) {
            throw new BusinessValidationException("La suma de los porcentajes legados no puede superar el 100%.");
        }

        LegacyBeneficiary saved = legacyBeneficiaryRepository.save(beneficiary);
        return toDTO(saved);
    }

    @Transactional
    public LegacyBeneficiaryDTO update(LegacyBeneficiaryDTO dto) {
        LegacyBeneficiaryId id = new LegacyBeneficiaryId(
                dto.getPresentationYear(), dto.getTaxType(), dto.getPresentationCode(),
                dto.getAssetSequence(), dto.getCausantNif(), dto.getCausantSubcode(),
                dto.getBeneficiaryNif(), dto.getBeneficiarySubcode());
        
        LegacyBeneficiary existing = legacyBeneficiaryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Beneficiario de legado no existente."));
        
        existing.setPclegadosp(dto.getLegacyPercentage());
        existing.setCdtpadqui2(dto.getAcquisitionType());
        
        LegacyBeneficiary saved = legacyBeneficiaryRepository.save(existing);
        return toDTO(saved);
    }

    @Transactional
    public void delete(String presentationYear, String taxType, String presentationCode,
                       String assetSequence, String causantNif, String causantSubcode,
                       String beneficiaryNif, String beneficiarySubcode) {
        LegacyBeneficiaryId id = new LegacyBeneficiaryId(
                presentationYear, taxType, presentationCode, assetSequence,
                causantNif, causantSubcode, beneficiaryNif, beneficiarySubcode);
        if (!legacyBeneficiaryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Beneficiario de legado no existente.");
        }
        legacyBeneficiaryRepository.deleteById(id);
    }

    private void validateLegacyBeneficiary(LegacyBeneficiary beneficiary) {
        validationService.validateLegacyPercentage(beneficiary.getPclegadosp());
        validationService.validateAcquisitionType(beneficiary.getCdtpadqui2());

        if (beneficiary.getCdnifsupas() == null || beneficiary.getCdnifsupas().trim().isEmpty()) {
            throw new BusinessValidationException("El NIF del beneficiario es obligatorio.");
        }
    }

    private LegacyBeneficiaryDTO toDTO(LegacyBeneficiary entity) {
        LegacyBeneficiaryDTO dto = new LegacyBeneficiaryDTO();
        dto.setPresentationYear(entity.getAapresenta());
        dto.setTaxType(entity.getVftipoimpu());
        dto.setPresentationCode(entity.getCdpresenta());
        dto.setAssetSequence(entity.getCdsecubien());
        dto.setCausantNif(entity.getCdnifcausa());
        dto.setCausantSubcode(entity.getCdsubcausa());
        dto.setBeneficiaryNif(entity.getCdnifsupas());
        dto.setBeneficiarySubcode(entity.getCdsubsupas());
        dto.setLegacyPercentage(entity.getPclegadosp());
        dto.setAcquisitionType(entity.getCdtpadqui2());
        return dto;
    }

    private LegacyBeneficiary toEntity(LegacyBeneficiaryDTO dto) {
        LegacyBeneficiary entity = new LegacyBeneficiary();
        entity.setAapresenta(dto.getPresentationYear());
        entity.setVftipoimpu(dto.getTaxType());
        entity.setCdpresenta(dto.getPresentationCode());
        entity.setCdsecubien(dto.getAssetSequence());
        entity.setCdnifcausa(dto.getCausantNif());
        entity.setCdsubcausa(dto.getCausantSubcode());
        entity.setCdnifsupas(dto.getBeneficiaryNif());
        entity.setCdsubsupas(dto.getBeneficiarySubcode());
        entity.setPclegadosp(dto.getLegacyPercentage());
        entity.setCdtpadqui2(dto.getAcquisitionType());
        return entity;
    }
}
