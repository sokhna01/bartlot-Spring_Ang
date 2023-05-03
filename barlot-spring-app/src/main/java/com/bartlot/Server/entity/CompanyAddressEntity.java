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
@Table(name = "companyadress")
public class CompanyAddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcompanyaddress", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idcompany", referencedColumnName = "idcompany", foreignKey = @ForeignKey(name = "fk_companyaddress_companiesinfo", foreignKeyDefinition = "FOREIGN KEY (idcompany) REFERENCES companies_info(idcompany) ON UPDATE RESTRICT ON DELETE RESTRICT"))
    private CompaniesInfoEntity companiesInfo;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "city", length = 30)
    private String city;

    @Column(name = "state", length = 30)
    private String state;

    // @Column(name = "zipcode", length = 20)
    // private String zipcode;

    @Column(name = "country", length = 30)
    private String country;

    @Column(name = "addresstype", length = 20)
    private String addressType;

    // Constructors, getters and setters
}
