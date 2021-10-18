package com.middleware.mini.project.DTO;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class PetType {
    private int id;
    private String categoryName;

    public PetType(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
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
