package com.unla.Grupo14OO22020.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo14OO22020.converters.LocalConverter;
import com.unla.Grupo14OO22020.entities.Localito;
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
	public List<Localito> getAll() {
		return localRepository.findAll();
	}
	
	@Override
	public LocalModel insert(LocalModel localModel) {
		Localito local = localRepository.save(localConverter.modelToEntity(localModel));
		return localConverter.entityToModel(local);
	}
	

	@Override
	public LocalModel update(LocalModel localModel) {
		Localito localOrig =localRepository.findByIdLocal(localModel.getIdLocal());
		localOrig.setStockCantidad(localModel.getStockCantidad());
		Localito local = localRepository.save(localOrig);
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
