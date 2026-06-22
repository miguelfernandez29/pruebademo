package com.example.app.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "GATA_BIENURBA")
@IdClass(AssetDocumentId.class)
public class UrbanProperty implements Serializable {

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

    @Column(name = "CDPROVINCI", length = 2)
    private String cdprovinci;

    @Column(name = "CDMUNICIPI", length = 3)
    private String cdmunicipi;

    @Column(name = "CDPAIS", length = 3)
    private String cdpais;

    @Column(name = "TLREFECATA", length = 20)
    private String tlrefecata;

    @Column(name = "CDTIPOBIEN", length = 2)
    private String cdtipobien;

    @Column(name = "CDTIPOVIAP", length = 2)
    private String cdtipoviap;

    @Column(name = "TLNOMBVIAP", length = 50)
    private String tlnombviap;

    @Column(name = "TLNUMEVIAP", length = 5)
    private String tlnumeviap;

    @Column(name = "TLCODIPOST", length = 5)
    private String tlcodipost;

    @Column(name = "TLESCALERA", length = 2)
    private String tlescalera;

    @Column(name = "TLPISO", length = 3)
    private String tlpiso;

    @Column(name = "TLPUERTA", length = 3)
    private String tlpuerta;

    @Column(name = "VFDUPLICAD", length = 1)
    private String vfduplicad;

    @Column(name = "AACONSTRUC")
    private Integer aaconstruc;

    @Column(name = "CDSITUACI1", length = 3)
    private String cdsituaci1;

    @Column(name = "CDSITUACI2", length = 2)
    private String cdsituaci2;

    @Column(name = "ITARRENDAM", length = 1)
    private String itarrendam;

    @Column(name = "AACONTARRE")
    private Integer aacontarre;

    @Column(name = "ITPROTOFIC", length = 1)
    private String itprotofic;

    @Column(name = "ITDESCALIF", length = 1)
    private String itdescalif;

    @Column(name = "ITVIVIHABI", length = 1)
    private String itvivihabi;

    @Column(name = "PTVIVIHABI", precision = 15, scale = 2)
    private BigDecimal ptvivihabi;

    @Column(name = "NMUNIDADES")
    private Integer nmunidades;

    @Column(name = "NMSUPERFIC", precision = 12, scale = 2)
    private BigDecimal nmsuperfic;

    @Column(name = "PTMAXVENTA", precision = 15, scale = 2)
    private BigDecimal ptmaxventa;

    @Column(name = "PTDECLARAD", precision = 15, scale = 2)
    private BigDecimal ptdeclarad;

    @Column(name = "PTCOMPROBA", precision = 15, scale = 2)
    private BigDecimal ptcomproba;

    @Column(name = "PCTRANSMIS", precision = 5, scale = 2)
    private BigDecimal pctransmis;

    @Column(name = "CDPOSBIEN2", length = 1)
    private String cdposbien2;

    @Column(name = "ITVALORREF", length = 1)
    private String itvalorref;

    @Column(name = "ITVRVALIDO", length = 1)
    private String itvrvalido;

    @Column(name = "ITVALBDBI", length = 1)
    private String itvalbdbi;

    @Column(name = "PTVALORREF", precision = 15, scale = 2)
    private BigDecimal ptvalorref;

    @Column(name = "CDZONAURBA", length = 3)
    private String cdzonaurba;

    @Column(name = "TLOBSERVAC", length = 200)
    private String tlobservac;

    public UrbanProperty() {
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

    public String getCdpais() {
        return cdpais;
    }

    public void setCdpais(String cdpais) {
        this.cdpais = cdpais;
    }

    public String getTlrefecata() {
        return tlrefecata;
    }

    public void setTlrefecata(String tlrefecata) {
        this.tlrefecata = tlrefecata;
    }

    public String getCdtipobien() {
        return cdtipobien;
    }

    public void setCdtipobien(String cdtipobien) {
        this.cdtipobien = cdtipobien;
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

    public String getTlescalera() {
        return tlescalera;
    }

    public void setTlescalera(String tlescalera) {
        this.tlescalera = tlescalera;
    }

    public String getTlpiso() {
        return tlpiso;
    }

    public void setTlpiso(String tlpiso) {
        this.tlpiso = tlpiso;
    }

    public String getTlpuerta() {
        return tlpuerta;
    }

    public void setTlpuerta(String tlpuerta) {
        this.tlpuerta = tlpuerta;
    }

    public String getVfduplicad() {
        return vfduplicad;
    }

    public void setVfduplicad(String vfduplicad) {
        this.vfduplicad = vfduplicad;
    }

    public Integer getAaconstruc() {
        return aaconstruc;
    }

    public void setAaconstruc(Integer aaconstruc) {
        this.aaconstruc = aaconstruc;
    }

    public String getCdsituaci1() {
        return cdsituaci1;
    }

    public void setCdsituaci1(String cdsituaci1) {
        this.cdsituaci1 = cdsituaci1;
    }

    public String getCdsituaci2() {
        return cdsituaci2;
    }

    public void setCdsituaci2(String cdsituaci2) {
        this.cdsituaci2 = cdsituaci2;
    }

    public String getItarrendam() {
        return itarrendam;
    }

    public void setItarrendam(String itarrendam) {
        this.itarrendam = itarrendam;
    }

    public Integer getAacontarre() {
        return aacontarre;
    }

    public void setAacontarre(Integer aacontarre) {
        this.aacontarre = aacontarre;
    }

    public String getItprotofic() {
        return itprotofic;
    }

    public void setItprotofic(String itprotofic) {
        this.itprotofic = itprotofic;
    }

    public String getItdescalif() {
        return itdescalif;
    }

    public void setItdescalif(String itdescalif) {
        this.itdescalif = itdescalif;
    }

    public String getItvivihabi() {
        return itvivihabi;
    }

    public void setItvivihabi(String itvivihabi) {
        this.itvivihabi = itvivihabi;
    }

    public BigDecimal getPtvivihabi() {
        return ptvivihabi;
    }

    public void setPtvivihabi(BigDecimal ptvivihabi) {
        this.ptvivihabi = ptvivihabi;
    }

    public Integer getNmunidades() {
        return nmunidades;
    }

    public void setNmunidades(Integer nmunidades) {
        this.nmunidades = nmunidades;
    }

    public BigDecimal getNmsuperfic() {
        return nmsuperfic;
    }

    public void setNmsuperfic(BigDecimal nmsuperfic) {
        this.nmsuperfic = nmsuperfic;
    }

    public BigDecimal getPtmaxventa() {
        return ptmaxventa;
    }

    public void setPtmaxventa(BigDecimal ptmaxventa) {
        this.ptmaxventa = ptmaxventa;
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

    public String getCdposbien2() {
        return cdposbien2;
    }

    public void setCdposbien2(String cdposbien2) {
        this.cdposbien2 = cdposbien2;
    }

    public String getItvalorref() {
        return itvalorref;
    }

    public void setItvalorref(String itvalorref) {
        this.itvalorref = itvalorref;
    }

    public String getItvrvalido() {
        return itvrvalido;
    }

    public void setItvrvalido(String itvrvalido) {
        this.itvrvalido = itvrvalido;
    }

    public String getItvalbdbi() {
        return itvalbdbi;
    }

    public void setItvalbdbi(String itvalbdbi) {
        this.itvalbdbi = itvalbdbi;
    }

    public BigDecimal getPtvalorref() {
        return ptvalorref;
    }

    public void setPtvalorref(BigDecimal ptvalorref) {
        this.ptvalorref = ptvalorref;
    }

    public String getCdzonaurba() {
        return cdzonaurba;
    }

    public void setCdzonaurba(String cdzonaurba) {
        this.cdzonaurba = cdzonaurba;
    }

    public String getTlobservac() {
        return tlobservac;
    }

    public void setTlobservac(String tlobservac) {
        this.tlobservac = tlobservac;
    }
}