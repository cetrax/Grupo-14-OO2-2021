package com.unla.Grupo14OO22020.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unla.Grupo14OO22020.entities.Pedido;

@Repository("pedidoRepository")
public interface IPedidoRepository extends JpaRepository<Pedido, Serializable>{
	
	public abstract Pedido findByIdPedido(int idPedido);
	
	@Query(nativeQuery=true,value="select (p.cantidad*pr.precio) from pedido p inner join producto pr on p.producto_id_producto=pr.id_producto group by p.id_pedido;")
	public abstract List<Object> calcularSubtotal();


}
