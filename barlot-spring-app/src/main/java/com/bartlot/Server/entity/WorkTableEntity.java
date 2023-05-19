package com.bartlot.Server.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "work_table")

public class WorkTableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "idclient")
    private String idClient;

    @Column(name = "idsite")
    private String idSite;

    @Column(name = "idpointcomptage")
    private String pointComptageId;

    @Column(name = "idcompteur")
    private String idCompteur;

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

    @Column(name = "validation")
    private String validation;

    @Column(name = "commentaires")
    private String commentaire;

    @Column(name = "attente_action")
    private String attenteAction;

    // Getters and Setters

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

    public String getValidation() {
        return validation;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getAttenteAction() {
        return attenteAction;
    }

    public void setAttenteAction(String attenteAction) {
        this.attenteAction = attenteAction;
    }
}