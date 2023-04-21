package com.bartlot.Server.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bartlot.Server.entity.TokenEntity;

public interface TokenRepository extends JpaRepository<TokenEntity, String> {
    Optional<TokenEntity> findByUser_Id(Integer userId);

    Optional<TokenEntity> findByToken(String token);

    // trouver les tokens qui ont expiré
    @Query("SELECT t FROM TokenEntity t WHERE t.expirationDate < CURRENT_TIMESTAMP")
    List<TokenEntity> findExpiredTokens();

}
