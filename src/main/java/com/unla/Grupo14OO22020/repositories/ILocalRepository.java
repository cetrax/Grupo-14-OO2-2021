package com.unla.Grupo14OO22020.repositories;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.unla.Grupo14OO22020.entities.Localito;

@Repository("localRepository")
public interface ILocalRepository extends JpaRepository<Localito, Serializable>{
	public abstract Localito findByIdLocal(int id);

}//Fin class
