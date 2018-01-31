package com.rah.ems.support;

import com.rah.ems.builder.PatientBuilder;
import com.rah.ems.repository.PatientRepository;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Configuration
public class StaticInjectors {

    @Inject PatientRepository patientRepository;

    @PostConstruct
    public void inject() {
        PatientBuilder.setPatientRepository(patientRepository);
    }
}
