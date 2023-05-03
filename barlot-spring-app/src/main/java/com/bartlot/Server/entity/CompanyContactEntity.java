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
@Table(name = "companycontact")
public class CompanyContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "contact_firstname", nullable = false, length = 100)
    private String contactFirstname;

    @Column(name = "contact_lastname", nullable = false, length = 100)
    private String contactLastname;

    @Column(name = "contact_phone", length = 100)
    private String contactPhone;

    @Column(name = "contact_workphone", length = 100)
    private String contactWorkphone;

    @Column(name = "contact_email", length = 100)
    private String contactEmail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcompany", referencedColumnName = "idcompany", foreignKey = @ForeignKey(name = "companycontact_idcompany_fkey"))
    private CompaniesInfoEntity companiesInfo;

    @Column(name = "address_type", length = 50)
    private String addressType;

}
