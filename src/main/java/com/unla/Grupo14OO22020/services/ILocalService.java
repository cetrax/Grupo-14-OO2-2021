package com.unla.Grupo14OO22020.services;

import java.util.List;

import com.unla.Grupo14OO22020.entities.Localito;
import com.unla.Grupo14OO22020.models.LocalModel;

public interface ILocalService {

	public abstract List<Localito> getAll();

	public LocalModel insertOrUpdate(LocalModel localModel);

	public LocalModel findByIdLocal(int id);

	public boolean remove(int id);
}//Fin class