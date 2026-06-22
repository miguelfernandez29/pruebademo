package com.example.app.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "GATA_BIENOTRO")
@IdClass(AssetDocumentId.class)
public class OtherAsset implements Serializable {

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

    @Column(name = "DSNORMAL", length = 100)
    private String dsnormal;

    @Column(name = "NMUNIDADES")
    private Integer nmunidades;

    @Column(name = "PTDECLARAD", precision = 15, scale = 2)
    private BigDecimal ptdeclarad;

    @Column(name = "PTCOMPROBA", precision = 15, scale = 2)
    private BigDecimal ptcomproba;

    @Column(name = "PCTRANSMIS", precision = 5, scale = 2)
    private BigDecimal pctransmis;

    @Column(name = "TLOBSERVAC", length = 200)
    private String tlobservac;

    public OtherAsset() {
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

    public String getDsnormal() {
        return dsnormal;
    }

    public void setDsnormal(String dsnormal) {
        this.dsnormal = dsnormal;
    }

    public Integer getNmunidades() {
        return nmunidades;
    }

    public void setNmunidades(Integer nmunidades) {
        this.nmunidades = nmunidades;
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

    public BigDecimal getPctransmis() {
        return pctransmis;
    }

    public void setPctransmis(BigDecimal pctransmis) {
        this.pctransmis = pctransmis;
    }

    public String getTlobservac() {
        return tlobservac;
    }

    public void setTlobservac(String tlobservac) {
        this.tlobservac = tlobservac;
    }
}