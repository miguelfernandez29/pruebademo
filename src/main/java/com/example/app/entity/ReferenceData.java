package com.example.app.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Column;
import java.io.Serializable;

@Entity
@Table(name = "GATA_DATOGENA")
@IdClass(ReferenceDataId.class)
public class ReferenceData implements Serializable {

    @Id
    @Column(name = "CDTIPODATO", length = 3)
    private String cdtipodato;

    @Id
    @Column(name = "CDDATOGENA", length = 2)
    private String cddatogena;

    @Column(name = "DSABREV", length = 50)
    private String dsabrev;

    @Column(name = "DSDATOGENA", length = 100)
    private String dsdatogena;

    public ReferenceData() {
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

    public String getDsabrev() {
        return dsabrev;
    }

    public void setDsabrev(String dsabrev) {
        this.dsabrev = dsabrev;
    }

    public String getDsdatogena() {
        return dsdatogena;
    }

    public void setDsdatogena(String dsdatogena) {
        this.dsdatogena = dsdatogena;
    }
}