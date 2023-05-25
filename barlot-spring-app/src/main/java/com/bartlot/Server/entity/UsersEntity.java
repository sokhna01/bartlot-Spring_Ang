package com.bartlot.Server.entity;

import java.sql.Date;
import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")

public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    // JPA maps to the SERIAL data type in PostgreSQL automatically, so we, don't
    // need to specify it explicitly.

    @Column(name = "idcompany")
    private Integer idCompany;

    @Column(name = "firstname", length = 100)
    private String firstName;

    @Column(name = "lastname", length = 100)
    private String lastName;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "state", length = 100)
    private String state;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "active", columnDefinition = "boolean default false")
    private boolean active;

    @Column(name = "address_postal", length = 4)
    private String postalAddress;

    @Column(name = "prefered_language", length = 10)
    private String preferredLanguage;

    @Column(name = "reset_password", columnDefinition = "timestamp without time zone")
    private Timestamp resetPassword;

    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "code_verif", length = 100)
    private String verificationCode;

    @Column(name = "company_name", length = 100)
    private String companyName;

    @Column(name = "telephone", length = 100)
    private String telephone;

    @Column(name = "country", length = 100)
    private String country;

    // Getters
    public Integer getId() {
        return id;
    }

    public Integer getIdCompany() {
        return idCompany;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

}
