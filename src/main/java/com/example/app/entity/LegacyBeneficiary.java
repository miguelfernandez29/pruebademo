package com.example.app.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "GATA_BIENLEGA")
@IdClass(LegacyBeneficiaryId.class)
public class LegacyBeneficiary implements Serializable {

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

    @Id
    @Column(name = "CDNIFCAUSA", length = 9)
    private String cdnifcausa;

    @Id
    @Column(name = "CDSUBCAUSA", length = 2)
    private String cdsubcausa;

    @Id
    @Column(name = "CDNIFSUPAS", length = 9)
    private String cdnifsupas;

    @Id
    @Column(name = "CDSUBSUPAS", length = 2)
    private String cdsubsupas;

    @Column(name = "PCLEGADOSP", precision = 5, scale = 2)
    private BigDecimal pclegadosp;

    @Column(name = "CDTPADQUI2", length = 1)
    private String cdtpadqui2;

    public LegacyBeneficiary() {
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

    public String getCdnifcausa() {
        return cdnifcausa;
    }

    public void setCdnifcausa(String cdnifcausa) {
        this.cdnifcausa = cdnifcausa;
    }

    public String getCdsubcausa() {
        return cdsubcausa;
    }

    public void setCdsubcausa(String cdsubcausa) {
        this.cdsubcausa = cdsubcausa;
    }

    public String getCdnifsupas() {
        return cdnifsupas;
    }

    public void setCdnifsupas(String cdnifsupas) {
        this.cdnifsupas = cdnifsupas;
    }

    public String getCdsubsupas() {
        return cdsubsupas;
    }

    public void setCdsubsupas(String cdsubsupas) {
        this.cdsubsupas = cdsubsupas;
    }

    public BigDecimal getPclegadosp() {
        return pclegadosp;
    }

    public void setPclegadosp(BigDecimal pclegadosp) {
        this.pclegadosp = pclegadosp;
    }

    public String getCdtpadqui2() {
        return cdtpadqui2;
    }

    public void setCdtpadqui2(String cdtpadqui2) {
        this.cdtpadqui2 = cdtpadqui2;
    }
}