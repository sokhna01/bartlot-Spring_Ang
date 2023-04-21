package com.bartlot.Server.entity;

import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "access_rules")

public class AccesRulesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    // JPA maps to the SERIAL data type in PostgreSQL automatically, so you don't
    // need to specify it explicitly.

    @Column(name = "copy", length = 100)
    private String copy;

    @Column(name = "read", length = 100)
    private String read;

    @Column(name = "write", length = 100)
    private String write;

    @Column(name = "write_content", length = 100)
    private String writeContent;

    @Column(name = "download", length = 100)
    private String download;

    @Column(name = "upload", length = 100)
    private String upload;

    @Column(name = "message", length = 100)
    private String message;

    @Column(name = "created_date", columnDefinition = "timestamp without time zone default now()")
    private Timestamp createdDate;

    @Column(name = "id_user")
    private Integer IdUser;

    @Column(name = "id_directory")
    private Integer idDirectory;
}
