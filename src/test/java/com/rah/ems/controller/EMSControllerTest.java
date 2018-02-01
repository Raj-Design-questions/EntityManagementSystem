package com.rah.ems.controller;

import com.rah.ems.boot.Application;
import com.rah.ems.builder.DoctorBuilder;
import com.rah.ems.builder.PatientBuilder;
import com.rah.ems.model.Doctor;
import com.rah.ems.model.EMSEntity;
import com.rah.ems.model.EntityRequest;
import com.rah.ems.model.EntityResponse;
import com.rah.ems.model.Patient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class EMSControllerTest {

    @Inject private EMSController controller;

    @Test
    public void testSystemHealth() {
        ResponseEntity healthCheck = controller.healthCheck();
        assertTrue(healthCheck.getStatusCode() == HttpStatus.OK);
    }

    @Test
    public void testCreatePatient() {
        EMSEntity emsEntity = PatientBuilder.createPatient().inMemory();
        EntityRequest entityRequest = new EntityRequest("Patient", emsEntity);
        ResponseEntity responseEntity = controller.createEntity(entityRequest);
        assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
        assertTrue(((Patient)((EntityResponse)responseEntity.getBody()).getEntity()).getName().equals("Test_Name"));
    }

    @Test
    public void testUpdateAndGetPatient() {
        String type = "Patient";
        EMSEntity emsEntity = PatientBuilder.createPatient().inMemory();
        EntityRequest entityRequest = new EntityRequest(type, emsEntity);
        ResponseEntity responseEntity = controller.createEntity(entityRequest);
        assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
        Patient patient = (Patient)((EntityResponse)responseEntity.getBody()).getEntity();
        assertTrue(patient.getName().equals("Test_Name"));

        patient.setName("Test_Name_2");
        entityRequest.setEntity(patient);
        responseEntity = controller.updateEntity(patient.getUuid(), entityRequest);
        assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);

        responseEntity = controller.getEntity(patient.getUuid(), type);
        assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
        patient = (Patient)((EntityResponse)responseEntity.getBody()).getEntity();
        assertTrue(patient.getName().equals("Test_Name_2"));
    }

    @Test
    public void testCreateAndGetDoctorAndPatient() {
        String typeDoctor = "Doctor";
        EMSEntity emsDoctorEntity = DoctorBuilder.createDoctor().inMemory();
        EntityRequest entityDoctorRequest = new EntityRequest(typeDoctor, emsDoctorEntity);
        ResponseEntity responseDoctorEntity = controller.createEntity(entityDoctorRequest);
        assertTrue(responseDoctorEntity.getStatusCode() == HttpStatus.OK);
        Doctor doctor = (Doctor)((EntityResponse)responseDoctorEntity.getBody()).getEntity();
        assertTrue(doctor.getName().equals("Test_Name_Doc"));

        String typePatient = "Patient";
        EMSEntity emsPatientEntity = PatientBuilder.createPatient().withConsultingDoctorUuid(doctor.getUuid()).inMemory();
        EntityRequest entityPatientRequest = new EntityRequest(typePatient, emsPatientEntity);
        ResponseEntity responsePatientEntity = controller.createEntity(entityPatientRequest);
        assertTrue(responsePatientEntity.getStatusCode() == HttpStatus.OK);
        Patient patient = (Patient)((EntityResponse)responsePatientEntity.getBody()).getEntity();
        assertTrue(patient.getName().equals("Test_Name"));

        responsePatientEntity = controller.getEntity(patient.getUuid(), typePatient);
        assertTrue(responsePatientEntity.getStatusCode() == HttpStatus.OK);
        patient = (Patient)((EntityResponse)responsePatientEntity.getBody()).getEntity();
        assertTrue(patient.getName().equals("Test_Name"));
        assertTrue(patient.getConsultingDoctor().getUuid().equals(doctor.getUuid()));
        assertTrue(patient.getConsultingDoctor().getName().equals(doctor.getName()));

        responseDoctorEntity = controller.getEntity(doctor.getUuid(), typeDoctor);
        assertTrue(responseDoctorEntity.getStatusCode() == HttpStatus.OK);
        doctor = (Doctor)((EntityResponse)responseDoctorEntity.getBody()).getEntity();
        assertTrue(doctor.getName().equals("Test_Name_Doc"));
        assertTrue(doctor.getPatients().get(0).getName().equals("Test_Name"));
    }
}
