package com.middleware.mini.project.pettype.controller;

import com.middleware.mini.project.pettype.dto.PetTypeDTO;
import com.middleware.mini.project.pettype.Services.AnimalTypeServices;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

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
    @Operation(summary = "adding pet-type",description = "here you have to specify these properties categoryName, family, environment and climate in json.")
    @APIResponse(responseCode = "200",description = "Added the pet-category successfully")
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
    @Operation(summary = "Retrieve the pet-category with id",description = "Here it is essential to send the pet-category id in url")
    @APIResponse(responseCode = "200",description = "Pet-category is found")
    @APIResponse(responseCode = "400",description = "There is no such a pet-category")
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
    @Operation(summary = "give all the pets")
    @APIResponse(responseCode = "200",description = "Here is the pet-category list")
    @APIResponse(responseCode = "400",description = "There pet-category list is empty")
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
    @Operation(summary = "update the pet category")
    @APIResponse(responseCode = "200",description = "Updated the pet-category successfully")
    @APIResponse(responseCode = "400",description = "There is no such a pet-category")
    public Response update(PetTypeDTO dto, @PathParam("id")long id){
        if(animalTypeServices.update(dto, id)){
            return Response.status(Response.Status.OK).build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Operation(summary = "Delete the pet-category by id")
    @APIResponse(responseCode = "200",description = "Deleted the pet-category successfully")
    @APIResponse(responseCode = "400",description = "There is no such a pet-category")
    public Response deleteById(@PathParam("id") long id){
        if(animalTypeServices.deleteType(id)){
            return Response.status(Response.Status.OK).build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}
