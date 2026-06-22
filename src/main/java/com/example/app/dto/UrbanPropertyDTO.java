package com.example.app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

public class UrbanPropertyDTO {

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

    @NotBlank(message = "Provincia es obligatoria")
    @Size(max = 2)
    private String cdprovinci;

    @Size(max = 3)
    private String cdmunicipi;

    @Size(max = 3)
    private String cdpais;

    @Size(max = 20)
    private String tlrefecata;

    @Size(max = 2)
    private String cdtipobien;

    @Size(max = 2)
    private String cdtipoviap;

    @Size(max = 50)
    private String tlnombviap;

    @Size(max = 5)
    private String tlnumeviap;

    @Size(max = 5)
    private String tlcodipost;

    @Size(max = 2)
    private String tlescalera;

    @Size(max = 3)
    private String tlpiso;

    @Size(max = 3)
    private String tlpuerta;

    @Size(max = 1)
    private String vfduplicad;

    @Min(value = 1500, message = "El año de construcción debe ser mayor a 1500")
    private Integer aaconstruc;

    @Size(max = 3)
    private String cdsituaci1;

    @Size(max = 2)
    private String cdsituaci2;

    @Size(max = 1)
    private String itarrendam;

    private Integer aacontarre;

    @Size(max = 1)
    private String itprotofic;

    @Size(max = 1)
    private String itdescalif;

    @Size(max = 1)
    private String itvivihabi;

    private BigDecimal ptvivihabi;

    private Integer nmunidades;

    private BigDecimal nmsuperfic;

    private BigDecimal ptmaxventa;

    private BigDecimal ptdeclarad;

    private BigDecimal ptcomproba;

    @Min(value = 0, message = "El porcentaje de transmisión no puede ser negativo")
    @Max(value = 100, message = "El porcentaje de transmisión no puede superar 100")
    private BigDecimal pctransmis;

    @Size(max = 1)
    private String cdposbien2;

    @Size(max = 1)
    private String itvalorref;

    @Size(max = 1)
    private String itvrvalido;

    @Size(max = 1)
    private String itvalbdbi;

    private BigDecimal ptvalorref;

    @Size(max = 3)
    private String cdzonaurba;

    @Size(max = 200)
    private String tlobservac;

    private String dsprovinci;
    private String dsmunicipi;
    private String dstipobien;
    private String dssituaci2;
    private String dspais;

    public UrbanPropertyDTO() {
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

    public String getDsprovinci() {
        return dsprovinci;
    }

    public void setDsprovinci(String dsprovinci) {
        this.dsprovinci = dsprovinci;
    }

    public String getDsmunicipi() {
        return dsmunicipi;
    }

    public void setDsmunicipi(String dsmunicipi) {
        this.dsmunicipi = dsmunicipi;
    }

    public String getDstipobien() {
        return dstipobien;
    }

    public void setDstipobien(String dstipobien) {
        this.dstipobien = dstipobien;
    }

    public String getDssituaci2() {
        return dssituaci2;
    }

    public void setDssituaci2(String dssituaci2) {
        this.dssituaci2 = dssituaci2;
    }

    public String getDspais() {
        return dspais;
    }

    public void setDspais(String dspais) {
        this.dspais = dspais;
    }
}