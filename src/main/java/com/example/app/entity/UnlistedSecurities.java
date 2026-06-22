package com.example.app.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "GATA_BIENVANO")
@IdClass(AssetDocumentId.class)
public class UnlistedSecurities implements Serializable {

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

    @Column(name = "TLCIFEMPRE", length = 9)
    private String tlcifempre;

    @Column(name = "DSBIENVANO", length = 100)
    private String dsbienvano;

    @Column(name = "PTVTEORICO", precision = 15, scale = 2)
    private BigDecimal ptvteorico;

    @Column(name = "NMUNIDADES")
    private Integer nmunidades;

    @Column(name = "PTDECLARAD", precision = 15, scale = 2)
    private BigDecimal ptdeclarad;

    @Column(name = "PTCOMPROBA", precision = 15, scale = 2)
    private BigDecimal ptcomproba;

    @Column(name = "TLOBSERVAC", length = 200)
    private String tlobservac;

    public UnlistedSecurities() {
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

    public String getTlcifempre() {
        return tlcifempre;
    }

    public void setTlcifempre(String tlcifempre) {
        this.tlcifempre = tlcifempre;
    }

    public String getDsbienvano() {
        return dsbienvano;
    }

    public void setDsbienvano(String dsbienvano) {
        this.dsbienvano = dsbienvano;
    }

    public BigDecimal getPtvteorico() {
        return ptvteorico;
    }

    public void setPtvteorico(BigDecimal ptvteorico) {
        this.ptvteorico = ptvteorico;
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

    public String getTlobservac() {
        return tlobservac;
    }

    public void setTlobservac(String tlobservac) {
        this.tlobservac = tlobservac;
    }
}