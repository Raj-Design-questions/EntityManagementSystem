package com.rah.ems.builder;

import com.rah.ems.model.EMSEntity;
import com.rah.ems.model.Patient;
import com.rah.ems.repository.EMSRepository;
import com.rah.ems.repository.PatientRepository;

public class PatientBuilder implements EntityBuilder {

    private static PatientRepository patientRepository;
    private Patient patient = new Patient();

    @Override
    public EMSEntity saveEntity(EMSEntity emsEntity) {
        Patient existingPatient = (Patient)emsEntity;
        return new PatientBuilder().withId(existingPatient.getId()).withName(existingPatient.getName()).withUuid(existingPatient.getUuid()).inDatabase();
    }

    public static PatientBuilder createPatient() {
        return new PatientBuilder().withName("Test_Name");
    }

    public PatientBuilder withId(Long id) {
        patient.setId(id);
        return this;
    }

    public PatientBuilder withName(String name) {
        patient.setName(name);
        return this;
    }

    public PatientBuilder withUuid(String uuid) {
        patient.setUuid(uuid);
        return this;
    }

    public Patient inMemory() {
        return patient;
    }

    @Override
    public EMSEntity inDatabase() {
        return patientRepository.saveAndFlush(patient);
    }

    public static void setPatientRepository(PatientRepository patientRepository) {
        PatientBuilder.patientRepository = patientRepository;
    }

    @Override
    public EMSRepository getRepository() {
        return patientRepository;
    }
}
