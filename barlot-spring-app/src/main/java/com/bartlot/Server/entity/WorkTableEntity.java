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

    @Column(name = "idpointcomptage")
    private String pointComptageId;

    @Column(name = "idcompteur")
    private String idCompteur;

    @Column(name = "horodatage", columnDefinition = "timestamp without time zone")
    private Timestamp horodatage;

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