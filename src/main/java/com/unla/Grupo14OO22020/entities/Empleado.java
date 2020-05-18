package com.unla.Grupo14OO22020.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "empleado")
public class Empleado extends Persona{
	
	
	@Column(name = "franjaHoraria")
	private String franjaHoraria;
	
	@Column(name = "tipoEmpleado")
	private boolean tipoEmpleado;
	
	
	public Empleado() {
		
	}
	
	

	public Empleado(int idPersona, int dni, String nombre, String apellido, Date fechaDeNacimiento,
			String franjaHoraria, boolean tipoEmpleado) {
		super(idPersona, dni, nombre, apellido, fechaDeNacimiento);
		this.franjaHoraria = franjaHoraria;
		this.tipoEmpleado = tipoEmpleado;
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



	@Override
	public String toString() {
		return "Empleado [franjaHoraria=" + franjaHoraria + ", tipoEmpleado=" + tipoEmpleado + "]";
	}
	
}
