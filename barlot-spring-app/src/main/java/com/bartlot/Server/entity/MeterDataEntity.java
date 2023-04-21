package com.bartlot.Server.entity;

import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "meter_data")

public class MeterDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    // JPA maps to the SERIAL data type in PostgreSQL automatically, so we don't
    // need to specify it explicitly.

    @Column(name = "idclient")
    private String idClient;

    @Column(name = "idsite")
    private String idSite;

    @Column(name = "idpointcomptage")
    private String pointComptageId;

    @Column(name = "idcompteur")
    private String idCompteur;

    @Column(name = "horodotage", columnDefinition = "timestamp without time zone")
    private Timestamp horodotage;

    @Column(name = "dataaplus")
    private String dataAPlus;

    @Column(name = "dataamoins")
    private String dataAMoins;

    @Column(name = "datarplus")
    private String dataRPlus;

    @Column(name = "datarmoins")
    private String dataRMoins;

    @Column(name = "created_date", columnDefinition = "timestamp without time zone default now()")
    private Timestamp createdDate;

    @Column(name = "idcompany")
    private Integer idCompany;

    @Column(name = "source")
    private String source;

    @Column(name = "presence")
    private String presence;

    @Column(name = "qualite")
    private String qualite;

    @Column(name = "puissance_reactive_qualite", length = 2, columnDefinition = "varchar(2) default '5'")
    private String puissanceReactiveQualite = "5";

    // public MeterDataModel() {

    // }
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

    public Integer getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Integer idCompany) {
        this.idCompany = idCompany;
    }

    public String getIdSite() {
        return idSite;
    }

    public void setIdSite(String idSite) {
        this.idSite = idSite;
    }

    public String getPointComptageId() {
        return pointComptageId;
    }

    public void setPointComptageId(String pointComptageId) {
        this.pointComptageId = pointComptageId;
    }

    public String getIdCompteur() {
        return idCompteur;
    }

    public void setIdCompteur(String idCompteur) {
        this.idCompteur = idCompteur;
    }

    public Timestamp getHorodotage() {
        return horodotage;
    }

    public void setHorodotage(Timestamp horodotage) {
        this.horodotage = horodotage;
    }

    public String getDataAPlus() {
        return dataAPlus;
    }

    public void setDataAPlus(String dataAPlus) {
        this.dataAPlus = dataAPlus;
    }

    public String getDataAMoins() {
        return dataAMoins;
    }

    public void setDataAMoins(String dataAMoins) {
        this.dataAMoins = dataAMoins;
    }

    public String getDataRPlus() {
        return dataRPlus;
    }

    public void setDataRPlus(String dataRPlus) {
        this.dataRPlus = dataRPlus;
    }

    public String getDataRMoins() {
        return dataRMoins;
    }

    public void setDataRMoins(String dataRMoins) {
        this.dataRMoins = dataRMoins;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
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

    public String getPuissanceReactiveQualite() {
        return puissanceReactiveQualite;
    }

    public void setPuissanceReactiveQualite(String puissanceReactiveQualite) {
        this.puissanceReactiveQualite = puissanceReactiveQualite;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
