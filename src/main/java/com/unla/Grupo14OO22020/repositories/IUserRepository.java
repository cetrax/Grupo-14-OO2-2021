package com.unla.Grupo14OO22020.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.unla.Grupo14OO22020.entities.User;

@Repository
public interface IUserRepository extends CrudRepository<User, Long>  {
	public Optional<User> findByUsername(String username);
}