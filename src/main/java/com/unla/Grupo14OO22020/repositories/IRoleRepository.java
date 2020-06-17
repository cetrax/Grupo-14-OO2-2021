package com.unla.Grupo14OO22020.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.unla.Grupo14OO22020.entities.Role;

@Repository
public interface IRoleRepository extends CrudRepository<Role, Long>{

}