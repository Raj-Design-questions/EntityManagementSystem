package com.rah.ems.model;

public class EntityRequest {
    private String    type;
    private EMSEntity entity;

    public EntityRequest () {

    }

    public EntityRequest (String type, EMSEntity entity) {
        this.type = type;
        this.entity = entity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public EMSEntity getEntity() {
        return entity;
    }

    public void setEntity(EMSEntity entity) {
        this.entity = entity;
    }
}
