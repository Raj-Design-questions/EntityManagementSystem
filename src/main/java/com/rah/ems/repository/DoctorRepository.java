package com.rah.ems.repository;

import com.rah.ems.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>, EMSRepository<Doctor> {
}
