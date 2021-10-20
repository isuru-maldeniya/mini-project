package com.middleware.mini.project.pet.dto;

import java.time.LocalDate;

public class PetDTO {
    private long id;
    private String name;
    private String color;
    private String owner;
    private LocalDate birthDate;
    private boolean gender;
    private long typeId;

    public PetDTO(long id, String name, String color, String owner, LocalDate birthDate, boolean gender, long typeId) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.owner = owner;
        this.birthDate = birthDate;
        this.gender = gender;
        this.typeId = typeId;
    }

    public PetDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }


}
