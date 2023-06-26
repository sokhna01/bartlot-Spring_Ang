package com.bartlot.Server.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "rapport_point_De_Comptage")
public class RapportPointDeComptageEntity extends BaseEntity {

    @Column(name = "id_point_de_comptage")
    private String idPointDeComptage;
    @Column(name = "created_at")
    private LocalDate createdDate;
    @Column(name = "valeurs_du_jour_absentes_compt_redondant")
    private boolean valeursDuJourAbsentesComptRedundant;
    @Column(name = "valeurs_du_jour_absentes_compt_principal")
    private boolean valeursDuJourAbsentesComptPrincipal;
    @Column(name = "valeurs_du_jour_absentes_point_comptage")
    private boolean valeursDuJourAbsentesPointComptage;
    @Column(name = "nombre_de_jours_de_la_periode_en_cours_avec_valeurs_absentes_compt_redondant")
    private int nombreDeJoursDeLaPeriodeEnCoursAvecValeursAbsentesComptRedundant;
    @Column(name = "nombre_de_jours_de_la_periode_en_cours_avec_valeurs_absentes_compt_principal")
    private int nombreDeJoursDeLaPeriodeEnCoursAvecValeursAbsentesComptPrincipal;
    @Column(name = "nombre_de_jours_de_la_periode_en_cours_avec_valeurs_absentes_point_comptage")
    private int nombreDeJoursDeLaPeriodeEnCoursAvecValeursAbsentesPointComptage;
    @Column(name = "nombre_de_jours_avec_valeurs_absentes_compt_redondant")
    private int nombreDeJoursAvecValeursAbsentesComptRedundant;
    @Column(name = "nombre_de_jours_avec_valeurs_absentes_compt_principal")
    private int nombreDeJoursAvecValeursAbsentesComptPrincipal;
    @Column(name = "nombre_de_jours_avec_valeurs_absentes_point_comptage")
    private int nombreDeJoursAvecValeursAbsentesPointComptage;
    @Column(name = "periodes_avec_intervention_compt_redondant")
    private int periodesAvecInterventionComptRedundant;
    @Column(name = "periodes_avec_intervention_compt_principal")
    private int periodesAvecInterventionComptPrincipal;
    @Column(name = "periodes_avec_intervention_point_comptage")
    private int periodesAvecInterventionPointComptage;
    @Column(name = "periodes_avec_valeurs_abesentes_inf_ou_egale_a_une_heure_compt_redondant")
    private int periodesAvecValeursAbesentesInfOuEgaleAUneHeureComptRedundant;
    @Column(name = "periodes_avec_valeurs_abesentes_inf_ou_egale_a_une_heure_compt_principal")
    private int periodesAvecValeursAbesentesInfOuEgaleAUneHeureComptPrincipal;
    @Column(name = "periodes_avec_valeurs_abesentes_inf_ou_egale_a_une_heure_point_comptage")
    private int periodesAvecValeursAbesentesInfOuEgaleAUneHeurePointComptage;
    @Column(name = "periodes_avec_valeurs_abesentes_sup_a_une_heure_compt_redondant")
    private int periodesAvecValeursAbsentesSupAUneHeureComptRedundant;
    @Column(name = "periodes_avec_valeurs_abesentes_sup_a_une_heure_compt_principal")
    private int periodesAvecValeursAbsentesSupAUneHeureComptPrincipal;
    @Column(name = "periodes_avec_valeurs_abesentes_sup_a_une_heure_point_comptage")
    private int periodesAvecValeursAbsentesSupAUneHeurePointComptage;
    @Column(name = "periodes_avec_valeurs_nulles_inf_ou_egale_a_une_heure_compt_redondant")
    private int periodesAvecValeursNullesInfOuEgaleAUneHeureComptRedundant;
    @Column(name = "periodes_avec_valeurs_nulles_inf_ou_egale_a_une_heure_compt_principal")
    private int periodesAvecValeursNullesInfOuEgaleAUneHeureComptPrincipal;
    @Column(name = "periodes_avec_valeurs_nulles_inf_ou_egale_a_une_heure_point_comptage")
    private int periodesAvecValeursNullesInfOuEgaleAUneHeurePointComptage;
    @Column(name = "periodes_avec_valeurs_nulles_sup_a_une_heure_compt_redondant")
    private int periodesAvecValeursNullesSupAUneHeureComptRedundant;
    @Column(name = "periodes_avec_valeurs_nulles_sup_a_une_heure_compt_principal")
    private int periodesAvecValeursNullesSupAUneHeureComptPrincipal;
    @Column(name = "periodes_avec_valeurs_nulles_sup_a_une_heure_point_comptage")
    private int periodesAvecValeursNullesSupAUneHeurePointComptage;
    @Column(name = "resultat_du_calcul_de_redondance")
    private double resultatDuCalculDeRedondance;
    @Column(name = "periodes_avec_donnees_issues_du_cpt_redondant_jourj")
    private int periodesAvecDonneesIssuesDuCptRedondantJourj;
    @Column(name = "periodes_avec_donnees_issues_du_cpt_redondant_mensuel")
    private int periodesAvecDonneesIssuesDuCptRedondantMensuel;
    @Column(name = "periodes_avec_donnees_issues_de_se_jourj")
    private int periodesAvecDonneesIssuesDeSeJourj;
    @Column(name = "periodes_avec_donnees_issues_de_se_jour_mensuel")
    private int periodesAvecDonneesIssuesDeSeMensuel;
    @Column(name = "periodes_avec_donnees_issues_de_ev_jourj")
    private int periodesAvecDonneesIssuesDeEvJourj;
    @Column(name = "periodes_avec_donnees_issues_de_ev_mensuel")
    private int periodesAvecDonneesIssuesDeEvMensuel;
    @Column(name = "donnees_restant_a_valider_jourj")
    private int donneesRestantAValiderJourj;
    @Column(name = "donnees_restant_a_valider_mensuel")
    private int donneesRestantAValiderMensuel;
    @Column(name = "energie_journaliere_a_plus")
    private double energieJournaliereAPlus;
    @Column(name = "energie_journaliere_a_moins")
    private double energieJournaliereAMoins;
    @Column(name = "energie_journaliere_r_plus")
    private double energieJournaliereRPlus;
    @Column(name = "energie_journaliere_r_moins")
    private double energieJournaliereRMoins;
    @Column(name = "energie_mensuelle_a_plus")
    private double energieMensuelleAPlus;
    @Column(name = "energie_mensuelle_a_moins")
    private double energieMensuelleAMoins;
    @Column(name = "energie_mensuelle_r_plus")
    private double energieMensuelleRPlus;
    @Column(name = "energie_mensuelle_r_moins")
    private double energieMensuelleRMoins;
    @Column(name = "alarme_seuil_p_plus_franchi")
    private boolean alarmeSeuilPPlusFranchi;
    @Column(name = "alarme_seuil_p_moins_franchi")
    private boolean alarmeSeuilPMoinsFranchi;
    @Column(name = "alarme_seuil_r_plus_franchi")
    private boolean alarmeSeuilRPlusFranchi;
    @Column(name = "alarme_seuil_r_moins_franchi")
    private boolean alarmeSeuilRMoinsFranchi;
    @Column(name = "alarme_seuil_data_a_plus_sur_data_r_plus_franchi")
    private boolean alarmeSeuilDataRPlusSurDataAPlusFranchi;
    @Column(name = "action_de_traitement_attendue")
    private boolean actionDeTraitementAttendue;

}
