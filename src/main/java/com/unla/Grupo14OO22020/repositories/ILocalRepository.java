package com.unla.Grupo14OO22020.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.unla.Grupo14OO22020.entities.Local;

@Repository("localRepository")
public interface ILocalRepository extends JpaRepository<Local, Serializable>{
	public abstract Local findByIdLocal(int id);
	
	@Query(nativeQuery=true,value="select lo.id_local,l.producto_id_producto,(select sum(cantidad_actual)from Lote where producto_id_producto=l.producto_id_producto and id_local=l.id_local) as resultado from Lote l inner join Local lo on lo.id_local=l.id_local inner join Producto p on p.id_producto=l.producto_id_producto where p.id_producto=(:idProducto) and l.cantidad_actual>=(:cantidad) group by l. producto_id_producto,l.id_local")
	public abstract List<Local> localesConEseProducto(int idProducto,int cantidad);

}//Fin class
