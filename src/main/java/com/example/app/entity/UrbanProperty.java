package com.example.app.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "GATA_BIENURBA")
@IdClass(AssetDocumentId.class)
public class UrbanProperty implements Serializable {

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

    @Column(name = "CDPROVINCI", length = 2)
    private String provinceCode;

    @Column(name = "CDMUNICIPI", length = 3)
    private String municipalityCode;

    @Column(name = "CDTIPOVIAP", length = 2)
    private String streetType;

    @Column(name = "TLNOMBVIAP", length = 100)
    private String streetName;

    @Column(name = "TLNUMEVIAP", length = 10)
    private String streetNumber;

    @Column(name = "TLCODIPOST", length = 5)
    private String postalCode;

    @Column(name = "TLESCALERA", length = 10)
    private String staircase;

    @Column(name = "TLPISO", length = 10)
    private String floor;

    @Column(name = "TLPUERTA", length = 10)
    private String door;

    @Column(name = "CDPAIS", length = 3)
    private String country;

    @Column(name = "TLREFECATA", length = 20)
    private String cadastralReference;

    @Column(name = "CDTIPOBIEN", length = 2)
    private String propertyType;

    @Column(name = "ITVIVIHABI", length = 1)
    private String isHabitualResidence;

    @Column(name = "PTVIVIHABI", precision = 15, scale = 2)
    private BigDecimal habitualResidenceValue;

    @Column(name = "NMUNIDADES")
    private Integer numberOfUnits;

    @Column(name = "NMSUPERFIC", precision = 12, scale = 2)
    private BigDecimal surfaceArea;

    @Column(name = "AACONSTRUC")
    private Integer constructionYear;

    @Column(name = "CDSITUACI2", length = 2)
    private String situationCode;

    @Column(name = "ITARRENDAM", length = 1)
    private String isRented;

    @Column(name = "AACONTARRE")
    private Integer rentalContractYear;

    @Column(name = "ITPROTOFIC", length = 1)
    private String isOfficialProtection;

    @Column(name = "ITDESCALIF", length = 1)
    private String isDisqualified;

    @Column(name = "PTMAXVENTA", precision = 15, scale = 2)
    private BigDecimal maxSalePrice;

    @Column(name = "PTDECLARAD", precision = 15, scale = 2)
    private BigDecimal declaredAmount;

    @Column(name = "PTCOMPROBA", precision = 15, scale = 2)
    private BigDecimal verifiedAmount;

    @Column(name = "TLOBSERVAC", length = 500)
    private String observations;

    @Column(name = "PCTRANSMIS", precision = 5, scale = 2)
    private BigDecimal transmissionPercentage;

    public UrbanProperty() {
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

    public String getMunicipalityCode() {
        return municipalityCode;
    }

    public void setMunicipalityCode(String municipalityCode) {
        this.municipalityCode = municipalityCode;
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