package com.middleware.mini.project.pettype.controller;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

class PetTypeControllerTest {

//    @Test
//    @Order(1)
//    void getAllWhenEmpty() {
//        given().when().get("data/pet-type/").then().statusCode(400);
//    }

    private void createType(){
        given().contentType(
                        MediaType.APPLICATION_JSON).body(
                        Json.createObjectBuilder().
                                add("categoryName","Dog").
                                add("family","Dog").
                                add("environment","house-hold").
                                add("climate","normal").
                                build().
                                toString()
                ).
                when().
                post("data/pet-type/add-type");
    }

    @Test
    @Order(2)
    void addType() {
        given().contentType(
            MediaType.APPLICATION_JSON).body(
                Json.createObjectBuilder().
                    add("categoryName","cat").
                    add("family","cat").
                    add("environment","house-hold").
                    add("climate","normal").
                    build().
                    toString()
        ).
        when().
        post("data/pet-type/add-type").
        then().
        statusCode(200);
    }

//    private long id;
//    private String categoryName;
//    private String family;
//    private String environment;
//    private String climate;

    @Test
    @Order(3)
    void updateTypeByNameSuccess() {
//        the database can be empty (the pettype id=1 is not existing)
        createType();
        given().contentType(MediaType.APPLICATION_JSON).body(
                Json.createObjectBuilder().
                        add("categoryName","cat1").
                        add("environment","house-hold-animal").
                        build().
                        toString()
        ).when().
            put("data/pet-type/update/1").
                then().
                statusCode(200);
    }

    @Test
    @Order(4)
    void updateTypeByNameWithError() {
        given().contentType(MediaType.APPLICATION_JSON).body(
                        Json.createObjectBuilder().
                                add("categoryName","cat1").
                                add("environment","house-hold-animal").
                                build().
                                toString()
                ).when().
                put("data/pet-type/update/4000").
                then().
                statusCode(400);
    }

    @Test
    @Order(5)
    void getAllSuccess() {
//        the database can be empty (the pettype id=1 is not existing)
        createType();
        given().when().get("data/pet-type/").then().statusCode(200);
    }


    @Test
    @Order(5)
    void findById() {
//        the database can be empty (the pettype id=1 is not existing)
        createType();
        given().when().get("data/pet-type/1").then().statusCode(200);
    }

    @Test
    @Order(6)
    void deleteByIdSuccess() {
//        the database can be empty (the pettype id=1 is not existing)
        createType();
//        first we are creating one type
        given().contentType(
                        MediaType.APPLICATION_JSON).body(
                        Json.createObjectBuilder().
                                add("categoryName","Dog").
                                add("family","Dog").
                                add("environment","house-hold").
                                add("climate","normal").
                                build().
                                toString()
                ).
                when().
                post("data/pet-type/add-type");

//        now we are deleting the type we previously created
        given().when().delete("data/pet-type/delete/1").then().statusCode(200);
    }

    @Test
    @Order(7)
    void deleteByIdWithError() {
        given().when().delete("data/pet-type/delete/5000").then().statusCode(400);
    }
}
