package com.unla.Grupo14OO22020.services;

import java.util.List;

import com.unla.Grupo14OO22020.entities.Local;
import com.unla.Grupo14OO22020.models.LocalModel;

public interface ILocalService {

	public abstract List<Local> getAll();

//	public List<Localito> traerLocalesConTotalStock(); //este método lo usabamos agregando la columna "StockCantidad" en el entities
	
	public LocalModel insertOrUpdate(LocalModel localModel);

	public LocalModel findByIdLocal(int id);

	public boolean remove(int id);
}//Fin class