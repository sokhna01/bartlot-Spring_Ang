package com.bartlot.Server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bartlot.Server.entity.MeterConfigEntity;

public interface MeterConfigRepository extends JpaRepository<MeterConfigEntity, Integer> {
}
