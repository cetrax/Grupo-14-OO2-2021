package com.unla.Grupo14OO22020.services;

import com.unla.Grupo14OO22020.entities.User;

public interface IUserService {

	public Iterable<User> getAllUsers();

	public User createUser(User user) throws Exception;

}