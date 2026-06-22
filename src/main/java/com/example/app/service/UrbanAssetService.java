package com.example.app.service;

import com.example.app.dto.UrbanAssetDTO;
import com.example.app.entity.UrbanAsset;
import com.example.app.entity.AssetDocumentId;
import com.example.app.entity.Province;
import com.example.app.entity.Municipality;
import com.example.app.entity.Country;
import com.example.app.repository.UrbanAssetRepository;
import com.example.app.repository.ProvinceRepository;
import com.example.app.repository.MunicipalityRepository;
import com.example.app.repository.CountryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UrbanAssetService {

    private static final BigDecimal MAX_VALUE = new BigDecimal("999999999999.99");
    private static final BigDecimal MAX_SURFACE = new BigDecimal("9999999999.99");

    private final UrbanAssetRepository urbanAssetRepository;
    private final ProvinceRepository provinceRepository;
    private final MunicipalityRepository municipalityRepository;
    private final CountryRepository countryRepository;
    private final AssetDocumentService assetDocumentService;

    public UrbanAssetService(UrbanAssetRepository urbanAssetRepository,
                              ProvinceRepository provinceRepository,
                              MunicipalityRepository municipalityRepository,
                              CountryRepository countryRepository,
                              AssetDocumentService assetDocumentService) {
        this.urbanAssetRepository = urbanAssetRepository;
        this.provinceRepository = provinceRepository;
        this.municipalityRepository = municipalityRepository;
        this.countryRepository = countryRepository;
        this.assetDocumentService = assetDocumentService;
    }

    public List<UrbanAssetDTO> findByDeclaration(String presentationYear, String taxType, String presentationCode) {
        List<UrbanAsset> assets = urbanAssetRepository.findByPresentationYearAndTaxTypeAndPresentationCode(
                presentationYear, taxType, presentationCode);
        return assets.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<UrbanAssetDTO> findById(String presentationYear, String taxType,
                                             String presentationCode, String assetSequence) {
        AssetDocumentId id = new AssetDocumentId(presentationYear, taxType, presentationCode, assetSequence);
        return urbanAssetRepository.findById(id).map(this::toDTO);
    }

    public UrbanAssetDTO create(UrbanAssetDTO dto) {
        validateUrbanAsset(dto);
        applyDefaults(dto);

        UrbanAsset entity = toEntity(dto);
        entity = urbanAssetRepository.save(entity);
        return toDTO(entity);
    }

    public UrbanAssetDTO update(UrbanAssetDTO dto) {
        AssetDocumentId id = new AssetDocumentId(dto.getPresentationYear(), dto.getTaxType(),
                dto.getPresentationCode(), dto.getAssetSequence());

        UrbanAsset existing = urbanAssetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Urban asset not found"));

        validateUrbanAsset(dto);
        updateEntity(existing, dto);

        existing = urbanAssetRepository.save(existing);
        return toDTO(existing);
    }

    public void delete(String presentationYear, String taxType, String presentationCode, String assetSequence) {
        AssetDocumentId id = new AssetDocumentId(presentationYear, taxType, presentationCode, assetSequence);
        if (!urbanAssetRepository.existsById(id)) {
            throw new IllegalArgumentException("Urban asset not found");
        }
        urbanAssetRepository.deleteById(id);
    }

    public UrbanAssetDTO applyConformity(UrbanAssetDTO dto) {
        dto.setVerifiedValue(dto.getDeclaredValue());
        if (dto.getTransmissionPercentage() != null && dto.getDeclaredValue() != null) {
            BigDecimal proportional = dto.getDeclaredValue().multiply(dto.getTransmissionPercentage())
                    .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
            dto.setProportionalVerifiedValue(proportional);
        }
        return dto;
    }

    public UrbanAssetDTO applyReferenceValue(UrbanAssetDTO dto) {
        if (dto.getReferenceValue() != null && "S".equals(dto.getHasReferenceValue())) {
            dto.setVerifiedValue(dto.getReferenceValue());
            if (dto.getTransmissionPercentage() != null) {
                BigDecimal proportional = dto.getReferenceValue().multiply(dto.getTransmissionPercentage())
                        .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
                dto.setProportionalVerifiedValue(proportional);
            }
        }
        return dto;
    }

    private void validateUrbanAsset(UrbanAssetDTO dto) {
        validateProvinceCode(dto.getProvinceCode());
        validateMunicipalityCode(dto.getProvinceCode(), dto.getMunicipalityCode());
        validateCountryCode(dto.getCountryCode());
        validateDuplicateFlag(dto.getDuplicateFlag());
        validateHabitualResidence(dto.getHabitualResidence());
        validateIsRented(dto.getIsRented());
        validateOfficialProtection(dto.getOfficialProtection());
        validateDisqualified(dto.getDisqualified());
        validateConstructionYear(dto.getConstructionYear());
        validateRentalContractYear(dto.getRentalContractYear());
        validateCadastralReference(dto.getCadastralReference(), dto.getCountryCode());
        validateMonetaryValue(dto.getDeclaredValue(), "Declared value");
        validateMonetaryValue(dto.getVerifiedValue(), "Verified value");
        validateMonetaryValue(dto.getHabitualResidenceValue(), "Habitual residence value");
        validateMonetaryValue(dto.getMaximumSalePrice(), "Maximum sale price");
        validateSurfaceArea(dto.getSurfaceArea());
        validateNumberOfUnits(dto.getNumberOfUnits(), dto.getPropertyType());
        validateOfficialProtectionMaxPrice(dto.getOfficialProtection(), dto.getVerifiedValue(), dto.getMaximumSalePrice());
    }

    private void validateProvinceCode(String provinceCode) {
        if (provinceCode == null || provinceCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Province code is required");
        }
        String paddedCode = String.format("%2s", provinceCode).replace(' ', '0');
        if (!provinceRepository.existsById(paddedCode)) {
            throw new IllegalArgumentException("Invalid province code: " + provinceCode);
        }
    }

    private void validateMunicipalityCode(String provinceCode, String municipalityCode) {
        if (municipalityCode == null || municipalityCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Municipality code is required");
        }
        String paddedProvince = String.format("%2s", provinceCode).replace(' ', '0');
        String paddedMunicipality = String.format("%3s", municipalityCode).replace(' ', '0');
        Optional<Municipality> municipality = municipalityRepository.findByProvinceCodeAndMunicipalityCode(paddedProvince, paddedMunicipality);
        if (!municipality.isPresent()) {
            throw new IllegalArgumentException("Invalid municipality code for province");
        }
    }

    private void validateCountryCode(String countryCode) {
        if (countryCode != null && !countryCode.trim().isEmpty()) {
            if (!countryRepository.existsById(countryCode)) {
                throw new IllegalArgumentException("Invalid country code: " + countryCode);
            }
        }
    }

    private void validateDuplicateFlag(String duplicateFlag) {
        if (duplicateFlag != null && !duplicateFlag.isEmpty()) {
            if (!"D".equals(duplicateFlag) && !"T".equals(duplicateFlag)) {
                throw new IllegalArgumentException("Duplicate flag must be D or T");
            }
        }
    }

    private void validateHabitualResidence(String habitualResidence) {
        if (habitualResidence != null && !habitualResidence.isEmpty()) {
            if (!"S".equals(habitualResidence) && !"N".equals(habitualResidence)) {
                throw new IllegalArgumentException("Habitual residence must be S or N");
            }
        }
    }

    private void validateIsRented(String isRented) {
        if (isRented != null && !isRented.isEmpty()) {
            if (!"S".equals(isRented) && !"N".equals(isRented)) {
                throw new IllegalArgumentException("Is rented must be S or N");
            }
        }
    }

    private void validateOfficialProtection(String officialProtection) {
        if (officialProtection != null && !officialProtection.isEmpty()) {
            if (!"S".equals(officialProtection) && !"N".equals(officialProtection)) {
                throw new IllegalArgumentException("Official protection must be S or N");
            }
        }
    }

    private void validateDisqualified(String disqualified) {
        if (disqualified != null && !disqualified.isEmpty()) {
            if (!"S".equals(disqualified) && !"N".equals(disqualified)) {
                throw new IllegalArgumentException("Disqualified must be S or N");
            }
        }
    }

    private void validateConstructionYear(Integer constructionYear) {
        if (constructionYear != null) {
            int currentYear = LocalDate.now().getYear();
            if (constructionYear < 1500 || constructionYear > currentYear) {
                throw new IllegalArgumentException("Construction year must be between 1500 and current year");
            }
        }
    }

    private void validateRentalContractYear(Integer rentalContractYear) {
        if (rentalContractYear != null) {
            int currentYear = LocalDate.now().getYear();
            if (rentalContractYear < 1500 || rentalContractYear > currentYear) {
                throw new IllegalArgumentException("Rental contract year must be between 1500 and current year");
            }
        }
    }

    private void validateCadastralReference(String cadastralReference, String countryCode) {
        if (cadastralReference != null && !cadastralReference.trim().isEmpty()) {
            String cleanRef = cadastralReference.replaceAll("\\s+", "");
            if ("ESP".equals(countryCode) && cleanRef.length() != 20) {
                throw new IllegalArgumentException("Cadastral reference must be 20 characters for Spanish properties");
            }
        }
    }

    private void validateMonetaryValue(BigDecimal value, String fieldName) {
        if (value != null) {
            if (value.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException(fieldName + " cannot be negative");
            }
            if (value.compareTo(MAX_VALUE) > 0) {
                throw new IllegalArgumentException(fieldName + " exceeds maximum allowed value: 999,999,999,999.99");
            }
        }
    }

    private void validateSurfaceArea(BigDecimal surfaceArea) {
        if (surfaceArea != null) {
            if (surfaceArea.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Surface area cannot be negative");
            }
            if (surfaceArea.compareTo(MAX_SURFACE) > 0) {
                throw new IllegalArgumentException("Surface area exceeds maximum allowed value: 9,999,999,999.99");
            }
        }
    }

    private void validateNumberOfUnits(Integer numberOfUnits, String propertyType) {
        if (numberOfUnits != null && !"PG".equals(propertyType)) {
            if (numberOfUnits != 1) {
                throw new IllegalArgumentException("Number of units must be 1 for this property type");
            }
        }
    }

    private void validateOfficialProtectionMaxPrice(String officialProtection, BigDecimal verifiedValue, BigDecimal maxSalePrice) {
        if ("S".equals(officialProtection) && verifiedValue != null && maxSalePrice != null) {
            if (verifiedValue.compareTo(maxSalePrice) > 0) {
                throw new IllegalArgumentException("Verified value cannot exceed maximum sale price for officially protected housing");
            }
        }
    }

    private void applyDefaults(UrbanAssetDTO dto) {
        if (dto.getCountryCode() == null || dto.getCountryCode().trim().isEmpty()) {
            dto.setCountryCode("ESP");
        }
        if (dto.getHabitualResidence() == null) {
            dto.setHabitualResidence("N");
        }
        if (dto.getTransmissionPercentage() == null) {
            dto.setTransmissionPercentage(new BigDecimal("100.00"));
        }
        if (dto.getProvinceCode() != null) {
            dto.setProvinceCode(String.format("%2s", dto.getProvinceCode()).replace(' ', '0'));
        }
        if (dto.getMunicipalityCode() != null) {
            dto.setMunicipalityCode(String.format("%3s", dto.getMunicipalityCode()).replace(' ', '0'));
        }
        if (dto.getPropertyType() != null) {
            dto.setPropertyType(String.format("%2s", dto.getPropertyType()).replace(' ', '0'));
        }
    }

    private UrbanAssetDTO toDTO(UrbanAsset entity) {
        UrbanAssetDTO dto = new UrbanAssetDTO();
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
        dto.setCountryCode(entity.getCountryCode());
        dto.setDuplicateFlag(entity.getDuplicateFlag());
        dto.setLocationNumber(entity.getLocationNumber());
        dto.setCadastralReference(entity.getCadastralReference());
        dto.setPropertyType(entity.getPropertyType());
        dto.setHabitualResidence(entity.getHabitualResidence());
        dto.setHabitualResidenceValue(entity.getHabitualResidenceValue());
        dto.setNumberOfUnits(entity.getNumberOfUnits());
        dto.setSurfaceArea(entity.getSurfaceArea());
        dto.setConstructionYear(entity.getConstructionYear());
        dto.setSituationCode(entity.getSituationCode2());
        dto.setIsRented(entity.getIsRented());
        dto.setRentalContractYear(entity.getRentalContractYear());
        dto.setOfficialProtection(entity.getOfficialProtection());
        dto.setDisqualified(entity.getDisqualified());
        dto.setMaximumSalePrice(entity.getMaximumSalePrice());
        dto.setTransmissionPercentage(entity.getTransmissionPercentage());
        dto.setDeclaredValue(entity.getDeclaredValue());
        dto.setVerifiedValue(entity.getVerifiedValue());
        dto.setHasReferenceValue(entity.getHasReferenceValue());
        dto.setReferenceValue(entity.getReferenceValue());
        dto.setObservations(entity.getObservations());
        dto.setAssetPosition(entity.getAssetPosition());
        dto.setAssetNature("U");
        dto.setAssetNatureDescription("Urbano");

        if (entity.getProvinceCode() != null) {
            provinceRepository.findById(entity.getProvinceCode())
                    .ifPresent(p -> dto.setProvinceName(p.getProvinceName()));
        }

        if (entity.getProvinceCode() != null && entity.getMunicipalityCode() != null) {
            municipalityRepository.findByProvinceCodeAndMunicipalityCode(entity.getProvinceCode(), entity.getMunicipalityCode())
                    .ifPresent(m -> dto.setMunicipalityName(m.getMunicipalityName()));
        }

        if (entity.getCountryCode() != null) {
            countryRepository.findById(entity.getCountryCode())
                    .ifPresent(c -> dto.setCountryName(c.getName()));
        }

        if (entity.getVerifiedValue() != null && entity.getTransmissionPercentage() != null) {
            dto.setProportionalVerifiedValue(assetDocumentService.calculateProportionalValue(
                    entity.getVerifiedValue(), entity.getTransmissionPercentage(), 2));
        }

        return dto;
    }

    private UrbanAsset toEntity(UrbanAssetDTO dto) {
        UrbanAsset entity = new UrbanAsset();
        updateEntity(entity, dto);
        return entity;
    }

    private void updateEntity(UrbanAsset entity, UrbanAssetDTO dto) {
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
        entity.setCountryCode(dto.getCountryCode());
        entity.setDuplicateFlag(dto.getDuplicateFlag());
        entity.setLocationNumber(dto.getLocationNumber());
        entity.setCadastralReference(dto.getCadastralReference() != null ?
                dto.getCadastralReference().replaceAll("\\s+", "") : null);
        entity.setPropertyType(dto.getPropertyType());
        entity.setHabitualResidence(dto.getHabitualResidence());
        entity.setHabitualResidenceValue(dto.getHabitualResidenceValue() != null ?
                dto.getHabitualResidenceValue().setScale(2, RoundingMode.HALF_UP) : null);
        entity.setNumberOfUnits(dto.getNumberOfUnits());
        entity.setSurfaceArea(dto.getSurfaceArea() != null ?
                dto.getSurfaceArea().setScale(2, RoundingMode.HALF_UP) : null);
        entity.setConstructionYear(dto.getConstructionYear());
        entity.setSituationCode1(dto.getSituationCode() != null ? "311" : null);
        entity.setSituationCode2(dto.getSituationCode());
        entity.setIsRented(dto.getIsRented());
        entity.setRentalContractYear(dto.getRentalContractYear());
        entity.setOfficialProtection(dto.getOfficialProtection());
        entity.setDisqualified(dto.getDisqualified());
        entity.setMaximumSalePrice(dto.getMaximumSalePrice() != null ?
                dto.getMaximumSalePrice().setScale(2, RoundingMode.HALF_UP) : null);
        entity.setTransmissionPercentage(dto.getTransmissionPercentage());
        entity.setDeclaredValue(dto.getDeclaredValue() != null ?
                dto.getDeclaredValue().setScale(2, RoundingMode.HALF_UP) : null);
        entity.setVerifiedValue(dto.getVerifiedValue() != null ?
                dto.getVerifiedValue().setScale(2, RoundingMode.HALF_UP) : null);
        entity.setHasReferenceValue(dto.getHasReferenceValue());
        entity.setReferenceValue(dto.getReferenceValue());
        entity.setObservations(dto.getObservations());
        entity.setAssetPosition(dto.getAssetPosition());
    }
}