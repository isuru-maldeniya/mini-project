package com.middleware.mini.project.pettype.dto;

public class PetTypeDTO {
    private long id;
    private String categoryName;
    private String family;
    private String environment;
    private String climate;

    public PetTypeDTO(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public PetTypeDTO(long id, String categoryName, String family, String environment, String climate) {
        this.id = id;
        this.categoryName = categoryName;
        this.family = family;
        this.environment = environment;
        this.climate = climate;
    }

    public PetTypeDTO() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
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
