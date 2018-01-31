package com.rah.ems.repository;

import com.rah.ems.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>, EMSRepository<Patient> {
}
