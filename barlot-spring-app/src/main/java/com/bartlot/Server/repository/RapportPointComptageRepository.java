package com.bartlot.Server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bartlot.Server.entity.RapportPointDeComptageEntity;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface RapportPointComptageRepository extends JpaRepository<RapportPointDeComptageEntity, Long> {

    RapportPointDeComptageEntity findByCreatedDate(LocalDate createdDate);

    Set<RapportPointDeComptageEntity> findAllByPeriodesAvecDonneesIssuesDuCptRedondantJourjIsGreaterThan(
            int periodesAvecDonneesIssuesDuCptRedondantJourj);

    Set<RapportPointDeComptageEntity> findAllByPeriodesAvecDonneesIssuesDeEvJourjIsGreaterThan(
            int periodesAvecDonneesIssuesDeEvJourj);

    Set<RapportPointDeComptageEntity> findAllByPeriodesAvecDonneesIssuesDeSeJourjIsGreaterThan(
            int periodesAvecDonneesIssuesDeSeJourj);

    Set<RapportPointDeComptageEntity> findAllByDonneesRestantAValiderJourjIsGreaterThan(
            int donneesRestantAValiderJourj);

    Set<RapportPointDeComptageEntity> findAllByValeursDuJourAbsentesComptPrincipal(
            boolean valeursDuJourAbsentesComptPrincipal);

    Set<RapportPointDeComptageEntity> findAllByValeursDuJourAbsentesComptRedundant(
            boolean valeursDuJourAbsentesComptRedundant);

    Set<RapportPointDeComptageEntity> findAllByNombreDeJoursAvecValeursAbsentesComptPrincipal(
            int nombreDeJoursAvecValeursAbsentesComptPrincipal);

    Set<RapportPointDeComptageEntity> findAllByNombreDeJoursAvecValeursAbsentesComptRedundant(
            int nombreDeJoursAvecValeursAbsentesComptRedundant);

    Set<RapportPointDeComptageEntity> findAllByValeursDuJourAbsentesPointComptage(boolean isValeurAbsent);

    Set<RapportPointDeComptageEntity> findByNombreDeJoursAvecValeursAbsentesPointComptageGreaterThanEqual(int nbJour);

    Set<RapportPointDeComptageEntity> findByNombreDeJoursAvecValeursAbsentesPointComptageGreaterThan(int nbJour);

    Set<RapportPointDeComptageEntity> findAllByPeriodesAvecInterventionPointComptageIsGreaterThan(int nbPeriodes);

    Set<RapportPointDeComptageEntity> findAllByPeriodesAvecValeursNullesInfOuEgaleAUneHeurePointComptageIsGreaterThan(
            int nbPeriodes);

    Set<RapportPointDeComptageEntity> findAllByPeriodesAvecValeursAbsentesSupAUneHeurePointComptageIsGreaterThan(
            int nbPeriodes);

    Set<RapportPointDeComptageEntity> findAllByPeriodesAvecValeursNullesSupAUneHeurePointComptageIsGreaterThan(
            int nbPeriodes);

    Set<RapportPointDeComptageEntity> findAllByPeriodesAvecValeursAbesentesInfOuEgaleAUneHeurePointComptageIsGreaterThan(
            int nbPeriodes);

    Set<RapportPointDeComptageEntity> findAllByResultatDuCalculDeRedondanceIsNull();

    Set<RapportPointDeComptageEntity> findAllByResultatDuCalculDeRedondanceIsAfterAndResultatDuCalculDeRedondanceIsBefore(
            double resultatDuCalculDeRedondance, double resultatDuCalculDeRedondance2);

    Set<RapportPointDeComptageEntity> findAllByResultatDuCalculDeRedondanceIsGreaterThan(
            double resultatDuCalculDeRedondance);
}
