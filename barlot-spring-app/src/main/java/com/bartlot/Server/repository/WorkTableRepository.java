package com.bartlot.Server.repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.bartlot.Server.entity.WorkTableEntity;

public interface WorkTableRepository extends JpaRepository<WorkTableEntity, Integer> {

    @Query("SELECT md.horodatage FROM WorkTableEntity md ORDER BY md.horodatage DESC LIMIT 1")
    Timestamp findLastRecentRowDate();

    @Query("SELECT wt FROM WorkTableEntity wt WHERE "
            + "(wt.dataAPlus IS NULL OR wt.dataAPlus = '' "
            + "OR wt.dataAMoins IS NULL OR wt.dataAMoins = '' "
            + "OR wt.dataRPlus IS NULL OR wt.dataRPlus = '' "
            + "OR wt.dataRMoins IS NULL OR wt.dataRMoins = '' "
            + "OR (wt.commentaire = 'Données disponibles sur compteur principal' AND wt.attenteAction = 'oui')) "
            + "AND wt.horodatage BETWEEN :startDate AND :endDate")
    List<WorkTableEntity> findByHorodatageBetweenOrderByHoradotageAsc(Date startDate, Date endDate);

}
