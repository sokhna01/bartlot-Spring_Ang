package com.bartlot.Server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bartlot.Server.entity.UsersQA;

public interface UsersQARepository extends JpaRepository<UsersQA, Integer> {
}