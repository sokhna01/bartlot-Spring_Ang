package com.bartlot.Server.service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import com.bartlot.Server.model.TokenCached;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    private static final long TOKEN_EXPIRATION_TIME = 3600;

    // Clé secrète utilisée pour signer le token
    private static final byte[] JWT_SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded();

    public String generateToken(Integer id) {
        // Vérifie si l'utilisateur a déjà un jeton dans le cache
        String cachedToken = TokenCached.get(id);
        if (cachedToken != null) {
            if (isTokenValid(cachedToken)) {
                // Extraire la date d'expiration à partir du jeton
                Date expirationDate = Jwts.parser()
                        .setSigningKey(JWT_SECRET_KEY)
                        .parseClaimsJws(cachedToken)
                        .getBody()
                        .getExpiration();
                // Vérifier si la date d'expiration est dans le futur
                if (expirationDate.after(new Date())) {
                    // Renouveler la date d'expiration
                    String renewedToken = Jwts.builder()
                            .setSubject(id.toString())
                            .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME * 1000))
                            .signWith(SignatureAlgorithm.HS512, JWT_SECRET_KEY)
                            .compact();
                    // Stocker le jeton renouvelé dans le cache
                    TokenCached.put(id, renewedToken);
                    return renewedToken;
                } else {
                    // Si le jeton a expiré, le supprimer du cache
                    TokenCached.remove(id);
                }
            } else {
                // Si le jeton est invalide, le supprimer du cache
                TokenCached.remove(id);
            }
        }

        // Générer un nouvel identifiant pour le token
        Integer newTokenId = generateNewTokenId();

        // Générer un nouveau token pour l'utilisateur
        String token = Jwts.builder()
                .setSubject(id.toString())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME * 1000))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET_KEY)
                .compact();

        // Stocker le jeton dans le cache
        TokenCached.put(id, token);

        return token;
    }

    public boolean isTokenValid(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(JWT_SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            Integer userId = Integer.parseInt(claims.getSubject());
            // Vérifier si le jeton est dans le cache
            if (TokenCached.get(userId) != null && TokenCached.get(userId).equals(token)) {
                return true;
            }
            return false;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Integer generateNewTokenId() {
        Integer newId = null;
        while (newId == null || TokenCached.containsKey(newId)) {
            newId = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
        }
        return newId;
    }

    public static byte[] getJwtSecretKey() {
        return JWT_SECRET_KEY.clone();
    }

}