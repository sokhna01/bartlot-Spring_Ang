package com.bartlot.Server.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tokens")
public class TokenEntity {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private CompanyUsersEntity user;

    @Column(name = "expirationDate")
    private LocalDateTime expirationDate;

    @Column(name = "token")
    private String token;

    // Constructeur par défaut
    public TokenEntity() {
    }

    // Constructeur avec paramètres
    public TokenEntity(Integer id, CompanyUsersEntity user, LocalDateTime expirationDate) {
        this.id = id;
        this.user = user;
        this.expirationDate = expirationDate;
    }

    // Getters et setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public CompanyUsersEntity getUser() {
        return user;
    }

    public void setUser(CompanyUsersEntity user) {
        this.user = user;
    }

    public Integer getUserId() {
        return user.getId();
    }

    public void setUserId(Integer userId) {
        CompanyUsersEntity user = new CompanyUsersEntity();
        user.setId(userId);
        this.user = user;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }
}
