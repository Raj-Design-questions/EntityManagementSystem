package com.rah.ems.builder;

import com.rah.ems.model.Doctor;
import com.rah.ems.model.EMSEntity;
import com.rah.ems.repository.DoctorRepository;
import com.rah.ems.repository.EMSRepository;

public class DoctorBuilder implements EntityBuilder {

    private static DoctorRepository doctorRepository;
    private Doctor doctor = new Doctor();

    @Override
    public EMSEntity saveEntity(EMSEntity emsEntity) {
        Doctor existingDoctor = (Doctor)emsEntity;
        return new DoctorBuilder().withId(existingDoctor.getId()).withName(existingDoctor.getName()).withUuid(existingDoctor.getUuid()).inDatabase();
    }

    public static DoctorBuilder createDoctor() {
        return new DoctorBuilder().withName("Test_Name_Doc");
    }

    public DoctorBuilder withId(Long id) {
        doctor.setId(id);
        return this;
    }

    public DoctorBuilder withName(String name) {
        doctor.setName(name);
        return this;
    }

    public DoctorBuilder withUuid(String uuid) {
        doctor.setUuid(uuid);
        return this;
    }

    public Doctor inMemory() {
        return doctor;
    }

    @Override
    public EMSEntity inDatabase() {
        return doctorRepository.saveAndFlush(doctor);
    }

    public static void setRepository(DoctorRepository doctorRepository) {
        DoctorBuilder.doctorRepository = doctorRepository;
    }

    @Override
    public EMSRepository getRepository() {
        return doctorRepository;
    }
}
