package com.middleware.mini.project.pet.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.middleware.mini.project.pettype.Entity.PetType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pets")
@NamedQuery(name = "getAllByName",query = "SELECT m FROM Pet m WHERE m.name LIKE ?1")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String color;
    private String owner;
    private LocalDate birthDate;
    private boolean gender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    @JsonBackReference
    private PetType type;

    public Pet() {
    }

    public Pet(String name, String color, String owner, LocalDate birthDate, PetType type, boolean gender) {
        this.name = name;
        this.color = color;
        this.owner = owner;
        this.birthDate = birthDate;
        this.type = type;
        this.gender=gender;
    }

    public long getId() {
        return id;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
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

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

}
