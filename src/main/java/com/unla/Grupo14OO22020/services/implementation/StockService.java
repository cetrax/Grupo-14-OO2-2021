package com.unla.Grupo14OO22020.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.unla.Grupo14OO22020.converters.StockConverter;
import com.unla.Grupo14OO22020.entities.Localito;
import com.unla.Grupo14OO22020.entities.Stock;
import com.unla.Grupo14OO22020.models.StockModel;
import com.unla.Grupo14OO22020.repositories.IStockRepository;
import com.unla.Grupo14OO22020.services.ILoteService;
import com.unla.Grupo14OO22020.services.IStockService;

@Service("stockService")
public class StockService implements IStockService{
	
	@Autowired
	@Qualifier("stockRepository")
	private IStockRepository stockRepository;
	
	@Autowired
	@Qualifier("stockConverter")
	private StockConverter stockConverter;
	
	@Autowired
	@Qualifier("loteService")
	private ILoteService loteService;
	
	@Override
	public List<Stock> getAll() {
		return stockRepository.findAll();
	}
	
	@Override
	public StockModel Insert(StockModel stockModel) {
		Stock stock = stockRepository.save(stockConverter.modelToEntity(stockModel));
		
		return stockConverter.entityToModel(stock);
	}
	
	@Override
	public StockModel Update(StockModel stockModel) {		
		Stock stock = stockRepository.save(stockConverter.modelToEntity(stockModel));
		return stockConverter.entityToModel(stock);
	}
	
	@Override
	public StockModel findByIdStock(int id) {
		return stockConverter.entityToModel(stockRepository.findByIdStock(id));
	}
	
	@Override
	public boolean remove(int id) {
		try {
			stockRepository.deleteById(id);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
}
