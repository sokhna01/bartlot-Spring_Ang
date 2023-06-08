package com.bartlot.Server.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "marche_ppe_variante_2")
public class MarchePPEV2Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "id_client", columnDefinition = "character varrying")
    private String idClient;

    @Column(name = "id_site", columnDefinition = "character varrying")
    private String idSite;

    @Column(name = "id_ppe", columnDefinition = "character varrying")
    private String idPpe;

    @Column(name = "horodatage_debut_periode", columnDefinition = "timestamp without time zone")
    private Timestamp horodatageDebutPeriode;

    @Column(name = "data_p", columnDefinition = "real")
    private Double dataP;

    @Column(name = "data_c", columnDefinition = "real")
    private Double dataC;
}
