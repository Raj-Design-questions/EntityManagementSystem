package com.rah.ems.support;

import com.rah.ems.builder.DoctorBuilder;
import com.rah.ems.builder.PatientBuilder;
import com.rah.ems.repository.DoctorRepository;
import com.rah.ems.repository.PatientRepository;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Configuration
public class StaticInjectors {

    @Inject PatientRepository patientRepository;
    @Inject DoctorRepository  doctorRepository;

    @PostConstruct
    public void inject() {
        PatientBuilder.setRepository(patientRepository);
        DoctorBuilder.setRepository(doctorRepository);
    }
}
