package com.unla.Grupo14OO22020.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import org.hibernate.annotations.CreationTimestamp;



@Entity
@Table(name = "lote")
public class Lote {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idLote;

	@Column(name = "cantidadInicial")
	private int cantidadInicial;

	@Column(name = "cantidadActual")
	private int cantidadActual;

	
	@Column(name = "fechaIngreso")
	@CreationTimestamp
	private LocalDate fechaIngreso;
	
	
	@OneToOne(cascade = CascadeType.MERGE)
	private Producto producto;
	
	@Column(name = "estado")
	private boolean estado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_local", nullable=false)
    private Local local;
	

	public Lote() {	}

	public Lote(int idLote, int cantidadInicial, int cantidadActual, LocalDate fechaIngreso, Producto producto,
			boolean estado) {
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

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

}//Fin class
