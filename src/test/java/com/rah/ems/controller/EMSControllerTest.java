package com.rah.ems.controller;

import com.rah.ems.model.EMSEntity;
import com.rah.ems.boot.Application;
import com.rah.ems.builder.PatientBuilder;
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
    public void testCreateEntity() {
        EMSEntity emsEntity = PatientBuilder.createPatient().inMemory();
        EntityRequest entityRequest = new EntityRequest("Patient", emsEntity);
        ResponseEntity responseEntity = controller.createEntity(entityRequest);
        assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
        assertTrue(((Patient)((EntityResponse)responseEntity.getBody()).getEntity()).getName().equals("Test_Name"));
    }

    @Test
    public void testUpdateAndGetEntity() {
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
}
