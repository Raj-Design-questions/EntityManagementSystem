package com.rah.ems.model;

import lombok.Data;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "DOCTOR")
public class Doctor implements EMSEntity {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "UUID")
    private String uuid;

    @PrePersist
    public void prePersist() {
        if (uuid == null) {
            uuid = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
        }

    }
}
