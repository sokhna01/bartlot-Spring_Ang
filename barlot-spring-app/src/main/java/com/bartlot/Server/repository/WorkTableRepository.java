package com.bartlot.Server.repository;

import jakarta.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bartlot.Server.entity.WorkTableEntity;

public interface WorkTableRepository extends JpaRepository<WorkTableEntity, Integer> {

    @Query("SELECT md.horodatage FROM WorkTableEntity md ORDER BY md.horodatage DESC LIMIT 1")
    Timestamp findLastRecentRowDate();

    @Query("SELECT wt FROM WorkTableEntity wt WHERE "
            + "(wt.commentaire ='Analyse Attendue' "
            + "OR (wt.commentaire = 'Données disponibles sur compteur principal' AND wt.attenteAction = 'oui')) "
            + "AND wt.horodatage BETWEEN :startDate AND :endDate")
    List<WorkTableEntity> findByHorodatageBetweenOrderByHoradotageAsc(Date startDate, Date endDate);

    @Modifying
    @Transactional
    @Query("update WorkTableEntity md set md.dataAPlus = :dataAPlus,"
            + "md.dataAMoins = :dataAMoins, md.dataRPlus = :dataRPlus, md.dataRMoins = :dataRMoins,"
            + " md.source = :source,md.idCompteur = :idCompteur,"
            + "md.commentaire = :commentaire,validation = 'validée',attenteAction ='non' where md.horodatage = :horodatage")
    void update(Timestamp horodatage, String dataAPlus, String dataAMoins, String dataRPlus, String dataRMoins,
            String source, String idCompteur,
            String commentaire);

    @Query("SELECT wt FROM WorkTableEntity wt WHERE "
            + "wt.horodatage = :horodatage AND "
            + "(wt.dataAPlus IS NOT NULL OR wt.dataAPlus != 0.0 AND "
            + "wt.dataAMoins IS NOT NULL OR wt.dataAMoins != 0.0 AND "
            + "wt.dataRPlus IS NOT NULL OR wt.dataRPlus != 0.0 AND "
            + "wt.dataRMoins IS NOT NULL OR wt.dataRMoins != 0.0) ")
    List<WorkTableEntity> findByHorodatageNotNull(Timestamp horodatage);

    WorkTableEntity findByHorodatage(Timestamp horodatage);

    @Query("SELECT wt FROM WorkTableEntity wt WHERE "
            + "wt.horodatage = :horodatage AND "
            + "wt.idCompteur = :idCompteur ")
    List<WorkTableEntity> existsByHorodatageAndIdCompteur(Timestamp horodatage, String idCompteur);
}