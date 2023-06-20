package com.bartlot.Server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "table_configuration_points_de_compteur")

public class Table_configuration_points_de_compteurEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "idcompany")
    private Integer idCompany;

    @Column(name = "id_client")
    private String id_client;

    @Column(name = "id_site")
    private String id_site;

    @Column(name = "id_point_de_comptage")
    private String id_point_de_comptage;

    // CONVENSION : bOOLEAN

    // @Column(name = "convention")
    // private String convention;

    @Column(name = "convention_signe")
    private String convention_signe;

    @Column(name = "id_compteur_principal")
    private String id_compteur_principal;

    @Column(name = "id_compteur_redondance")
    private String id_compteur_redondance;

    @Column(name = "tension_nominale_vn_primaire")
    private Float tension_nominale_vn_primaire;

    @Column(name = "courant_nominale_in_primaire")
    private Float courant_nominale_in_primaire;

    @Column(name = "puissance_nominale_pn_du_point_de_comptage")
    private Float puissance_nominale_pn_du_point_de_comptage;

    @Column(name = "b_injection")
    private Float b_injection;

    @Column(name = "b_soutirage")
    private Float b_soutirage;

    @Column(name = "alarme_seul_a_plus")
    private Float alarme_seul_a_plus;

    @Column(name = "alarme_seul_a_moins")
    private Float alarme_seul_a_moins;

    @Column(name = "alarme_seul_r_plus")
    private Float alarme_seul_r_plus;

    @Column(name = "alarme_seul_r_moins")
    private Float alarme_seul_r_moins;

    @Column(name = "alarme_seul_r_tg_mois")
    private Float alarme_seul_r_tg_mois;

    @Column(name = "annuler", nullable = false, columnDefinition = "boolean default false")
    private boolean annuler;

}
