package com.bartlot.Server.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
// import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "profiles")
public class ProfilesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "pf_code", length = 100, nullable = false, unique = true)
    private String pfCode;

    @Column(name = "pf_name", length = 100, nullable = false, unique = true)
    private String pfName;

    @Column(name = "pf_description", length = 100, nullable = false)
    private String pfDescription;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "category", length = 100, nullable = false)
    private String category;

    @OneToMany(mappedBy = "profilesmodel", fetch = FetchType.EAGER)
    // obtenir les instances de UserProfiles liées à une instance de Profiles.
    private List<UserProfilesEntity> userprofilesmodel;

    @OneToMany(mappedBy = "profilesmodel", fetch = FetchType.EAGER)
    // obtenir les instances de UserProfiles liées à une instance de Profiles.
    private List<CompanyUsersProfilesEntity> companyUsersProfilesModel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPfCode() {
        return pfCode;
    }

    public void setPfCode(String pfCode) {
        this.pfCode = pfCode;
    }

    public String getPfName() {
        return pfName;
    }

    public void setPfName(String pfName) {
        this.pfName = pfName;
    }

    public String getPfDescription() {
        return pfDescription;
    }

    public void setPfDescription(String pfDescription) {
        this.pfDescription = pfDescription;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}