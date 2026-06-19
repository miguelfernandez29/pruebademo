package com.example.app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

public class RuralPropertyDTO {

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

    @Size(min = 2, max = 2)
    private String provinceCode;

    private String provinceName;

    @Size(max = 3)
    private String municipalityCode;

    private String municipalityName;

    @Size(max = 5)
    private String postalCode;

    @Size(max = 3)
    private String countryCode;

    @Size(max = 25)
    private String cadastralReference;

    @Size(max = 10)
    private String polygon;

    @Size(max = 10)
    private String parcel;

    @Size(max = 100)
    private String location;

    @Size(max = 2)
    private String propertyType;

    private String propertyTypeDescription;

    @Size(max = 3)
    private String measurementUnitType;

    @Size(max = 2)
    private String measurementUnit;

    private String measurementUnitDescription;

    @Positive(message = "Surface area must be positive")
    private BigDecimal surfaceArea;

    @Positive(message = "Declared value must be positive")
    private BigDecimal declaredValue;

    @Positive(message = "Verified value must be positive")
    private BigDecimal verifiedValue;

    @Min(value = 0, message = "Transmission percentage must be at least 0")
    @Max(value = 100, message = "Transmission percentage cannot exceed 100")
    private BigDecimal transmissionPercentage;

    @Pattern(regexp = "^[PG]$", message = "Position type must be P or G")
    private String positionType;

    private String referenceValueIndicator;

    private String validReferenceValueIndicator;

    private BigDecimal referenceValue;

    @Size(max = 500)
    private String observations;

    private BigDecimal calculatedVerifiedValue;

    private String conformityStatus;

    public RuralPropertyDTO() {
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

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getMunicipalityCode() {
        return municipalityCode;
    }

    public void setMunicipalityCode(String municipalityCode) {
        this.municipalityCode = municipalityCode;
    }

    public String getMunicipalityName() {
        return municipalityName;
    }

    public void setMunicipalityName(String municipalityName) {
        this.municipalityName = municipalityName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCadastralReference() {
        return cadastralReference;
    }

    public void setCadastralReference(String cadastralReference) {
        this.cadastralReference = cadastralReference;
    }

    public String getPolygon() {
        return polygon;
    }

    public void setPolygon(String polygon) {
        this.polygon = polygon;
    }

    public String getParcel() {
        return parcel;
    }

    public void setParcel(String parcel) {
        this.parcel = parcel;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getPropertyTypeDescription() {
        return propertyTypeDescription;
    }

    public void setPropertyTypeDescription(String propertyTypeDescription) {
        this.propertyTypeDescription = propertyTypeDescription;
    }

    public String getMeasurementUnitType() {
        return measurementUnitType;
    }

    public void setMeasurementUnitType(String measurementUnitType) {
        this.measurementUnitType = measurementUnitType;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public String getMeasurementUnitDescription() {
        return measurementUnitDescription;
    }

    public void setMeasurementUnitDescription(String measurementUnitDescription) {
        this.measurementUnitDescription = measurementUnitDescription;
    }

    public BigDecimal getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(BigDecimal surfaceArea) {
        this.surfaceArea = surfaceArea;
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

    public BigDecimal getTransmissionPercentage() {
        return transmissionPercentage;
    }

    public void setTransmissionPercentage(BigDecimal transmissionPercentage) {
        this.transmissionPercentage = transmissionPercentage;
    }

    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    public String getReferenceValueIndicator() {
        return referenceValueIndicator;
    }

    public void setReferenceValueIndicator(String referenceValueIndicator) {
        this.referenceValueIndicator = referenceValueIndicator;
    }

    public String getValidReferenceValueIndicator() {
        return validReferenceValueIndicator;
    }

    public void setValidReferenceValueIndicator(String validReferenceValueIndicator) {
        this.validReferenceValueIndicator = validReferenceValueIndicator;
    }

    public BigDecimal getReferenceValue() {
        return referenceValue;
    }

    public void setReferenceValue(BigDecimal referenceValue) {
        this.referenceValue = referenceValue;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public BigDecimal getCalculatedVerifiedValue() {
        return calculatedVerifiedValue;
    }

    public void setCalculatedVerifiedValue(BigDecimal calculatedVerifiedValue) {
        this.calculatedVerifiedValue = calculatedVerifiedValue;
    }

    public String getConformityStatus() {
        return conformityStatus;
    }

    public void setConformityStatus(String conformityStatus) {
        this.conformityStatus = conformityStatus;
    }
}