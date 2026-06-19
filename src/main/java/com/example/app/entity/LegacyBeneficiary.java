package com.example.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "GATA_BIENLEGA")
@IdClass(LegacyBeneficiary.LegacyBeneficiaryId.class)
public class LegacyBeneficiary {

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

    @Id
    @Column(name = "CDNIFCAUSA", length = 9)
    private String causantNif;

    @Id
    @Column(name = "CDSUBCAUSA", length = 2)
    private String causantSubcode;

    @Id
    @Column(name = "CDNIFSUPAS", length = 9)
    private String beneficiaryNif;

    @Id
    @Column(name = "CDSUBSUPAS", length = 2)
    private String beneficiarySubcode;

    @Column(name = "ITLEGADO", length = 1)
    private String legacyIndicator;

    @Column(name = "PCLEGADOSP", precision = 5, scale = 2)
    private BigDecimal legacyPercentage;

    @Column(name = "CDTPADQUI2", length = 1)
    private String acquisitionType;

    public LegacyBeneficiary() {
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

    public String getCausantNif() {
        return causantNif;
    }

    public void setCausantNif(String causantNif) {
        this.causantNif = causantNif;
    }

    public String getCausantSubcode() {
        return causantSubcode;
    }

    public void setCausantSubcode(String causantSubcode) {
        this.causantSubcode = causantSubcode;
    }

    public String getBeneficiaryNif() {
        return beneficiaryNif;
    }

    public void setBeneficiaryNif(String beneficiaryNif) {
        this.beneficiaryNif = beneficiaryNif;
    }

    public String getBeneficiarySubcode() {
        return beneficiarySubcode;
    }

    public void setBeneficiarySubcode(String beneficiarySubcode) {
        this.beneficiarySubcode = beneficiarySubcode;
    }

    public String getLegacyIndicator() {
        return legacyIndicator;
    }

    public void setLegacyIndicator(String legacyIndicator) {
        this.legacyIndicator = legacyIndicator;
    }

    public BigDecimal getLegacyPercentage() {
        return legacyPercentage;
    }

    public void setLegacyPercentage(BigDecimal legacyPercentage) {
        this.legacyPercentage = legacyPercentage;
    }

    public String getAcquisitionType() {
        return acquisitionType;
    }

    public void setAcquisitionType(String acquisitionType) {
        this.acquisitionType = acquisitionType;
    }

    public static class LegacyBeneficiaryId implements Serializable {
        private String presentationYear;
        private String taxType;
        private String presentationCode;
        private String assetSequence;
        private String causantNif;
        private String causantSubcode;
        private String beneficiaryNif;
        private String beneficiarySubcode;

        public LegacyBeneficiaryId() {
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

        public String getCausantNif() {
            return causantNif;
        }

        public void setCausantNif(String causantNif) {
            this.causantNif = causantNif;
        }

        public String getCausantSubcode() {
            return causantSubcode;
        }

        public void setCausantSubcode(String causantSubcode) {
            this.causantSubcode = causantSubcode;
        }

        public String getBeneficiaryNif() {
            return beneficiaryNif;
        }

        public void setBeneficiaryNif(String beneficiaryNif) {
            this.beneficiaryNif = beneficiaryNif;
        }

        public String getBeneficiarySubcode() {
            return beneficiarySubcode;
        }

        public void setBeneficiarySubcode(String beneficiarySubcode) {
            this.beneficiarySubcode = beneficiarySubcode;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            LegacyBeneficiaryId that = (LegacyBeneficiaryId) o;
            return presentationYear != null && presentationYear.equals(that.presentationYear) &&
                   taxType != null && taxType.equals(that.taxType) &&
                   presentationCode != null && presentationCode.equals(that.presentationCode) &&
                   assetSequence != null && assetSequence.equals(that.assetSequence) &&
                   causantNif != null && causantNif.equals(that.causantNif) &&
                   causantSubcode != null && causantSubcode.equals(that.causantSubcode) &&
                   beneficiaryNif != null && beneficiaryNif.equals(that.beneficiaryNif) &&
                   beneficiarySubcode != null && beneficiarySubcode.equals(that.beneficiarySubcode);
        }

        @Override
        public int hashCode() {
            int result = presentationYear != null ? presentationYear.hashCode() : 0;
            result = 31 * result + (taxType != null ? taxType.hashCode() : 0);
            result = 31 * result + (presentationCode != null ? presentationCode.hashCode() : 0);
            result = 31 * result + (assetSequence != null ? assetSequence.hashCode() : 0);
            result = 31 * result + (causantNif != null ? causantNif.hashCode() : 0);
            result = 31 * result + (causantSubcode != null ? causantSubcode.hashCode() : 0);
            result = 31 * result + (beneficiaryNif != null ? beneficiaryNif.hashCode() : 0);
            result = 31 * result + (beneficiarySubcode != null ? beneficiarySubcode.hashCode() : 0);
            return result;
        }
    }
}