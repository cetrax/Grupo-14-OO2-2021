package com.unla.Grupo14OO22020.models;

import java.util.Date;

public class EmpleadoModel extends PersonaModel{
	
	private String franjaHoraria;
	private boolean tipoEmpleado; // true = Vendedor, false = Gerente.


	public EmpleadoModel(int idPersona, int dni, String nombre, String apellido, Date fechaDeNacimiento,
			String franjaHoraria, boolean tipoEmpleado) {
		super(idPersona, dni, nombre, apellido, fechaDeNacimiento);
		this.franjaHoraria = franjaHoraria;
		this.tipoEmpleado = tipoEmpleado;
	}

	public EmpleadoModel() { }

	public String getFranjaHoraria() {
		return franjaHoraria;
	}

	public void setFranjaHoraria(String franjaHoraria) {
		this.franjaHoraria = franjaHoraria;
	}



	public boolean isTipoEmpleado() {
		return tipoEmpleado;
	}

	public void setTipoEmpleado(boolean tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
	}

}
