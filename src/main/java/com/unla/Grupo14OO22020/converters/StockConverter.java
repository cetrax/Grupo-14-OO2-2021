package com.unla.Grupo14OO22020.converters;

import org.springframework.stereotype.Component;

import com.unla.Grupo14OO22020.entities.Stock;
import com.unla.Grupo14OO22020.models.StockModel;


@Component("stockConverter")
public class StockConverter {

	public StockModel entityToModel(Stock stock) {
		return new StockModel(stock.getIdStock(),stock.getCantidad());
	}
	
	public Stock modelToEntity(StockModel stockModel) {
		return new Stock(stockModel.getIdStock(),stockModel.getCantidad());
	}
	
	
	
}
