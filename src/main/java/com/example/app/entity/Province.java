package com.example.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "SUCA_PROVINCIA")
public class Province {

    @Id
    @Column(name = "CDPROVINCI", length = 2)
    private String provinceCode;

    @Column(name = "DSPROVINCI", length = 100)
    private String provinceName;

    public Province() {
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
}