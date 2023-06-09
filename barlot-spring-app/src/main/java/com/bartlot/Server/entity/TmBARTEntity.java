// package com.bartlot.Server.entity;

// import java.sql.Timestamp;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;

// @Entity
// @Table(name = "tm_bart")
// public class TmBARTEntity {
// @Id
// @GeneratedValue(strategy = GenerationType.IDENTITY)
// @Column(name = "id", nullable = false)
// private Integer id;

// @Column(name = "id_poste", columnDefinition = "character varying")
// private String idPoste;

// @Column(name = "id_tm", columnDefinition = "character varying")
// private String idTm;

// @Column(name = "horodatage_debut_periode", columnDefinition = "timestamp
// without time zone")
// private Timestamp horodatageDebutPeriode;

// @Column(name = "data_a_plus", columnDefinition = "real")
// private Double dataAPlus;

// @Column(name = "data_a_moins", columnDefinition = "real")
// private Double dataAMoins;

// @Column(name = "data_r_plus", columnDefinition = "real")
// private Double dataRPlus;

// @Column(name = "data_r_moins", columnDefinition = "real")
// private Double dataRMoins;

// }