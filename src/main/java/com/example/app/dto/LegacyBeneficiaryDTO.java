package com.example.app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

public class LegacyBeneficiaryDTO {

    @NotBlank(message = "Presentation year is required")
    @Size(max = 4)
    private String presentationYear;

    @NotBlank(message = "Tax type is required")
    @Size(max = 2)
    private String taxType;

    @NotBlank(message = "Presentation code is required")
    @Size(max = 10)
    private String presentationCode;

    @NotBlank(message = "Asset sequence is required")
    @Size(max = 3)
    private String assetSequence;

    @NotBlank(message = "Causant NIF is required")
    @Size(max = 9)
    private String causantNif;

    @Size(max = 2)
    private String causantSubcode;

    @NotBlank(message = "Beneficiary NIF is required")
    @Size(max = 9)
    private String beneficiaryNif;

    @Size(max = 2)
    private String beneficiarySubcode;

    private String beneficiaryName;

    @Pattern(regexp = "^[SN]$", message = "Legacy indicator must be S or N")
    private String legacyIndicator;

    @Min(value = 0, message = "Legacy percentage must be at least 0")
    @Max(value = 100, message = "Legacy percentage cannot exceed 100")
    private BigDecimal legacyPercentage;

    @Pattern(regexp = "^[PN]$", message = "Acquisition type must be P or N")
    private String acquisitionType;

    public LegacyBeneficiaryDTO() {
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

    public String getAssetSequence() {
        return assetSequence;
    }

    public void setAssetSequence(String assetSequence) {
        this.assetSequence = assetSequence;
    }

    public String getCausantNif() {
        return causantNif;
    }

    public void setCausantNif(String causantNif) {
        this.causantNif = causantNif;
    }

    public String getCausantSubcode() {
        return causantSubcode;
    }

    public void setCausantSubcode(String causantSubcode) {
        this.causantSubcode = causantSubcode;
    }

    public String getBeneficiaryNif() {
        return beneficiaryNif;
    }

    public void setBeneficiaryNif(String beneficiaryNif) {
        this.beneficiaryNif = beneficiaryNif;
    }

    public String getBeneficiarySubcode() {
        return beneficiarySubcode;
    }

    public void setBeneficiarySubcode(String beneficiarySubcode) {
        this.beneficiarySubcode = beneficiarySubcode;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getLegacyIndicator() {
        return legacyIndicator;
    }

    public void setLegacyIndicator(String legacyIndicator) {
        this.legacyIndicator = legacyIndicator;
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