package com.example.app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public class VehicleDTO {

    @NotBlank(message = "Año de presentación es obligatorio")
    @Size(max = 4)
    private String aapresenta;

    @NotBlank(message = "Tipo de impuesto es obligatorio")
    @Size(max = 2)
    private String vftipoimpu;

    @NotBlank(message = "Código de presentación es obligatorio")
    @Size(max = 14)
    private String cdpresenta;

    @Size(max = 3)
    private String cdsecubien;

    private LocalDate fcmatricul;
    private LocalDate fcvehicata;

    @Size(max = 1)
    private String cdvehitipo;

    @Size(max = 3)
    private String cdvehimarc;

    @Size(max = 6)
    private String cdvehimode;

    private Integer nmcilindcc;
    private BigDecimal ptdeclarad;
    private BigDecimal ptcomproba;
    private BigDecimal pctitulari;

    @Size(max = 200)
    private String tlobservac;

    private String dsvehitipo;
    private String dsvehimarc;
    private String dsabrevModelo;

    public VehicleDTO() {
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

    public String getDsvehitipo() {
        return dsvehitipo;
    }

    public void setDsvehitipo(String dsvehitipo) {
        this.dsvehitipo = dsvehitipo;
    }

    public String getDsvehimarc() {
        return dsvehimarc;
    }

    public void setDsvehimarc(String dsvehimarc) {
        this.dsvehimarc = dsvehimarc;
    }

    public String getDsabrevModelo() {
        return dsabrevModelo;
    }

    public void setDsabrevModelo(String dsabrevModelo) {
        this.dsabrevModelo = dsabrevModelo;
    }
}