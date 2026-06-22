package com.example.app.entity;

public class Municipality {

    private String provinceCode;
    private String code;
    private String name;

    public Municipality() {
    }

    public Municipality(String provinceCode, String code, String name) {
        this.provinceCode = provinceCode;
        this.code = code;
        this.name = name;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
