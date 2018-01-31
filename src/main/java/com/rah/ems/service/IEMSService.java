package com.rah.ems.service;

import com.rah.ems.model.EntityRequest;
import com.rah.ems.model.EntityResponse;
import org.springframework.stereotype.Service;

@Service
public interface IEMSService {
    public EntityResponse createEntity(EntityRequest entity);
    public void updateEntity(String uuid, EntityRequest entity);
    public EntityResponse getEntity(String uuid, String type);
}
