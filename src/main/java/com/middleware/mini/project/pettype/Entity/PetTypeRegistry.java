package com.middleware.mini.project.pettype.Entity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PetTypeRegistry implements PanacheRepository<PetType> {

}
