package com.unla.Grupo14OO22020.services;

import java.util.List;

import com.unla.Grupo14OO22020.entities.Lote;
import com.unla.Grupo14OO22020.models.LoteModel;

public interface ILoteService {

	public abstract List<Lote> getAll();
	
	public List<Lote> lostesPorLocal(int idLocal);
	
//	public List<Lote> lostesPorPedido(int idPedido);
	
	public LoteModel Insert(LoteModel loteModel);

	public LoteModel Update(LoteModel loteModel);

	public LoteModel findByIdLote(int id);

	public boolean remove(int id);

	
}//Fin class
