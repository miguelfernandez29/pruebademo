package com.example.app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

public class LegacyAssetDTO {

    @NotBlank(message = "Presentation year is required")
    private String presentationYear;

    @NotBlank(message = "Tax type is required")
    private String taxType;

    @NotBlank(message = "Presentation code is required")
    private String presentationCode;

    @NotBlank(message = "Deceased NIF is required")
    private String deceasedNif;

    private String deceasedSubCode;

    @NotBlank(message = "Asset sequence is required")
    private String assetSequence;

    @NotBlank(message = "Beneficiary NIF is required")
    private String beneficiaryNif;

    private String beneficiarySubCode;

    private String beneficiaryName;

    @Pattern(regexp = "^[SN]$", message = "Is Legacy must be S or N")
    private String isLegacy;

    @DecimalMin(value = "0.00", message = "Legacy percentage must be at least 0")
    @DecimalMax(value = "100.00", message = "Legacy percentage cannot exceed 100")
    private BigDecimal legacyPercentage;

    @Pattern(regexp = "^[PN]$", message = "Acquisition type must be P or N")
    private String acquisitionType;

    public LegacyAssetDTO() {
    }

    public String getPresentationYear() {
        return presentationYear;
    }

    public void setPresentationYear(String presentationYear) {
        this.presentationYear = presentationYear;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public String getPresentationCode() {
        return presentationCode;
    }

    public void setPresentationCode(String presentationCode) {
        this.presentationCode = presentationCode;
    }

    public String getDeceasedNif() {
        return deceasedNif;
    }

    public void setDeceasedNif(String deceasedNif) {
        this.deceasedNif = deceasedNif;
    }

    public String getDeceasedSubCode() {
        return deceasedSubCode;
    }

    public void setDeceasedSubCode(String deceasedSubCode) {
        this.deceasedSubCode = deceasedSubCode;
    }

    public String getAssetSequence() {
        return assetSequence;
    }

    public void setAssetSequence(String assetSequence) {
        this.assetSequence = assetSequence;
    }

    public String getBeneficiaryNif() {
        return beneficiaryNif;
    }

    public void setBeneficiaryNif(String beneficiaryNif) {
        this.beneficiaryNif = beneficiaryNif;
    }

    public String getBeneficiarySubCode() {
        return beneficiarySubCode;
    }

    public void setBeneficiarySubCode(String beneficiarySubCode) {
        this.beneficiarySubCode = beneficiarySubCode;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getIsLegacy() {
        return isLegacy;
    }

    public void setIsLegacy(String isLegacy) {
        this.isLegacy = isLegacy;
    }

    public BigDecimal getLegacyPercentage() {
        return legacyPercentage;
    }

    public void setLegacyPercentage(BigDecimal legacyPercentage) {
        this.legacyPercentage = legacyPercentage;
    }

    public String getAcquisitionType() {
        return acquisitionType;
    }

    public void setAcquisitionType(String acquisitionType) {
        this.acquisitionType = acquisitionType;
    }
}