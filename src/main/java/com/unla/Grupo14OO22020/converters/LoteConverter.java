package com.unla.Grupo14OO22020.converters;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.unla.Grupo14OO22020.entities.Lote;
import com.unla.Grupo14OO22020.models.LoteModel;


@Component("loteConverter")
public class LoteConverter {

	@Autowired
	@Qualifier("productoConverter")
	private ProductoConverter productoConverter;

	
	public LoteModel entityToModel (Lote lote) {
		return new LoteModel(lote.getIdLote(), lote.getCantidadInicial(),lote.getCantidadActual(),lote.getFechaIngreso(), productoConverter.entityToModel(lote.getProducto()), lote.isEstado());
	}	
	
	public  Lote modelToEntity (LoteModel loteModel) {
		return new Lote(loteModel.getIdLote(), loteModel.getCantidadInicial(),loteModel.getCantidadActual(),loteModel.getFechaIngreso(),productoConverter.modelToEntity(loteModel.getProducto()), loteModel.isEstado());
	}
	//Converter para listas de stock
	/*
	public List<LoteModel> entityToModel (List<Lote> lote) {
		List<LoteModel> aux =new ArrayList<LoteModel>();
		
		for(Lote l : lote) 
		{aux.add(new LoteModel(l.getIdLote(), l.getCantidadInicial(),l.getCantidadActual(),l.getFechaIngreso(), productoConverter.entityToModel(l.getProducto()), l.isEstado())); }
		
		return aux;
	}	
	public List<Lote> modelToEntity (List<LoteModel> lote) {
		List<Lote> aux =new ArrayList<Lote>();
		
		for(LoteModel l : lote) 
		{aux.add(new Lote(l.getIdLote(), l.getCantidadInicial(),l.getCantidadActual(),l.getFechaIngreso(),productoConverter.modelToEntity(l.getProducto()), l.isEstado())); }
		
		return aux;
	}
	*/	
	
}
