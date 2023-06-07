package com.bartlot.Server.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "marche_10")
public class Marche10Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "id_client", columnDefinition = "character varrying")
    private String idClient;

    @Column(name = "id_site", columnDefinition = "character varrying")
    private String idSite;

    @Column(name = "id_point_de_comptage", columnDefinition = "character varrying")
    private String pointComptageId;

    @Column(name = "horodatage_debut_periode", columnDefinition = "timestamp without time zone")
    private Timestamp horodatageDebutPeriode;

    @Column(name = "data_a_plus", columnDefinition = "real")
    private Double dataAPlus;

    @Column(name = "data_a_moins", columnDefinition = "real")
    private Double dataAMoins;

    @Column(name = "data_r_plus", columnDefinition = "real")
    private Double dataRPlus;

    @Column(name = "data_r_moins", columnDefinition = "real")
    private Double dataRMoins;

    @Column(name = "source", columnDefinition = "character(2)")
    private String source;

    @Column(name = "presence", columnDefinition = "character(1)")
    private String presence;

    @Column(name = "qualite", columnDefinition = "character(1)")
    private String qualite;

    @Column(name = "datacopertes_a_plus", columnDefinition = "real")
    private Double dataCopertesAPlus;

    @Column(name = "datacopertes_a_moins", columnDefinition = "real")
    private Double dataCopertesAMoins;

}
