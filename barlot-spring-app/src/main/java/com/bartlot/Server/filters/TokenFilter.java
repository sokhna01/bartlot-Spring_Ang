// package com.bartlot.Server.filters;

// import java.io.IOException;
// import java.util.UUID;

// import org.springframework.beans.factory.annotation.Autowired;

// import com.bartlot.Server.entity.CompanyUsersEntity;
// import com.bartlot.Server.service.LoginWeb;
// import com.bartlot.Server.service.TokenService;

// import jakarta.servlet.Filter;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.ServletRequest;
// import jakarta.servlet.ServletResponse;
// import jakarta.servlet.http.Cookie;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// public class TokenFilter implements Filter {

// @Autowired
// private TokenService tokenService;
// private LoginWeb loginWeb;

// @Override
// public void doFilter(ServletRequest req, ServletResponse res, FilterChain
// chain)
// throws IOException, ServletException {

// HttpServletRequest request = (HttpServletRequest) req;
// HttpServletResponse response = (HttpServletResponse) res;

// // Ajouter un cookie CSRF à la réponse
// addCsrfCookie(response);
// if (!isCsrfValid(request)) {
// response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid CSRF token");
// return;
// }

// if ("/login".equals(request.getRequestURI())) {
// chain.doFilter(request, response);
// return;
// }

// // Vérifier si l'utilisateur est authentifié
// if (isAuthenticated(request)) {
// String token = request.getHeader("Authorization");
// if (token != null && tokenService.isTokenValid(token)) {
// // Ajouter le jeton CSRF à la requête POST
// if ("POST".equals(request.getMethod())) {
// String csrfToken = request.getParameter("csrfToken");
// if (csrfToken == null) {
// response.sendError(HttpServletResponse.SC_BAD_REQUEST, "CSRF token missing");
// return;
// }
// // Inclure le jeton CSRF dans l'en-tête X-CSRF-Token de la requête POST
// response.addHeader("X-CSRF-Token", csrfToken);
// }

// chain.doFilter(request, response);
// } else if (token == null) { // vérifier si l'en-tête Authorization est déjà
// présent
// // Générer un nouveau token pour l'utilisateur
// Integer userId = getUserIdFromUsername(request);
// token = tokenService.generateToken(userId);

// response.addHeader("Authorization", token);

// chain.doFilter(request, response);
// } else {
// response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
// }
// } else {
// chain.doFilter(request, response);
// }
// }

// private boolean isAuthenticated(HttpServletRequest request) {
// // Si l'utilisateur est authentifié, retourner true. Sinon, retourner false.
// return getUserIdFromUsername(request) != null;
// }

// private Integer getUserIdFromUsername(HttpServletRequest request) {
// String username = request.getParameter("username");
// if (username != null) {
// CompanyUsersEntity user = loginWeb.getUserByUsername(username);
// if (user != null) {
// return user.getId();
// }
// }
// return null;
// }

// private void addCsrfCookie(HttpServletResponse response) {
// // Générer un jeton CSRF aléatoire
// String csrfToken = UUID.randomUUID().toString();
// // Ajouter le jeton CSRF à la réponse
// Cookie csrfCookie = new Cookie("X-CSRF-Token", csrfToken);
// csrfCookie.setHttpOnly(true);
// response.addCookie(csrfCookie);
// }

// private boolean isCsrfValid(HttpServletRequest request) {
// // Récupérer le cookie CSRF de la requête
// Cookie[] cookies = request.getCookies();
// if (cookies != null) {
// for (Cookie cookie : cookies) {
// if (cookie.getName().equals("X-CSRF-Token")) {
// String csrfToken = cookie.getValue();
// // Vérifier que le cookie CSRF correspond à l'en-tête X-CSRF-Token de la
// requête
// return csrfToken.equals(request.getHeader("X-CSRF-Token"));
// }
// }
// }
// return false;
// }

// }
