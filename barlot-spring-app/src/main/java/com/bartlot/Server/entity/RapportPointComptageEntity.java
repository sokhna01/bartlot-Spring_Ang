// package com.bartlot.Server.entity;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;

// @Entity
// @Table(name = "rapport_point_de_comptage")
// public class RapportPointComptageEntity {
// @Id
// @GeneratedValue(strategy = GenerationType.IDENTITY)
// @Column(name = "id", nullable = false)
// private Integer id;

// @Column(name = "valeur_du_jour_absentes")
// private Boolean[][] valeurDuJourAbsentes;// must_review

// @Column(name = "nb_de_jours_avec_valeurs_absentes", columnDefinition =
// "integer[]")
// private Integer[] nbDeJoursAvecValeursAbsentes;// must_review

// @Column(name = "periodes_avec_intervention", columnDefinition =
// "integer[][]")
// private Integer[][] periodesAvecIntervention;// must_review

// @Column(name = "periodes_avec_valeurs_absentes_<_=_1_heure", columnDefinition
// = "integer[][]")
// private Integer[][] periodesAvecValeursAbsentesInfA1Heure;// must_review

// @Column(name = "periodes_avec_valeurs_absentes_>_=_1_heure", columnDefinition
// = "integer[][]")
// private Integer[][] periodesAvecValeursAbsentesSupA1Heure;// must_review

// @Column(name = "periodes_avec_valeurs_nulles_<_=_1_heure", columnDefinition =
// "integer[][]")
// private Integer[][] periodesAvecValeursNullesInfA1Heure;// must_review

// @Column(name = "periodes_avec_valeurs_nulles_>_=_1_heure", columnDefinition =
// "integer[][]")
// private Integer[][] periodesAvecValeursNullesSupA1Heure;// must_review

// @Column(name = "resultat_du_calcul_de_redondance", columnDefinition =
// "integer[]")
// private Integer[] resultatDuCalculDeRedondance; // must_review

// @Column(name = "periodes_avec_donnees_issues_cpt_re", columnDefinition =
// "integer")
// private Integer periodesAvecDonneesIssuesCptRe;

// @Column(name = "periodes_avec_donnees_issues_se", columnDefinition =
// "integer")
// private Integer periodesAvecDonneesIssuesSe;

// @Column(name = "periodes_avec_donnees_issues_ev", columnDefinition =
// "integer")
// private Integer periodesAvecDonneesIssuesEv;

// @Column(name = "donnees_restant_a_valider", columnDefinition = "integer")
// private Integer donneesRestantAValider;

// @Column(name = "energie_journaliere_a_plus", columnDefinition = "real")
// private Double energieJournaliereAPlus;

// @Column(name = "energie_journaliere_a_moins", columnDefinition = "real")
// private Double energieJournaliereAMoins;

// @Column(name = "energie_journaliere_r_plus", columnDefinition = "real")
// private Double energieJournaliereRPlus;

// @Column(name = "energie_journaliere_r_moins", columnDefinition = "real")
// private Double energieJournaliereRMoins;

// @Column(name = "energie_mensuelle_r_plus", columnDefinition = "real")
// private Double energieMensuelleRPlus;

// @Column(name = "energie_mensuelle_r_moins", columnDefinition = "real")
// private Double energieMensuelleRMoins;

// @Column(name = "alarme_seuil_p_plus_franchi")
// private Boolean alarmeSeuilPPlusFranchi;

// @Column(name = "alarme_seuil_p_moins_franchi")
// private Boolean alarmeSeuilPMoinsFranchi;

// @Column(name = "alarme_seuil_r_plus_franchi")
// private Boolean alarmeSeuilRPlusFranchi;

// @Column(name = "alarme_seuil_r_moins_franchi")
// private Boolean alarmeSeuilRMoinsFranchi;

// @Column(name = "alarme_seuil_tg_phi_=_data_r_plus_/_data_a_plus_franchi")
// private Boolean alarmeSeuilTgPhiEqualsDataRPlusDataAPlusFranchi;

// }
