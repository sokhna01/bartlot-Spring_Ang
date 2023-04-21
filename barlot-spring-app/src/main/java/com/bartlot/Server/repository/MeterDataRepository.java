package com.bartlot.Server.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bartlot.Server.entity.MeterDataEntity;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;

public interface MeterDataRepository extends JpaRepository<MeterDataEntity, Integer> {

        @Query("SELECT md.horodotage FROM MeterDataEntity md ORDER BY md.horodotage DESC LIMIT 1")
        Timestamp findLastRecentRowDate();

        @Query("SELECT md FROM MeterDataEntity md WHERE md.idCompany = :idCompany "
                        + "AND (md.dataAPlus IS NULL OR md.dataAPlus = '' "
                        + "OR md.dataAMoins IS NULL OR md.dataAMoins = '' "
                        + "OR md.dataRPlus IS NULL OR md.dataRPlus = '' "
                        + "OR md.dataRMoins IS NULL OR md.dataRMoins = '') "
                        + "AND md.horodotage BETWEEN :startDate AND :endDate")
        List<MeterDataEntity> findMeterDataBybetweenDate(int idCompany, Date startDate, Date endDate);

        List<MeterDataEntity> findByIdCompanyAndHorodotageBetween(int idCompany, Date startDate, Date endDate);

        List<MeterDataEntity> findByHorodotageBetweenOrderByHorodotageAsc(Date startDate, Date endDate);

        @Modifying
        @Transactional
        @Query("update MeterDataEntity md set md.dataAPlus = :dataAPlus,md.dataAMoins = :dataAMoins,md.dataRPlus = :dataRPlus,md.dataRMoins = :dataRMoins where md.id = :id")
        void updateMissingData(String dataAPlus, String dataAMoins, String dataRPlus, String dataRMoins, int id);

        @Modifying
        @Transactional
        @Query("update MeterDataEntity md set md.source = :source,md.presence = :presence where md.id = :id")
        void updateSource(String source, String presence, int id);

        @Modifying
        @Transactional
        @Query("update MeterDataEntity md set md.qualite = :qualite where md.id = :id")
        void updateQualite(String qualite, int id);

        Long countByIdCompany(int idCompany);
}