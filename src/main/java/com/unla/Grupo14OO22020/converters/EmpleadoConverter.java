package com.unla.Grupo14OO22020.converters;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.unla.Grupo14OO22020.entities.Empleado;
import com.unla.Grupo14OO22020.models.EmpleadoModel;

@Component("empleadoConverter")
public class EmpleadoConverter {

//	@Autowired
//	@Qualifier("localConverter")
//	private LocalConverter localConverter;
	
	public EmpleadoModel entityToModel(Empleado empleado) {
		if(empleado!=null)
    		return new EmpleadoModel(empleado.getIdPersona(), empleado.getDni(), empleado.getNombre(), empleado.getApellido(), empleado.getFechaDeNacimiento(),empleado.getFranjaHoraria(), empleado.getEsGerente());
		else return null;
	}
	
	public Empleado modelToEntity(EmpleadoModel empleadoModel) {
		if(empleadoModel!=null)
		  return new Empleado(empleadoModel.getIdPersona(), empleadoModel.getDni(), empleadoModel.getNombre(), empleadoModel.getApellido(),
				empleadoModel.getFechaDeNacimiento(),empleadoModel.getFranjaHoraria(), empleadoModel.getEsGerente());
		else return null;
	}


//	
//	
//	public EmpleadoModel entityToModel(Empleado empleado) {
//		if(empleado!=null)
//			return new EmpleadoModel(empleado.getIdPersona(), empleado.getNombre(), empleado.getApellido(), empleado.getFechaDeNacimiento(),
//					empleado.getDni(), empleado.getFranjaHoraria(), empleado.isGerente(), localConverter.entityToModel(empleado.getLocal()));
//		else return null;
//	}
//
//	public Empleado modelToEntity(EmpleadoModel empleadoModel) {
//		if(empleadoModel!=null)
//			return new Empleado(empleadoModel.getIdPersona(),empleadoModel.getNombre(), empleadoModel.getApellido(), empleadoModel.getFechaNacimiento(),
//				empleadoModel.getDni(), empleadoModel.getFranjaHoraria(), empleadoModel.isGerente(), localConverter.modelToEntity(empleadoModel.getLocal()));
//		else return null;
//	}
	
}
