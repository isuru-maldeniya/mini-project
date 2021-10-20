package com.middleware.mini.project.pet.controller;

import com.middleware.mini.project.pet.dto.PetDTO;
import com.middleware.mini.project.pet.service.PetServices;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/pet")
public class PetController {

    @Inject
    private PetServices services;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    public Response addPet(PetDTO dto){
        if(services.addPet(dto)){
            return Response.status(Response.Status.OK).build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    @GET
    @Path("/{id}")
    public Response getPetById(@PathParam("id") long id){
        PetDTO byId = services.getById(id);
        if(byId!=null){
            return Response.ok(byId).build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/")
    public Response giveAllPets(){
        return null;
    }


}
