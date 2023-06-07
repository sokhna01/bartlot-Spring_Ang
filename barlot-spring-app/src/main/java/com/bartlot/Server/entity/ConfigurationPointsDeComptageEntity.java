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
public class ConfigurationPointsDeComptageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "idclient")
    private String idClient;

    @Column(name = "idsite")
    private String idsite;

    @Column(name = "idpointcomptage")
    private String idPointComptage;

    @Column(name = "puissance_nominale_pn_du_point_de_comptage", columnDefinition = "REAL")
    private Double puissanceNominalePnDuPointDeComptage;

    @Column(name = "courant_nominal_in_primaire", columnDefinition = "REAL")
    private Double courantNominalInPrimaire;

    @Column(name = "tension_nominale_vn_primaire_tt", columnDefinition = "REAL")
    private Double tensionNominalVnPrimaireTt;

    @Column(name = "modifier_la_convention_de_signe", columnDefinition = "boolean default false")
    private boolean modifierLaConventionPointDeSigne;

    @Column(name = "id_comptuer_principal")
    private String idCompteurPrincipal;

    @Column(name = "id_comptuer_redondant")
    private String idCompteurRedondant;

    @Column(name = "phi_injection", columnDefinition = "REAL")
    private Double phiInjection;

    @Column(name = "phi_soutirage", columnDefinition = "REAL")
    private Double phiSoutirage;

    @Column(name = "alarme_seuil_a_plus", columnDefinition = "REAL")
    private Double alarmeSeuilAPlus;

    @Column(name = "alarme_seuil_a_moins", columnDefinition = "REAL")
    private Double alarmeSeuilAMoins;

    @Column(name = "alarme_seuil_r_plus", columnDefinition = "REAL")
    private Double alarmeSeuilRPlus;

    @Column(name = "alarme_seuil_r_moins", columnDefinition = "REAL")
    private Double alarmeSeuilRMoins;

    @Column(name = "alarme_seuil_tg_phi", columnDefinition = "REAL")
    private Double alarmeSeuilTGPhi;

}