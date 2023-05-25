package com.bartlot.Server.entity;

import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "meter_configuration")

public class MeterConfigEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    // JPA maps to the SERIAL data type in PostgreSQL automatically, so we don't
    // need to specify it explicitly.

    @Column(name = "idcompteur", nullable = false)
    private String idCompteur;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "created_date", nullable = false, columnDefinition = "timestamp without time zone default now()")
    private Timestamp createdDate;

    @Column(name = "inverse", nullable = false, columnDefinition = "boolean default false")
    private Boolean inverse;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdCompteur() {
        return idCompteur;
    }

    public void setIdCompteur(String idCompteur) {
        this.idCompteur = idCompteur;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getInverse() {
        return inverse;
    }

    public void setInverse(Boolean inverse) {
        this.inverse = inverse;
    }

    public boolean isInverse() {
        return this.inverse;
    }
}
