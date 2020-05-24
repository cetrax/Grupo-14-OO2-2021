package com.unla.Grupo14OO22020.models;

import java.util.Date;

public class ClienteModel extends PersonaModel{
private String email;

public ClienteModel(){}

public ClienteModel(int idPersona, int dni, String nombre, String apellido,Date fechaDeNacimiento,String email) {
	super(idPersona, dni, nombre, apellido, fechaDeNacimiento);
	this.email = email;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

@Override
public String toString() {
	return "ClienteModel [email=" + email + "," + super.toString() + "]";
}





	
	
	
}
