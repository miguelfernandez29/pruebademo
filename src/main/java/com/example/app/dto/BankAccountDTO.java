package com.example.app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class BankAccountDTO {

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

    @Size(max = 50)
    private String tlentidepo;

    @Size(max = 34)
    private String tldeposito;

    private BigDecimal ptdeclarad;
    private BigDecimal ptcomproba;
    private BigDecimal pctitulari;

    @Size(max = 200)
    private String tlobservac;

    public BankAccountDTO() {
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

    public String getTlentidepo() {
        return tlentidepo;
    }

    public void setTlentidepo(String tlentidepo) {
        this.tlentidepo = tlentidepo;
    }

    public String getTldeposito() {
        return tldeposito;
    }

    public void setTldeposito(String tldeposito) {
        this.tldeposito = tldeposito;
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