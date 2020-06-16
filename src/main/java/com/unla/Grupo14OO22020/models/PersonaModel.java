package com.unla.Grupo14OO22020.models;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public abstract class PersonaModel {
	
protected int idPersona;
protected int dni;
protected String nombre;
protected String apellido;
@DateTimeFormat(pattern="yyyy-MM-dd")
protected Date fechaDeNacimiento;


public PersonaModel() {}


public PersonaModel(int idPersona, int dni, String nombre, String apellido, Date fechaDeNacimiento) {
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
	return "PersonaModel [idPersona=" + idPersona + ", dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido
			+ ", fechaDeNacimiento=" + fechaDeNacimiento + "]";
}

}//fin class
