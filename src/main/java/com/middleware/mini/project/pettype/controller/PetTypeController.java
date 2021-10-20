package com.middleware.mini.project.pettype.controller;

import com.middleware.mini.project.pettype.DTO.PetTypeDTO;
import com.middleware.mini.project.pettype.Services.AnimalTypeServices;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findTypeByName(@PathParam("id") long id){
        PetTypeDTO typeById = animalTypeServices.getTypeById(id);
        if(typeById==null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }else{
            return Response.ok(typeById).status(Response.Status.OK).build();
        }
    }

    @GET
    @Path("/")
    public Response getAll(){
        List<PetTypeDTO> all = animalTypeServices.getAll();
        if(all.isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }else{
            return Response.ok(all).build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(PetTypeDTO dto, @PathParam("id")long id){
        if(animalTypeServices.update(dto, id)){
            return Response.status(Response.Status.OK).build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}
