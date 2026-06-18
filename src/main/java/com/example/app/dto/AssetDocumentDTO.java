package com.example.app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AssetDocumentDTO {

    @NotBlank(message = "Presentation year is required")
    @Size(max = 4)
    private String presentationYear;

    @NotBlank(message = "Tax type is required")
    @Size(max = 2)
    private String taxType;

    @NotBlank(message = "Presentation code is required")
    @Size(max = 14)
    private String presentationCode;

    @NotBlank(message = "Asset sequence is required")
    @Size(max = 3)
    private String assetSequence;

    @Size(max = 1)
    private String assetNature;

    @Size(max = 1)
    private String assetPosition;

    @DecimalMin(value = "0.00", message = "Transmission percentage must be at least 0")
    @DecimalMax(value = "100.00", message = "Transmission percentage cannot exceed 100")
    private BigDecimal transmissionPercentage;

    @DecimalMax(value = "999999999999.99", message = "Maximum allowed value is 999,999,999,999.99")
    private BigDecimal declaredAmount;

    @DecimalMax(value = "999999999999.99", message = "Maximum allowed value is 999,999,999,999.99")
    private BigDecimal verifiedAmount;

    private LocalDate verificationDate;

    private Integer valuationNumber;

    private String referenceValueStatus;

    private String hasReferenceValue;

    private String referenceValueValid;

    private BigDecimal referenceValue;

    private BigDecimal proportionalVerifiedAmount;

    private String assetNatureDescription;

    private boolean hasReduction;

    private boolean conformity;

    public AssetDocumentDTO() {
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

    public BigDecimal getProportionalVerifiedAmount() {
        return proportionalVerifiedAmount;
    }

    public void setProportionalVerifiedAmount(BigDecimal proportionalVerifiedAmount) {
        this.proportionalVerifiedAmount = proportionalVerifiedAmount;
    }

    public String getAssetNatureDescription() {
        return assetNatureDescription;
    }

    public void setAssetNatureDescription(String assetNatureDescription) {
        this.assetNatureDescription = assetNatureDescription;
    }

    public boolean isHasReduction() {
        return hasReduction;
    }

    public void setHasReduction(boolean hasReduction) {
        this.hasReduction = hasReduction;
    }

    public boolean isConformity() {
        return conformity;
    }

    public void setConformity(boolean conformity) {
        this.conformity = conformity;
    }
}