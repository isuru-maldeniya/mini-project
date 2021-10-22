package com.middleware.mini.project.pet.controller;

import com.middleware.mini.project.pet.dto.PetDTO;
import com.middleware.mini.project.pet.service.PetServices;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

@Path("/pet")
public class PetController {

    @Inject
    private PetServices services;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    @Operation(summary = "adding pet",description = "here you have to specify these properties name, color, owner, birthDate, gender and typeId in json. the typeId is the primary key of certain type. If the gender is true, that means it is a male, otherwise it is a female animal")
    @APIResponse(responseCode = "200",description = "Added the pet successfully")
    @APIResponse(responseCode = "400",description = "There is no such a pet type")
    public Response addPet(PetDTO dto){
        if(services.addPet(dto)){
            return Response.status(Response.Status.OK).build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    @GET
    @Path("/{id}")
    @Operation(summary = "Retrieve the pet with id",description = "Here it is essential to send the pet id in url")
    @APIResponse(responseCode = "200",description = "Pet is found")
    @APIResponse(responseCode = "400",description = "There is no such a pet")
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
    @Operation(summary = "give all the pets")
    @APIResponse(responseCode = "200",description = "Here is the pet list")
    @APIResponse(responseCode = "400",description = "There pet list is empty")
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
    @Operation(summary = "update the pet")
    @APIResponse(responseCode = "200",description = "Updated the pet successfully")
    @APIResponse(responseCode = "400",description = "There is no such a pet")
    public Response updatePet(PetDTO dto, @PathParam("id") long id){
        if(services.updatePet(dto,id)){
            return Response.status(Response.Status.OK).build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Operation(summary = "Delete the pet by id")
    @APIResponse(responseCode = "200",description = "Deleted the pet successfully")
    @APIResponse(responseCode = "400",description = "There is no such a pet")
    public Response deleteById(@PathParam("id")long id){
        if(services.deleteById(id)){
            return Response.status(Response.Status.OK).build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/filter-search")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    @Operation(summary = "Search the pet",description = "Here, filtering the pet by name age and type. you can give one attribute, two attributes or all the three attributes to filter")
    @APIResponse(responseCode = "200",description = "Here is the pet list")
    @APIResponse(responseCode = "400",description = "There pet list is empty")
    public Response filterSearch(
            @QueryParam("name") String name,
            @QueryParam("age") long age,
            @QueryParam("type") long type
    ){
        List<PetDTO> allPets=new LinkedList<>();
        if(name!=null && age!=0 && type!=0){
            allPets=services.findAllByNameAndAgeAndType(name,age,type);
        }else if(name!=null && age!=0){
            allPets=services.findAllByNameAndAge(name,age);
        }else if(name!=null && type!=0){
            allPets=services.findAllByNameAndType(name,type);
        }else if(age!=0 && type!=0){
            allPets=services.findAllByAgeAndType(age,type);
        }else if(name!=null){
            allPets=services.getSearchByName(name);
        }else if(age!=0){
            allPets=services.getAllByAge(age);
        }else if(type!=0){
            allPets=services.getAllByType(type);
        }else{
            allPets = services.getAllPets();

        }

        if(allPets.isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }else{
            return Response.ok(allPets).build();
        }

    }


    @GET
    @Path("/common-search")
    @Operation(summary = "Search the pet",description = "Here, Searching the pet by color, owner or name. you can give one of them as the parameter in the URL")
    @APIResponse(responseCode = "200",description = "Here is the pet list")
    @APIResponse(responseCode = "400",description = "There pet list is empty")
    public Response commonSearch(@QueryParam("content") String content){
        List<PetDTO> allByContent = services.getAllByContent(content);
        if(allByContent.isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }else{
            return Response.ok(allByContent).build();
        }
    }


}
