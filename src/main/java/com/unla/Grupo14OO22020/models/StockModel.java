package com.unla.Grupo14OO22020.models;
import java.util.Set;

import org.springframework.lang.Nullable;

import com.unla.Grupo14OO22020.entities.Lote;

public class StockModel {
	private int idStock;
	private int cantidad;
	@Nullable
	private LocalModel local;
	private Set<Lote> lotes;
	
	public StockModel() {
		
	}
	
	
	public StockModel(int idStock, int cantidad, LocalModel local) {
		this.idStock = idStock;
		this.cantidad = cantidad;
		this.local = local;
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
	
	
	public LocalModel getLocal() {
		return local;
	}

	public void setLocal(LocalModel local) {
		this.local = local;
	}

	public Set<Lote> getLotes() {
		return lotes;
	}
	public void setLotes(Set<Lote> lotes) {
		this.lotes = lotes;
	}
	
}// fin class
