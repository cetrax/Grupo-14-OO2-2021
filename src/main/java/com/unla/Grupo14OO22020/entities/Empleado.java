package com.unla.Grupo14OO22020.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class Empleado extends Persona{
	
	
	@Column(name = "franjaHoraria")
	private String franjaHoraria;
	
	@Column(name = "esGerente")
	private boolean esGerente;
	
	
	public Empleado() { }
	
	
	public Empleado(int idPersona, int dni, String nombre, String apellido, Date fechaDeNacimiento,
			String franjaHoraria, boolean esGerente) {
		super(idPersona, dni, nombre, apellido, fechaDeNacimiento);
		this.franjaHoraria = franjaHoraria;
		this.esGerente = esGerente;
	}



	public String getFranjaHoraria() {
		return franjaHoraria;
	}



	public void setFranjaHoraria(String franjaHoraria) {
		this.franjaHoraria = franjaHoraria;
	}



	public boolean getEsGerente() {
		return esGerente;
	}



	public void setEsGerente(boolean esGerente) {
		this.esGerente = esGerente;
	}

	
}
