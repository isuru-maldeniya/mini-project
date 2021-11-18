package com.middleware.mini.project.pet.controller;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

class PetControllerTest {

    private void addType(){
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

    private void createPet(String name,String color,String owner,String birthDate){
//        first we are creating a type because there can be the database empty (the pettype id= 1 record is not existing)
        addType();
//        now we are creating the pet with type id =1
        given().contentType(
                        MediaType.APPLICATION_JSON).body(
                        Json.createObjectBuilder().
                                add("name",name).
                                add("color",color).
                                add("owner",owner).
                                add("birthDate",birthDate).
                                add("gender",true).
                                add("typeId",1).
                                build().
                                toString()
                ).
                when().
                post("data/pet/add").
                then().
                statusCode(200);


    }

    @Test
    @Order(1)
    void addPetSuccessfully() {
//        first we are creating one type
        addType();
//        now we are creating the new pet
        given().contentType(
            MediaType.APPLICATION_JSON).body(
                Json.createObjectBuilder().
                add("name","Jimmy").
                add("color","black").
                add("owner","Sumith").
                add("birthDate","2010-01-01").
                add("gender",true).
                add("typeId",1).
                build().
                toString()
        ).
        when().
        post("data/pet/add").
        then().
        statusCode(200);

    }


    @Test
    @Order(2)
    void addPetWithError() {
        given().contentType(
            MediaType.APPLICATION_JSON).body(
                Json.createObjectBuilder().
                    add("name","Jimmy").
                    add("color","black").
                    add("owner","Sumith").
                    add("birthDate","2010-01-01").
                    add("gender",true).
                    add("typeId",890890).
                    build().
                    toString()
                ).
        when().
        post("data/pet/add").
        then().
        statusCode(400);

    }
//    private long id;
//    private String name;
//    private String color;
//    private String owner;
//    private LocalDate birthDate;
//    private boolean gender;
//    private long typeId;


    @Test
    @Order(3)
    void getPetByIdSuccess() {
//        first we are creating pet because there can be the database empty ( the pet id= 1 record can be empty )
        createPet("Tim","Black","Jhone","2010-10-10");
        given().when().get("data/pet/1").then().statusCode(200);
    }

    @Test
    @Order(4)
    void getPetByIdWithError() {
        given().when().get("data/pet/1000").then().statusCode(400);
    }

    @Test
    @Order(5)
    void giveAllPets() {
//        first we are creating pet because there can be the database empty
        createPet("Tim","Black","Jhone","2010-10-10");
        given().when().get("data/pet/").then().statusCode(200);
    }

    @Test
    @Order(6)
    void updatePetSuccess() {
//        first we are creating because there can be the database empty (the pet id= 1 record can be empty)
        createPet("Miw","White","Rose","2011-08-09");
//        now we are updating the pet which id =1
        given().contentType(
                        MediaType.APPLICATION_JSON).body(
                        Json.createObjectBuilder().
                                add("color","White").
                                add("owner","Damith").
                                add("birthDate","2010-01-01").
                                add("gender",false).
                                build().
                                toString()
                ).
                when().
                put("data/pet/update/1").
                then().
                statusCode(200);
    }

    @Test
    @Order(6)
    void updatePetWithError() {
        given().contentType(
                        MediaType.APPLICATION_JSON).body(
                        Json.createObjectBuilder().
                                add("color","White").
                                add("owner","Damith").
                                add("birthDate","2010-01-01").
                                add("gender",false).
                                build().
                                toString()
                ).
                when().
                put("data/pet/update/4000").
                then().
                statusCode(400);
    }
    @Test
    @Order(7)
    void deleteById() {

//        first we are creating because there can be the database empty( the pet id= 1 record can be empty )
        createPet("Sera","Black","Jhone","2018-01-01");

//        now we are deleting the pet which id =1
        given().when().delete("data/pet/delete/1").then().statusCode(200);
    }

    @Test
    @Order(8)
    void filterSearch() {
//        first we are creating three pet
        createPet("Tommy","White","Jhone","2017-01-01");
        createPet("Tom","White","Jhone","2017-01-01");
        createPet("Lara","Black","Jhone","2018-01-01");

//        success testing
        given().when().get("data/pet/filter-search?name=Tom&age=4").then().statusCode(200);
//        failure testing
        given().when().get("data/pet/filter-search?name=Kitty&age=6").then().statusCode(400);

    }


    @Test
    @Order(9)
    void commonSearch() {
        createPet("Lara","Black","Jhone","2018-01-01");

//        success testing
        given().when().get("data/pet/common-search?content=Lar").then().statusCode(200);
//        failure testing
        given().when().get("data/pet/common-search?content=Juisysdgkfgdol").then().statusCode(400);


    }
}
