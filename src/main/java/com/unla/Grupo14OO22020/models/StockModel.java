package com.unla.Grupo14OO22020.models;
import java.util.List;
import java.util.Set;

import com.unla.Grupo14OO22020.entities.Lote;

public class StockModel {
	private int idStock;
	private int cantidad;
	private Set<Lote> lotes;
	
	public StockModel() {
		
	}
	
	public StockModel(int idStock, int cantidad,Set<Lote>lotes) {
		super();
		this.idStock = idStock;
		this.cantidad = cantidad;
		this.lotes=lotes;
	}
	public int getIdStock() {
		return idStock;
	}
	protected void setIdStock(int idStock) {
		this.idStock = idStock;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Set<Lote> getLotes() {
		return lotes;
	}
	public void setLotes(Set<Lote> lotes) {
		this.lotes = lotes;
	}
	
	
	
	
}
