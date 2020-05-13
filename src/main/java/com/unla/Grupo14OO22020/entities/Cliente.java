package com.unla.Grupo14OO22020.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="cliente")
public class Cliente extends Persona{

	@Column(name = "email")
	private String  email;
	
	public Cliente() {}

	public Cliente(int idPersona, int dni, String nombre, String apellido,Date fechaDeNacimiento,String email) {
		super(idPersona, dni, nombre, apellido, fechaDeNacimiento);
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
	
	
	
	
}
