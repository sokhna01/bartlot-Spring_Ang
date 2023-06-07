package com.bartlot.Server.entity;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

    
@Entity
@Table(name = "intervention")
public class InterventionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "idclient", columnDefinition = "character varying")
    private String idclient;

    @Column(name = "idcompany")
    private Integer idCompany;

    @Column(name = "idsite", columnDefinition = "character varying")
    private String idsite;

    @Column(name = "idcompteur", columnDefinition = "character varying")
    private String idcompteur;

    @Column(name = "horodotage_debut", columnDefinition = "timestamp without time zone")
    private Timestamp startHorodatage;

    @Column(name = "horodotage_fin", columnDefinition = "timestamp without time zone")
    private Timestamp endHorodatage;

    @Column(name = "annuler", nullable = false, columnDefinition = "boolean default false")
    private boolean annuler;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdclient() {
        return idclient;
    }

    public void setIdclient(String idclient) {
        this.idclient = idclient;
    }

    public Integer getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Integer idCompany) {
        this.idCompany = idCompany;
    }

    public String getIdsite() {
        return idsite;
    }

    public void setIdsite(String idsite) {
        this.idsite = idsite;
    }

    public String getIdcompteur() {
        return idcompteur;
    }

    public void setIdcompteur(String idcompteur) {
        this.idcompteur = idcompteur;
    }

    public Timestamp getStartHorodatage() {
        return startHorodatage;
    }

    public void setStartHorodatage(Timestamp startHorodatage) {
        this.startHorodatage = startHorodatage;
    }

    public Timestamp getEndHorodatage() {
        return endHorodatage;
    }

    public void setEndHorodatage(Timestamp endHorodatage) {
        this.endHorodatage = endHorodatage;
    }

    public boolean isAnnuler() {
        return annuler;
    }

    public void setAnnuler(boolean annuler) {
        this.annuler = annuler;
    }

}