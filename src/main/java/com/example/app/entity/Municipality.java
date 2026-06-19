package com.example.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Column;
import java.io.Serializable;

@Entity
@Table(name = "SUCA_MUNICIPIO")
@IdClass(Municipality.MunicipalityId.class)
public class Municipality {

    @Id
    @Column(name = "CDPROVINCI", length = 2)
    private String provinceCode;

    @Id
    @Column(name = "CDMUNICIPI", length = 3)
    private String municipalityCode;

    @Column(name = "DSMUNICIPI", length = 100)
    private String municipalityName;

    public Municipality() {
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

    public String getMunicipalityName() {
        return municipalityName;
    }

    public void setMunicipalityName(String municipalityName) {
        this.municipalityName = municipalityName;
    }

    public static class MunicipalityId implements Serializable {
        private String provinceCode;
        private String municipalityCode;

        public MunicipalityId() {
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MunicipalityId that = (MunicipalityId) o;
            return provinceCode != null && provinceCode.equals(that.provinceCode) &&
                   municipalityCode != null && municipalityCode.equals(that.municipalityCode);
        }

        @Override
        public int hashCode() {
            int result = provinceCode != null ? provinceCode.hashCode() : 0;
            result = 31 * result + (municipalityCode != null ? municipalityCode.hashCode() : 0);
            return result;
        }
    }
}