package com.bartlot.Server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;

@Entity
@Table(name = "profil_action", uniqueConstraints = @UniqueConstraint(columnNames = { "pf_code", "act_code" }))
public class ProfilActionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pac_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pf_code", referencedColumnName = "pf_code", foreignKey = @ForeignKey(name = "pf_code_fkey"), nullable = false)
    private ProfilesEntity profilesEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "act_code", foreignKey = @ForeignKey(name = "act_code_fkey"), nullable = false)
    private ActionEntity actionEntity;

    @Column(name = "allow")
    private Boolean allow;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProfilesEntity getProfiles() {
        return profilesEntity;
    }

    public void setProfiles(ProfilesEntity profilesentity) {
        this.profilesEntity = profilesentity;
    }

    public ActionEntity getActions() {
        return this.actionEntity;
    }

    public void setActions(ActionEntity actionEntity) {
        this.actionEntity = actionEntity;
    }

    public Boolean getAllow() {
        return allow;
    }

    public void setAllow(Boolean allow) {
        this.allow = allow;
    }
}
