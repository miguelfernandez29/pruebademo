package com.example.app.entity;

import java.io.Serializable;
import java.util.Objects;

public class LegacyAssetId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String presentationYear;
    private String taxType;
    private String presentationCode;
    private String deceasedNif;
    private String deceasedSubCode;
    private String assetSequence;
    private String beneficiaryNif;
    private String beneficiarySubCode;

    public LegacyAssetId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LegacyAssetId that = (LegacyAssetId) o;
        return Objects.equals(presentationYear, that.presentationYear) &&
                Objects.equals(taxType, that.taxType) &&
                Objects.equals(presentationCode, that.presentationCode) &&
                Objects.equals(deceasedNif, that.deceasedNif) &&
                Objects.equals(deceasedSubCode, that.deceasedSubCode) &&
                Objects.equals(assetSequence, that.assetSequence) &&
                Objects.equals(beneficiaryNif, that.beneficiaryNif) &&
                Objects.equals(beneficiarySubCode, that.beneficiarySubCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(presentationYear, taxType, presentationCode, deceasedNif, deceasedSubCode, assetSequence, beneficiaryNif, beneficiarySubCode);
    }
}