package com.bartlot.Server.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "travail")

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

    @Column(name = "dataaplus", columnDefinition = "REAL")
    private Double dataAPlus;

    @Column(name = "dataamoins", columnDefinition = "REAL")
    private Double dataAMoins;

    @Column(name = "datarplus", columnDefinition = "REAL")
    private Double dataRPlus;

    @Column(name = "datarmoins", columnDefinition = "REAL")
    private Double dataRMoins;

    @Column(name = "source", length = 2, columnDefinition = "character(2)")
    private String source;

    @Column(name = "presence", columnDefinition = "character(1)")
    private String presence;

    @Column(columnDefinition = "character(1) DEFAULT '5'::bpchar")
    private String qualite;

    @Column(name = "validation")
    private String validation;

    @Column(name = "commentaires")
    private String commentaire;

    @Column(name = "attente_action")
    private String attenteAction;

    @Column(columnDefinition = "character varying(2) DEFAULT '5'")
    private String puissanceReactiveQualite;
}
