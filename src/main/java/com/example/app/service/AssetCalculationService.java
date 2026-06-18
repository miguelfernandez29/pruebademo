package com.example.app.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class AssetCalculationService {

    private static final int DEFAULT_DECIMAL_PLACES = 2;
    private static final BigDecimal HUNDRED = new BigDecimal("100");

    public BigDecimal calculateProportionalVerifiedAmount(BigDecimal verifiedAmount, 
                                                          BigDecimal transmissionPercentage,
                                                          int decimalPlaces) {
        if (verifiedAmount == null || transmissionPercentage == null) {
            return null;
        }
        return verifiedAmount.multiply(transmissionPercentage)
                .divide(HUNDRED, decimalPlaces, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateProportionalVerifiedAmount(BigDecimal verifiedAmount, 
                                                          BigDecimal transmissionPercentage) {
        return calculateProportionalVerifiedAmount(verifiedAmount, transmissionPercentage, DEFAULT_DECIMAL_PLACES);
    }

    public BigDecimal calculateVerifiedFromProportional(BigDecimal proportionalAmount,
                                                         BigDecimal transmissionPercentage,
                                                         int decimalPlaces) {
        if (proportionalAmount == null || transmissionPercentage == null) {
            return null;
        }
        if (transmissionPercentage.compareTo(BigDecimal.ZERO) == 0) {
            return null;
        }
        return proportionalAmount.multiply(HUNDRED)
                .divide(transmissionPercentage, decimalPlaces, RoundingMode.HALF_UP);
    }

    public BigDecimal applyConformity(BigDecimal declaredAmount, 
                                       BigDecimal transmissionPercentage,
                                       int decimalPlaces) {
        if (declaredAmount == null || transmissionPercentage == null) {
            return null;
        }
        if (transmissionPercentage.compareTo(BigDecimal.ZERO) == 0) {
            return null;
        }
        return declaredAmount.multiply(HUNDRED)
                .divide(transmissionPercentage, decimalPlaces, RoundingMode.HALF_UP);
    }

    public boolean shouldApplyConformity(String hasReferenceValue, 
                                          String referenceValueValid,
                                          BigDecimal referenceValue,
                                          BigDecimal declaredAmount) {
        if ("S".equals(hasReferenceValue) && "S".equals(referenceValueValid) && referenceValue != null) {
            if (declaredAmount != null && declaredAmount.compareTo(referenceValue) >= 0) {
                return true;
            }
        }
        return false;
    }

    public BigDecimal calculateListedSecuritiesValue(BigDecimal quotationPrice, Integer numberOfUnits) {
        if (quotationPrice == null || numberOfUnits == null) {
            return null;
        }
        return quotationPrice.multiply(new BigDecimal(numberOfUnits))
                .setScale(DEFAULT_DECIMAL_PLACES, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateUnlistedSecuritiesValue(BigDecimal theoreticalValue, Integer numberOfUnits) {
        if (theoreticalValue == null || numberOfUnits == null) {
            return null;
        }
        return theoreticalValue.multiply(new BigDecimal(numberOfUnits))
                .setScale(DEFAULT_DECIMAL_PLACES, RoundingMode.HALF_UP);
    }

    public BigDecimal truncateToDecimals(BigDecimal value, int decimalPlaces) {
        if (value == null) {
            return null;
        }
        return value.setScale(decimalPlaces, RoundingMode.DOWN);
    }

    public String padAssetSequence(String sequence) {
        if (sequence == null) {
            return "001";
        }
        try {
            int num = Integer.parseInt(sequence.trim());
            return String.format("%03d", num);
        } catch (NumberFormatException e) {
            return sequence;
        }
    }

    public String padProvinceCode(String code) {
        if (code == null) {
            return null;
        }
        try {
            int num = Integer.parseInt(code.trim());
            return String.format("%02d", num);
        } catch (NumberFormatException e) {
            return code;
        }
    }

    public String padMunicipalityCode(String code) {
        if (code == null) {
            return null;
        }
        try {
            int num = Integer.parseInt(code.trim());
            return String.format("%03d", num);
        } catch (NumberFormatException e) {
            return code;
        }
    }
}