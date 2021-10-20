package com.middleware.mini.project.pet.controller;

import com.middleware.mini.project.pet.dto.PetDTO;
import com.middleware.mini.project.pet.service.PetServices;

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

    @GET
    @Path("/filter-search")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
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


}
