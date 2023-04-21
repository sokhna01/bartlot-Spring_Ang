package com.bartlot.Server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bartlot.Server.entity.UsersEntity;

public interface UsersRepository extends JpaRepository<UsersEntity, Integer> {
}