package com.example.app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Date;

public class VehicleDTO {

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

    private Date registrationDate;

    @Size(max = 1)
    private String vehicleType;

    private String vehicleTypeDescription;

    @Size(max = 5)
    private String vehicleBrand;

    private String vehicleBrandDescription;

    @Size(max = 10)
    private String vehicleModel;

    private String vehicleModelDescription;

    @Positive(message = "Engine displacement must be positive")
    private Integer engineDisplacement;

    @Positive(message = "Declared value must be positive")
    private BigDecimal declaredValue;

    @Positive(message = "Verified value must be positive")
    private BigDecimal verifiedValue;

    @Min(value = 0, message = "Ownership percentage must be at least 0")
    @Max(value = 100, message = "Ownership percentage cannot exceed 100")
    private BigDecimal ownershipPercentage;

    private Date catalogDate;

    @Size(max = 500)
    private String observations;

    private BigDecimal calculatedVerifiedValue;

    public VehicleDTO() {
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

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleTypeDescription() {
        return vehicleTypeDescription;
    }

    public void setVehicleTypeDescription(String vehicleTypeDescription) {
        this.vehicleTypeDescription = vehicleTypeDescription;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public String getVehicleBrandDescription() {
        return vehicleBrandDescription;
    }

    public void setVehicleBrandDescription(String vehicleBrandDescription) {
        this.vehicleBrandDescription = vehicleBrandDescription;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleModelDescription() {
        return vehicleModelDescription;
    }

    public void setVehicleModelDescription(String vehicleModelDescription) {
        this.vehicleModelDescription = vehicleModelDescription;
    }

    public Integer getEngineDisplacement() {
        return engineDisplacement;
    }

    public void setEngineDisplacement(Integer engineDisplacement) {
        this.engineDisplacement = engineDisplacement;
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

    public BigDecimal getOwnershipPercentage() {
        return ownershipPercentage;
    }

    public void setOwnershipPercentage(BigDecimal ownershipPercentage) {
        this.ownershipPercentage = ownershipPercentage;
    }

    public Date getCatalogDate() {
        return catalogDate;
    }

    public void setCatalogDate(Date catalogDate) {
        this.catalogDate = catalogDate;
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
}