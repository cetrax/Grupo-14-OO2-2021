package com.unla.Grupo14OO22020.converters;

import org.springframework.stereotype.Component;

import com.unla.Grupo14OO22020.entities.Empleado;
import com.unla.Grupo14OO22020.models.EmpleadoModel;

@Component("empleadoConverter")
public class EmpleadoConverter {
	
	public EmpleadoModel entityToModel(Empleado empleado) {
		return new EmpleadoModel(empleado.getIdPersona(), empleado.getDni(), empleado.getNombre(), empleado.getApellido(), empleado.getFechaDeNacimiento(),empleado.getFranjaHoraria(), empleado.isTipoEmpleado());
	}
	
	public Empleado modelToEntity(EmpleadoModel empleadoModel) {
		return new Empleado(empleadoModel.getIdPersona(), empleadoModel.getDni(), empleadoModel.getNombre(), empleadoModel.getApellido(), empleadoModel.getFechaDeNacimiento(),empleadoModel.getFranjaHoraria(), empleadoModel.isTipoEmpleado());
	}

}
