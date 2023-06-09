package com.bartlot.Server.Tasks;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bartlot.Server.model.TokenCached;
import com.bartlot.Server.service.TokenService;

@Component
public class TokenCleanupTask {

    @Autowired
    TokenService tokenService;

    @Scheduled(fixedRate = 1800000) // Planifier la tâche toutes les 30 minutes
    public void cleanupExpiredTokens() {
        // Récupérer tous les jetons du cache
        Map<Integer, String> cachedTokens = TokenCached.getAllTokens();

        // Parcourir tous les jetons et supprimer les jetons expirés
        for (Map.Entry<Integer, String> entry : cachedTokens.entrySet()) {
            String cachedToken = entry.getValue();
            if (!(tokenService.isTokenValid(cachedToken))) {
                TokenCached.remove(entry.getKey());
            }
        }
    }
}
