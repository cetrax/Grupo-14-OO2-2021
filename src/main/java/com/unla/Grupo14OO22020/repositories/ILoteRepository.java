package com.unla.Grupo14OO22020.repositories;


import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unla.Grupo14OO22020.entities.Lote;



@Repository("loteRepository")
public interface ILoteRepository extends JpaRepository<Lote, Serializable>{
	public abstract Lote findByIdLote(int idLote);
	
	@Query("SELECT SUM(l.cantidadActual) FROM Lote l")
	int calcularStock();
	 

}//Fin class
