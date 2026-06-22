package com.example.app.dto;

import java.math.BigDecimal;

public class LegacyBeneficiaryDTO {

    private String presentationYear;
    private String taxType;
    private String presentationCode;
    private String assetSequence;
    private String causantNif;
    private String causantSubcode;
    private String beneficiaryNif;
    private String beneficiarySubcode;
    private BigDecimal legacyPercentage;
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
