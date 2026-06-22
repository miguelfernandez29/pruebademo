package com.example.app.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "GATA_BIENDOCU")
@IdClass(AssetDocumentId.class)
public class AssetDocument implements Serializable {

    @Id
    @Column(name = "AAPRESENTA", length = 4)
    private String aapresenta;

    @Id
    @Column(name = "VFTIPOIMPU", length = 2)
    private String vftipoimpu;

    @Id
    @Column(name = "CDPRESENTA", length = 14)
    private String cdpresenta;

    @Id
    @Column(name = "CDSECUBIEN", length = 3)
    private String cdsecubien;

    @Column(name = "CDNATBIEN2", length = 1)
    private String cdnatbien2;

    @Column(name = "CDSECUACEM", length = 3)
    private String cdsecuacem;

    @Column(name = "FCCOMPROBA")
    private LocalDate fccomproba;

    @Column(name = "IDCOMPROBA", length = 20)
    private String idcomproba;

    @Column(name = "PTDECLARAD", precision = 15, scale = 2)
    private BigDecimal ptdeclarad;

    @Column(name = "PTCOMPROBA", precision = 15, scale = 2)
    private BigDecimal ptcomproba;

    public AssetDocument() {
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