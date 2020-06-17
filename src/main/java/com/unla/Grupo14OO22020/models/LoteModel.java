package com.unla.Grupo14OO22020.models;

import java.time.LocalDate;

import org.springframework.lang.Nullable;

public class LoteModel {
	private int idLote;
	private int cantidadInicial;
	private int cantidadActual;
	private LocalDate fechaIngreso;
	private ProductoModel producto;
	private boolean estado;
	@Nullable
	private LocalModel local;

	
	public LoteModel() { }
	
	public LoteModel(int idLote, int cantidadInicial, int cantidadActual, LocalDate fechaIngreso,
			ProductoModel producto, boolean estado) {
		this.idLote = idLote;
		this.cantidadInicial = cantidadInicial;
		this.cantidadActual = cantidadActual;
		this.fechaIngreso = fechaIngreso;
		this.producto = producto;
		this.estado = true;
	}

	public int getIdLote() {
		return idLote;
	}

	public void setIdLote(int idLote) {
		this.idLote = idLote;
	}

	public int getCantidadInicial() {
		return cantidadInicial;
	}

	public void setCantidadInicial(int cantidadInicial) {
		this.cantidadInicial = cantidadInicial;
	}

	public int getCantidadActual() {
		return cantidadActual;
	}

	public void setCantidadActual(int cantidadActual) {
		this.cantidadActual = cantidadActual;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public ProductoModel getProducto() {
		return producto;
	}

	public void setProducto(ProductoModel producto) {
		this.producto = producto;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}


	public LocalModel getLocal() {
		return local;
	}
	public void setLocal(LocalModel local) {
		this.local = local;
	}



	@Override
	public String toString() {
		return "Lote [idLote=" + idLote + ", cantidadInicial=" + cantidadInicial + ", cantidadActual=" + cantidadActual
				+ ", fechaIngreso=" + fechaIngreso + ", producto=" + producto + ", estado=" + estado + "]\n";
	}


}//Fin class
