package com.middleware.mini.project.pettype.Services;

import com.middleware.mini.project.pettype.DTO.PetType;
import com.middleware.mini.project.pettype.Entity.AnimalType;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class AnimalTypeServices {


    public void addAnimalType(PetType type){
        AnimalType.persist(new AnimalType(type.getId(),type.getCategoryName()));
    }

//    public java.util.List<PetType> search(String name){
//        return AnimalType.getEntityManager().createNamedQuery("AnimalType.findByCategoryName").setParameter("categoryName",name).getResultList();
//    }
}
