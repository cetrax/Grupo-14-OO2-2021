package com.unla.Grupo14OO22020.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sistema.application.entities.Chango;
import com.unla.Grupo14OO22020.models.Lote;

@Entity
@Table(name = "stock")
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idStock;
	
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="Stock")
	private List<Lote> lotes;
	
	@Column(name = "cantidad")
	private int cantidad;
	
	public Stock() {}

	public Stock(int idStock, int cantidad) {
		super();
		this.idStock = idStock;
		this.cantidad = cantidad;
	}

	public int getIdStock() {
		return idStock;
	}

	public void setIdStock(int idStock) {
		this.idStock = idStock;
	}

	public List<Lote> getLotes() {
		return lotes;
	}

	public void setLotes(List<Lote> lotes) {
		this.lotes = lotes;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
	
}
