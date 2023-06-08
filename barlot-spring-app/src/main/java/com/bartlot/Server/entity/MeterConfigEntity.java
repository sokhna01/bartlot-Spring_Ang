package com.bartlot.Server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
    private Boolean modifier_la_convention_de_signe;

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

    @Column(name = "id_comptuer_principal")
    private String idCompteurPrincipal;

    @Column(name = "id_comptuer_redondant")
    private String idCompteurRedondant;

    @Column(name = "type", nullable = false)
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getIdSite() {
        return idSite;
    }

    public void setIdSite(String idSite) {
        this.idSite = idSite;
    }

    public String getPointComptageId() {
        return pointComptageId;
    }

    public void setPointComptageId(String pointComptageId) {
        this.pointComptageId = pointComptageId;
    }

    public String getIdCompteurPr() {
        return idCompteurPr;
    }

    public void setIdCompteurPr(String idCompteurPr) {
        this.idCompteurPrincipal = idCompteurPr;
    }

    public String getIdCompteurRed() {
        return idCompteurRedondant;
    }

    public void setIdCompteurRed(String idCompteurRe) {
        this.idCompteurRedondant = idCompteurRe;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getModifierLaConventionDeSigne() {
        return modifier_la_convention_de_signe;
    }

    public void setModifierLaConventionDeSigne(Boolean modifier_la_convention_de_signe) {
        this.modifier_la_convention_de_signe = modifier_la_convention_de_signe;
    }

    public boolean ModifierLaConventionDeSigne() {
        return this.modifier_la_convention_de_signe;
    }

    public Double getPuissanceNominal() {
        return puissanceNominal;
    }

    public void setPuissanceNominal(Double puissanceNominal) {
        this.puissanceNominal = puissanceNominal;
    }

    public Double getCourantNominalInPr() {
        return courantNominalInPr;
    }

    public void setCourantNominalInPr(Double courantNominalInPr) {
        this.courantNominalInPr = courantNominalInPr;
    }

    public Double getTensionNominalVnPrT() {
        return tensionNominalVnPrT;
    }

    public void setTensionNominalVnPrT(Double tensionNominalVnPrT) {
        this.tensionNominalVnPrT = tensionNominalVnPrT;
    }

    public Double getPhiInjection() {
        return phiInjection;
    }

    public void setPhiInjection(Double phiInjection) {
        this.phiInjection = phiInjection;
    }

    public Double getPhiSoutirage() {
        return phiSoutirage;
    }

    public void setPhiSoutirage(Double phiSoutirage) {
        this.phiSoutirage = phiSoutirage;
    }

    public Double getAlarmeSeuilAPlus() {
        return alarmeSeuilAPlus;
    }

    public void setAlarmeSeuilAPlus(Double alarmeSeuilAPlus) {
        this.alarmeSeuilAPlus = alarmeSeuilAPlus;
    }

    public Double getAlarmeSeuilAMoins() {
        return alarmeSeuilAMoins;
    }

    public void setAlarmeSeuilAMoins(Double alarmeSeuilAMoins) {
        this.alarmeSeuilAMoins = alarmeSeuilAMoins;
    }

    public Double getAlarmeSeuilRPlus() {
        return alarmeSeuilRPlus;
    }

    public void setAlarmeSeuilRPlus(Double alarmeSeuilRPlus) {
        this.alarmeSeuilRPlus = alarmeSeuilRPlus;
    }

    public Double getAlarmeSeuilRMoins() {
        return alarmeSeuilRMoins;
    }

    public void setAlarmeSeuilRMoins(Double alarmeSeuilRMoins) {
        this.alarmeSeuilRMoins = alarmeSeuilRMoins;
    }

    public Double getAlarmeSeuiTgPhi() {
        return alarmeSeuiTgPhi;
    }

    public void setAlarmeSeuiTgPhi(Double alarmeSeuiTgPhi) {
        this.alarmeSeuiTgPhi = alarmeSeuiTgPhi;
    }
}
