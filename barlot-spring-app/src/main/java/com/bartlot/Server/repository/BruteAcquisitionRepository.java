package com.bartlot.Server.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bartlot.Server.entity.BruteAcquisitionEntity;
import com.bartlot.Server.model.ReturnObject;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;

public interface BruteAcquisitionRepository extends JpaRepository<BruteAcquisitionEntity, Integer> {

    String returnCodeBase = "Server error: 01";

    @Query("SELECT md.horodatage FROM BruteAcquisitionEntity md ORDER BY md.horodatage DESC LIMIT 1")
    Timestamp findLastRecentRowDate();

    @Query("SELECT md FROM BruteAcquisitionEntity md WHERE "
            + "(md.dataAPlus IS NULL OR md.dataAPlus = 0.0 "
            + "OR md.dataAMoins IS NULL OR md.dataAMoins = 0.0 "
            + "OR md.dataRPlus IS NULL OR md.dataRPlus = 0.0 "
            + "OR md.dataRMoins IS NULL OR md.dataRMoins = 0.0) "
            + "AND md.horodatage BETWEEN :startDate AND :endDate")
    List<BruteAcquisitionEntity> findMeterDataBybetweenDate(Date startDate, Date endDate);

    List<BruteAcquisitionEntity> findByHorodatageBetweenOrderByHorodatageAsc(Date startDate, Date endDate);

    @Modifying
    @Transactional
    @Query("update BruteAcquisitionEntity md set md.dataAPlus = :dataAPlus,md.dataAMoins = :dataAMoins,md.dataRPlus = :dataRPlus,md.dataRMoins = :dataRMoins where md.id = :id")
    void updateMissingData(Double dataAPlus, Double dataAMoins, Double dataRPlus, Double dataRMoins, int id);

    @Modifying
    @Transactional
    @Query("update BruteAcquisitionEntity md set md.source = :source,md.presence = :presence where md.id = :id")
    void updateSource(String source, String presence, int id);

    @Modifying
    @Transactional
    @Query("update BruteAcquisitionEntity md set md.qualite = :qualite where md.id = :id")
    void updateQualite(String qualite, int id);

    @Query("SELECT DISTINCT md.idClient, md.idSite, md.pointComptageId FROM BruteAcquisitionEntity md WHERE md.idClient IS NOT NULL AND md.idClient <> '' AND md.idSite IS NOT NULL AND md.idSite <> '' AND md.pointComptageId IS NOT NULL AND md.pointComptageId <> ''")
    List<Object[]> findAllSiteClientAndPointDeComptage();

    BruteAcquisitionEntity findByIdCompteur(String idCompteur);

    @Query("SELECT md FROM BruteAcquisitionEntity md WHERE md.idCompteur = 'CPT-P'")
    List<BruteAcquisitionEntity> findAllByIdCompteur();

    List<BruteAcquisitionEntity> findBySourceIsNullAndPresenceIsNullAndQualiteIsNull();

    /* **************************************************************** */
    /* *******************METHODE AVEC TRY-CATCH********************** */
    /* ************************************************************** */

    default ReturnObject findLastRecentRowDateWithException() {

        try {
            Timestamp horodatage = findLastRecentRowDate();
            ReturnObject returnObject = new ReturnObject();
            returnObject.setObject(horodatage);
            returnObject.setStatus("ok");
            return returnObject;
        } catch (Exception e) {
            ReturnObject returnObject = new ReturnObject();
            returnObject.setObject(null);
            returnObject.setStatus(returnCodeBase + "01");
            return returnObject;
        }
    }

    default ReturnObject findMeterDataBybetweenDateWithException(Date startDate, Date endDate) {
        try {
            ReturnObject returnObject = new ReturnObject();
            returnObject.setObject(findMeterDataBybetweenDate(startDate, endDate));
            returnObject.setStatus("ok");
            return returnObject;
        } catch (Exception e) {
            ReturnObject returnObject = new ReturnObject();
            returnObject.setObject(null);
            returnObject.setStatus(returnCodeBase + "02");
            return returnObject;
        }
    }

    default ReturnObject findByHorodatageBetweenOrderByHorodatageAscWithException(Date startDate,
            Date endDate) {
        try {
            ReturnObject returnObject = new ReturnObject();
            returnObject.setObject(findByHorodatageBetweenOrderByHorodatageAsc(startDate, endDate));
            returnObject.setStatus("ok");
            return returnObject;
        } catch (Exception e) {
            ReturnObject returnObject = new ReturnObject();
            returnObject.setObject(null);
            returnObject.setStatus(returnCodeBase + "03");
            return returnObject;
        }
    }

    default void updateMissingDataWithException(Double dataAPlus, Double dataAMoins, Double dataRPlus,
            Double dataRMoins, int id) {
        try {
            updateMissingData(dataAPlus, dataAMoins, dataRPlus, dataRMoins, id);
        } catch (Exception e) {
            ReturnObject returnObject = new ReturnObject();
            returnObject.setObject(null);
            returnObject.setStatus(returnCodeBase + "04");
            throw new CustomRuntimeException(returnObject);
        }
    }

    default void updateSourceWithException(String source, String presence, int id) {
        try {
            updateSource(source, presence, id);
        } catch (Exception e) {
            ReturnObject returnObject = new ReturnObject();
            returnObject.setObject(null);
            returnObject.setStatus(returnCodeBase + "05");
            throw new CustomRuntimeException(returnObject);
        }
    }

    default void updateQualiteWithException(String qualite, int id) {
        try {
            updateQualite(qualite, id);
        } catch (Exception e) {
            ReturnObject returnObject = new ReturnObject();
            returnObject.setObject(null);
            returnObject.setStatus(returnCodeBase + "06");
            throw new CustomRuntimeException(returnObject);
        }
    }

    default ReturnObject findAllSiteClientAndPointDeComptageWithException() {
        try {
            ReturnObject returnObject = new ReturnObject();
            returnObject.setObject(findAllSiteClientAndPointDeComptage());
            returnObject.setStatus("ok");
            return returnObject;
        } catch (Exception e) {
            ReturnObject returnObject = new ReturnObject();
            returnObject.setObject(null);
            returnObject.setStatus(returnCodeBase + "07");
            return returnObject;
        }
    }

    default ReturnObject findByIdCompteurWithException(String idCompteur) {
        try {
            ReturnObject returnObject = new ReturnObject();
            returnObject.setObject(findByIdCompteur(idCompteur));
            returnObject.setStatus("ok");
            return returnObject;

        } catch (Exception e) {

            ReturnObject returnObject = new ReturnObject();
            returnObject.setObject(null);
            returnObject.setStatus(returnCodeBase + "08");
            return returnObject;
        }
    }

    default ReturnObject findAllByIdCompteurWithException() {
        try {
            ReturnObject returnObject = new ReturnObject();
            returnObject.setObject(findAllByIdCompteur());
            returnObject.setStatus("ok");
            return returnObject;
        } catch (Exception e) {
            ReturnObject returnObject = new ReturnObject();
            returnObject.setObject(null);
            returnObject.setStatus(returnCodeBase + "09");
            return returnObject;
        }
    }

    default ReturnObject findBySourceIsNullAndPresenceIsNullAndQualiteIsNullWithException() {
        try {
            ReturnObject returnObject = new ReturnObject();
            returnObject.setObject(findBySourceIsNullAndPresenceIsNullAndQualiteIsNull());
            returnObject.setStatus("ok");
            return returnObject;

        } catch (Exception e) {
            ReturnObject returnObject = new ReturnObject();
            returnObject.setObject(null);
            returnObject.setStatus(returnCodeBase + "10");
            return returnObject;
        }
    }

    public class CustomRuntimeException extends RuntimeException {
        private final ReturnObject returnObject;

        public CustomRuntimeException(ReturnObject returnObject) {
            this.returnObject = returnObject;
        }

        public ReturnObject getReturnObject() {
            return returnObject;
        }
    }
    // default List<BruteAcquisitionEntity> findAllByIdCompteurWithException() {
    // try {
    // return findAllByIdCompteur();
    // } catch (Exception e) {
    // return null;
    // }
    // }

    // default List<BruteAcquisitionEntity>
    // findBySourceIsNullAndPresenceIsNullAndQualiteIsNullWithException() {
    // try {

    // return findBySourceIsNullAndPresenceIsNullAndQualiteIsNull();

    // } catch (Exception e) {
    // return null;
    // }
    // }

}