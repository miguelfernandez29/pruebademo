package com.example.app.entity;

import java.io.Serializable;
import java.util.Objects;

public class AssetDocumentId implements Serializable {

    private String aapresenta;
    private String vftipoimpu;
    private String cdpresenta;
    private String cdsecubien;

    public AssetDocumentId() {
    }

    public AssetDocumentId(String aapresenta, String vftipoimpu, String cdpresenta, String cdsecubien) {
        this.aapresenta = aapresenta;
        this.vftipoimpu = vftipoimpu;
        this.cdpresenta = cdpresenta;
        this.cdsecubien = cdsecubien;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssetDocumentId that = (AssetDocumentId) o;
        return Objects.equals(aapresenta, that.aapresenta) &&
                Objects.equals(vftipoimpu, that.vftipoimpu) &&
                Objects.equals(cdpresenta, that.cdpresenta) &&
                Objects.equals(cdsecubien, that.cdsecubien);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aapresenta, vftipoimpu, cdpresenta, cdsecubien);
    }
}
