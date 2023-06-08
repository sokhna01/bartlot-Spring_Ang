package com.bartlot.Server.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bartlot.Server.entity.BruteAcquisitionEntity;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;

public interface BruteAcquisitionRepository extends JpaRepository<BruteAcquisitionEntity, Integer> {

    @Query("SELECT ba.horodatage FROM BruteAcquisitionEntity ba ORDER BY ba.horodatage DESC LIMIT 1")
    Timestamp findLastRecentRowDate();

    @Query("SELECT ba FROM BruteAcquisitionEntity ba WHERE "
            + "(ba.dataAPlus IS NULL OR ba.dataAPlus = 0.0 "
            + "OR ba.dataAMoins IS NULL OR ba.dataAMoins = 0.0 "
            + "OR ba.dataRPlus IS NULL OR ba.dataRPlus = 0.0 "
            + "OR ba.dataRMoins IS NULL OR ba.dataRMoins = 0.0) "
            + "AND ba.horodatage BETWEEN :startDate AND :endDate")
    List<BruteAcquisitionEntity> findMeterDataBybetweenDate(Date startDate, Date endDate);

    // List<MeterDataEntity> findByHorodatageBetweenOrderByHorodatageAsc(int
    // idCompany, Date startDate,
    // Date endDate);

    List<BruteAcquisitionEntity> findByHorodatageBetweenOrderByHorodatageAsc(Date startDate, Date endDate);

    @Modifying
    @Transactional
    @Query("update BruteAcquisitionEntity ba set ba.dataAPlus = :dataAPlus,ba.dataAMoins = :dataAMoins,ba.dataRPlus = :dataRPlus,ba.dataRMoins = :dataRMoins where ba.id = :id")
    void updateMissingData(Double dataAPlus, Double dataAMoins, Double dataRPlus, Double dataRMoins, int id);

    @Modifying
    @Transactional
    @Query("update BruteAcquisitionEntity ba set ba.source = :source,ba.presence = :presence where ba.id = :id")
    void updateSource(String source, String presence, int id);

    @Modifying
    @Transactional
    @Query("update BruteAcquisitionEntity ba set ba.qualite = :qualite where ba.id = :id")
    void updateQualite(String qualite, int id);

    @Query("SELECT DISTINCT ba.idClient, ba.idSite, ba.pointComptageId FROM BruteAcquisitionEntity ba")
    List<Object[]> findAllSiteClientAndPointDeComptage();

    // Long countByIdCompany(int idCompany);

    BruteAcquisitionEntity findByIdCompteur(String idCompteur);

    List<BruteAcquisitionEntity> findByHorodatage(Timestamp timestamp);

    @Query("SELECT ba FROM BruteAcquisitionEntity ba WHERE ba.idCompteur = :idCompteur")
    BruteAcquisitionEntity findByCompteurId(@Param("idCompteur") String idCompteur);

    @Query("SELECT ba FROM BruteAcquisitionEntity ba WHERE ba.idClient = :idClient")
    BruteAcquisitionEntity findByClientId(@Param("idClient") String idClient);

    @Query("SELECT ba FROM BruteAcquisitionEntity ba WHERE ba.idCompteur = 'CPT-P'")
    List<BruteAcquisitionEntity> findAllByIdCompteur();

    List<BruteAcquisitionEntity> findBySourceIsNullAndPresenceIsNullAndQualiteIsNull();

    /* **************************************************************** */
    /* *******************METHODE avec try-catch********************** */
    /* ************************************************************** */

    default Timestamp findLastRecentRowDateWithException() {
        try {
            return findLastRecentRowDate();
        } catch (Exception e) {
            // Gestion de l'exception
            // Vous pouvez afficher un message d'erreur, enregistrer des journaux, ou
            // prendre toute autre action appropriée
            return null; // Ou lancez une nouvelle exception si nécessaire
        }
    }

    default List<BruteAcquisitionEntity> findByHorodatageBetweenOrderByHorodatageAscWithException(Date startDate,
            Date endDate) {
        try {
            return findByHorodatageBetweenOrderByHorodatageAsc(startDate, endDate);
        } catch (Exception e) {
            // Gestion de l'exception
            // Vous pouvez afficher un message d'erreur, enregistrer des journaux, ou
            // prendre toute autre action appropriée
            return null; // Ou lancez une nouvelle exception si nécessaire
        }
    }

}