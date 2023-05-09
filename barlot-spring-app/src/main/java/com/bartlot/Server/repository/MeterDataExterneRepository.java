package com.bartlot.Server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bartlot.Server.entity.MeterDataExterneEntity;

@Repository
public interface MeterDataExterneRepository extends JpaRepository<MeterDataExterneEntity, Integer> {

    // @Query("SELECT e FROM MeterDataSourceExterne e WHERE e.idClient = :idClient")
    // List<MeterDataExterneEntity> findAllSourceExterne(String idClient);

}
