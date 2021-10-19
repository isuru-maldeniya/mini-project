package com.middleware.mini.project.pettype.Services;

import com.middleware.mini.project.pettype.DTO.PetTypeDTO;
import com.middleware.mini.project.pettype.Entity.PetType;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class AnimalTypeServices {


    public void addAnimalType(PetTypeDTO type){
        PetType.persist(new PetType(type.getId(),type.getCategoryName()));
    }

//    public java.util.List<PetType> search(String name){
//        return AnimalType.getEntityManager().createNamedQuery("AnimalType.findByCategoryName").setParameter("categoryName",name).getResultList();
//    }
}
