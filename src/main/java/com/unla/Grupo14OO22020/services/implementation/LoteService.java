package com.unla.Grupo14OO22020.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo14OO22020.converters.LoteConverter;
import com.unla.Grupo14OO22020.entities.Localito;
import com.unla.Grupo14OO22020.entities.Lote;
import com.unla.Grupo14OO22020.entities.Stock;
import com.unla.Grupo14OO22020.models.LoteModel;
import com.unla.Grupo14OO22020.repositories.ILocalRepository;
import com.unla.Grupo14OO22020.repositories.ILoteRepository;
import com.unla.Grupo14OO22020.repositories.IStockRepository;
import com.unla.Grupo14OO22020.services.ILoteService;
import com.unla.Grupo14OO22020.services.IProductoService;



@Service("loteService")
public class LoteService implements ILoteService{
	
	@Autowired
	@Qualifier("loteRepository")
	private ILoteRepository loteRepository;
	//injeccion de dependencias
	@Autowired
	@Qualifier("stockRepository")
	private IStockRepository stockRepository;
	
	@Autowired
	@Qualifier("localRepository")
	private ILocalRepository localRepository;
	
	@Autowired
	@Qualifier("loteConverter")
	private LoteConverter loteConverter;

	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;
	
	@Override
	public List<Lote> getAll() {
		return loteRepository.findAll();
	}

	@Override
	public LoteModel Insert(LoteModel loteModel) {
		
		Localito l1=localRepository.findByIdLocal(loteModel.getLocal().getIdLocal());
		
		Stock stock=l1.getStock();
		if(stock==null ){
			stock=stockRepository.save(new Stock(loteModel.getCantidadInicial()));
			l1.setStock(stock);
		}
		Lote lote=loteConverter.modelToEntity(loteModel); 
		lote.setStock(stock);
		lote = loteRepository.save(lote);
		
		return loteConverter.entityToModel(lote);
	}
	
	@Override
	public LoteModel Update(LoteModel loteModel) {
		loteModel.setProducto(productoService.findByIdProducto(loteModel.getProducto().getIdProducto()));
		Lote lote = loteRepository.save(loteConverter.modelToEntity(loteModel));
		return loteConverter.entityToModel(lote);
	}
	
	
	public LoteModel findByIdLote (int idLote) {
		return loteConverter.entityToModel(loteRepository.findByIdLote(idLote));
	}
	
 
	@Override
	public boolean remove(int idLote) {
		try {
			loteRepository.deleteById(idLote);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}

}//Fin class
