package com.example.app.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "GATA_BIENACEM")
@IdClass(AssetDocumentId.class)
public class BusinessAsset implements Serializable {

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

    @Column(name = "TLEPIGRAFE", length = 10)
    private String tlepigrafe;

    @Column(name = "DSBIENACEM", length = 100)
    private String dsbienacem;

    @Column(name = "CDPROVINCI", length = 2)
    private String cdprovinci;

    @Column(name = "CDMUNICIPI", length = 3)
    private String cdmunicipi;

    @Column(name = "CDTIPOVIAP", length = 2)
    private String cdtipoviap;

    @Column(name = "TLNOMBVIAP", length = 50)
    private String tlnombviap;

    @Column(name = "TLNUMEVIAP", length = 5)
    private String tlnumeviap;

    @Column(name = "TLCODIPOST", length = 5)
    private String tlcodipost;

    @Column(name = "PTDECLARAD", precision = 15, scale = 2)
    private BigDecimal ptdeclarad;

    @Column(name = "PTCOMPROBA", precision = 15, scale = 2)
    private BigDecimal ptcomproba;

    @Column(name = "PCTITULARI", precision = 5, scale = 2)
    private BigDecimal pctitulari;

    @Column(name = "ITREDUCCIO", length = 1)
    private String itreduccio;

    @Column(name = "ITBIENAFEC", length = 1)
    private String itbienafec;

    @Column(name = "TLOBSERVAC", length = 200)
    private String tlobservac;

    public BusinessAsset() {
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

    public String getTlepigrafe() {
        return tlepigrafe;
    }

    public void setTlepigrafe(String tlepigrafe) {
        this.tlepigrafe = tlepigrafe;
    }

    public String getDsbienacem() {
        return dsbienacem;
    }

    public void setDsbienacem(String dsbienacem) {
        this.dsbienacem = dsbienacem;
    }

    public String getCdprovinci() {
        return cdprovinci;
    }

    public void setCdprovinci(String cdprovinci) {
        this.cdprovinci = cdprovinci;
    }

    public String getCdmunicipi() {
        return cdmunicipi;
    }

    public void setCdmunicipi(String cdmunicipi) {
        this.cdmunicipi = cdmunicipi;
    }

    public String getCdtipoviap() {
        return cdtipoviap;
    }

    public void setCdtipoviap(String cdtipoviap) {
        this.cdtipoviap = cdtipoviap;
    }

    public String getTlnombviap() {
        return tlnombviap;
    }

    public void setTlnombviap(String tlnombviap) {
        this.tlnombviap = tlnombviap;
    }

    public String getTlnumeviap() {
        return tlnumeviap;
    }

    public void setTlnumeviap(String tlnumeviap) {
        this.tlnumeviap = tlnumeviap;
    }

    public String getTlcodipost() {
        return tlcodipost;
    }

    public void setTlcodipost(String tlcodipost) {
        this.tlcodipost = tlcodipost;
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

    public String getItreduccio() {
        return itreduccio;
    }

    public void setItreduccio(String itreduccio) {
        this.itreduccio = itreduccio;
    }

    public String getItbienafec() {
        return itbienafec;
    }

    public void setItbienafec(String itbienafec) {
        this.itbienafec = itbienafec;
    }

    public String getTlobservac() {
        return tlobservac;
    }

    public void setTlobservac(String tlobservac) {
        this.tlobservac = tlobservac;
    }
}