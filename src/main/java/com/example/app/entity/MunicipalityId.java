package com.example.app.entity;

import java.io.Serializable;
import java.util.Objects;

public class MunicipalityId implements Serializable {

    private String cdprov;
    private String cdmuni;

    public MunicipalityId() {
    }

    public MunicipalityId(String cdprov, String cdmuni) {
        this.cdprov = cdprov;
        this.cdmuni = cdmuni;
    }

    public String getCdprov() {
        return cdprov;
    }

    public void setCdprov(String cdprov) {
        this.cdprov = cdprov;
    }

    public String getCdmuni() {
        return cdmuni;
    }

    public void setCdmuni(String cdmuni) {
        this.cdmuni = cdmuni;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MunicipalityId that = (MunicipalityId) o;
        return Objects.equals(cdprov, that.cdprov) &&
                Objects.equals(cdmuni, that.cdmuni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cdprov, cdmuni);
    }
}