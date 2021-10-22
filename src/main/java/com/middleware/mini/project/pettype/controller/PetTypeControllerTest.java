package com.middleware.mini.project.pettype.controller;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;

import static org.junit.jupiter.api.Assertions.*;
@QuarkusTest
@Tag("integration")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PetTypeControllerTest {

    @Test
    @Order(1)
    void addType() {
        System.out.println("entered");
        JsonObject jsonObject= Json.createObjectBuilder()
                .add("categoryName","Dog")
                .add("family","dog family")
                .add("environment","any")
                .add("climate","any")
                .build();
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonObject.toString())
                .when()
                .post("/data/pet-type/add-type")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }

//    id
//    categoryName
//    family
//    environment
//    climate

    @Test
    void findTypeByName() {
    }

    @Test
    void getAll() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}
