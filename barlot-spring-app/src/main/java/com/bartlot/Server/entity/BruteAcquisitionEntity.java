package com.bartlot.Server.entity;

import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
<<<<<<< HEAD

=======
import lombok.Data;

@Data
>>>>>>> c93e7d78809e2e9ea0e59131a559f3cea844086f
@Entity
@Table(name = "brute_acquisition")

public class BruteAcquisitionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
<<<<<<< HEAD

    @Column(name = "idclient", columnDefinition = "character varying")
    private String idClient;

    @Column(name = "idsite", columnDefinition = "character varying")
    private String idSite;

    @Column(name = "idpointcomptage", columnDefinition = "character varying")
    private String pointComptageId;

    @Column(name = "idcompteur", columnDefinition = "character varying")
=======
    // JPA maps to the SERIAL data type in PostgreSQL automatically, so we don't
    // need to specify it explicitly.

    @Column(name = "idclient")
    private String idClient;

    @Column(name = "idsite")
    private String idSite;

    @Column(name = "idpointcomptage")
    private String pointComptageId;

    @Column(name = "idcompteur")
>>>>>>> c93e7d78809e2e9ea0e59131a559f3cea844086f
    private String idCompteur;

    @Column(name = "horodatage", columnDefinition = "timestamp without time zone")
    private Timestamp horodatage;

<<<<<<< HEAD
    @Column(name = "dataaplus", columnDefinition = "real")
    private Double dataAPlus;

    @Column(name = "dataamoins", columnDefinition = "real")
    private Double dataAMoins;

    @Column(name = "datarplus", columnDefinition = "real")
    private Double dataRPlus;

    @Column(name = "datarmoins", columnDefinition = "real")
    private Double dataRMoins;

    @Column(name = "source", columnDefinition = "character(2)")
    private String source;

    @Column(name = "presence", columnDefinition = "character(1)")
    private String presence;

    @Column(name = "qualite", columnDefinition = "character(1) default '5'::bpchar")
    private String qualite;

    @Column(name = "puissance_reactive_qualite", columnDefinition = "character varying(2)  default '5'::character varying")
    private String puissanceReactiveQualite = "5";

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
=======
    @Column(name = "dataaplus", columnDefinition = "REAL")
    private Double dataAPlus;

    @Column(name = "dataamoins", columnDefinition = "REAL")
    private Double dataAMoins;

    @Column(name = "datarplus", columnDefinition = "REAL")
    private Double dataRPlus;

    @Column(name = "datarmoins", columnDefinition = "REAL")
    private Double dataRMoins;

    @Column(name = "idcompany")
    private Integer idCompany;

    @Column(name = "source", length = 2, columnDefinition = "character(2)")
    private String source;

    @Column(name = "presence", length = 1, columnDefinition = "character(1)")
    private String presence;

    @Column(columnDefinition = "character(1) DEFAULT '5'::bpchar")
    private String qualite;

    @Column(name = "puissance_reactive_qualite", length = 2, columnDefinition = "character(2) default '5'")
    private String puissanceReactiveQualite = "5";
}
>>>>>>> c93e7d78809e2e9ea0e59131a559f3cea844086f
