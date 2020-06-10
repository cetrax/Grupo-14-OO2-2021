package com.unla.Grupo14OO22020.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo14OO22020.converters.LoteConverter;
import com.unla.Grupo14OO22020.entities.Localito;
import com.unla.Grupo14OO22020.entities.Lote;
import com.unla.Grupo14OO22020.models.LoteModel;
import com.unla.Grupo14OO22020.repositories.ILocalRepository;
import com.unla.Grupo14OO22020.repositories.ILoteRepository;
import com.unla.Grupo14OO22020.repositories.IProductoRepository;
import com.unla.Grupo14OO22020.services.ILocalService;
import com.unla.Grupo14OO22020.services.ILoteService;
import com.unla.Grupo14OO22020.services.IProductoService;



@Service("loteService")
public class LoteService implements ILoteService{
	
	@Autowired
	@Qualifier("loteRepository")
	private ILoteRepository loteRepository;

	//injeccion de dependencias
	@Autowired
	@Qualifier("localRepository")
	private ILocalRepository localRepository;
	
	@Autowired
	@Qualifier("loteConverter")
	private LoteConverter loteConverter;
	
	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;

	@Autowired
	@Qualifier("localService")
	private ILocalService localService;
	
	@Autowired
	@Qualifier("productoRepository")
	private IProductoRepository productoRepository;
	
	
	@Override
	public List<Lote> getAll() {
		return loteRepository.findAll();
	}
	

	@Override
	public LoteModel Insert(LoteModel loteModel) {
      Localito loc = localRepository.findByIdLocal(loteModel.getLocal().getIdLocal());
		Lote lote=loteConverter.modelToEntity(loteModel);
		lote.setLocal(loc);
		lote = loteRepository.save(lote);
		
		return loteConverter.entityToModel(lote);
	}
	

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
