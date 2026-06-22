package com.example.app.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "GATA_BIENRUST")
@IdClass(AssetDocumentId.class)
public class RuralProperty implements Serializable {

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

    @Column(name = "TLCODIPOST", length = 5)
    private String tlcodipost;

    @Column(name = "TLPOLIGONO", length = 10)
    private String tlpoligono;

    @Column(name = "TLPARCELA", length = 10)
    private String tlparcela;

    @Column(name = "TLPARAJE", length = 50)
    private String tlparaje;

    @Column(name = "CDUNIMEDI1", length = 3)
    private String cdunimedi1;

    @Column(name = "CDUNIMEDI2", length = 2)
    private String cdunimedi2;

    @Column(name = "NMSUPERFIC", precision = 14, scale = 4)
    private BigDecimal nmsuperfic;

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

    @Column(name = "TLOBSERVAC", length = 200)
    private String tlobservac;

    public RuralProperty() {
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

    public String getTlcodipost() {
        return tlcodipost;
    }

    public void setTlcodipost(String tlcodipost) {
        this.tlcodipost = tlcodipost;
    }

    public String getTlpoligono() {
        return tlpoligono;
    }

    public void setTlpoligono(String tlpoligono) {
        this.tlpoligono = tlpoligono;
    }

    public String getTlparcela() {
        return tlparcela;
    }

    public void setTlparcela(String tlparcela) {
        this.tlparcela = tlparcela;
    }

    public String getTlparaje() {
        return tlparaje;
    }

    public void setTlparaje(String tlparaje) {
        this.tlparaje = tlparaje;
    }

    public String getCdunimedi1() {
        return cdunimedi1;
    }

    public void setCdunimedi1(String cdunimedi1) {
        this.cdunimedi1 = cdunimedi1;
    }

    public String getCdunimedi2() {
        return cdunimedi2;
    }

    public void setCdunimedi2(String cdunimedi2) {
        this.cdunimedi2 = cdunimedi2;
    }

    public BigDecimal getNmsuperfic() {
        return nmsuperfic;
    }

    public void setNmsuperfic(BigDecimal nmsuperfic) {
        this.nmsuperfic = nmsuperfic;
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

    public String getTlobservac() {
        return tlobservac;
    }

    public void setTlobservac(String tlobservac) {
        this.tlobservac = tlobservac;
    }
}