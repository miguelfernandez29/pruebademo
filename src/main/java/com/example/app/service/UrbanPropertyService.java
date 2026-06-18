package com.example.app.service;

import com.example.app.dto.UrbanPropertyDTO;
import com.example.app.entity.UrbanProperty;
import com.example.app.entity.AssetDocumentId;
import com.example.app.exception.ResourceNotFoundException;
import com.example.app.exception.ValidationException;
import com.example.app.repository.UrbanPropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UrbanPropertyService {

    private final UrbanPropertyRepository urbanPropertyRepository;
    private final AssetValidationService validationService;
    private final AssetCalculationService calculationService;

    @Autowired
    public UrbanPropertyService(UrbanPropertyRepository urbanPropertyRepository,
                                 AssetValidationService validationService,
                                 AssetCalculationService calculationService) {
        this.urbanPropertyRepository = urbanPropertyRepository;
        this.validationService = validationService;
        this.calculationService = calculationService;
    }

    public List<UrbanPropertyDTO> findByDocument(String presentationYear, String taxType, String presentationCode) {
        List<UrbanProperty> properties = urbanPropertyRepository
                .findByPresentationYearAndTaxTypeAndPresentationCode(presentationYear, taxType, presentationCode);
        return properties.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UrbanPropertyDTO findById(String presentationYear, String taxType, 
                                      String presentationCode, String assetSequence) {
        AssetDocumentId id = new AssetDocumentId(presentationYear, taxType, presentationCode, assetSequence);
        UrbanProperty property = urbanPropertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Urban property not found"));
        return convertToDTO(property);
    }

    public UrbanPropertyDTO create(UrbanPropertyDTO dto) {
        validateUrbanProperty(dto, null);
        
        dto.setProvinceCode(calculationService.padProvinceCode(dto.getProvinceCode()));
        dto.setMunicipalityCode(calculationService.padMunicipalityCode(dto.getMunicipalityCode()));
        
        if (dto.getCountry() == null || dto.getCountry().isEmpty()) {
            dto.setCountry("ESP");
        }
        
        UrbanProperty property = convertToEntity(dto);
        property = urbanPropertyRepository.save(property);
        return convertToDTO(property);
    }

    public UrbanPropertyDTO update(UrbanPropertyDTO dto) {
        AssetDocumentId id = new AssetDocumentId(
                dto.getPresentationYear(), dto.getTaxType(),
                dto.getPresentationCode(), dto.getAssetSequence());
        
        UrbanProperty existing = urbanPropertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Urban property not found"));
        
        validateUrbanProperty(dto, existing);
        
        updateEntity(existing, dto);
        existing = urbanPropertyRepository.save(existing);
        return convertToDTO(existing);
    }

    public void delete(String presentationYear, String taxType, 
                       String presentationCode, String assetSequence) {
        AssetDocumentId id = new AssetDocumentId(presentationYear, taxType, presentationCode, assetSequence);
        
        if (!urbanPropertyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Urban property not found");
        }
        
        urbanPropertyRepository.deleteById(id);
    }

    private void validateUrbanProperty(UrbanPropertyDTO dto, UrbanProperty existing) {
        validationService.validateProvinceCode(dto.getProvinceCode());
        validationService.validateMunicipalityCode(dto.getMunicipalityCode(), dto.getProvinceCode());
        validationService.validatePostalCode(dto.getPostalCode(), dto.getProvinceCode());
        validationService.validateCadastralReference(dto.getCadastralReference(), "U");
        validationService.validateConstructionYear(dto.getConstructionYear(), null);
        validationService.validateYesNoField(dto.getIsHabitualResidence(), "Habitual residence");
        validationService.validateHabitualResidence(dto.getIsHabitualResidence(), dto.getHabitualResidenceValue());
        validationService.validateYesNoField(dto.getIsRented(), "Is rented");
        validationService.validateRentalContractYear(dto.getRentalContractYear(), dto.getIsRented(), null);
        validationService.validateYesNoField(dto.getIsOfficialProtection(), "Official protection");
        validationService.validateOfficialProtection(dto.getIsOfficialProtection(), dto.getMaxSalePrice());
        validationService.validateYesNoField(dto.getIsDisqualified(), "Disqualified");
        validationService.validateAmount(dto.getDeclaredAmount(), "Declared amount");
        validationService.validateAmount(dto.getVerifiedAmount(), "Verified amount");
        validationService.validateAmount(dto.getHabitualResidenceValue(), "Habitual residence value");
        validationService.validateAmount(dto.getMaxSalePrice(), "Maximum sale price");
        validationService.validateSurfaceArea(dto.getSurfaceArea());
        validationService.validateNumberOfUnits(dto.getNumberOfUnits(), dto.getPropertyType());
    }

    private void updateEntity(UrbanProperty existing, UrbanPropertyDTO dto) {
        existing.setProvinceCode(calculationService.padProvinceCode(dto.getProvinceCode()));
        existing.setMunicipalityCode(calculationService.padMunicipalityCode(dto.getMunicipalityCode()));
        existing.setStreetType(dto.getStreetType());
        existing.setStreetName(dto.getStreetName());
        existing.setStreetNumber(dto.getStreetNumber());
        existing.setPostalCode(dto.getPostalCode());
        existing.setStaircase(dto.getStaircase());
        existing.setFloor(dto.getFloor());
        existing.setDoor(dto.getDoor());
        existing.setCountry(dto.getCountry() != null ? dto.getCountry() : "ESP");
        existing.setCadastralReference(dto.getCadastralReference());
        existing.setPropertyType(dto.getPropertyType());
        existing.setIsHabitualResidence(dto.getIsHabitualResidence());
        existing.setHabitualResidenceValue(dto.getHabitualResidenceValue());
        existing.setNumberOfUnits(dto.getNumberOfUnits());
        existing.setSurfaceArea(dto.getSurfaceArea());
        existing.setConstructionYear(dto.getConstructionYear());
        existing.setSituationCode(dto.getSituationCode());
        existing.setIsRented(dto.getIsRented());
        existing.setRentalContractYear(dto.getRentalContractYear());
        existing.setIsOfficialProtection(dto.getIsOfficialProtection());
        existing.setIsDisqualified(dto.getIsDisqualified());
        existing.setMaxSalePrice(dto.getMaxSalePrice());
        existing.setDeclaredAmount(dto.getDeclaredAmount());
        existing.setVerifiedAmount(dto.getVerifiedAmount());
        existing.setObservations(dto.getObservations());
        existing.setTransmissionPercentage(dto.getTransmissionPercentage());
    }

    private UrbanPropertyDTO convertToDTO(UrbanProperty entity) {
        UrbanPropertyDTO dto = new UrbanPropertyDTO();
        dto.setPresentationYear(entity.getPresentationYear());
        dto.setTaxType(entity.getTaxType());
        dto.setPresentationCode(entity.getPresentationCode());
        dto.setAssetSequence(entity.getAssetSequence());
        dto.setProvinceCode(entity.getProvinceCode());
        dto.setMunicipalityCode(entity.getMunicipalityCode());
        dto.setStreetType(entity.getStreetType());
        dto.setStreetName(entity.getStreetName());
        dto.setStreetNumber(entity.getStreetNumber());
        dto.setPostalCode(entity.getPostalCode());
        dto.setStaircase(entity.getStaircase());
        dto.setFloor(entity.getFloor());
        dto.setDoor(entity.getDoor());
        dto.setCountry(entity.getCountry());
        dto.setCadastralReference(entity.getCadastralReference());
        dto.setPropertyType(entity.getPropertyType());
        dto.setIsHabitualResidence(entity.getIsHabitualResidence());
        dto.setHabitualResidenceValue(entity.getHabitualResidenceValue());
        dto.setNumberOfUnits(entity.getNumberOfUnits());
        dto.setSurfaceArea(entity.getSurfaceArea());
        dto.setConstructionYear(entity.getConstructionYear());
        dto.setSituationCode(entity.getSituationCode());
        dto.setIsRented(entity.getIsRented());
        dto.setRentalContractYear(entity.getRentalContractYear());
        dto.setIsOfficialProtection(entity.getIsOfficialProtection());
        dto.setIsDisqualified(entity.getIsDisqualified());
        dto.setMaxSalePrice(entity.getMaxSalePrice());
        dto.setDeclaredAmount(entity.getDeclaredAmount());
        dto.setVerifiedAmount(entity.getVerifiedAmount());
        dto.setObservations(entity.getObservations());
        dto.setTransmissionPercentage(entity.getTransmissionPercentage());
        
        if (entity.getVerifiedAmount() != null && entity.getTransmissionPercentage() != null) {
            dto.setProportionalVerifiedAmount(
                    calculationService.calculateProportionalVerifiedAmount(
                            entity.getVerifiedAmount(), entity.getTransmissionPercentage()));
        }
        
        return dto;
    }

    private UrbanProperty convertToEntity(UrbanPropertyDTO dto) {
        UrbanProperty entity = new UrbanProperty();
        entity.setPresentationYear(dto.getPresentationYear());
        entity.setTaxType(dto.getTaxType());
        entity.setPresentationCode(dto.getPresentationCode());
        entity.setAssetSequence(dto.getAssetSequence());
        entity.setProvinceCode(dto.getProvinceCode());
        entity.setMunicipalityCode(dto.getMunicipalityCode());
        entity.setStreetType(dto.getStreetType());
        entity.setStreetName(dto.getStreetName());
        entity.setStreetNumber(dto.getStreetNumber());
        entity.setPostalCode(dto.getPostalCode());
        entity.setStaircase(dto.getStaircase());
        entity.setFloor(dto.getFloor());
        entity.setDoor(dto.getDoor());
        entity.setCountry(dto.getCountry());
        entity.setCadastralReference(dto.getCadastralReference());
        entity.setPropertyType(dto.getPropertyType());
        entity.setIsHabitualResidence(dto.getIsHabitualResidence());
        entity.setHabitualResidenceValue(dto.getHabitualResidenceValue());
        entity.setNumberOfUnits(dto.getNumberOfUnits());
        entity.setSurfaceArea(dto.getSurfaceArea());
        entity.setConstructionYear(dto.getConstructionYear());
        entity.setSituationCode(dto.getSituationCode());
        entity.setIsRented(dto.getIsRented());
        entity.setRentalContractYear(dto.getRentalContractYear());
        entity.setIsOfficialProtection(dto.getIsOfficialProtection());
        entity.setIsDisqualified(dto.getIsDisqualified());
        entity.setMaxSalePrice(dto.getMaxSalePrice());
        entity.setDeclaredAmount(dto.getDeclaredAmount());
        entity.setVerifiedAmount(dto.getVerifiedAmount());
        entity.setObservations(dto.getObservations());
        entity.setTransmissionPercentage(dto.getTransmissionPercentage() != null ? 
                dto.getTransmissionPercentage() : new BigDecimal("100"));
        return entity;
    }
}