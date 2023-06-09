package com.bartlot.Server.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "marche_30")
public class Marche30Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "id_client", columnDefinition = "character varying")
    private String idClient;

    @Column(name = "id_site", columnDefinition = "character varying")
    private String idSite;

    @Column(name = "id_point_de_comptage", columnDefinition = "character varying")
    private String pointComptageId;

    @Column(name = "horodatage_debut_periode", columnDefinition = "timestamp without time zone")
    private Timestamp horodatageDebutPeriode;

    @Column(name = "datacopertes_a_plus", columnDefinition = "real")
    private Double dataCopertesAPlus;

    @Column(name = "datacopertes_a_moins", columnDefinition = "real")
    private Double dataCopertesAMoins;

    @Column(name = "data_r_plus", columnDefinition = "real")
    private Double dataRPlus;

    @Column(name = "data_r_moins", columnDefinition = "real")
    private Double dataRMoins;

}
