package com.middleware.mini.project.pettype.Services;

import com.middleware.mini.project.pettype.DTO.PetTypeDTO;
import com.middleware.mini.project.pettype.Entity.PetType;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class AnimalTypeServices {


    public boolean addAnimalType(PetTypeDTO type){
        PetType petType = new PetType(type.getCategoryName(), type.getFamily(), type.getEnvironment(), type.getClimate());
        PetType.persist(petType);
        return petType.isPersistent();
    }

//    public java.util.List<PetType> search(String name){
//        return AnimalType.getEntityManager().createNamedQuery("AnimalType.findByCategoryName").setParameter("categoryName",name).getResultList();
//    }
}
