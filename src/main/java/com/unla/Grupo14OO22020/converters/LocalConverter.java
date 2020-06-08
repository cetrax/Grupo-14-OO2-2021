package com.unla.Grupo14OO22020.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.unla.Grupo14OO22020.entities.Localito;
import com.unla.Grupo14OO22020.models.LocalModel;

@Component("localConverter")
public class LocalConverter {
	
	@Autowired
	@Qualifier("stockConverter")
	private StockConverter stockConverter;	

	public LocalModel entityToModel(Localito local) {
		return new LocalModel(local.getIdLocal(), local.getDireccion(),local.getLatitud(),local.getLongitud(),local.getTelefono(),stockConverter.entityToModel(local.getStock()));
	}
 	
	public Localito modelToEntity(LocalModel localModel) {
		return new Localito(localModel.getIdLocal(),localModel.getDireccion(),localModel.getLatitud(),localModel.getLongitud(),localModel.getTelefono(), stockConverter.modelToEntity(localModel.getStockModel()));
	}
	
	
}
