package com.bartlot.Server.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bartlot.Server.entity.InterventionEntity;

public interface InterventionRepository extends JpaRepository<InterventionEntity, Integer> {

    InterventionEntity findByStartHorodatageAndEndHorodatage(Timestamp startHorodatage, Timestamp endHorodatage);

    @Query("SELECT int FROM InterventionEntity int WHERE "
            + "(startHorodatage>=:beginHoradatage AND endHorodatage<=:endHoradatage) ORDER BY id")
    List<InterventionEntity> findByHorodatage(Timestamp beginHoradatage,
            Timestamp endHoradatage);

}