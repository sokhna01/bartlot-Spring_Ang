package com.bartlot.Server.repository;

import jakarta.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bartlot.Server.entity.TravailEntity;
import com.bartlot.Server.model.ReturnObject;

public interface TravailRepository extends JpaRepository<TravailEntity, Integer> {
        String returnCodeBase = "Server error: 04";

        @Query("SELECT md.horodatage FROM TravailEntity md ORDER BY md.horodatage DESC LIMIT 1")
        Timestamp findLastRecentRowDate();

        @Query("SELECT wt FROM TravailEntity wt WHERE "
                        + "(wt.commentaire ='Analyse Attendue' "
                        + "OR (wt.commentaire = 'Données disponibles sur compteur principal' AND wt.attenteAction = 'oui')) "
                        + "AND wt.horodatage BETWEEN :startDate AND :endDate")
        List<TravailEntity> findByHorodatageBetweenOrderByHoradotageAsc(Date startDate, Date endDate);

        @Modifying
        @Transactional
        @Query("update TravailEntity md set md.dataAPlus = :dataAPlus,"
                        + "md.dataAMoins = :dataAMoins, md.dataRPlus = :dataRPlus, md.dataRMoins = :dataRMoins,"
                        + " md.source = :source,md.idCompteur = :idCompteur,"
                        + "md.commentaire = :commentaire,validation = 'validée',attenteAction ='non' where md.horodatage = :horodatage")
        void update(Timestamp horodatage, String dataAPlus, String dataAMoins, String dataRPlus, String dataRMoins,
                        String source, String idCompteur,
                        String commentaire);

        @Query("SELECT wt FROM TravailEntity wt WHERE "
                        + "wt.horodatage = :horodatage AND "
                        + "(wt.dataAPlus IS NOT NULL OR wt.dataAPlus != 0.0 AND "
                        + "wt.dataAMoins IS NOT NULL OR wt.dataAMoins != 0.0 AND "
                        + "wt.dataRPlus IS NOT NULL OR wt.dataRPlus != 0.0 AND "
                        + "wt.dataRMoins IS NOT NULL OR wt.dataRMoins != 0.0) ")
        List<TravailEntity> findByHorodatageNotNull(Timestamp horodatage);

        TravailEntity findByHorodatage(Timestamp horodatage);

        @Query("SELECT wt FROM TravailEntity wt WHERE "
                        + "wt.horodatage = :horodatage AND "
                        + "wt.idCompteur = :idCompteur ")
        List<TravailEntity> existsByHorodatageAndIdCompteur(Timestamp horodatage, String idCompteur);

        /* **************************************************************** */
        /* *******************METHODE AVEC TRY-CATCH********************** */
        /* ************************************************************** */

        default ReturnObject existsByHorodatageAndIdCompteurWithException(Timestamp horodatage, String idCompteur) {
                try {
                        ReturnObject returnObject = new ReturnObject();
                        returnObject.setObject(existsByHorodatageAndIdCompteur(horodatage, idCompteur));
                        returnObject.setStatus("ok");
                        return returnObject;

                } catch (Exception e) {
                        ReturnObject returnObject = new ReturnObject();
                        returnObject.setObject(null);
                        returnObject.setStatus(returnCodeBase + "06");
                        return returnObject;
                }
        }

}