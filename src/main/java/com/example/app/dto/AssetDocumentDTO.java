package com.example.app.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AssetDocumentDTO {

    private String aapresenta;
    private String vftipoimpu;
    private String cdpresenta;
    private String cdsecubien;
    private String cdnatbien2;
    private String cdsecuacem;
    private LocalDate fccomproba;
    private String idcomproba;
    private BigDecimal ptdeclarad;
    private BigDecimal ptcomproba;

    public AssetDocumentDTO() {
    }

    public String getAapresenta() {
        return aapresenta;
    }

    public void setAapresenta(String aapresenta) {
        this.aapresenta = aapresenta;
    }

    public String getVftipoimpu() {
        return vftipoimpu;
    }

    public void setVftipoimpu(String vftipoimpu) {
        this.vftipoimpu = vftipoimpu;
    }

    public String getCdpresenta() {
        return cdpresenta;
    }

    public void setCdpresenta(String cdpresenta) {
        this.cdpresenta = cdpresenta;
    }

    public String getCdsecubien() {
        return cdsecubien;
    }

    public void setCdsecubien(String cdsecubien) {
        this.cdsecubien = cdsecubien;
    }

    public String getCdnatbien2() {
        return cdnatbien2;
    }

    public void setCdnatbien2(String cdnatbien2) {
        this.cdnatbien2 = cdnatbien2;
    }

    public String getCdsecuacem() {
        return cdsecuacem;
    }

    public void setCdsecuacem(String cdsecuacem) {
        this.cdsecuacem = cdsecuacem;
    }

    public LocalDate getFccomproba() {
        return fccomproba;
    }

    public void setFccomproba(LocalDate fccomproba) {
        this.fccomproba = fccomproba;
    }

    public String getIdcomproba() {
        return idcomproba;
    }

    public void setIdcomproba(String idcomproba) {
        this.idcomproba = idcomproba;
    }

    public BigDecimal getPtdeclarad() {
        return ptdeclarad;
    }

    public void setPtdeclarad(BigDecimal ptdeclarad) {
        this.ptdeclarad = ptdeclarad;
    }

    public BigDecimal getPtcomproba() {
        return ptcomproba;
    }

    public void setPtcomproba(BigDecimal ptcomproba) {
        this.ptcomproba = ptcomproba;
    }
}
