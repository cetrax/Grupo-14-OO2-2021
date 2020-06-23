package com.unla.Grupo14OO22020.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo14OO22020.converters.EmpleadoConverter;
import com.unla.Grupo14OO22020.converters.LocalConverter;
import com.unla.Grupo14OO22020.entities.Empleado;
import com.unla.Grupo14OO22020.entities.Local;
import com.unla.Grupo14OO22020.models.EmpleadoModel;
import com.unla.Grupo14OO22020.repositories.IEmpleadoRepository;
import com.unla.Grupo14OO22020.repositories.ILocalRepository;
import com.unla.Grupo14OO22020.services.ILocalService;
import com.unla.Grupo14OO22020.services.IEmpleadoService;

@Service("empleadoService")
public class EmpleadoService implements IEmpleadoService{
	
	@Autowired
	@Qualifier("empleadoRepository")
	private IEmpleadoRepository empleadoRepository;
	
	@Autowired
	@Qualifier("localService")
	private ILocalService localService;
	
	@Autowired
	@Qualifier("localRepository")
	private ILocalRepository localRepository;
	
	@Autowired
	@Qualifier("empleadoConverter")
	private EmpleadoConverter empleadoConverter;
	
	@Autowired
	@Qualifier("localConverter")
	private LocalConverter localConverter;
	
	@Override
	public List<Empleado> getAll(){
		return empleadoRepository.findAll();
	}
	
	@Override
	public EmpleadoModel insertOrUpdate(EmpleadoModel empleadoModel) {
		Local local = localRepository.findByIdLocal(empleadoModel.getLocal().getIdLocal());
		Empleado empleado = empleadoConverter.modelToEntity(empleadoModel);
        empleado.setLocal(local);
		empleado = empleadoRepository.save(empleado);
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
