package com.middleware.mini.project.pettype.Entity;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
@NamedQuery(name="AnimalType.findByCategoryName",query="SELECT a FROM AnimalType a WHERE a.categoryName = :categoryName")
public class AnimalType extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String categoryName;

    public AnimalType(int id,String categoryName) {
        this.categoryName = categoryName;
//        this.id=id;
    }

    public AnimalType() {
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
}
