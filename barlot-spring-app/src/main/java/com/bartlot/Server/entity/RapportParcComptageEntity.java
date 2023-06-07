package com.bartlot.Server.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rapport_parc_de_comptage")
public class RapportParcComptageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ElementCollection
    @Column(name = "pdc_toutes_valeurs_du_jour_absentes", columnDefinition = "text")
    private List<String> pdcToutesValeursDuJourAbsentes;

    @ElementCollection
    @Column(name = "pdc_toutes_valeurs_absentes_est_<_=_3_jours_ouvres", columnDefinition = "text")
    private List<String> pdcToutesValeursAbsentesEstInfA3JoursOuvres;

    @ElementCollection
    @Column(name = "pdc_toutes_valeurs_absentes_est_>_=_3_jours_ouvres", columnDefinition = "text")
    private List<String> pdcToutesValeursAbsentesEstSupA3JoursOuvres;

    @ElementCollection
    @Column(name = "pdc_avec_periodes_affectees_par_une_intervention", columnDefinition = "text")
    private List<String> pdcAvecPeriodesAffecteesParUneIntervention;

    @ElementCollection
    @Column(name = "pdc_avec_periodes_valeurs_absentes_<_=_1_heure", columnDefinition = "text")
    private List<String> pdcAvecPeriodesValeursAbsentesInfA1Heure;

    @ElementCollection
    @Column(name = "pdc_avec_periodes_valeurs_absentes_>_1_heure", columnDefinition = "text")
    private List<String> pdcAvecPeriodesValeursAbsentesSupA1Heure;

    @ElementCollection
    @Column(name = "pdc_avec_periodes_valeurs_nulles_<_=_1_heure", columnDefinition = "text")
    private List<String> pdcAvecPeriodesValeursNullesInfA1Heure;

    @ElementCollection
    @Column(name = "pdc_avec_periodes_valeurs_nulles_>_1_heure", columnDefinition = "text")
    private List<String> pdcAvecPeriodesValeursNullesSupA1Heure;

    @ElementCollection
    @Column(name = "periodes_avec_donnees_issues_cpt_re", columnDefinition = "text")
    private List<String> periodesAvecDonneesIssuesCptRe;

    @ElementCollection
    @Column(name = "periodes_avec_donnees_issues_cpt_se", columnDefinition = "text")
    private List<String> periodesAvecDonneesIssuesCptSe;

    @ElementCollection
    @Column(name = "periodes_avec_donnees_issues_cpt_ev", columnDefinition = "text")
    private List<String> periodesAvecDonneesIssuesCptEv;

    @ElementCollection
    @Column(name = "donnees_restant_a_valider", columnDefinition = "text")
    private List<String> donneesRestantAValider;

}
