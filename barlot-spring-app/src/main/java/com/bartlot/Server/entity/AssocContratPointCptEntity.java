package com.bartlot.Server.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "association_contrats_points_comptage")
public class AssocContratPointCptEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "type_objet_associe_au_contrat")
    private String typeObjetAssocieAuContrat; // which type//?

    @Column(name = "id_objet", columnDefinition = "real")
    private String idObjet;

    @Column(name = "id_client", columnDefinition = "real")
    private String idClient;

    @Column(name = "id_contrat", columnDefinition = "real")
    private String idContrat;

    @Column(name = "id_point_de_comptage_n*1", columnDefinition = "real")
    private String idPointDeComptageN1;

    @Column(name = "id_point_de_comptage_n_2", columnDefinition = "real")
    private String idPointDeComptageN2;

    @Column(name = "id_point_de_comptage_n_3", columnDefinition = "real")
    private String idPointDeComptageN3;

    @Column(name = "id_point_de_comptage_n_...", columnDefinition = "real")
    private String idPointDeComptageN;

    @Column(name = "id_point_de_comptage_n_100", columnDefinition = "real")
    private String idPointDeComptageN100;
}
