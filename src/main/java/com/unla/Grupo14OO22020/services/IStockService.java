package com.unla.Grupo14OO22020.services;

import java.util.List;
import com.unla.Grupo14OO22020.entities.Stock;
import com.unla.Grupo14OO22020.models.StockModel;


public interface IStockService {
	
	public abstract List<Stock> getAll();

	public StockModel insertOrUpdate(StockModel stockModel);

	public StockModel findByIdStock(int id);

	public boolean remove(int id);
	
	
}
