package com.middleware.mini.project.pet.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestMethodOrder;

import javax.json.Json;
import javax.json.JsonObject;
import javax.transaction.Status;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;
import java.time.LocalDate;
import io.quarkus.test.junit.QuarkusTest;

import static org.junit.jupiter.api.Assertions.*;
@QuarkusTest
@Tag("integration")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PetControllerTest {

    @org.junit.jupiter.api.Test
    @Order(1)
    void addPet() {

        JsonObject jsonObject= Json.createObjectBuilder()
                .add("name","Tomy")
                .add("color","white")
                .add("owner","Samson")
                .add("birthDate","2020-10-10")
                .add("gender",true)
                .add("typeId",1)
                .build();
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonObject.toString())
                .when()
                .post("/data/pet/add")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

    }

//    name
//    color
//    owner
//    birthDate
//    gender
//    typeId

    @org.junit.jupiter.api.Test
    void getPetById() {
    }

    @org.junit.jupiter.api.Test
    void giveAllPets() {
    }

    @org.junit.jupiter.api.Test
    void updatePet() {
    }

    @org.junit.jupiter.api.Test
    void deleteById() {
    }

    @org.junit.jupiter.api.Test
    void filterSearch() {
    }

    @org.junit.jupiter.api.Test
    void commonSearch() {
    }
}
