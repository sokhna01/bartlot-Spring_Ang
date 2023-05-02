package com.bartlot.Server.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
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

}
