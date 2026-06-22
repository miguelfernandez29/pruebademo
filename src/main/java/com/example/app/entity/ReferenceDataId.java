package com.example.app.entity;

import java.io.Serializable;
import java.util.Objects;

public class ReferenceDataId implements Serializable {

    private String cdtipodato;
    private String cddatogena;

    public ReferenceDataId() {
    }

    public ReferenceDataId(String cdtipodato, String cddatogena) {
        this.cdtipodato = cdtipodato;
        this.cddatogena = cddatogena;
    }

    public String getCdtipodato() {
        return cdtipodato;
    }

    public void setCdtipodato(String cdtipodato) {
        this.cdtipodato = cdtipodato;
    }

    public String getCddatogena() {
        return cddatogena;
    }

    public void setCddatogena(String cddatogena) {
        this.cddatogena = cddatogena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReferenceDataId that = (ReferenceDataId) o;
        return Objects.equals(cdtipodato, that.cdtipodato) &&
                Objects.equals(cddatogena, that.cddatogena);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cdtipodato, cddatogena);
    }
}