package com.middleware.mini.project.pet.service;

import com.middleware.mini.project.pet.dto.PetDTO;
import com.middleware.mini.project.pet.entity.Pet;
import com.middleware.mini.project.pet.entity.PetRepository;
import com.middleware.mini.project.pettype.Entity.PetType;
import com.middleware.mini.project.pettype.Entity.PetTypeRegistry;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.awt.*;
import java.util.LinkedList;
import java.util.stream.Collectors;

@ApplicationScoped
public class PetServices {

    @Inject
    private PetRepository petRepository;

    @Inject
    private PetTypeRegistry typeRegistry;

    @Transactional
    public boolean addPet(PetDTO dto){
        PetType byId = typeRegistry.findById(dto.getTypeId());
        if(byId==null){
            return false;
        }
        petRepository.persist(new Pet(
                dto.getName(),
                dto.getColor(),
                dto.getOwner(),
                dto.getBirthDate(),
                byId,
                dto.isGender()
        ));
        return true;
    }

    public PetDTO getById(long id){
        Pet byId = petRepository.findById(id);
        PetDTO dto=null;
        if(byId!=null){
            dto=new PetDTO(
                    byId.getId(),
                    byId.getName(),
                    byId.getColor(),
                    byId.getOwner(),
                    byId.getBirthDate(),
                    byId.isGender(),
                    byId.getType().getId()
            );
        }
        return dto;
    }

    public java.util.List<PetDTO> getAllPets(){
        return petRepository.findAll().stream().map(pet -> new PetDTO(
                pet.getId(),
                pet.getName(),
                pet.getColor(),
                pet.getOwner(),
                pet.getBirthDate(),
                pet.isGender(),
                pet.getType().getId()
        )).collect(Collectors.toList());
    }


}
