package com.example.app.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

@Service
public class ValidationService {

    private static final BigDecimal MAX_VALUE = new BigDecimal("999999999999.99");
    private static final BigDecimal HUNDRED = new BigDecimal("100");
    private static final int MIN_CONSTRUCTION_YEAR = 1500;

    public String padLeft(String value, int length, char padChar) {
        if (value == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = value.length(); i < length; i++) {
            sb.append(padChar);
        }
        sb.append(value);
        return sb.toString();
    }

    public void validateProvinceCode(String provinceCode) {
        if (provinceCode == null || provinceCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Province code is required");
        }
        if (!provinceCode.matches("\\d{2}")) {
            throw new IllegalArgumentException("Province code must be 2 digits");
        }
    }

    public void validateMunicipalityCode(String municipalityCode) {
        if (municipalityCode != null && !municipalityCode.matches("\\d{3}")) {
            throw new IllegalArgumentException("Municipality code must be 3 digits");
        }
    }

    public void validatePostalCode(String postalCode, String provinceCode) {
        if (postalCode == null || postalCode.trim().isEmpty()) {
            return;
        }
        if (!postalCode.matches("\\d{5}")) {
            throw new IllegalArgumentException("Postal code must be 5 digits");
        }
        if (provinceCode != null && !postalCode.startsWith(provinceCode)) {
            throw new IllegalArgumentException("Postal code must start with province code");
        }
    }

    public void validateTransmissionPercentage(BigDecimal percentage) {
        if (percentage == null) {
            return;
        }
        if (percentage.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transmission percentage must be greater than 0");
        }
        if (percentage.compareTo(HUNDRED) > 0) {
            throw new IllegalArgumentException("Transmission percentage cannot exceed 100");
        }
    }

    public void validatePositionType(String positionType) {
        if (positionType != null && !positionType.matches("[PG]")) {
            throw new IllegalArgumentException("Position type must be P or G");
        }
    }

    public void validateIndicator(String indicator, String fieldName) {
        if (indicator != null && !indicator.matches("[SN]")) {
            throw new IllegalArgumentException(fieldName + " must be S or N");
        }
    }

    public void validateDuplicateIndicator(String indicator) {
        if (indicator != null && !indicator.matches("[DT]")) {
            throw new IllegalArgumentException("Duplicate indicator must be D or T");
        }
    }

    public void validateConstructionYear(Integer year, Date accrualDate) {
        if (year == null) {
            return;
        }
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        
        if (year < MIN_CONSTRUCTION_YEAR || year > currentYear) {
            throw new IllegalArgumentException("Construction year must be between 1500 and current year");
        }
        
        if (accrualDate != null) {
            cal.setTime(accrualDate);
            int accrualYear = cal.get(Calendar.YEAR);
            if (year > accrualYear) {
                throw new IllegalArgumentException("Construction year cannot be after accrual year");
            }
        }
    }

    public void validateMonetaryValue(BigDecimal value, String fieldName) {
        if (value == null) {
            return;
        }
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(fieldName + " must be positive");
        }
        if (value.compareTo(MAX_VALUE) > 0) {
            throw new IllegalArgumentException(fieldName + " exceeds maximum allowed value");
        }
    }

    public BigDecimal truncateToTwoDecimals(BigDecimal value) {
        if (value == null) {
            return null;
        }
        return value.setScale(2, BigDecimal.ROUND_DOWN);
    }

    public boolean validateCadastralReference(String reference) {
        if (reference == null || reference.trim().isEmpty()) {
            return true;
        }
        String cleaned = reference.replaceAll("\\s+", "");
        return cleaned.length() == 20 || cleaned.length() == 14;
    }

    public boolean validateIban(String iban) {
        if (iban == null || iban.trim().isEmpty()) {
            return true;
        }
        String cleaned = iban.replaceAll("\\s+", "").toUpperCase();
        if (cleaned.length() < 15 || cleaned.length() > 34) {
            return false;
        }
        if (!cleaned.matches("[A-Z]{2}[0-9]{2}[A-Z0-9]+")) {
            return false;
        }
        String rearranged = cleaned.substring(4) + cleaned.substring(0, 4);
        StringBuilder numericIban = new StringBuilder();
        for (char c : rearranged.toCharArray()) {
            if (Character.isLetter(c)) {
                numericIban.append(c - 'A' + 10);
            } else {
                numericIban.append(c);
            }
        }
        try {
            java.math.BigInteger ibanNumber = new java.math.BigInteger(numericIban.toString());
            return ibanNumber.mod(java.math.BigInteger.valueOf(97)).intValue() == 1;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void validateRegistrationDate(Date registrationDate, Date accrualDate) {
        if (registrationDate == null) {
            return;
        }
        Date now = new Date();
        if (registrationDate.after(now)) {
            throw new IllegalArgumentException("Registration date cannot be in the future");
        }
        if (accrualDate != null && registrationDate.after(accrualDate)) {
            throw new IllegalArgumentException("Registration date cannot be after accrual date");
        }
    }

    public void validateLegacyPercentage(BigDecimal percentage) {
        if (percentage == null) {
            return;
        }
        if (percentage.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Legacy percentage must be at least 0");
        }
        if (percentage.compareTo(HUNDRED) > 0) {
            throw new IllegalArgumentException("Legacy percentage cannot exceed 100");
        }
    }

    public void validateAcquisitionType(String type) {
        if (type != null && !type.matches("[PN]")) {
            throw new IllegalArgumentException("Acquisition type must be P or N");
        }
    }

    public String validateAndFormatNif(String nif) {
        if (nif == null || nif.trim().isEmpty()) {
            return null;
        }
        return padLeft(nif.trim(), 9, '0');
    }
}