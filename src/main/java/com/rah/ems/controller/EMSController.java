package com.rah.ems.controller;

import com.rah.ems.model.EntityRequest;
import com.rah.ems.service.IEMSService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping(value = "entity")
public class EMSController {

    @Inject IEMSService emsService;

    @RequestMapping(value = "/healthCheck", method = RequestMethod.GET)
    public ResponseEntity healthCheck() {
        return new ResponseEntity<>("Hello : Sample application is up.", HttpStatus.OK);
    }

    @RequestMapping(value = "/{uuid}/{type}", method = RequestMethod.GET)
    public ResponseEntity getEntity(@PathVariable String uuid, @PathVariable String type) {
        return new ResponseEntity<>(emsService.getEntity(uuid, type), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createEntity(@RequestBody EntityRequest entityRequest) {
        return new ResponseEntity<>(emsService.createEntity(entityRequest), HttpStatus.OK);
    }

    @RequestMapping(value = "/{uuid}", method = RequestMethod.PATCH)
    public ResponseEntity updateEntity(@PathVariable String uuid, @RequestBody EntityRequest entityRequest) {
        emsService.updateEntity(uuid, entityRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
