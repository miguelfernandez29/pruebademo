package com.example.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "GATA_BIENOTRO")
@IdClass(OtherAsset.OtherAssetId.class)
public class OtherAsset {

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

    @Column(name = "DSNORMAL", length = 200)
    private String description;

    @Column(name = "NMUNIDADES")
    private Integer numberOfUnits;

    @Column(name = "PTDECLARAD", precision = 15, scale = 2)
    private BigDecimal declaredValue;

    @Column(name = "PTCOMPROBA", precision = 15, scale = 2)
    private BigDecimal verifiedValue;

    @Column(name = "PCTRANSMIS", precision = 5, scale = 2)
    private BigDecimal transmissionPercentage;

    @Column(name = "TLOBSERVAC", length = 500)
    private String observations;

    public OtherAsset() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(Integer numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
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

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public static class OtherAssetId implements Serializable {
        private String presentationYear;
        private String taxType;
        private String presentationCode;
        private String assetSequence;

        public OtherAssetId() {
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
            OtherAssetId that = (OtherAssetId) o;
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