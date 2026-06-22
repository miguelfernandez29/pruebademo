package com.example.app.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Column;
import java.io.Serializable;

@Entity
@Table(name = "SUCA_MUNICIPIO")
@IdClass(MunicipalityId.class)
public class Municipality implements Serializable {

    @Id
    @Column(name = "CDPROV", length = 2)
    private String cdprov;

    @Id
    @Column(name = "CDMUNI", length = 3)
    private String cdmuni;

    @Column(name = "DSMUNI", length = 50)
    private String dsmuni;

    public Municipality() {
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

    public String getDsmuni() {
        return dsmuni;
    }

    public void setDsmuni(String dsmuni) {
        this.dsmuni = dsmuni;
    }
}