package com.unla.Grupo14OO22020.models;

 import java.util.Set;

import org.springframework.lang.Nullable;
import com.unla.Grupo14OO22020.entities.Lote;
import com.unla.Grupo14OO22020.entities.Pedido;
import com.unla.Grupo14OO22020.entities.Empleado;

public class LocalModel{

	private int idLocal;
	private String direccion;
	private double latitud;
	private double longitud;
	private long telefono;
	@Nullable
    private Set<Lote> lotes;
	@Nullable
    private Set<Empleado> empleados;  
	@Nullable
    private Set<Pedido> pedidos;  
	
	
	public LocalModel(){ }

	public LocalModel(int idLocal, String direccion, double latitud, double longitud, long telefono) {
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

	public void setIdLocal(int idLocal) {
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

	public long getTelefono() {
		return telefono;
	}

	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}


	public Set<Lote> getLotes() {
		return lotes;
	}

	public void setLotes(Set<Lote> lotes) {
		this.lotes = lotes;
	}

	public Set<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(Set<Empleado> empleados) {
		this.empleados = empleados;
	}


	public Set<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public String toString() {
		return "LocalModel [idLocal=" + idLocal + ", direccion=" + direccion + ", latitud=" + latitud + ", longitud="
				+ longitud + ", telefono=" + telefono + "]";
	}


// IMPLEMENTADO EN EL CONTROLLER POR CONSEJO DEL PROFE
//	public double calcularDistancia(LocalModel local) {
//		double radioTierra = 6371; // en kilómetros
//		double dLat = Math.toRadians(local.latitud - this.latitud);
//		double dLng = Math.toRadians(local.longitud - this.longitud);
//		double sindLat = Math.sin(dLat / 2);
//		double sindLng = Math.sin(dLng / 2);
//		double va1 = Math.pow(sindLat, 2)
//				+ Math.pow(sindLng, 2) * Math.cos(Math.toRadians(this.latitud)) 
//				* Math.cos(Math.toRadians(local.latitud));
//		double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
//		return radioTierra * va2;
//	}	

}
