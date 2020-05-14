package com.unla.Grupo14OO22020.models;

import java.time.LocalDate;

public class LocalModel {
	
	private int idLocal;
	private String direccion;
	private double latitud;
	private double longitud;
	private String telefono;
	
	
	public LocalModel() {
		super();
	}


	public LocalModel(int idLocal, String direccion, double latitud, double longitud, String telefono) {
		super();
		this.idLocal = idLocal;
		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.telefono = telefono;
	}





	public int getIdLocal() {
		return idLocal;
	}


	protected void setIdLocal(int idLocal) {
		this.idLocal = idLocal;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public double getLatitud() {
		return latitud;
	}


	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}


	public double getLongitud() {
		return longitud;
	}


	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	@Override
	public String toString() {
		return "LocalModel [idLocal=" + idLocal + ", direccion=" + direccion + ", latitud=" + latitud + ", longitud="
				+ longitud + ", telefono=" + telefono + "]";
	}
	
	
	
	
	
	

}
