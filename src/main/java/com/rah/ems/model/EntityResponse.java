package com.rah.ems.model;

import lombok.Data;

@Data
public class EntityResponse {
    private EMSEntity entity;

    public EntityResponse () {

    }

    public EntityResponse (EMSEntity entity) {
        this.entity = entity;
    }
}
