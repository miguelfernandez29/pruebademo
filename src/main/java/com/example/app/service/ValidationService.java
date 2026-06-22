package com.example.app.service;

import com.example.app.exception.BusinessValidationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.regex.Pattern;

@Service
public class ValidationService {

    private static final BigDecimal MAX_VALUE = new BigDecimal("999999999999.99");
    private static final BigDecimal HUNDRED = new BigDecimal("100");

    public void validateProvinceCode(String cdprovinci) {
        if (cdprovinci == null || cdprovinci.trim().isEmpty()) {
            throw new BusinessValidationException("El campo 'Provincia' es obligatorio.");
        }
        if (cdprovinci.length() > 2) {
            throw new BusinessValidationException("El código de provincia no puede tener más de 2 caracteres.");
        }
    }

    public void validateMunicipalityCode(String cdmunicipi, String cdprovinci) {
        if (cdmunicipi != null && !cdmunicipi.trim().isEmpty()) {
            if (cdprovinci == null || cdprovinci.trim().isEmpty()) {
                throw new BusinessValidationException("Para obtener el municipio es necesario su provincia.");
            }
        }
    }

    public void validateTransmissionPercentage(BigDecimal pctransmis) {
        if (pctransmis == null) {
            return;
        }
        if (pctransmis.compareTo(HUNDRED) > 0) {
            throw new BusinessValidationException("El campo '% titularidad' no puede ser superior al 100%.");
        }
        if (pctransmis.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessValidationException("El campo '% titularidad' no puede ser menor o igual al 0%.");
        }
    }

    public void validateDeclaredValue(BigDecimal ptdeclarad) {
        if (ptdeclarad != null && ptdeclarad.compareTo(MAX_VALUE) > 0) {
            throw new BusinessValidationException("El valor máximo permitido es: 999.999.999.999,99");
        }
    }

    public void validateVerifiedValue(BigDecimal ptcomproba) {
        if (ptcomproba != null) {
            if (ptcomproba.compareTo(MAX_VALUE) > 0) {
                throw new BusinessValidationException("El valor máximo permitido es: 999.999.999.999,99");
            }
            if (ptcomproba.compareTo(BigDecimal.ZERO) == 0) {
                throw new BusinessValidationException("El valor comprobado no puede ser 0.");
            }
        }
    }

    public void validateConstructionYear(Integer aaconstruc, LocalDate fcdevengo) {
        if (aaconstruc == null) {
            return;
        }
        int currentYear = LocalDate.now().getYear();
        if (aaconstruc < 1500 || aaconstruc > currentYear) {
            throw new BusinessValidationException("El año de construcción debe de estar entre 1500 y el año actual.");
        }
        if (fcdevengo != null && aaconstruc > fcdevengo.getYear()) {
            throw new BusinessValidationException("Año de construcción posterior al año de devengo.");
        }
    }

    public void validateRentalContractYear(Integer aacontarre, LocalDate fcdevengo) {
        if (aacontarre == null) {
            return;
        }
        int currentYear = LocalDate.now().getYear();
        if (aacontarre < 1500 || aacontarre > currentYear) {
            throw new BusinessValidationException("El año de contrato debe de estar entre 1500 y el año actual.");
        }
        if (fcdevengo != null && aacontarre > fcdevengo.getYear()) {
            throw new BusinessValidationException("Año de contrato posterior al año del devengo.");
        }
    }

    public void validateYesNoIndicator(String indicator, String fieldName) {
        if (indicator != null && !indicator.isEmpty()) {
            if (!"S".equals(indicator) && !"N".equals(indicator)) {
                throw new BusinessValidationException("Valores permitidos para " + fieldName + ": S o N.");
            }
        }
    }

    public void validateDuplicateIndicator(String vfduplicad) {
        if (vfduplicad != null && !vfduplicad.isEmpty()) {
            if (!"D".equals(vfduplicad) && !"T".equals(vfduplicad)) {
                throw new BusinessValidationException("Valores permitidos: D o T.");
            }
        }
    }

    public void validateCadastralReference(String tlrefecata, String cdnatbien2) {
        if (tlrefecata == null || tlrefecata.trim().isEmpty()) {
            return;
        }
        String cleanRef = tlrefecata.replaceAll("\\s+", "");
        if ("U".equals(cdnatbien2)) {
            if (cleanRef.length() != 20) {
                throw new BusinessValidationException("La referencia catastral urbana debe tener 20 caracteres.");
            }
        } else if ("R".equals(cdnatbien2)) {
            if (cleanRef.length() != 14 && cleanRef.length() != 20) {
                throw new BusinessValidationException("La referencia catastral rústica debe tener 14 o 20 caracteres.");
            }
        }
    }

    public void validateVehicleRegistrationDate(LocalDate fcmatricul, LocalDate fcdevengo) {
        if (fcmatricul == null) {
            return;
        }
        if (fcmatricul.isAfter(LocalDate.now())) {
            throw new BusinessValidationException("Fecha no puede ser mayor que la actual.");
        }
        if (fcdevengo != null && fcmatricul.isAfter(fcdevengo)) {
            throw new BusinessValidationException("Fecha no puede ser mayor que la fecha de defunción (devengo).");
        }
    }

    public boolean validateIBAN(String iban) {
        if (iban == null || iban.trim().isEmpty()) {
            return true;
        }
        String cleanIban = iban.replaceAll("\\s+", "").toUpperCase();
        if (cleanIban.length() < 15 || cleanIban.length() > 34) {
            return false;
        }
        String rearranged = cleanIban.substring(4) + cleanIban.substring(0, 4);
        StringBuilder numericIban = new StringBuilder();
        for (char c : rearranged.toCharArray()) {
            if (Character.isLetter(c)) {
                numericIban.append(Character.getNumericValue(c));
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

    public void validateLegacyPercentage(BigDecimal pclegadosp) {
        if (pclegadosp == null) {
            return;
        }
        if (pclegadosp.compareTo(BigDecimal.ZERO) < 0 || pclegadosp.compareTo(HUNDRED) > 0) {
            throw new BusinessValidationException("El porcentaje de legado debe estar entre 0 y 100.");
        }
    }

    public void validateAcquisitionType(String cdtpadqui2) {
        if (cdtpadqui2 != null && !cdtpadqui2.isEmpty()) {
            if (!"P".equals(cdtpadqui2) && !"N".equals(cdtpadqui2)) {
                throw new BusinessValidationException("Valores permitidos: P o N.");
            }
        }
    }

    public void validatePositionType(String cdposbien2) {
        if (cdposbien2 != null && !cdposbien2.isEmpty()) {
            if (!"P".equals(cdposbien2) && !"G".equals(cdposbien2)) {
                throw new BusinessValidationException("Valores permitidos: P o G.");
            }
        }
    }

    public void validateNatureType(String cdnatbien2) {
        if (cdnatbien2 == null || cdnatbien2.trim().isEmpty()) {
            throw new BusinessValidationException("Campo 'Naturaleza' obligatorio.");
        }
    }

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
}