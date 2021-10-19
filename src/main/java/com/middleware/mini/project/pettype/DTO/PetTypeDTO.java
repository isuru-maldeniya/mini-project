package com.middleware.mini.project.pettype.DTO;

public class PetTypeDTO {
    private int id;
    private String categoryName;
    private String family;
    private String environment;
    private String climate;

    public PetTypeDTO(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public PetTypeDTO(int id, String categoryName, String family, String environment, String climate) {
        this.id = id;
        this.categoryName = categoryName;
        this.family = family;
        this.environment = environment;
        this.climate = climate;
    }

    public PetTypeDTO() {
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
