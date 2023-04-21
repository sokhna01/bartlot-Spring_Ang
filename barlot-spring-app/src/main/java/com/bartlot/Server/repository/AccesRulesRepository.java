package com.bartlot.Server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bartlot.Server.entity.AccesRulesEntity;

public interface AccesRulesRepository extends JpaRepository<AccesRulesEntity, Integer> {

}