package com.middleware.mini.project.pettype.Entity;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
@NamedQuery(name="AnimalType.findByCategoryName",query="SELECT a FROM PetType a WHERE a.categoryName = :categoryName")
public class PetType extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String categoryName;
    private String family;
    private String environment;
    private String climate;

    public PetType(int id, String categoryName) {
        this.categoryName = categoryName;
//        this.id=id;
    }

    public PetType(String categoryName, String family, String environment, String climate) {
        this.categoryName = categoryName;
        this.family = family;
        this.environment = environment;
        this.climate = climate;
    }

    public PetType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }
}
