package com.bartlot.Server.entity;

import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "brute_acquisition")

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