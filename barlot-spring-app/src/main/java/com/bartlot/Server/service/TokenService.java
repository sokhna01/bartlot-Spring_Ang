package com.bartlot.Server.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenService {

    // Utilisez une structure de données de type ConcurrentHashMap pour stocker les
    // jetons en cache
    private static final Map<Integer, TokenData> tokenCache = new ConcurrentHashMap<>();

    private static final long TOKEN_EXPIRATION_TIME = 3600; // 1 heure de temps

    // Clé secrète utilisée pour signer le token
    private static final byte[] JWT_SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded();

    public String generateToken(Integer id) {
        // Vérifie si l'utilisateur a déjà un jeton dans le cache
        TokenData cachedToken = tokenCache.get(id);
        if (cachedToken != null && isTokenValid(cachedToken)) {
            // Mettre à jour la date de dernière utilisation
            cachedToken.setLastUsedTime(System.currentTimeMillis());
            return cachedToken.getToken();
        }

        // Générer un nouvel identifiant pour le token
        Integer newTokenId = generateNewTokenId();

        // Générer un nouveau token pour l'utilisateur
        String token = Jwts.builder()
                .setSubject(id.toString())
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET_KEY)
                .compact();

        TokenData tokenData = new TokenData(token, System.currentTimeMillis());
        tokenCache.put(id, tokenData);

        return token;
    }

    public boolean isTokenValid(TokenData tokenData) {
        return System.currentTimeMillis() - tokenData.getLastUsedTime() < TOKEN_EXPIRATION_TIME * 1000;
    }

    private Integer generateNewTokenId() {
        Integer newId = null;
        while (newId == null || tokenCache.containsKey(newId)) {
            newId = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
        }
        return newId;
    }

    public static class TokenData {
        private final String token;
        private long lastUsedTime;

        public TokenData(String token, long lastUsedTime) {
            this.token = token;
            this.lastUsedTime = lastUsedTime;
        }

        public String getToken() {
            return token;
        }

        public long getLastUsedTime() {
            return lastUsedTime;
        }

        public void setLastUsedTime(long lastUsedTime) {
            this.lastUsedTime = lastUsedTime;
        }
    }

    public TokenData getTokenData(Integer id) {
        return tokenCache.get(id);
    }

    public static byte[] getJwtSecretKey() {
        return JWT_SECRET_KEY;
    }
}
