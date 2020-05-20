package com.unla.Grupo14OO22020.models;
import java.util.ArrayList;
import java.util.List;

public class StockModel {
	private int idStock;
	private List<Lote> lotes;
	private int cantidad;
	
	
	public StockModel() {}
	
	public StockModel(int idStock, int cantidad) {
		super();
		this.idStock=idStock;
		this.lotes = new ArrayList<Lote>;
		this.cantidad = cantidad;
		
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

	public int getIdStock() {
		return idStock;
	}

	public void setIdStock(int idStock) {
		this.idStock = idStock;
	}
	
	
}
