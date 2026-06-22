package com.example.app.service;

import com.example.app.dto.BankAccountDTO;
import com.example.app.entity.BankAccount;
import com.example.app.entity.AssetDocumentId;
import com.example.app.exception.BusinessValidationException;
import com.example.app.exception.ResourceNotFoundException;
import com.example.app.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final ValidationService validationService;

    @Autowired
    public BankAccountService(BankAccountRepository bankAccountRepository, ValidationService validationService) {
        this.bankAccountRepository = bankAccountRepository;
        this.validationService = validationService;
    }

    public List<BankAccount> findAllByDeclaration(String aapresenta, String vftipoimpu, String cdpresenta) {
        return bankAccountRepository.findByAapresentaAndVftipoimpuAndCdpresenta(aapresenta, vftipoimpu, cdpresenta);
    }

    public Optional<BankAccount> findById(String aapresenta, String vftipoimpu, String cdpresenta, String cdsecubien) {
        AssetDocumentId id = new AssetDocumentId(aapresenta, vftipoimpu, cdpresenta, cdsecubien);
        return bankAccountRepository.findById(id);
    }

    @Transactional
    public BankAccount create(BankAccountDTO dto) {
        validateBankAccount(dto);

        BankAccount entity = new BankAccount();
        entity.setAapresenta(dto.getAapresenta());
        entity.setVftipoimpu(dto.getVftipoimpu());
        entity.setCdpresenta(dto.getCdpresenta());
        entity.setCdsecubien(dto.getCdsecubien());
        updateEntityFromDto(entity, dto);

        return bankAccountRepository.save(entity);
    }

    @Transactional
    public BankAccount update(BankAccountDTO dto) {
        AssetDocumentId id = new AssetDocumentId(dto.getAapresenta(), dto.getVftipoimpu(), dto.getCdpresenta(), dto.getCdsecubien());
        BankAccount existing = bankAccountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta bancaria no existente."));

        validateBankAccount(dto);
        updateEntityFromDto(existing, dto);

        return bankAccountRepository.save(existing);
    }

    @Transactional
    public void delete(String aapresenta, String vftipoimpu, String cdpresenta, String cdsecubien) {
        AssetDocumentId id = new AssetDocumentId(aapresenta, vftipoimpu, cdpresenta, cdsecubien);
        if (!bankAccountRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cuenta bancaria no existente.");
        }
        bankAccountRepository.deleteById(id);
    }

    private void validateBankAccount(BankAccountDTO dto) {
        validationService.validateDeclaredValue(dto.getPtdeclarad());
        validationService.validateVerifiedValue(dto.getPtcomproba());

        if (dto.getTldeposito() != null && !dto.getTldeposito().trim().isEmpty()) {
            if (!validationService.validateIBAN(dto.getTldeposito())) {
                throw new BusinessValidationException("Nº de IBAN no válido.");
            }
        }
    }

    private void updateEntityFromDto(BankAccount entity, BankAccountDTO dto) {
        entity.setTlentidepo(dto.getTlentidepo());
        entity.setTldeposito(dto.getTldeposito());
        entity.setPtdeclarad(truncateValue(dto.getPtdeclarad(), 2));
        entity.setPtcomproba(truncateValue(dto.getPtcomproba(), 2));
        entity.setPctitulari(dto.getPctitulari());
        entity.setTlobservac(dto.getTlobservac());
    }

    private BigDecimal truncateValue(BigDecimal value, int scale) {
        if (value == null) {
            return null;
        }
        return value.setScale(scale, RoundingMode.DOWN);
    }
}