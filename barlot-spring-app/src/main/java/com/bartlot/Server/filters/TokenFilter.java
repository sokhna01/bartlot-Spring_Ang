package com.bartlot.Server.filters;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;

import com.bartlot.Server.service.TokenService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TokenFilter implements Filter {

    @Autowired
    private TokenService tokenService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Récupérer le token d'authentification
        String authorizationHeader = httpRequest.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token manquant ou invalide");
            return;
        }

        // Extraire le token du header
        String token = authorizationHeader.substring(7);

        // Vérifier la validité du token
        Integer id = null;
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(tokenService.getJwtSecretKey()).parseClaimsJws(token);
            String subject = claims.getBody().getSubject();
            id = Integer.valueOf(subject);
        } catch (JwtException | IllegalArgumentException e) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token invalide ou expiré");
            return;
        }

        TokenService.TokenData tokenData = tokenService.getTokenData(id);
        if (tokenData == null || !tokenData.getToken().equals(token) || !tokenService.isTokenValid(tokenData)) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token invalide ou expiré");
            return;
        }

        // Ajouter l'identifiant de l'utilisateur à la requête

        httpRequest.setAttribute("userId", id);

        chain.doFilter(request, response);
    }
}