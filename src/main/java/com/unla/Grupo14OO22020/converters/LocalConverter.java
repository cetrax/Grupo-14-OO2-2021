package com.unla.Grupo14OO22020.converters;

import org.springframework.stereotype.Component;
import com.unla.Grupo14OO22020.entities.Localito;
import com.unla.Grupo14OO22020.models.LocalModel;

@Component("localConverter")
public class LocalConverter {
	

	public LocalModel entityToModel(Localito local) {
		return new LocalModel(local.getIdLocal(), local.getDireccion(),local.getLatitud(),local.getLongitud(),local.getTelefono());
	}
 	
	public Localito modelToEntity(LocalModel localModel) {
		return new Localito(localModel.getIdLocal(),localModel.getDireccion(),localModel.getLatitud(),localModel.getLongitud(),localModel.getTelefono());
	}
	
	
}
