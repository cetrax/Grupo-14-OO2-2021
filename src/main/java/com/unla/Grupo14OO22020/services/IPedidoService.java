package com.unla.Grupo14OO22020.services;

import java.util.List;
import com.unla.Grupo14OO22020.entities.Pedido;
import com.unla.Grupo14OO22020.models.PedidoModel;

public interface IPedidoService {
	
	public abstract List<Pedido> getAll();
	
	public PedidoModel Insert(PedidoModel pedidoModel);
	
	public PedidoModel Update(PedidoModel pedidoModel);
	
	public PedidoModel Insert_2(PedidoModel pedidoModel);
	
	public PedidoModel Update_2(PedidoModel pedidoModel);

	public PedidoModel UpdateEmpleadoOrig(PedidoModel pedidoModel);

	public PedidoModel UpdateEmpleadoAuxi(PedidoModel pedidoModel);

	public PedidoModel findByIdPedido(int id);

	public boolean remove(int id);
	


}

