package com.unla.Grupo14OO22020.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="persona")
public abstract class Persona {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int idPersona;
	
	@Column(name = "dni")
	protected int dni;
	
	@Column(name = "nombre")
	protected String nombre;
	
	@Column(name = "apellido")
	protected String apellido;
	
	@Column(name = "fechaDeNacimiento")
	@Temporal(TemporalType.DATE)
	protected Date fechaDeNacimiento;
	
	Persona(){}

	public Persona(int idPersona, int dni, String nombre, String apellido,Date fechaDeNacimiento) {
		super();
		this.idPersona = idPersona;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	@Override
	public String toString() {
		return "Persona [idPersona=" + idPersona + ", dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", fechaDeNacimiento=" + fechaDeNacimiento + "]";
	}
	

}
