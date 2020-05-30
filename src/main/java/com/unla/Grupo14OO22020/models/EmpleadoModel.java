package com.unla.Grupo14OO22020.models;

import java.util.Date;

public class EmpleadoModel extends PersonaModel{
	
	private String franjaHoraria;
	private boolean esGerente; // true = Gerente, false = Vendedor.


	public EmpleadoModel() { }
	
	
	public EmpleadoModel(int idPersona, int dni, String nombre, String apellido, Date fechaDeNacimiento,
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
