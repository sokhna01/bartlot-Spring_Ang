package com.bartlot.Server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bartlot.Server.entity.MeterDataExterneEntity;

public interface MeterDataExterneRepository extends JpaRepository<MeterDataExterneEntity, Integer> {

    // @Query(value = "SELECT * FROM meter_data_source_externe WHERE idclient=?1",
    // nativeQuery = true)
    @Query("SELECT e FROM MeterDataExterneEntity e WHERE e.idClient = :idClient")
    List<MeterDataExterneEntity> findAllSourceExterne(String idClient);

}
