package com.bartlot.Server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_profiles")
public class UserProfilesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "upf_id", nullable = false)
    private Integer idUpf;

    @Column(name = "iduser", nullable = false)
    private Integer idUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pf_code", referencedColumnName = "pf_code", foreignKey = @ForeignKey(name = "pf_code_fkey"), nullable = false)
    private ProfilesEntity profilesmodel;

    public Integer getIdUpf() {
        return idUpf;
    }

    public void setIdUpf(Integer idUpf) {
        this.idUpf = idUpf;
    }

    public Integer getUserId() {
        return idUser;
    }

    public void setUserId(Integer idUser) {
        this.idUser = idUser;
    }

    public ProfilesEntity getProfiles() {
        return profilesmodel;
    }

    public void setProfiles(ProfilesEntity profilesmodel) {
        this.profilesmodel = profilesmodel;
    }

}
