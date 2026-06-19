package com.example.app.service;

import com.example.app.dto.UrbanPropertyDTO;
import com.example.app.entity.AssetDocument;
import com.example.app.entity.UrbanProperty;
import com.example.app.repository.AssetDocumentRepository;
import com.example.app.repository.UrbanPropertyRepository;
import com.example.app.repository.ProvinceRepository;
import com.example.app.repository.MunicipalityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UrbanPropertyService {

    private final UrbanPropertyRepository urbanPropertyRepository;
    private final AssetDocumentRepository assetDocumentRepository;
    private final ProvinceRepository provinceRepository;
    private final MunicipalityRepository municipalityRepository;
    private final ValidationService validationService;
    private final ConformityService conformityService;

    public UrbanPropertyService(UrbanPropertyRepository urbanPropertyRepository,
                                 AssetDocumentRepository assetDocumentRepository,
                                 ProvinceRepository provinceRepository,
                                 MunicipalityRepository municipalityRepository,
                                 ValidationService validationService,
                                 ConformityService conformityService) {
        this.urbanPropertyRepository = urbanPropertyRepository;
        this.assetDocumentRepository = assetDocumentRepository;
        this.provinceRepository = provinceRepository;
        this.municipalityRepository = municipalityRepository;
        this.validationService = validationService;
        this.conformityService = conformityService;
    }

    public List<UrbanPropertyDTO> findAll() {
        List<UrbanProperty> properties = urbanPropertyRepository.findAll();
        List<UrbanPropertyDTO> dtos = new ArrayList<UrbanPropertyDTO>();
        for (UrbanProperty property : properties) {
            dtos.add(convertToDTO(property));
        }
        return dtos;
    }

    public Optional<UrbanPropertyDTO> findById(String presentationYear, String taxType, 
                                                String presentationCode, String assetSequence) {
        return urbanPropertyRepository.findByPresentationYearAndTaxTypeAndPresentationCodeAndAssetSequence(
                presentationYear, taxType, presentationCode, assetSequence)
                .map(this::convertToDTO);
    }

    @Transactional
    public UrbanPropertyDTO create(UrbanPropertyDTO dto) {
        validateUrbanProperty(dto);
        
        String paddedSequence = validationService.padLeft(dto.getAssetSequence(), 3, '0');
        dto.setAssetSequence(paddedSequence);
        
        String paddedProvince = validationService.padLeft(dto.getProvinceCode(), 2, '0');
        dto.setProvinceCode(paddedProvince);
        
        if (dto.getMunicipalityCode() != null) {
            String paddedMunicipality = validationService.padLeft(dto.getMunicipalityCode(), 3, '0');
            dto.setMunicipalityCode(paddedMunicipality);
        }
        
        if (dto.getCountryCode() == null || dto.getCountryCode().trim().isEmpty()) {
            dto.setCountryCode("ESP");
        }
        
        if (dto.getTransmissionPercentage() == null) {
            dto.setTransmissionPercentage(new BigDecimal("100"));
        }
        
        dto.setDeclaredValue(validationService.truncateToTwoDecimals(dto.getDeclaredValue()));
        dto.setVerifiedValue(validationService.truncateToTwoDecimals(dto.getVerifiedValue()));
        
        String conformity = conformityService.calculateConformityForUrbanProperty(
                dto.getReferenceValueIndicator(),
                dto.getValidReferenceValueIndicator(),
                dto.getReferenceValue(),
                dto.getDeclaredValue(),
                dto.getVerifiedValue());
        dto.setConformityStatus(conformity);
        
        BigDecimal calculatedValue = conformityService.calculateConformingValue(
                dto.getDeclaredValue(),
                dto.getVerifiedValue(),
                dto.getTransmissionPercentage(),
                conformity,
                2);
        dto.setCalculatedVerifiedValue(calculatedValue);
        
        AssetDocument assetDoc = new AssetDocument();
        assetDoc.setPresentationYear(dto.getPresentationYear());
        assetDoc.setTaxType(dto.getTaxType());
        assetDoc.setPresentationCode(dto.getPresentationCode());
        assetDoc.setAssetSequence(dto.getAssetSequence());
        assetDoc.setAssetNature("U");
        assetDoc.setDeclaredValue(dto.getDeclaredValue());
        assetDoc.setVerifiedValue(dto.getVerifiedValue());
        assetDocumentRepository.save(assetDoc);
        
        UrbanProperty property = convertToEntity(dto);
        UrbanProperty saved = urbanPropertyRepository.save(property);
        
        return convertToDTO(saved);
    }

    @Transactional
    public UrbanPropertyDTO update(UrbanPropertyDTO dto) {
        validateUrbanProperty(dto);
        
        UrbanProperty existing = urbanPropertyRepository
                .findByPresentationYearAndTaxTypeAndPresentationCodeAndAssetSequence(
                        dto.getPresentationYear(), dto.getTaxType(), 
                        dto.getPresentationCode(), dto.getAssetSequence())
                .orElseThrow(() -> new IllegalArgumentException("Urban property not found"));
        
        dto.setDeclaredValue(validationService.truncateToTwoDecimals(dto.getDeclaredValue()));
        dto.setVerifiedValue(validationService.truncateToTwoDecimals(dto.getVerifiedValue()));
        
        String conformity = conformityService.calculateConformityForUrbanProperty(
                dto.getReferenceValueIndicator(),
                dto.getValidReferenceValueIndicator(),
                dto.getReferenceValue(),
                dto.getDeclaredValue(),
                dto.getVerifiedValue());
        dto.setConformityStatus(conformity);
        
        BigDecimal calculatedValue = conformityService.calculateConformingValue(
                dto.getDeclaredValue(),
                dto.getVerifiedValue(),
                dto.getTransmissionPercentage(),
                conformity,
                2);
        dto.setCalculatedVerifiedValue(calculatedValue);
        
        updateEntityFromDTO(existing, dto);
        UrbanProperty saved = urbanPropertyRepository.save(existing);
        
        assetDocumentRepository.findByPresentationYearAndTaxTypeAndPresentationCodeAndAssetSequence(
                dto.getPresentationYear(), dto.getTaxType(), 
                dto.getPresentationCode(), dto.getAssetSequence())
                .ifPresent(doc -> {
                    doc.setDeclaredValue(dto.getDeclaredValue());
                    doc.setVerifiedValue(dto.getVerifiedValue());
                    assetDocumentRepository.save(doc);
                });
        
        return convertToDTO(saved);
    }

    @Transactional
    public void delete(String presentationYear, String taxType, 
                       String presentationCode, String assetSequence) {
        urbanPropertyRepository.findByPresentationYearAndTaxTypeAndPresentationCodeAndAssetSequence(
                presentationYear, taxType, presentationCode, assetSequence)
                .ifPresent(urbanPropertyRepository::delete);
        
        AssetDocument.AssetDocumentId docId = new AssetDocument.AssetDocumentId();
        docId.setPresentationYear(presentationYear);
        docId.setTaxType(taxType);
        docId.setPresentationCode(presentationCode);
        docId.setAssetSequence(assetSequence);
        assetDocumentRepository.deleteById(docId);
    }

    private void validateUrbanProperty(UrbanPropertyDTO dto) {
        validationService.validateProvinceCode(dto.getProvinceCode());
        validationService.validateMunicipalityCode(dto.getMunicipalityCode());
        validationService.validatePostalCode(dto.getPostalCode(), dto.getProvinceCode());
        validationService.validateTransmissionPercentage(dto.getTransmissionPercentage());
        validationService.validatePositionType(dto.getPositionType());
        validationService.validateIndicator(dto.getRentalIndicator(), "Rental indicator");
        validationService.validateIndicator(dto.getOfficialProtectionIndicator(), "Official protection indicator");
        validationService.validateIndicator(dto.getDisqualificationIndicator(), "Disqualification indicator");
        validationService.validateIndicator(dto.getHabitualResidenceIndicator(), "Habitual residence indicator");
        validationService.validateDuplicateIndicator(dto.getDuplicateIndicator());
        validationService.validateConstructionYear(dto.getConstructionYear(), null);
        validationService.validateMonetaryValue(dto.getDeclaredValue(), "Declared value");
        validationService.validateMonetaryValue(dto.getVerifiedValue(), "Verified value");
        validationService.validateMonetaryValue(dto.getMaxSalePrice(), "Max sale price");
        validationService.validateMonetaryValue(dto.getHabitualResidenceValue(), "Habitual residence value");
        
        if (dto.getCadastralReference() != null && !validationService.validateCadastralReference(dto.getCadastralReference())) {
            throw new IllegalArgumentException("Invalid cadastral reference format");
        }
        
        if (dto.getNumberOfUnits() != null && !"PG".equals(dto.getPropertyType()) && dto.getNumberOfUnits() != 1) {
            throw new IllegalArgumentException("Number of units must be 1 for this property type");
        }
        
        if (dto.getHabitualResidenceValue() != null && dto.getVerifiedValue() != null 
            && dto.getHabitualResidenceValue().compareTo(dto.getVerifiedValue()) > 0) {
            throw new IllegalArgumentException("Habitual residence value cannot exceed verified value");
        }
    }

    private UrbanPropertyDTO convertToDTO(UrbanProperty entity) {
        UrbanPropertyDTO dto = new UrbanPropertyDTO();
        dto.setPresentationYear(entity.getPresentationYear());
        dto.setTaxType(entity.getTaxType());
        dto.setPresentationCode(entity.getPresentationCode());
        dto.setAssetSequence(entity.getAssetSequence());
        dto.setProvinceCode(entity.getProvinceCode());
        dto.setMunicipalityCode(entity.getMunicipalityCode());
        dto.setStreetTypeCode(entity.getStreetTypeCode());
        dto.setStreetName(entity.getStreetName());
        dto.setStreetNumber(entity.getStreetNumber());
        dto.setPostalCode(entity.getPostalCode());
        dto.setCountryCode(entity.getCountryCode());
        dto.setCadastralReference(entity.getCadastralReference());
        dto.setPropertyType(entity.getPropertyType());
        dto.setConstructionYear(entity.getConstructionYear());
        dto.setSituationCode(entity.getSituationCode());
        dto.setRentalIndicator(entity.getRentalIndicator());
        dto.setRentalContractYear(entity.getRentalContractYear());
        dto.setOfficialProtectionIndicator(entity.getOfficialProtectionIndicator());
        dto.setDisqualificationIndicator(entity.getDisqualificationIndicator());
        dto.setMaxSalePrice(entity.getMaxSalePrice());
        dto.setHabitualResidenceIndicator(entity.getHabitualResidenceIndicator());
        dto.setHabitualResidenceValue(entity.getHabitualResidenceValue());
        dto.setNumberOfUnits(entity.getNumberOfUnits());
        dto.setSurfaceArea(entity.getSurfaceArea());
        dto.setDeclaredValue(entity.getDeclaredValue());
        dto.setVerifiedValue(entity.getVerifiedValue());
        dto.setTransmissionPercentage(entity.getTransmissionPercentage());
        dto.setPositionType(entity.getPositionType());
        dto.setReferenceValueIndicator(entity.getReferenceValueIndicator());
        dto.setValidReferenceValueIndicator(entity.getValidReferenceValueIndicator());
        dto.setReferenceValue(entity.getReferenceValue());
        dto.setDuplicateIndicator(entity.getDuplicateIndicator());
        dto.setObservations(entity.getObservations());
        
        provinceRepository.findByProvinceCode(entity.getProvinceCode())
                .ifPresent(p -> dto.setProvinceName(p.getProvinceName()));
        
        if (entity.getProvinceCode() != null && entity.getMunicipalityCode() != null) {
            municipalityRepository.findByProvinceCodeAndMunicipalityCode(
                    entity.getProvinceCode(), entity.getMunicipalityCode())
                    .ifPresent(m -> dto.setMunicipalityName(m.getMunicipalityName()));
        }
        
        String conformity = conformityService.calculateConformityForUrbanProperty(
                entity.getReferenceValueIndicator(),
                entity.getValidReferenceValueIndicator(),
                entity.getReferenceValue(),
                entity.getDeclaredValue(),
                entity.getVerifiedValue());
        dto.setConformityStatus(conformity);
        
        BigDecimal calculatedValue = conformityService.calculateConformingValue(
                entity.getDeclaredValue(),
                entity.getVerifiedValue(),
                entity.getTransmissionPercentage(),
                conformity,
                2);
        dto.setCalculatedVerifiedValue(calculatedValue);
        
        return dto;
    }

    private UrbanProperty convertToEntity(UrbanPropertyDTO dto) {
        UrbanProperty entity = new UrbanProperty();
        updateEntityFromDTO(entity, dto);
        return entity;
    }

    private void updateEntityFromDTO(UrbanProperty entity, UrbanPropertyDTO dto) {
        entity.setPresentationYear(dto.getPresentationYear());
        entity.setTaxType(dto.getTaxType());
        entity.setPresentationCode(dto.getPresentationCode());
        entity.setAssetSequence(dto.getAssetSequence());
        entity.setProvinceCode(dto.getProvinceCode());
        entity.setMunicipalityCode(dto.getMunicipalityCode());
        entity.setStreetTypeCode(dto.getStreetTypeCode());
        entity.setStreetName(dto.getStreetName());
        entity.setStreetNumber(dto.getStreetNumber());
        entity.setPostalCode(dto.getPostalCode());
        entity.setCountryCode(dto.getCountryCode());
        entity.setCadastralReference(dto.getCadastralReference());
        entity.setPropertyType(dto.getPropertyType());
        entity.setConstructionYear(dto.getConstructionYear());
        entity.setSituationCode(dto.getSituationCode());
        entity.setRentalIndicator(dto.getRentalIndicator());
        entity.setRentalContractYear(dto.getRentalContractYear());
        entity.setOfficialProtectionIndicator(dto.getOfficialProtectionIndicator());
        entity.setDisqualificationIndicator(dto.getDisqualificationIndicator());
        entity.setMaxSalePrice(dto.getMaxSalePrice());
        entity.setHabitualResidenceIndicator(dto.getHabitualResidenceIndicator());
        entity.setHabitualResidenceValue(dto.getHabitualResidenceValue());
        entity.setNumberOfUnits(dto.getNumberOfUnits());
        entity.setSurfaceArea(dto.getSurfaceArea());
        entity.setDeclaredValue(dto.getDeclaredValue());
        entity.setVerifiedValue(dto.getVerifiedValue());
        entity.setTransmissionPercentage(dto.getTransmissionPercentage());
        entity.setPositionType(dto.getPositionType());
        entity.setReferenceValueIndicator(dto.getReferenceValueIndicator());
        entity.setValidReferenceValueIndicator(dto.getValidReferenceValueIndicator());
        entity.setReferenceValue(dto.getReferenceValue());
        entity.setDuplicateIndicator(dto.getDuplicateIndicator());
        entity.setObservations(dto.getObservations());
    }
}