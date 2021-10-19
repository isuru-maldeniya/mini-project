package com.middleware.mini.project.pettype.controller;

import com.middleware.mini.project.pettype.DTO.PetTypeDTO;
import com.middleware.mini.project.pettype.Services.AnimalTypeServices;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
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
    public void addType(PetTypeDTO petTypeDTO){
        animalTypeServices.addAnimalType(petTypeDTO);
    }
}
