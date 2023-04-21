package com.bartlot.Server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "country_companies")
public class CountryCompaniesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "code", length = 5)
    private String code;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "state", length = 50)
    private String state;

    @Column(name = "idcompany")
    private Integer idCompany;

    @Column(name = "centerlat")
    private Double centerLat;

    @Column(name = "centerlong")
    private Double centerLong;

    // getters and setters

}
