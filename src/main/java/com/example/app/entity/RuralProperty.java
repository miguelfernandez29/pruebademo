package com.example.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "GATA_BIENRUST")
@IdClass(RuralProperty.RuralPropertyId.class)
public class RuralProperty {

    @Id
    @Column(name = "AAPRESENTA", length = 4)
    private String presentationYear;

    @Id
    @Column(name = "VFTIPOIMPU", length = 2)
    private String taxType;

    @Id
    @Column(name = "CDPRESENTA", length = 10)
    private String presentationCode;

    @Id
    @Column(name = "CDSECUBIEN", length = 3)
    private String assetSequence;

    @Column(name = "CDPROVINCI", length = 2)
    private String provinceCode;

    @Column(name = "CDMUNICIPI", length = 3)
    private String municipalityCode;

    @Column(name = "TLCODIPOST", length = 5)
    private String postalCode;

    @Column(name = "CDPAIS", length = 3)
    private String countryCode;

    @Column(name = "TLREFECATA", length = 25)
    private String cadastralReference;

    @Column(name = "TLPOLIGONO", length = 10)
    private String polygon;

    @Column(name = "TLPARCELA", length = 10)
    private String parcel;

    @Column(name = "TLPARAJE", length = 100)
    private String location;

    @Column(name = "CDTIPOBIEN", length = 2)
    private String propertyType;

    @Column(name = "CDUNIMEDI1", length = 3)
    private String measurementUnitType;

    @Column(name = "CDUNIMEDI2", length = 2)
    private String measurementUnit;

    @Column(name = "NMSUPERFIC", precision = 14, scale = 4)
    private BigDecimal surfaceArea;

    @Column(name = "PTDECLARAD", precision = 15, scale = 2)
    private BigDecimal declaredValue;

    @Column(name = "PTCOMPROBA", precision = 15, scale = 2)
    private BigDecimal verifiedValue;

    @Column(name = "PCTRANSMIS", precision = 5, scale = 2)
    private BigDecimal transmissionPercentage;

    @Column(name = "CDPOSBIEN2", length = 1)
    private String positionType;

    @Column(name = "ITVALORREF", length = 1)
    private String referenceValueIndicator;

    @Column(name = "ITVRVALIDO", length = 1)
    private String validReferenceValueIndicator;

    @Column(name = "PTVALORREF", precision = 15, scale = 2)
    private BigDecimal referenceValue;

    @Column(name = "TLOBSERVAC", length = 500)
    private String observations;

    public RuralProperty() {
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

    public static class RuralPropertyId implements Serializable {
        private String presentationYear;
        private String taxType;
        private String presentationCode;
        private String assetSequence;

        public RuralPropertyId() {
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RuralPropertyId that = (RuralPropertyId) o;
            return presentationYear != null && presentationYear.equals(that.presentationYear) &&
                   taxType != null && taxType.equals(that.taxType) &&
                   presentationCode != null && presentationCode.equals(that.presentationCode) &&
                   assetSequence != null && assetSequence.equals(that.assetSequence);
        }

        @Override
        public int hashCode() {
            int result = presentationYear != null ? presentationYear.hashCode() : 0;
            result = 31 * result + (taxType != null ? taxType.hashCode() : 0);
            result = 31 * result + (presentationCode != null ? presentationCode.hashCode() : 0);
            result = 31 * result + (assetSequence != null ? assetSequence.hashCode() : 0);
            return result;
        }
    }
}