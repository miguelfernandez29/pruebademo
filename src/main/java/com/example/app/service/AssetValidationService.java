package com.example.app.service;

import com.example.app.dto.AssetDocumentDTO;
import com.example.app.dto.UrbanPropertyDTO;
import com.example.app.dto.LegacyAssetDTO;
import com.example.app.exception.ValidationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;

@Service
public class AssetValidationService {

    private static final BigDecimal MAX_AMOUNT = new BigDecimal("999999999999.99");
    private static final BigDecimal HUNDRED = new BigDecimal("100");
    private static final BigDecimal ZERO = BigDecimal.ZERO;

    public void validateTransmissionPercentage(BigDecimal percentage) {
        if (percentage == null) {
            return;
        }
        if (percentage.compareTo(ZERO) <= 0) {
            throw new ValidationException("Transmission percentage must be greater than 0%");
        }
        if (percentage.compareTo(HUNDRED) > 0) {
            throw new ValidationException("Transmission percentage cannot exceed 100%");
        }
    }

    public void validateAmount(BigDecimal amount, String fieldName) {
        if (amount == null) {
            return;
        }
        if (amount.compareTo(MAX_AMOUNT) > 0) {
            throw new ValidationException("Maximum allowed value for " + fieldName + " is 999,999,999,999.99");
        }
        if (amount.compareTo(ZERO) < 0) {
            throw new ValidationException(fieldName + " cannot be negative");
        }
    }

    public void validateVerifiedAmountNotZero(BigDecimal verifiedAmount) {
        if (verifiedAmount != null && verifiedAmount.compareTo(ZERO) == 0) {
            throw new ValidationException("Verified amount cannot be 0");
        }
    }

    public void validateAssetNature(String nature, String context) {
        if (nature == null || nature.isEmpty()) {
            throw new ValidationException("Asset nature is required");
        }
        String validNatures = "URTACVNEO";
        if (!validNatures.contains(nature)) {
            throw new ValidationException("Invalid asset nature: " + nature);
        }
        if ("Bienes afectos".equals(context)) {
            if (!"U".equals(nature) && !"R".equals(nature)) {
                throw new ValidationException("For affected assets, only Urban (U) or Rustic (R) nature is allowed");
            }
        }
    }

    public void validateAssetPosition(String position) {
        if (position == null || position.isEmpty()) {
            throw new ValidationException("Asset position (P/G) is required");
        }
        if (!"P".equals(position) && !"G".equals(position)) {
            throw new ValidationException("Asset position must be P or G");
        }
    }

    public void validateProvinceCode(String provinceCode) {
        if (provinceCode == null || provinceCode.isEmpty()) {
            throw new ValidationException("Province code is required");
        }
        if (provinceCode.length() > 2) {
            throw new ValidationException("Province code must be 2 characters");
        }
    }

    public void validateMunicipalityCode(String municipalityCode, String provinceCode) {
        if (municipalityCode != null && !municipalityCode.isEmpty()) {
            if (provinceCode == null || provinceCode.isEmpty()) {
                throw new ValidationException("Province is required to validate municipality");
            }
            if (municipalityCode.length() > 3) {
                throw new ValidationException("Municipality code must be 3 characters");
            }
        }
    }

    public void validatePostalCode(String postalCode, String provinceCode) {
        if (postalCode != null && !postalCode.isEmpty()) {
            if (postalCode.length() != 5) {
                throw new ValidationException("Postal code must be 5 digits");
            }
            if (provinceCode != null && !provinceCode.isEmpty()) {
                String postalProvince = postalCode.substring(0, 2);
                if (!postalProvince.equals(provinceCode)) {
                    throw new ValidationException("Postal code must match the province");
                }
            }
        }
    }

    public void validateConstructionYear(Integer constructionYear, LocalDate eventDate) {
        if (constructionYear == null) {
            return;
        }
        int currentYear = Year.now().getValue();
        if (constructionYear < 1500 || constructionYear > currentYear) {
            throw new ValidationException("Construction year must be between 1500 and current year");
        }
        if (eventDate != null && constructionYear > eventDate.getYear()) {
            throw new ValidationException("Construction year cannot be after the event date year");
        }
    }

    public void validateRentalContractYear(Integer contractYear, String isRented, LocalDate eventDate) {
        if ("S".equals(isRented)) {
            if (contractYear == null) {
                throw new ValidationException("Rental contract year is required when property is rented");
            }
            int currentYear = Year.now().getValue();
            if (contractYear < 1500 || contractYear > currentYear) {
                throw new ValidationException("Rental contract year must be between 1500 and current year");
            }
            if (eventDate != null && contractYear > eventDate.getYear()) {
                throw new ValidationException("Rental contract year cannot be after the event date year");
            }
        }
    }

    public void validateHabitualResidence(String isHabitualResidence, BigDecimal habitualResidenceValue) {
        if ("S".equals(isHabitualResidence)) {
            if (habitualResidenceValue == null) {
                throw new ValidationException("Habitual residence value is required when marked as habitual residence");
            }
        }
    }

    public void validateOfficialProtection(String isOfficialProtection, BigDecimal maxSalePrice) {
        if ("S".equals(isOfficialProtection)) {
            if (maxSalePrice == null) {
                throw new ValidationException("Maximum sale price is required for official protection properties");
            }
        }
    }

    public void validateYesNoField(String value, String fieldName) {
        if (value != null && !value.isEmpty()) {
            if (!"S".equals(value) && !"N".equals(value)) {
                throw new ValidationException(fieldName + " must be S or N");
            }
        }
    }

    public void validateCadastralReference(String reference, String assetNature) {
        if (reference == null || reference.isEmpty()) {
            return;
        }
        String cleanRef = reference.replaceAll("\\s+", "");
        if ("U".equals(assetNature)) {
            if (cleanRef.length() != 20) {
                throw new ValidationException("Urban cadastral reference must be 20 characters");
            }
        } else if ("R".equals(assetNature)) {
            if (cleanRef.length() != 14 && cleanRef.length() != 20) {
                throw new ValidationException("Rustic cadastral reference must be 14 or 20 characters");
            }
        }
    }

    public void validateIBAN(String iban) {
        if (iban == null || iban.isEmpty()) {
            return;
        }
        String cleanIban = iban.replaceAll("\\s+", "").toUpperCase();
        if (cleanIban.length() < 15 || cleanIban.length() > 34) {
            throw new ValidationException("Invalid IBAN format");
        }
        if (!cleanIban.matches("^[A-Z]{2}[0-9]{2}[A-Z0-9]+$")) {
            throw new ValidationException("Invalid IBAN format");
        }
    }

    public void validateLegacyPercentage(BigDecimal percentage) {
        if (percentage == null) {
            return;
        }
        if (percentage.compareTo(ZERO) <= 0) {
            throw new ValidationException("Legacy percentage must be greater than 0");
        }
        if (percentage.compareTo(HUNDRED) > 0) {
            throw new ValidationException("Legacy percentage cannot exceed 100%");
        }
    }

    public void validateTotalLegacyPercentage(BigDecimal currentTotal, BigDecimal newPercentage) {
        BigDecimal total = currentTotal.add(newPercentage != null ? newPercentage : ZERO);
        if (total.compareTo(HUNDRED) > 0) {
            throw new ValidationException("Total legacy percentages cannot exceed 100%");
        }
    }

    public void validateAcquisitionType(String acquisitionType) {
        if (acquisitionType != null && !acquisitionType.isEmpty()) {
            if (!"P".equals(acquisitionType) && !"N".equals(acquisitionType)) {
                throw new ValidationException("Acquisition type must be P or N");
            }
        }
    }

    public void validateVehicleRegistrationDate(LocalDate registrationDate, LocalDate eventDate) {
        if (registrationDate == null) {
            return;
        }
        if (registrationDate.isAfter(LocalDate.now())) {
            throw new ValidationException("Registration date cannot be in the future");
        }
        if (eventDate != null && registrationDate.isAfter(eventDate)) {
            throw new ValidationException("Registration date cannot be after the event date");
        }
    }

    public void validateNumberOfUnits(Integer units, String propertyType) {
        if (units == null) {
            return;
        }
        if (units <= 0) {
            throw new ValidationException("Number of units must be positive");
        }
        if (!"PG".equals(propertyType) && units != 1) {
            throw new ValidationException("For this property type, number of units must be 1");
        }
    }

    public void validateSurfaceArea(BigDecimal surfaceArea) {
        if (surfaceArea == null) {
            return;
        }
        if (surfaceArea.compareTo(ZERO) <= 0) {
            throw new ValidationException("Surface area must be positive");
        }
        if (surfaceArea.compareTo(new BigDecimal("9999999999.9999")) > 0) {
            throw new ValidationException("Surface area exceeds maximum allowed value");
        }
    }
}