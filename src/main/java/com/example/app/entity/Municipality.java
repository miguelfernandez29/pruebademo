package com.example.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "MUNICIPALITY")
@IdClass(Municipality.MunicipalityId.class)
public class Municipality {

    @Id
    @Column(name = "PROVINCE_CODE", length = 2)
    private String provinceCode;

    @Id
    @Column(name = "MUNICIPALITY_CODE", length = 3)
    private String municipalityCode;

    @Column(name = "MUNICIPALITY_NAME", length = 100)
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

        public MunicipalityId(String provinceCode, String municipalityCode) {
            this.provinceCode = provinceCode;
            this.municipalityCode = municipalityCode;
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
            return java.util.Objects.equals(provinceCode, that.provinceCode) &&
                    java.util.Objects.equals(municipalityCode, that.municipalityCode);
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(provinceCode, municipalityCode);
        }
    }
}
