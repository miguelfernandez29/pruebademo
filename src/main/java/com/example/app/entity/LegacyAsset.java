package com.example.app.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "GATA_BIENLEGA")
@IdClass(LegacyAssetId.class)
public class LegacyAsset implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "AAPRESENTA", length = 4)
    private String presentationYear;

    @Id
    @Column(name = "VFTIPOIMPU", length = 2)
    private String taxType;

    @Id
    @Column(name = "CDPRESENTA", length = 14)
    private String presentationCode;

    @Id
    @Column(name = "CDNIFCAUSA", length = 9)
    private String deceasedNif;

    @Id
    @Column(name = "CDSUBCAUSA", length = 2)
    private String deceasedSubCode;

    @Id
    @Column(name = "CDSECUBIEN", length = 3)
    private String assetSequence;

    @Id
    @Column(name = "CDNIFSUPAS", length = 9)
    private String beneficiaryNif;

    @Id
    @Column(name = "CDSUBSUPAS", length = 2)
    private String beneficiarySubCode;

    @Column(name = "PCLEGADOSP", precision = 5, scale = 2)
    private BigDecimal legacyPercentage;

    @Column(name = "CDTPADQUI2", length = 1)
    private String acquisitionType;

    public LegacyAsset() {
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