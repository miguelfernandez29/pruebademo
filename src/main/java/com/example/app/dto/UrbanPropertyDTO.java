package com.example.app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class UrbanPropertyDTO {

    @NotBlank(message = "Presentation year is required")
    private String presentationYear;

    @NotBlank(message = "Tax type is required")
    private String taxType;

    @NotBlank(message = "Presentation code is required")
    private String presentationCode;

    @NotBlank(message = "Asset sequence is required")
    private String assetSequence;

    @NotBlank(message = "Province code is required")
    @Size(max = 2)
    private String provinceCode;

    private String provinceDescription;

    @NotBlank(message = "Municipality code is required")
    @Size(max = 3)
    private String municipalityCode;

    private String municipalityDescription;

    @Size(max = 2)
    private String streetType;

    @Size(max = 100)
    private String streetName;

    @Size(max = 10)
    private String streetNumber;

    @Size(max = 5)
    private String postalCode;

    @Size(max = 10)
    private String staircase;

    @Size(max = 10)
    private String floor;

    @Size(max = 10)
    private String door;

    @Size(max = 3)
    private String country;

    private String countryDescription;

    @Size(max = 20)
    private String cadastralReference;

    @NotBlank(message = "Property type is required")
    @Size(max = 2)
    private String propertyType;

    private String propertyTypeDescription;

    @Size(max = 1)
    private String isHabitualResidence;

    @DecimalMax(value = "999999999999.99", message = "Maximum allowed value is 999,999,999,999.99")
    private BigDecimal habitualResidenceValue;

    private Integer numberOfUnits;

    @DecimalMax(value = "9999999999.99", message = "Maximum allowed value is 9,999,999,999.99")
    private BigDecimal surfaceArea;

    private Integer constructionYear;

    @Size(max = 2)
    private String situationCode;

    private String situationDescription;

    @Size(max = 1)
    private String isRented;

    private Integer rentalContractYear;

    @Size(max = 1)
    private String isOfficialProtection;

    @Size(max = 1)
    private String isDisqualified;

    @DecimalMax(value = "999999999999.99", message = "Maximum allowed value is 999,999,999,999.99")
    private BigDecimal maxSalePrice;

    @DecimalMax(value = "999999999999.99", message = "Maximum allowed value is 999,999,999,999.99")
    private BigDecimal declaredAmount;

    @DecimalMax(value = "999999999999.99", message = "Maximum allowed value is 999,999,999,999.99")
    private BigDecimal verifiedAmount;

    private BigDecimal proportionalVerifiedAmount;

    private BigDecimal referenceValue;

    private String hasReferenceValue;

    private String referenceValueValid;

    @Size(max = 500)
    private String observations;

    private BigDecimal transmissionPercentage;

    public UrbanPropertyDTO() {
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

    public String getProvinceDescription() {
        return provinceDescription;
    }

    public void setProvinceDescription(String provinceDescription) {
        this.provinceDescription = provinceDescription;
    }

    public String getMunicipalityCode() {
        return municipalityCode;
    }

    public void setMunicipalityCode(String municipalityCode) {
        this.municipalityCode = municipalityCode;
    }

    public String getMunicipalityDescription() {
        return municipalityDescription;
    }

    public void setMunicipalityDescription(String municipalityDescription) {
        this.municipalityDescription = municipalityDescription;
    }

    public String getStreetType() {
        return streetType;
    }

    public void setStreetType(String streetType) {
        this.streetType = streetType;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStaircase() {
        return staircase;
    }

    public void setStaircase(String staircase) {
        this.staircase = staircase;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryDescription() {
        return countryDescription;
    }

    public void setCountryDescription(String countryDescription) {
        this.countryDescription = countryDescription;
    }

    public String getCadastralReference() {
        return cadastralReference;
    }

    public void setCadastralReference(String cadastralReference) {
        this.cadastralReference = cadastralReference;
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

    public String getIsHabitualResidence() {
        return isHabitualResidence;
    }

    public void setIsHabitualResidence(String isHabitualResidence) {
        this.isHabitualResidence = isHabitualResidence;
    }

    public BigDecimal getHabitualResidenceValue() {
        return habitualResidenceValue;
    }

    public void setHabitualResidenceValue(BigDecimal habitualResidenceValue) {
        this.habitualResidenceValue = habitualResidenceValue;
    }

    public Integer getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(Integer numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public BigDecimal getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(BigDecimal surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public Integer getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(Integer constructionYear) {
        this.constructionYear = constructionYear;
    }

    public String getSituationCode() {
        return situationCode;
    }

    public void setSituationCode(String situationCode) {
        this.situationCode = situationCode;
    }

    public String getSituationDescription() {
        return situationDescription;
    }

    public void setSituationDescription(String situationDescription) {
        this.situationDescription = situationDescription;
    }

    public String getIsRented() {
        return isRented;
    }

    public void setIsRented(String isRented) {
        this.isRented = isRented;
    }

    public Integer getRentalContractYear() {
        return rentalContractYear;
    }

    public void setRentalContractYear(Integer rentalContractYear) {
        this.rentalContractYear = rentalContractYear;
    }

    public String getIsOfficialProtection() {
        return isOfficialProtection;
    }

    public void setIsOfficialProtection(String isOfficialProtection) {
        this.isOfficialProtection = isOfficialProtection;
    }

    public String getIsDisqualified() {
        return isDisqualified;
    }

    public void setIsDisqualified(String isDisqualified) {
        this.isDisqualified = isDisqualified;
    }

    public BigDecimal getMaxSalePrice() {
        return maxSalePrice;
    }

    public void setMaxSalePrice(BigDecimal maxSalePrice) {
        this.maxSalePrice = maxSalePrice;
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

    public BigDecimal getProportionalVerifiedAmount() {
        return proportionalVerifiedAmount;
    }

    public void setProportionalVerifiedAmount(BigDecimal proportionalVerifiedAmount) {
        this.proportionalVerifiedAmount = proportionalVerifiedAmount;
    }

    public BigDecimal getReferenceValue() {
        return referenceValue;
    }

    public void setReferenceValue(BigDecimal referenceValue) {
        this.referenceValue = referenceValue;
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

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public BigDecimal getTransmissionPercentage() {
        return transmissionPercentage;
    }

    public void setTransmissionPercentage(BigDecimal transmissionPercentage) {
        this.transmissionPercentage = transmissionPercentage;
    }
}