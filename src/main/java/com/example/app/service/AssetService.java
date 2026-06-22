package com.example.app.service;

import com.example.app.dto.AssetDocumentDTO;
import com.example.app.entity.AssetDocument;
import com.example.app.entity.AssetDocumentId;
import com.example.app.exception.BusinessValidationException;
import com.example.app.exception.ResourceNotFoundException;
import com.example.app.repository.AssetDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AssetService {

    private final AssetDocumentRepository assetDocumentRepository;
    private final ValidationService validationService;

    @Autowired
    public AssetService(AssetDocumentRepository assetDocumentRepository, ValidationService validationService) {
        this.assetDocumentRepository = assetDocumentRepository;
        this.validationService = validationService;
    }

    public List<AssetDocument> findAllByDeclaration(String aapresenta, String vftipoimpu, String cdpresenta) {
        return assetDocumentRepository.findByAapresentaAndVftipoimpu AndCdpresenta(aapresenta, vftipoimpu, cdpresenta);
    }

    public Optional<AssetDocument> findById(String aapresenta, String vftipoimpu, String cdpresenta, String cdsecubien) {
        AssetDocumentId id = new AssetDocumentId(aapresenta, vftipoimpu, cdpresenta, cdsecubien);
        return assetDocumentRepository.findById(id);
    }

    @Transactional
    public AssetDocument create(AssetDocumentDTO dto) {
        validationService.validateNatureType(dto.getCdnatbien2());

        String cdsecubien = dto.getCdsecubien();
        if (cdsecubien == null || cdsecubien.trim().isEmpty()) {
            Integer nextSeq = assetDocumentRepository.findNextSequence(dto.getAapresenta(), dto.getVftipoimpu(), dto.getCdpresenta());
            cdsecubien = validationService.padLeft(String.valueOf(nextSeq), 3, '0');
        } else {
            cdsecubien = validationService.padLeft(cdsecubien, 3, '0');
        }

        long count = assetDocumentRepository.countByAapresentaAndVftipoimpu AndCdpresentaAndCdsecubien(
                dto.getAapresenta(), dto.getVftipoimpu(), dto.getCdpresenta(), cdsecubien);
        if (count > 0) {
            throw new BusinessValidationException("Registro ya existe. Entre por modificación.");
        }

        AssetDocument entity = new AssetDocument();
        entity.setAapresenta(dto.getAapresenta());
        entity.setVftipoimpu(dto.getVftipoimpu());
        entity.setCdpresenta(dto.getCdpresenta());
        entity.setCdsecubien(cdsecubien);
        entity.setCdnatbien2(dto.getCdnatbien2());
        entity.setCdsecuacem(dto.getCdsecuacem());
        entity.setFccomproba(dto.getFccomproba());
        entity.setIdcomproba(dto.getIdcomproba());
        entity.setPtdeclarad(dto.getPtdeclarad());
        entity.setPtcomproba(dto.getPtcomproba());

        return assetDocumentRepository.save(entity);
    }

    @Transactional
    public AssetDocument update(AssetDocumentDTO dto) {
        AssetDocumentId id = new AssetDocumentId(dto.getAapresenta(), dto.getVftipoimpu(), dto.getCdpresenta(), dto.getCdsecubien());
        AssetDocument entity = assetDocumentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bien no existente."));

        entity.setCdnatbien2(dto.getCdnatbien2());
        entity.setCdsecuacem(dto.getCdsecuacem());
        entity.setFccomproba(dto.getFccomproba());
        entity.setIdcomproba(dto.getIdcomproba());
        entity.setPtdeclarad(dto.getPtdeclarad());
        entity.setPtcomproba(dto.getPtcomproba());

        return assetDocumentRepository.save(entity);
    }

    @Transactional
    public void delete(String aapresenta, String vftipoimpu, String cdpresenta, String cdsecubien) {
        AssetDocumentId id = new AssetDocumentId(aapresenta, vftipoimpu, cdpresenta, cdsecubien);
        if (!assetDocumentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Bien no existente.");
        }
        assetDocumentRepository.deleteById(id);
    }

    public boolean isAssetValuated(String aapresenta, String vftipoimpu, String cdpresenta, String cdsecubien) {
        Optional<AssetDocument> valuated = assetDocumentRepository.findValuatedAsset(aapresenta, vftipoimpu, cdpresenta, cdsecubien);
        return valuated.isPresent();
    }

    public String generateNextSequence(String aapresenta, String vftipoimpu, String cdpresenta) {
        Integer nextSeq = assetDocumentRepository.findNextSequence(aapresenta, vftipoimpu, cdpresenta);
        return validationService.padLeft(String.valueOf(nextSeq), 3, '0');
    }
}