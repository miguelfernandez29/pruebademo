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
@Table(name = "GATA_BIENVEHI")
@IdClass(AssetDocumentId.class)
public class Vehicle implements Serializable {

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

    @Column(name = "FCMATRICUL")
    private LocalDate fcmatricul;

    @Column(name = "FCVEHICATA")
    private LocalDate fcvehicata;

    @Column(name = "CDVEHITIPO", length = 1)
    private String cdvehitipo;

    @Column(name = "CDVEHIMARC", length = 3)
    private String cdvehimarc;

    @Column(name = "CDVEHIMODE", length = 6)
    private String cdvehimode;

    @Column(name = "NMCILINDCC")
    private Integer nmcilindcc;

    @Column(name = "PTDECLARAD", precision = 15, scale = 2)
    private BigDecimal ptdeclarad;

    @Column(name = "PTCOMPROBA", precision = 15, scale = 2)
    private BigDecimal ptcomproba;

    @Column(name = "PCTITULARI", precision = 5, scale = 2)
    private BigDecimal pctitulari;

    @Column(name = "TLOBSERVAC", length = 200)
    private String tlobservac;

    public Vehicle() {
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

    public LocalDate getFcmatricul() {
        return fcmatricul;
    }

    public void setFcmatricul(LocalDate fcmatricul) {
        this.fcmatricul = fcmatricul;
    }

    public LocalDate getFcvehicata() {
        return fcvehicata;
    }

    public void setFcvehicata(LocalDate fcvehicata) {
        this.fcvehicata = fcvehicata;
    }

    public String getCdvehitipo() {
        return cdvehitipo;
    }

    public void setCdvehitipo(String cdvehitipo) {
        this.cdvehitipo = cdvehitipo;
    }

    public String getCdvehimarc() {
        return cdvehimarc;
    }

    public void setCdvehimarc(String cdvehimarc) {
        this.cdvehimarc = cdvehimarc;
    }

    public String getCdvehimode() {
        return cdvehimode;
    }

    public void setCdvehimode(String cdvehimode) {
        this.cdvehimode = cdvehimode;
    }

    public Integer getNmcilindcc() {
        return nmcilindcc;
    }

    public void setNmcilindcc(Integer nmcilindcc) {
        this.nmcilindcc = nmcilindcc;
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

    public BigDecimal getPctitulari() {
        return pctitulari;
    }

    public void setPctitulari(BigDecimal pctitulari) {
        this.pctitulari = pctitulari;
    }

    public String getTlobservac() {
        return tlobservac;
    }

    public void setTlobservac(String tlobservac) {
        this.tlobservac = tlobservac;
    }
}