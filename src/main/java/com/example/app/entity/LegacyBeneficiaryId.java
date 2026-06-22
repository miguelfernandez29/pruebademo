package com.example.app.entity;

import java.io.Serializable;
import java.util.Objects;

public class LegacyBeneficiaryId implements Serializable {

    private String aapresenta;
    private String vftipoimpu;
    private String cdpresenta;
    private String cdsecubien;
    private String cdnifcausa;
    private String cdsubcausa;
    private String cdnifsupas;
    private String cdsubsupas;

    public LegacyBeneficiaryId() {
    }

    public LegacyBeneficiaryId(String aapresenta, String vftipoimpu, String cdpresenta,
                               String cdsecubien, String cdnifcausa, String cdsubcausa,
                               String cdnifsupas, String cdsubsupas) {
        this.aapresenta = aapresenta;
        this.vftipoimpu = vftipoimpu;
        this.cdpresenta = cdpresenta;
        this.cdsecubien = cdsecubien;
        this.cdnifcausa = cdnifcausa;
        this.cdsubcausa = cdsubcausa;
        this.cdnifsupas = cdnifsupas;
        this.cdsubsupas = cdsubsupas;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LegacyBeneficiaryId that = (LegacyBeneficiaryId) o;
        return Objects.equals(aapresenta, that.aapresenta) &&
                Objects.equals(vftipoimpu, that.vftipoimpu) &&
                Objects.equals(cdpresenta, that.cdpresenta) &&
                Objects.equals(cdsecubien, that.cdsecubien) &&
                Objects.equals(cdnifcausa, that.cdnifcausa) &&
                Objects.equals(cdsubcausa, that.cdsubcausa) &&
                Objects.equals(cdnifsupas, that.cdnifsupas) &&
                Objects.equals(cdsubsupas, that.cdsubsupas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aapresenta, vftipoimpu, cdpresenta, cdsecubien,
                cdnifcausa, cdsubcausa, cdnifsupas, cdsubsupas);
    }
}
