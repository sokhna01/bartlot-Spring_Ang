package com.bartlot.Server.entity;

import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "meter_data_source_externe")

public class MeterDataExterneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "idclient")
    private String idClient;

    @Column(name = "horodatage", columnDefinition = "timestamp without time zone")
    private Timestamp horodatage;

    @Column(name = "dataaplus")
    private Double dataAPlus;

    @Column(name = "dataamoins")
    private Double dataAMoins;

    @Column(name = "datarplus")
    private Double dataRPlus;

    @Column(name = "datarmoins")
    private Double dataRMoins;

    @Column(name = "created_date", columnDefinition = "timestamp without time zone default now()")
    private Timestamp createdDate;

    @Column(name = "source")
    private String source;

    @Column(name = "presence")
    private String presence;

    @Column(name = "qualite")
    private String qualite;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public Timestamp getHorodatage() {
        return horodatage;
    }

    public void setHorodatage(Timestamp horodatage) {
        this.horodatage = horodatage;
    }

    public Double getDataAPlus() {
        return dataAPlus;
    }

    public void setDataAPlus(Double dataAPlus) {
        this.dataAPlus = dataAPlus;
    }

    public Double getDataAMoins() {
        return dataAMoins;
    }

    public void setDataAMoins(Double dataAMoins) {
        this.dataAMoins = dataAMoins;
    }

    public Double getDataRPlus() {
        return dataRPlus;
    }

    public void setDataRPlus(Double dataRPlus) {
        this.dataRPlus = dataRPlus;
    }

    public Double getDataRMoins() {
        return dataRMoins;
    }

    public void setDataRMoins(Double dataRMoins) {
        this.dataRMoins = dataRMoins;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPresence() {
        return presence;
    }

    public void setPresence(String presence) {
        this.presence = presence;
    }

    public String getQualite() {
        return qualite;
    }

    public void setQualite(String qualite) {
        this.qualite = qualite;
    }
}
