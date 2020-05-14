package com.unla.Grupo14OO22020.entities;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import net.bytebuddy.asm.Advice.Local;

@Entity
@Table(name = "empleado")
public class Empleado extends Persona{
	
	
	@Column(name = "franjaHoraria")
	private String franjaHoraria;
	
	@Column(name = "tipoEmpleado")
	private boolean tipoEmpleado;
	
	
	public Empleado() {
		
	}
	
	

	public Empleado(int idPersona, int dni, String nombre, String apellido, java.util.Date fechaDeNacimiento,
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
