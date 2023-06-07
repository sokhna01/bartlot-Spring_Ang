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
@Table(name = "configuration_points_de_comptage")

public class MeterConfigEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "idclient", columnDefinition = "character varying")
    private String idClient;

    @Column(name = "idsite", columnDefinition = "character varying")
    private String idSite;

    @Column(name = "idpointcomptage", columnDefinition = "character varying")
    private String pointComptageId;

    @Column(name = " puissance_nominale_pn_du_point_de_comptage", columnDefinition = "real")
    private Double puissanceNominal;

    @Column(name = " courant_nominal_in_primaire", columnDefinition = "real")
    private Double courantNominalInPr;

    @Column(name = " tension_nominale_vn_primaire_tt", columnDefinition = "real")
    private Double tensionNominalVnPrT;

    @Column(name = "modifier_la_convention_de_signe", columnDefinition = "boolean default false")
    private Boolean modifierLaConventionDeSigne;

    @Column(name = "id_compteur_principal", columnDefinition = "character varying")
    private String idCompteurPr;

    @Column(name = "id_compteur_redondant", columnDefinition = "character varying")
    private String idCompteurRe;

    @Column(name = " phi_injection", columnDefinition = "real")
    private Double phiInjection;

    @Column(name = " phi_soutirage", columnDefinition = "real")
    private Double phiSoutirage;

    @Column(name = " alarme_seuil_a_plus", columnDefinition = "real")
    private Double alarmeSeuilAPlus;

    @Column(name = " alarme_seuil_a_moins", columnDefinition = "real")
    private Double alarmeSeuilAMoins;

    @Column(name = " alarme_seuil_r_plus", columnDefinition = "real")
    private Double alarmeSeuilRPlus;

    @Column(name = " alarme_seuil_r_moins", columnDefinition = "real")
    private Double alarmeSeuilRMoins;

    @Column(name = " alarme_seuil_tg_phi", columnDefinition = "real")
    private Double alarmeSeuiTgPhi;

}