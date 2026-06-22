package com.example.app.service;

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
import java.util.Optional;

@Service
public class LegacyBeneficiaryService {

    private final LegacyBeneficiaryRepository legacyBeneficiaryRepository;
    private final ValidationService validationService;

    @Autowired
    public LegacyBeneficiaryService(LegacyBeneficiaryRepository legacyBeneficiaryRepository, ValidationService validationService) {
        this.legacyBeneficiaryRepository = legacyBeneficiaryRepository;
        this.validationService = validationService;
    }

    public List<LegacyBeneficiary> findByAsset(String aapresenta, String vftipoimpu, String cdpresenta, String cdsecubien) {
        return legacyBeneficiaryRepository.findByAapresentaAndVftipoimpu AndCdpresentaAndCdsecubien(aapresenta, vftipoimpu, cdpresenta, cdsecubien);
    }

    @Transactional
    public LegacyBeneficiary create(LegacyBeneficiary beneficiary) {
        validateLegacyBeneficiary(beneficiary);

        BigDecimal currentTotal = legacyBeneficiaryRepository.sumLegacyPercentage(
                beneficiary.getAapresenta(), beneficiary.getVftipoimpu(), beneficiary.getCdpresenta(),
                beneficiary.getCdsecubien(), beneficiary.getCdnifcausa(), beneficiary.getCdsubcausa());

        BigDecimal newTotal = currentTotal.add(beneficiary.getPclegadosp() != null ? beneficiary.getPclegadosp() : BigDecimal.ZERO);
        if (newTotal.compareTo(new BigDecimal("100")) > 0) {
            throw new BusinessValidationException("La suma de los porcentajes legados no puede superar el 100%.");
        }

        return legacyBeneficiaryRepository.save(beneficiary);
    }

    @Transactional
    public void delete(LegacyBeneficiaryId id) {
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
}