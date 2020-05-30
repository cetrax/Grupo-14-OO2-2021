package com.unla.Grupo14OO22020.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.unla.Grupo14OO22020.entities.Stock;
import com.unla.Grupo14OO22020.models.StockModel;

@Component("stockConverter")
public class StockConverter {

	@Autowired
	@Qualifier("loteConverter")
	private LoteConverter loteConverter;
	
	public StockModel entityToModel(Stock stock) {
		
		return new StockModel(stock.getIdStock(),stock.getCantidad(),stock.getLotes());
	}
	
	public Stock modelToEntity(StockModel stockModel) {
		return new Stock(stockModel.getIdStock(),stockModel.getCantidad(),stockModel.getLotes());
	}

}
