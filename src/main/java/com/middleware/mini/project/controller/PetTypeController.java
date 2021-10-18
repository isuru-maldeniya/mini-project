package com.middleware.mini.project.controller;

import com.middleware.mini.project.DTO.PetType;
import com.middleware.mini.project.Entity.AnimalType;
import com.middleware.mini.project.Services.AnimalTypeServices;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("pet-type")
public class PetTypeController {

    @Inject
    private AnimalTypeServices animalTypeServices;

    @POST
    @Path("add-type")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addType(PetType petType){
        animalTypeServices.addAnimalType(petType);
    }
}
