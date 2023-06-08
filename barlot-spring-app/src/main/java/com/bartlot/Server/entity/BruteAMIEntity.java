package com.bartlot.Server.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "brute_ami")
public class BruteAMIEntity {
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

    @Column(name = "id_compteur", columnDefinition = "character varrying")
    private String idCompteur;

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

}
