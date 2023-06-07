package com.bartlot.Server.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "users")
public class CompanyUsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", length = 100)
    private String username;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "firstname", length = 100)
    private String firstname;

    @Column(name = "lastname", length = 100)
    private String lastname;

    @Column(name = "phone", length = 100)
    private String phone;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "city", length = 50)
    private String city;

    @Column(name = "state", length = 50)
    private String state;

    // @Column(name = "zipcode", length = 50)
    // private String zipcode;

    @Column(name = "country", length = 50)
    private String country;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "reset_password")
    private Date resetPassword;

    @Column(name = "homephone", length = 50)
    private String homephone;

    @Column(name = "workphone", length = 50)
    private String workphone;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "prefered_language", length = 10)
    private String preferredLanguage;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "useautocompletion")
    private Boolean useautocompletion;

    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
    // obtenir les instances de CompanyUsersProfilesModel liées à une instance de
    // CompanyUsers.
    private List<CompanyUsersProfilesEntity> companyUsersProfilesModels;

    // getters
    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    // public String getZipcode() {
    // return zipcode;
    // }

    public String getCountry() {
        return country;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getResetPassword() {
        return resetPassword;
    }

    public String getHomephone() {
        return homephone;
    }

    public String getWorkphone() {
        return workphone;
    }

    public Boolean getActive() {
        return active;
    }

    // setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    // public void setZipcode(String zipcode) {
    // this.zipcode = zipcode;
    // }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setResetPassword(Date resetPassword) {
        this.resetPassword = resetPassword;
    }

    public void setHomephone(String homephone) {
        this.homephone = homephone;
    }

    public void setWorkphone(String workphone) {
        this.workphone = workphone;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}
