package com.bartlot.Server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "intervention")
public class InterventionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "idclient")
    private String idclient;

    @Column(name = "idsite")
    private String idsite;

    @Column(name = "idcompteur")
    private String idcompteur;

    @Column(name = "horodotage_debut")
    private String startHorodatage;

    @Column(name = "horodotage_fin")
    private String endHorodatage;

}
