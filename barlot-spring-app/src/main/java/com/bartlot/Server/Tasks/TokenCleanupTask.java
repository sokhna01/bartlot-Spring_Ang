package com.bartlot.Server.Tasks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bartlot.Server.entity.TokenEntity;
import com.bartlot.Server.model.TokenCached;
import com.bartlot.Server.repository.TokenRepository;

@Component
public class TokenCleanupTask {

    @Autowired
    private TokenRepository tokenRepository;

    @Scheduled(fixedDelay = 3600000) // Exécuter toutes les heures
    public void cleanupExpiredTokens() {
        List<TokenEntity> expiredTokens = tokenRepository.findExpiredTokens();
        for (TokenEntity token : expiredTokens) {
            tokenRepository.delete(token);
            TokenCached.remove(token.getUser().getId());
        }
    }
}
