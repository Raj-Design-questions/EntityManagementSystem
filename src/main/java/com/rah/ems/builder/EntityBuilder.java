package com.rah.ems.builder;

import com.rah.ems.model.EMSEntity;
import com.rah.ems.repository.EMSRepository;

public interface EntityBuilder {

    public EMSEntity saveEntity(EMSEntity emsEntity);

    public EMSRepository getRepository();

    public EMSEntity inDatabase();
}
