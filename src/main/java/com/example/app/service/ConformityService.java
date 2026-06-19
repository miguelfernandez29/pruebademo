package com.example.app.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ConformityService {

    private static final BigDecimal CONFORMITY_THRESHOLD = new BigDecimal("0.10");

    public String calculateConformityForUrbanProperty(String referenceValueIndicator,
                                                       String validReferenceValueIndicator,
                                                       BigDecimal referenceValue,
                                                       BigDecimal declaredValue,
                                                       BigDecimal verifiedValue) {
        if ("S".equals(referenceValueIndicator) && "S".equals(validReferenceValueIndicator) 
            && referenceValue != null && referenceValue.compareTo(BigDecimal.ZERO) > 0) {
            
            if (declaredValue != null && declaredValue.compareTo(BigDecimal.ZERO) > 0) {
                BigDecimal difference = declaredValue.subtract(referenceValue).abs();
                BigDecimal percentageDiff = difference.divide(referenceValue, 4, RoundingMode.HALF_UP);
                
                if (percentageDiff.compareTo(CONFORMITY_THRESHOLD) <= 0) {
                    return "S";
                }
            }
            return "N";
        }
        
        if (verifiedValue != null && declaredValue != null 
            && verifiedValue.compareTo(BigDecimal.ZERO) > 0 
            && declaredValue.compareTo(BigDecimal.ZERO) > 0) {
            
            if (declaredValue.compareTo(verifiedValue) >= 0) {
                return "S";
            }
            return "N";
        }
        
        return "P";
    }

    public String calculateConformityForRuralProperty(String referenceValueIndicator,
                                                       String validReferenceValueIndicator,
                                                       BigDecimal referenceValue,
                                                       BigDecimal declaredValue,
                                                       BigDecimal verifiedValue) {
        if ("S".equals(referenceValueIndicator) && "S".equals(validReferenceValueIndicator) 
            && referenceValue != null && referenceValue.compareTo(BigDecimal.ZERO) > 0) {
            
            if (declaredValue != null && declaredValue.compareTo(BigDecimal.ZERO) > 0) {
                BigDecimal difference = declaredValue.subtract(referenceValue).abs();
                BigDecimal percentageDiff = difference.divide(referenceValue, 4, RoundingMode.HALF_UP);
                
                if (percentageDiff.compareTo(CONFORMITY_THRESHOLD) <= 0) {
                    return "S";
                }
            }
            return "N";
        }
        
        if (verifiedValue != null && declaredValue != null 
            && verifiedValue.compareTo(BigDecimal.ZERO) > 0 
            && declaredValue.compareTo(BigDecimal.ZERO) > 0) {
            
            if (declaredValue.compareTo(verifiedValue) >= 0) {
                return "S";
            }
            return "N";
        }
        
        return "P";
    }

    public BigDecimal calculateVerifiedValueWithPercentage(BigDecimal verifiedValue, 
                                                            BigDecimal transmissionPercentage,
                                                            int decimalPlaces) {
        if (verifiedValue == null || transmissionPercentage == null) {
            return null;
        }
        return verifiedValue.multiply(transmissionPercentage)
                           .divide(new BigDecimal("100"), decimalPlaces, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateConformingValue(BigDecimal declaredValue,
                                                BigDecimal verifiedValue,
                                                BigDecimal transmissionPercentage,
                                                String conformityStatus,
                                                int decimalPlaces) {
        if ("S".equals(conformityStatus)) {
            return declaredValue != null ? declaredValue.setScale(decimalPlaces, RoundingMode.HALF_UP) : null;
        }
        return calculateVerifiedValueWithPercentage(verifiedValue, transmissionPercentage, decimalPlaces);
    }
}