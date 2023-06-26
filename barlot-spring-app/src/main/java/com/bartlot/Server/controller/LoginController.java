package com.bartlot.Server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bartlot.Server.AuthenticationException;
import com.bartlot.Server.entity.UsersEntity;
import com.bartlot.Server.repository.UsersRepository;
import com.bartlot.Server.service.LoginWeb;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginWeb loginCompanyWeb;

    @Autowired
    private UsersRepository companyUsersRepository;

    private static final Logger logger = LogManager.getLogger(LoginController.class);

    @PostMapping("/web")
    public ResponseEntity<Map<String, Object>> login(HttpServletRequest request,
            @RequestParam("username") String username,
            @RequestParam("password") String password) {

        try {
            Map<String, Object> response = loginCompanyWeb.login(username, password);
            HttpStatus status = HttpStatus.OK;
            return new ResponseEntity<>(response, status);
        } catch (AuthenticationException e) {
            logger.error("Authentication error: Invalid credentials for username {}", username);
            Map<String, Object> response = new HashMap<>();
            response.put("error", "AuthenticationError");
            HttpStatus status = HttpStatus.UNAUTHORIZED;
            return new ResponseEntity<>(response, status);
        } catch (Exception e) {
            logger.error("Internal server error occurred: {}", e.getMessage());
            Map<String, Object> response = new HashMap<>();
            response.put("error", "InternalServerError");
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<>(response, status);
        }
    }

    @GetMapping("/getusers")
    public List<UsersEntity> getUsers() {
        return companyUsersRepository.findAll();
    }

}