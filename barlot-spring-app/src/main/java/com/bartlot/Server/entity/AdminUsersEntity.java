package com.bartlot.Server.entity;

import jakarta.persistence.ForeignKey;
// import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
// import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin_users")
public class AdminUsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idcompany", referencedColumnName = "idcompany", foreignKey = @ForeignKey(name = "fk_companyaddress_companiesinfo", foreignKeyDefinition = "FOREIGN KEY (idcompany) REFERENCES companies_info(idcompany) ON UPDATE RESTRICT ON DELETE RESTRICT"))
    private CompaniesInfoEntity companiesInfo;

    @Column(name = "pf_code", length = 32)
    private String pfCode;

    @Column(name = "userfname", length = 100)
    private String firstName;

    @Column(name = "userlname", length = 30)
    private String lastName;

    @Column(name = "usercellphone", length = 30)
    private String cellphone;

    @Column(name = "userworkphone", length = 30)
    private String workPhone;

    @Column(name = "login", length = 30)
    private String login;

    @Column(name = "password", length = 50)
    private String password;

    @Column(name = "active", columnDefinition = "boolean default true")
    private boolean active;

    @Column(name = "useremail", length = 30)
    private String email;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "state", length = 100)
    private String state;

    @Column(name = "country", length = 100)
    private String country;

    @Column(name = "zipcode", length = 100)
    private String zipCode;

    @Column(name = "prefered_language", length = 4)
    private String preferredLanguage;

    @Column(name = "reset_password")
    private java.sql.Timestamp resetPassword;

    @Column(name = "date_created")
    private java.sql.Date dateCreated;

    // getters and setters

    // @OneToMany(mappedBy = "adminUsers", fetch = FetchType.EAGER)
    // // obtenir les instances de ProfilAction liées à une instance de ActionModel.
    // private List<CompanyUsersEntity> companyUsersEntities;

}
