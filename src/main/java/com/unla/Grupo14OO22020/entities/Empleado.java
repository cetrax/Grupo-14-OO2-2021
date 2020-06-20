package com.unla.Grupo14OO22020.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="empleado")//esto ahora si va, para que en la BD haga una tabla aparte de la de persona
public class Empleado extends Persona{
	
	
	@Column(name = "franjaHoraria")
	private String franjaHoraria;
	
	@Column(name = "esGerente")
	private boolean esGerente;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_local", nullable=false)
    private Local local;
	
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


	public Local getLocal() {
		return local;
	}


	public void setLocal(Local local) {
		this.local = local;
	}
	


}//fin class
