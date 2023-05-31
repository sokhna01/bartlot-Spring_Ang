package com.bartlot.Server.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bartlot.Server.entity.MeterDataEntity;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;

public interface MeterDataRepository extends JpaRepository<MeterDataEntity, Integer> {

        @Query("SELECT md.horodatage FROM MeterDataEntity md ORDER BY md.horodatage DESC LIMIT 1")
        Timestamp findLastRecentRowDate();

        @Query("SELECT md FROM MeterDataEntity md WHERE "
                        + "(md.dataAPlus IS NULL OR md.dataAPlus = 0.0 "
                        + "OR md.dataAMoins IS NULL OR md.dataAMoins = 0.0 "
                        + "OR md.dataRPlus IS NULL OR md.dataRPlus = 0.0 "
                        + "OR md.dataRMoins IS NULL OR md.dataRMoins = 0.0) "
                        + "AND md.horodatage BETWEEN :startDate AND :endDate")
        List<MeterDataEntity> findMeterDataBybetweenDate(Date startDate, Date endDate);

        // List<MeterDataEntity> findByHorodatageBetweenOrderByHorodatageAsc(int
        // idCompany, Date startDate,
        // Date endDate);

        List<MeterDataEntity> findByHorodatageBetweenOrderByHorodatageAsc(Date startDate, Date endDate);

        @Modifying
        @Transactional
        @Query("update MeterDataEntity md set md.dataAPlus = :dataAPlus,md.dataAMoins = :dataAMoins,md.dataRPlus = :dataRPlus,md.dataRMoins = :dataRMoins where md.id = :id")
        void updateMissingData(Double dataAPlus, Double dataAMoins, Double dataRPlus, Double dataRMoins, int id);

        @Modifying
        @Transactional
        @Query("update MeterDataEntity md set md.source = :source,md.presence = :presence where md.id = :id")
        void updateSource(String source, String presence, int id);

        @Modifying
        @Transactional
        @Query("update MeterDataEntity md set md.qualite = :qualite where md.id = :id")
        void updateQualite(String qualite, int id);

        @Query("SELECT DISTINCT md.idClient, md.idSite, md.pointComptageId FROM MeterDataEntity md")
        List<Object[]> findAllSiteClientAndPointDeComptage();

        // Long countByIdCompany(int idCompany);

        MeterDataEntity findByIdCompteur(String idCompteur);

        List<MeterDataEntity> findByHorodatage(Timestamp timestamp);

        @Query("SELECT md FROM MeterDataEntity md WHERE md.idCompteur = :idCompteur")
        MeterDataEntity findByCompteurId(@Param("idCompteur") String idCompteur);

        @Query("SELECT md FROM MeterDataEntity md WHERE md.idClient = :idClient")
        MeterDataEntity findByClientId(@Param("idClient") String idClient);

        @Query("SELECT md FROM MeterDataEntity md WHERE md.idCompteur = 'CPT-P'")
        List<MeterDataEntity> findAllByIdCompteur();

        List<MeterDataEntity> findBySourceIsNullAndPresenceIsNullAndQualiteIsNull();

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

        default List<MeterDataEntity> findByHorodatageBetweenOrderByHorodatageAscWithException(Date startDate,
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