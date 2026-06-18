package com.example.app.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "GATA_BIENDOCU")
@IdClass(AssetDocumentId.class)
public class AssetDocument implements Serializable {

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
    @Column(name = "CDSECUBIEN", length = 3)
    private String assetSequence;

    @Column(name = "CDNATBIEN2", length = 1)
    private String assetNature;

    @Column(name = "CDPOSBIEN2", length = 1)
    private String assetPosition;

    @Column(name = "PCTRANSMIS", precision = 5, scale = 2)
    private BigDecimal transmissionPercentage;

    @Column(name = "PTDECLARAD", precision = 15, scale = 2)
    private BigDecimal declaredAmount;

    @Column(name = "PTCOMPROBA", precision = 15, scale = 2)
    private BigDecimal verifiedAmount;

    @Column(name = "FCCOMPROBA")
    private LocalDate verificationDate;

    @Column(name = "NMVALORACI")
    private Integer valuationNumber;

    @Column(name = "CDSITUVREF", length = 2)
    private String referenceValueStatus;

    @Column(name = "ITVALORREF", length = 1)
    private String hasReferenceValue;

    @Column(name = "ITVRVALIDO", length = 1)
    private String referenceValueValid;

    @Column(name = "PTVALORREF", precision = 15, scale = 2)
    private BigDecimal referenceValue;

    @Column(name = "CDSECUACEM", length = 3)
    private String linkedBusinessAssetSequence;

    @Column(name = "IDCOMPROBA", length = 20)
    private String verificationId;

    public AssetDocument() {
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

    public String getAssetNature() {
        return assetNature;
    }

    public void setAssetNature(String assetNature) {
        this.assetNature = assetNature;
    }

    public String getAssetPosition() {
        return assetPosition;
    }

    public void setAssetPosition(String assetPosition) {
        this.assetPosition = assetPosition;
    }

    public BigDecimal getTransmissionPercentage() {
        return transmissionPercentage;
    }

    public void setTransmissionPercentage(BigDecimal transmissionPercentage) {
        this.transmissionPercentage = transmissionPercentage;
    }

    public BigDecimal getDeclaredAmount() {
        return declaredAmount;
    }

    public void setDeclaredAmount(BigDecimal declaredAmount) {
        this.declaredAmount = declaredAmount;
    }

    public BigDecimal getVerifiedAmount() {
        return verifiedAmount;
    }

    public void setVerifiedAmount(BigDecimal verifiedAmount) {
        this.verifiedAmount = verifiedAmount;
    }

    public LocalDate getVerificationDate() {
        return verificationDate;
    }

    public void setVerificationDate(LocalDate verificationDate) {
        this.verificationDate = verificationDate;
    }

    public Integer getValuationNumber() {
        return valuationNumber;
    }

    public void setValuationNumber(Integer valuationNumber) {
        this.valuationNumber = valuationNumber;
    }

    public String getReferenceValueStatus() {
        return referenceValueStatus;
    }

    public void setReferenceValueStatus(String referenceValueStatus) {
        this.referenceValueStatus = referenceValueStatus;
    }

    public String getHasReferenceValue() {
        return hasReferenceValue;
    }

    public void setHasReferenceValue(String hasReferenceValue) {
        this.hasReferenceValue = hasReferenceValue;
    }

    public String getReferenceValueValid() {
        return referenceValueValid;
    }

    public void setReferenceValueValid(String referenceValueValid) {
        this.referenceValueValid = referenceValueValid;
    }

    public BigDecimal getReferenceValue() {
        return referenceValue;
    }

    public void setReferenceValue(BigDecimal referenceValue) {
        this.referenceValue = referenceValue;
    }

    public String getLinkedBusinessAssetSequence() {
        return linkedBusinessAssetSequence;
    }

    public void setLinkedBusinessAssetSequence(String linkedBusinessAssetSequence) {
        this.linkedBusinessAssetSequence = linkedBusinessAssetSequence;
    }

    public String getVerificationId() {
        return verificationId;
    }

    public void setVerificationId(String verificationId) {
        this.verificationId = verificationId;
    }
}