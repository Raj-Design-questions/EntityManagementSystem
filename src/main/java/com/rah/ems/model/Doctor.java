package com.rah.ems.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "consultingDoctor")
    private List<Patient> patients;

    @PrePersist
    public void prePersist() {
        if (uuid == null) {
            uuid = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
        }
    }

    @Override
    public String toString() {
        return "Doctor{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", uuid='" + uuid + '\'' +
            '}';
    }
}
