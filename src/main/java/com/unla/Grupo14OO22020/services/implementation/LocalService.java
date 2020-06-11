package com.unla.Grupo14OO22020.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo14OO22020.converters.LocalConverter;
import com.unla.Grupo14OO22020.entities.Local;
import com.unla.Grupo14OO22020.models.LocalModel;
import com.unla.Grupo14OO22020.repositories.ILocalRepository;
import com.unla.Grupo14OO22020.services.ILocalService;
import com.unla.Grupo14OO22020.services.ILoteService;

@Service("localService")
public class LocalService implements ILocalService{
	
	@Autowired
	@Qualifier("localRepository")
	private ILocalRepository localRepository;
	
	@Autowired
	@Qualifier("localConverter")
	private LocalConverter localConverter;
	
	@Autowired
	@Qualifier("loteService")
	private ILoteService loteService;

	@Override
	public List<Local> getAll() {
		return localRepository.findAll();
	}
	
	
//	@Override
//	public List<Localito> traerLocalesConTotalStock(){ //este método lo usabamos agregando la columna "StockCantidad" en el entities
//		List<Localito> locales = getAll();
//		for(Localito local: locales) {
//			int cant =0;
//			local.setStockCantidad(0);
//		    if(!local.getLotes().isEmpty()){
//				for(Lote lote: loteService.getAll()) {
//				    if(local.getIdLocal()==lote.getLocal().getIdLocal()) {
//				        local.setStockCantidad(cant += lote.getCantidadActual());
//				    }
//				}	
//		    }
//		}    
//	 return locales;
//	}
	
	

	@Override
	public LocalModel insertOrUpdate(LocalModel localModel) {
		Local local = localRepository.save(localConverter.modelToEntity(localModel));
		return localConverter.entityToModel(local);
	}
	
	@Override
	public LocalModel findByIdLocal(int id) {
		return localConverter.entityToModel(localRepository.findByIdLocal(id));
	}
 
	@Override
	public boolean remove(int id) {
		try {
			localRepository.deleteById(id);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
}
