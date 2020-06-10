package com.unla.Grupo14OO22020.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.unla.Grupo14OO22020.entities.Localito;

@Repository("localRepository")
public interface ILocalRepository extends JpaRepository<Localito, Serializable>{
	public abstract Localito findByIdLocal(int id);
	
	@Query(nativeQuery=true,value="SELECT SUM(l.cantidad_actual) FROM Lote l RIGHT JOIN Local lo on l.id_local=lo.id_local group by l.id_local")
	public abstract List<Object> calculoStock();

}//Fin class
