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
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users_profiles")
public class UserProfilesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "user_id_fkey"))
    private UsersEntity users;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pf_code", referencedColumnName = "pf_code", foreignKey = @ForeignKey(name = "pf_code_fkey"))
    private ProfilesEntity profilesmodel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UsersEntity getUsers() {
        return users;
    }

    public void setUsers(UsersEntity users) {
        this.users = users;
    }

    public ProfilesEntity getProfilesModel() {
        return profilesmodel;
    }

    public void setProfilesModel(ProfilesEntity profilesmodel) {
        this.profilesmodel = profilesmodel;
    }

}
