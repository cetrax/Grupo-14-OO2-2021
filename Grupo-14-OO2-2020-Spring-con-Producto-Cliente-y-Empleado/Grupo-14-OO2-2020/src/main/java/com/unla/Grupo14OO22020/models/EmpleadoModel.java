package com.unla.Grupo14OO22020.models;

import java.sql.Date;
import java.time.LocalDate;

import net.bytebuddy.asm.Advice.Local;

public class EmpleadoModel extends PersonaModel{
	
	private String franjaHoraria;
	private boolean tipoEmpleado; // true = Vendedor, false = Gerente.



	

	public EmpleadoModel(int idPersona, int dni, String nombre, String apellido, java.util.Date fechaDeNacimiento,
			String franjaHoraria, boolean tipoEmpleado) {
		super(idPersona, dni, nombre, apellido, fechaDeNacimiento);
		this.franjaHoraria = franjaHoraria;
		this.tipoEmpleado = tipoEmpleado;
	}

	public EmpleadoModel() {
		// TODO Auto-generated constructor stub
	}

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
