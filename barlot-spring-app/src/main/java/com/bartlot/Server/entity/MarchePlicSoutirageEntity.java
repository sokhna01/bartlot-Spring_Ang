package com.bartlot.Server.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "marche_plic_soutirage")
public class MarchePlicSoutirageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "id_client", columnDefinition = "character varying")
    private String idClient;

    @Column(name = "id_site", columnDefinition = "character varying")
    private String idSite;

    @Column(name = "id_plic", columnDefinition = "character varying")
    private String idPlic;

    @Column(name = "horodatage_debut_periode", columnDefinition = "timestamp without time zone")
    private Timestamp horodatageDebutPeriode;

    @Column(name = "data_a", columnDefinition = "real")
    private Double dataA;

    @Column(name = "data_r", columnDefinition = "real")
    private Double dataR;
}
