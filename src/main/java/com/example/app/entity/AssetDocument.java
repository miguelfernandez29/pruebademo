package com.example.app.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "ASSET_DOCUMENT")
@IdClass(AssetDocumentId.class)
public class AssetDocument {

    @Id
    @Column(name = "PRESENTATION_YEAR", length = 4)
    private String presentationYear;

    @Id
    @Column(name = "TAX_TYPE", length = 2)
    private String taxType;

    @Id
    @Column(name = "PRESENTATION_CODE", length = 14)
    private String presentationCode;

    @Id
    @Column(name = "ASSET_SEQUENCE", length = 3)
    private String assetSequence;

    @Column(name = "ASSET_NATURE", length = 2)
    private String assetNature;

    @Column(name = "ASSET_POSITION", length = 1)
    private String assetPosition;

    @Column(name = "TRANSMISSION_PERCENTAGE", precision = 5, scale = 2)
    private BigDecimal transmissionPercentage;

    @Column(name = "DECLARED_VALUE", precision = 14, scale = 2)
    private BigDecimal declaredValue;

    @Column(name = "VERIFIED_VALUE", precision = 14, scale = 2)
    private BigDecimal verifiedValue;

    @Column(name = "CONFORMITY_INDICATOR", length = 1)
    private String conformityIndicator;

    @Column(name = "REFERENCE_VALUE_SITUATION", length = 2)
    private String referenceValueSituation;

    @Column(name = "HAS_REFERENCE_VALUE", length = 1)
    private String hasReferenceValue;

    @Column(name = "REFERENCE_VALUE", precision = 14, scale = 2)
    private BigDecimal referenceValue;

    @Column(name = "VERIFICATION_DATE")
    private LocalDate verificationDate;

    @Column(name = "VERIFICATION_ID", length = 20)
    private String verificationId;

    @Column(name = "BUSINESS_ASSET_SEQUENCE", length = 3)
    private String businessAssetSequence;

    @Column(name = "ASSET_TYPE", length = 2)
    private String assetType;

    @Column(name = "OBSERVATIONS", length = 500)
    private String observations;

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

    public BigDecimal getDeclaredValue() {
        return declaredValue;
    }

    public void setDeclaredValue(BigDecimal declaredValue) {
        this.declaredValue = declaredValue;
    }

    public BigDecimal getVerifiedValue() {
        return verifiedValue;
    }

    public void setVerifiedValue(BigDecimal verifiedValue) {
        this.verifiedValue = verifiedValue;
    }

    public String getConformityIndicator() {
        return conformityIndicator;
    }

    public void setConformityIndicator(String conformityIndicator) {
        this.conformityIndicator = conformityIndicator;
    }

    public String getReferenceValueSituation() {
        return referenceValueSituation;
    }

    public void setReferenceValueSituation(String referenceValueSituation) {
        this.referenceValueSituation = referenceValueSituation;
    }

    public String getHasReferenceValue() {
        return hasReferenceValue;
    }

    public void setHasReferenceValue(String hasReferenceValue) {
        this.hasReferenceValue = hasReferenceValue;
    }

    public BigDecimal getReferenceValue() {
        return referenceValue;
    }

    public void setReferenceValue(BigDecimal referenceValue) {
        this.referenceValue = referenceValue;
    }

    public LocalDate getVerificationDate() {
        return verificationDate;
    }

    public void setVerificationDate(LocalDate verificationDate) {
        this.verificationDate = verificationDate;
    }

    public String getVerificationId() {
        return verificationId;
    }

    public void setVerificationId(String verificationId) {
        this.verificationId = verificationId;
    }

    public String getBusinessAssetSequence() {
        return businessAssetSequence;
    }

    public void setBusinessAssetSequence(String businessAssetSequence) {
        this.businessAssetSequence = businessAssetSequence;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}
