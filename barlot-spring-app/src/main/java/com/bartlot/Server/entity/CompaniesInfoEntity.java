package com.bartlot.Server.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "companiesinfo")
public class CompaniesInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "idcompany")
    private Integer id;

    @Column(name = "companycode", length = 20, unique = true)
    private String companyCode;

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "timezone", length = 50)
    private String timezone;

    @Column(name = "language", length = 5)
    private String language = "en";

    @Column(name = "legalname", length = 30)
    private String legalName;

    @Column(name = "billingaccountnumber", length = 20)
    private String billingAccountNumber;

    // @Column(name = "numberdrivers")
    // private Integer numberDrivers = 0;

    // @Column(name = "maxnumberdrivers")
    // private Integer maxNumberDrivers;

    @Column(name = "phoneareacode")
    private Integer phoneAreaCode;

    @Column(name = "comment", length = 200)
    private String comment;

    @Column(name = "modifydate")
    private LocalDateTime modifyDate;

    @Column(name = "active")
    private boolean active = true;

    @Column(name = "maxdeviceiduse")
    private Integer maxDeviceIdUse = 2;

    @Column(name = "dispatchtype", length = 15)
    private String dispatchType;

    @Column(name = "useautocompletion")
    private boolean useAutoCompletion;

    @Column(name = "type", length = 20)
    private String type;

    @OneToMany(mappedBy = "companiesInfo", fetch = FetchType.EAGER)
    private List<CompanyUsersEntity> companyUsersEntity;

    @OneToMany(mappedBy = "companiesInfo", fetch = FetchType.EAGER)
    private List<CompanyAddressEntity> companyAddressEntity;

    @OneToMany(mappedBy = "companiesInfo", fetch = FetchType.EAGER)
    private List<CompanyContactEntity> companyContactEntity;

    public Integer getIdCompany() {
        return id;
    }
}