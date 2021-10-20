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
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
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

    @Transactional
    public boolean updatePet(PetDTO dto,long id){
        Pet byId = petRepository.findById(id);
        if(byId==null){
            return false;
        }
        if(dto.getName()!=null && dto.getName()!=""){
            byId.setName(dto.getName());
        }
        if(dto.getColor()!=null && dto.getColor()!=""){
            byId.setColor(dto.getColor());
        }
        if(dto.getOwner()!=null && dto.getOwner()!=""){
            byId.setOwner(dto.getOwner());
        }
        if(dto.getBirthDate()!=null){
            byId.setBirthDate(dto.getBirthDate());
        }
        return true;
    }

    @Transactional
    public boolean deleteById(long id){
        Pet byId = petRepository.findById(id);
        if(byId==null){
            return false;
        }else{
            return petRepository.deleteById(id);
        }
    }

    public java.util.List<PetDTO> getSearchByName(String name){
        return petRepository.list(
                "SELECT m FROM Pet m WHERE name like ?1",name+"%").stream().map(pet -> new PetDTO(
                pet.getId(),
                pet.getName(),
                pet.getColor(),
                pet.getOwner(),
                pet.getBirthDate(),
                pet.isGender(),
                pet.getType().getId()
        )).collect(Collectors.toList());

    }

    public java.util.List<PetDTO> getAllByAge(long age){
        return petRepository.list("SELECT m FROM Pet m WHERE YEAR(CURRENT_TIMESTAMP)-YEAR(m.birthDate)=?1",(int)age).stream().map(pet ->new PetDTO(
                pet.getId(),
                pet.getName(),
                pet.getColor(),
                pet.getOwner(),
                pet.getBirthDate(),
                pet.isGender(),
                pet.getType().getId()
        )).collect(Collectors.toList());
    }

    public java.util.List<PetDTO> getAllByType(long type){
        java.util.List<PetDTO> petDTOS=new LinkedList<>();
        petRepository.findAll().stream().forEach(pet -> {
            if(pet.getType().getId()==type){
                petDTOS.add(
                        new PetDTO(
                                pet.getId(),
                                pet.getName(),
                                pet.getColor(),
                                pet.getOwner(),
                                pet.getBirthDate(),
                                pet.isGender(),
                                pet.getType().getId()

                ));
            }
        });
        return petDTOS;
    }

    public java.util.List<PetDTO> findAllByAgeAndType(long age, long type){
        java.util.List<PetDTO> petDTOS=new LinkedList<>();
        petRepository.list("SELECT m FROM Pet m WHERE YEAR(CURRENT_TIMESTAMP)-YEAR(m.birthDate)=?1",(int)age).stream().forEach(pet ->{
            if(pet.getType().getId()==type){
                petDTOS.add(
                        new PetDTO(
                                pet.getId(),
                                pet.getName(),
                                pet.getColor(),
                                pet.getOwner(),
                                pet.getBirthDate(),
                                pet.isGender(),
                                pet.getType().getId()
                        )
            );}
        });
        return petDTOS;
    }

    public java.util.List<PetDTO> findAllByNameAndType(String name, long type){
        java.util.List<PetDTO> petDTOS=new LinkedList<>();
        petRepository.list(  "SELECT m FROM Pet m WHERE name like ?1",name+"%").stream().forEach(pet ->{
            if(pet.getType().getId()==type){
                petDTOS.add(
                        new PetDTO(
                                pet.getId(),
                                pet.getName(),
                                pet.getColor(),
                                pet.getOwner(),
                                pet.getBirthDate(),
                                pet.isGender(),
                                pet.getType().getId()
                        )
                );}
        });
        return petDTOS;
    }

    public java.util.List<PetDTO> findAllByNameAndAge(String name, long age){
            return petRepository.list("SELECT m FROM Pet m WHERE YEAR(CURRENT_TIMESTAMP)-YEAR(m.birthDate)=?1",(int)age).stream().filter(
                    pet -> pet.getName().contains(name)
            ).map(pet ->new PetDTO(
                pet.getId(),
                pet.getName(),
                pet.getColor(),
                pet.getOwner(),
                pet.getBirthDate(),
                pet.isGender(),
                pet.getType().getId()
        )).collect(Collectors.toList());
    }
    public java.util.List<PetDTO> findAllByNameAndAgeAndType(String name, long age, long type){
        return petRepository.list("SELECT m FROM Pet m WHERE YEAR(CURRENT_TIMESTAMP)-YEAR(m.birthDate)=?1",(int)age).stream().filter(
                pet -> pet.getName().contains(name)
        ).filter(
                pet ->pet.getType().getId()==type
        ).map(pet ->new PetDTO(
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
