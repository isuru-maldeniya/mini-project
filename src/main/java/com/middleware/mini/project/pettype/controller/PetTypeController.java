package com.middleware.mini.project.pettype.controller;

import com.middleware.mini.project.pettype.DTO.PetTypeDTO;
import com.middleware.mini.project.pettype.Services.AnimalTypeServices;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("pet-type")
public class PetTypeController {

    @Inject
    private AnimalTypeServices animalTypeServices;

    @POST
    @Path("add-type")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addType(PetTypeDTO petTypeDTO){
        if(animalTypeServices.addAnimalType(petTypeDTO)){
            return Response.status(Response.Status.OK).build();
        }else{
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
