package com.middleware.mini.project.pet.controller;

import com.middleware.mini.project.pet.dto.PetDTO;
import com.middleware.mini.project.pet.service.PetServices;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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
    @Produces(MediaType.APPLICATION_JSON)
    public Response giveAllPets(){
        List<PetDTO> allPets = services.getAllPets();
        if(allPets.isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }else{
            return Response.ok(allPets).build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePet(PetDTO dto, @PathParam("id") long id){
        if(services.updatePet(dto,id)){
            return Response.status(Response.Status.OK).build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteById(@PathParam("id")long id){
        if(services.deleteById(id)){
            return Response.status(Response.Status.OK).build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }


}
