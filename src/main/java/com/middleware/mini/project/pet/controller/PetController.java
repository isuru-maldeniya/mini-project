package com.middleware.mini.project.pet.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/pet")
public class PetController {

    @GET
    @Path("test")
    public void test(){
        System.out.println("this is test");
    }
}
