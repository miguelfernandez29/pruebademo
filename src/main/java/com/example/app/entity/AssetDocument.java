package com.example.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "GATA_BIENDOCU")
@IdClass(AssetDocument.AssetDocumentId.class)
public class AssetDocument {

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

    @Column(name = "CDNATBIEN2", length = 1)
    private String assetNature;

    @Column(name = "CDSECUACEM", length = 3)
    private String businessAssetSequence;

    @Column(name = "FCCOMPROBA")
    private Date verificationDate;

    @Column(name = "IDCOMPROBA", length = 20)
    private String verificationId;

    @Column(name = "PTDECLARAD", precision = 15, scale = 2)
    private BigDecimal declaredValue;

    @Column(name = "PTCOMPROBA", precision = 15, scale = 2)
    private BigDecimal verifiedValue;

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

    public String getBusinessAssetSequence() {
        return businessAssetSequence;
    }

    public void setBusinessAssetSequence(String businessAssetSequence) {
        this.businessAssetSequence = businessAssetSequence;
    }

    public Date getVerificationDate() {
        return verificationDate;
    }

    public void setVerificationDate(Date verificationDate) {
        this.verificationDate = verificationDate;
    }

    public String getVerificationId() {
        return verificationId;
    }

    public void setVerificationId(String verificationId) {
        this.verificationId = verificationId;
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

    public static class AssetDocumentId implements Serializable {
        private String presentationYear;
        private String taxType;
        private String presentationCode;
        private String assetSequence;

        public AssetDocumentId() {
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
            AssetDocumentId that = (AssetDocumentId) o;
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