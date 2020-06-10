package com.unla.Grupo14OO22020.repositories;


import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.Grupo14OO22020.entities.Lote;



@Repository("loteRepository")
public interface ILoteRepository extends JpaRepository<Lote, Serializable>{
	public abstract Lote findByIdLote(int idLote);
	
	
	
}//Fin class
