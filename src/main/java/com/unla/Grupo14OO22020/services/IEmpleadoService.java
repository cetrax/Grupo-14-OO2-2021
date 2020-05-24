package com.unla.Grupo14OO22020.services;

import java.util.List;

import com.unla.Grupo14OO22020.entities.Empleado;
import com.unla.Grupo14OO22020.models.EmpleadoModel;



public interface IEmpleadoService {
	
	public abstract List<Empleado> getAll();
	
	public EmpleadoModel insertOrUpdate(EmpleadoModel empleadoModel);
	
	public EmpleadoModel findByIdPersona(int id);
	
	public boolean remove(int id);

}
