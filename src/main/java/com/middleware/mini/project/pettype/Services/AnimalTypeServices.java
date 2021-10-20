package com.middleware.mini.project.pettype.Services;

import com.middleware.mini.project.pettype.DTO.PetTypeDTO;
import com.middleware.mini.project.pettype.Entity.PetType;
import com.middleware.mini.project.pettype.Entity.PetTypeRegistry;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
@Transactional
public class AnimalTypeServices {
    @Inject
    private PetTypeRegistry petTypeRegistry;


    public boolean addAnimalType(PetTypeDTO type){
        PetType petType = new PetType(
                type.getCategoryName(),
                type.getFamily(),
                type.getEnvironment(),
                type.getClimate()
        );
        petTypeRegistry.persist(petType);
        return petTypeRegistry.isPersistent(petType);
    }

    public PetTypeDTO getTypeById(long id){
        PetTypeDTO petTypeDTO=null;
        List<PetTypeDTO> list = petTypeRegistry.list("id", id).stream().map(type -> new PetTypeDTO(
                type.getId(),
                type.getCategoryName(),
                type.getFamily(),
                type.getEnvironment(),
                type.getClimate()
        )).collect(Collectors.toList());

        if(list.isEmpty()){
            return null;
        }else{
            return list.get(0);
        }
    }

    public List<PetTypeDTO> getAll(){
        return petTypeRegistry.findAll().stream().map(type -> new PetTypeDTO(
                type.getId(),
                type.getCategoryName(),
                type.getFamily(),
                type.getEnvironment(),
                type.getClimate()
        )).collect(Collectors.toList());
    }


    @Transactional
    public boolean update(PetTypeDTO dto, long id){
        PetType byId=null;
        byId = petTypeRegistry.findById(id);
        if(byId==null){
            return false;
        }

        if(dto.getClimate()!="" && dto.getClimate()!=null){
            byId.setClimate(dto.getClimate());
        }
        if(dto.getCategoryName()!="" && dto.getCategoryName()!=null){
            byId.setCategoryName(dto.getCategoryName());
        }
        if(dto.getEnvironment()!="" && dto.getEnvironment()!=null){
            byId.setEnvironment(dto.getEnvironment());
        }
        if(dto.getFamily()!="" && dto.getFamily()!=null){
            byId.setFamily(dto.getFamily());
        }
        return true;
    }
//    public java.util.List<PetType> search(String name){
//        return AnimalType.getEntityManager().createNamedQuery("AnimalType.findByCategoryName").setParameter("categoryName",name).getResultList();
//    }
}
