package com.rah.ems.model;

import lombok.Data;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "PATIENT")
public class Patient implements EMSEntity {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "UUID")
    private String uuid;

    @Column(name = "DOCTOR_UUID")
    private String consultingDoctorUuid;

    @ManyToOne(targetEntity = Doctor.class)
    @JoinColumn(name = "DOCTOR_UUID", referencedColumnName = "UUID", insertable = false, updatable = false)
    private Doctor consultingDoctor ;

    @PrePersist
    public void prePersist() {
        if (uuid == null) {
            uuid = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
        }
    }

    @Override
    public String toString() {
        return "Patient{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", uuid='" + uuid + '\'' +
            ", consultingDoctorUuid='" + consultingDoctorUuid + '\'' +
            '}';
    }
}
