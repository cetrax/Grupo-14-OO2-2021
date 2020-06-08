package com.unla.Grupo14OO22020.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo14OO22020.converters.LoteConverter;
import com.unla.Grupo14OO22020.converters.StockConverter;
import com.unla.Grupo14OO22020.entities.Localito;
import com.unla.Grupo14OO22020.entities.Lote;
import com.unla.Grupo14OO22020.entities.Stock;
import com.unla.Grupo14OO22020.models.LoteModel;
import com.unla.Grupo14OO22020.repositories.ILocalRepository;
import com.unla.Grupo14OO22020.repositories.ILoteRepository;
import com.unla.Grupo14OO22020.repositories.IProductoRepository;
import com.unla.Grupo14OO22020.repositories.IStockRepository;
import com.unla.Grupo14OO22020.services.ILocalService;
import com.unla.Grupo14OO22020.services.ILoteService;
import com.unla.Grupo14OO22020.services.IProductoService;
import com.unla.Grupo14OO22020.services.IStockService;



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
	@Qualifier("stockConverter")
	private StockConverter stockConverter;

	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;

	@Autowired
	@Qualifier("localService")
	private ILocalService localService;
	
	@Autowired
	@Qualifier("productoRepository")
	private IProductoRepository productoRepository;
	
	@Autowired
	@Qualifier("stockService")
	private IStockService stockService;
	
	@Override
	public List<Lote> getAll() {
		return loteRepository.findAll();
	}

	@Override
	public LoteModel Insert(LoteModel loteModel) {
      Localito loc = localRepository.findByIdLocal(loteModel.getLocal().getIdLocal());
		Stock stock = loc.getStock();
		Lote lote=loteConverter.modelToEntity(loteModel); 
		if(stock==null ){
			stock=stockRepository.save(new Stock(loteModel.getCantidadInicial()));
			loc.setStock(stock);
		}
		stock.setLocal(loc);
		lote.setStock(stock);
		lote = loteRepository.save(lote);
		
		return loteConverter.entityToModel(lote);
	}
	
	
//	@Override
//	public LoteModel Update(LoteModel loteModel) {
//		loteModel.setProducto(productoService.findByIdProducto(loteModel.getProducto().getIdProducto()));
//		loteModel.setLocal(localService.findByIdLocal(loteModel.getLocal().getIdLocal()));
//		loteModel.setStock(stockService.findByIdStock(loteModel.getStock().getIdStock()));
//		Lote lote = loteRepository.save(loteConverter.modelToEntity(loteModel));
//		return loteConverter.entityToModel(lote);
//	}
	
	
	@Override
	public LoteModel Update(LoteModel loteModel) {
		Lote loteOrig = loteRepository.findByIdLote(loteModel.getIdLote());		
		loteOrig.setProducto(productoRepository.findByIdProducto(loteModel.getProducto().getIdProducto()));
		loteOrig.setCantidadActual(loteModel.getCantidadActual());
		loteOrig.setEstado(loteModel.isEstado());
		Lote lote = loteRepository.save(loteOrig);
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
