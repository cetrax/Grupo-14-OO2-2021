package com.unla.Grupo14OO22020.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo14OO22020.converters.EmpleadoConverter;
import com.unla.Grupo14OO22020.entities.Empleado;
import com.unla.Grupo14OO22020.models.EmpleadoModel;
import com.unla.Grupo14OO22020.repositories.IEmpleadoRepository;
import com.unla.Grupo14OO22020.services.IEmpleadoService;

@Service("empleadoService")
public class EmpleadoService implements IEmpleadoService{
	
	@Autowired
	@Qualifier("empleadoRepository")
	private IEmpleadoRepository empleadoRepository;
	
	@Autowired
	@Qualifier("empleadoConverter")
	private EmpleadoConverter empleadoConverter;
	
	@Override
	public List<Empleado> getAll(){
		return empleadoRepository.findAll();
	}
	
	@Override
	public EmpleadoModel insertOrUpdate(EmpleadoModel empleadoModel) {
		Empleado empleado = empleadoRepository.save(empleadoConverter.modelToEntity(empleadoModel));
		return empleadoConverter.entityToModel(empleado);
	}
	
	@Override
	public EmpleadoModel findByIdPersona(int id) {
		return empleadoConverter.entityToModel(empleadoRepository.findByIdPersona(id));
	}
	
	@Override
	public boolean remove(int id) {
		try {
			empleadoRepository.deleteById(id);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	
	

}
