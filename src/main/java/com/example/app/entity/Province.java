package com.example.app.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import java.io.Serializable;

@Entity
@Table(name = "SUCA_PROVINCIA")
public class Province implements Serializable {

    @Id
    @Column(name = "CDPROV", length = 2)
    private String cdprov;

    @Column(name = "DSPROV", length = 50)
    private String dsprov;

    public Province() {
    }

    public String getCdprov() {
        return cdprov;
    }

    public void setCdprov(String cdprov) {
        this.cdprov = cdprov;
    }

    public String getDsprov() {
        return dsprov;
    }

    public void setDsprov(String dsprov) {
        this.dsprov = dsprov;
    }
}