package com.rah.ems.service.impl;

import com.rah.ems.model.EMSEntity;
import com.rah.ems.model.EntityRequest;
import com.rah.ems.model.EntityResponse;
import com.rah.ems.service.IEMSService;
import com.rah.ems.support.EntityHelper;
import org.springframework.stereotype.Service;

@Service
public class EMSService implements IEMSService {

    @Override
    public EntityResponse createEntity(EntityRequest entityRequest) {
        String type = entityRequest.getType();
        EMSEntity entity = entityRequest.getEntity();
        return new EntityResponse(EntityHelper.createEntity(type, entity));
    }

    @Override
    public void updateEntity(String uuid, EntityRequest entityRequest) {
        String type = entityRequest.getType();
        EMSEntity entity = entityRequest.getEntity();
        EntityHelper.updateEntity(uuid, type, entity);
    }

    @Override
    public EntityResponse getEntity(String uuid, String type) {
        return new EntityResponse(EntityHelper.getEntity(uuid, type));
    }
}
