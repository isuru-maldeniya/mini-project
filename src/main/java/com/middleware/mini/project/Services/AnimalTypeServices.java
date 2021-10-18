package com.middleware.mini.project.Services;

import com.middleware.mini.project.DTO.PetType;
import com.middleware.mini.project.Entity.AnimalType;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class AnimalTypeServices {


    public void addAnimalType(PetType type){
        AnimalType.persist(new AnimalType(type.getId(),type.getCategoryName()));
    }
}
