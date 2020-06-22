package com.unla.Grupo14OO22020.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.unla.Grupo14OO22020.entities.Pedido;
import com.unla.Grupo14OO22020.models.PedidoModel;

@Component("pedidoConverter")
public class PedidoConverter {
	
	@Autowired
	@Qualifier("productoConverter")
	private ProductoConverter productoConverter;
	
	@Autowired
	@Qualifier("localConverter")
	private LocalConverter localConverter;
	
	@Autowired
	@Qualifier("clienteConverter")
	private ClienteConverter clienteConverter;
	
	@Autowired
	@Qualifier("empleadoConverter")
	private EmpleadoConverter empleadoConverter;
	
	public PedidoModel entityToModel(Pedido pedido) {
		return new PedidoModel(pedido.getIdPedido(), pedido.getFecha(), productoConverter.entityToModel(pedido.getProducto()), pedido.getCantidad(), clienteConverter.entityToModel(pedido.getCliente()), empleadoConverter.entityToModel(pedido.getVendedorOriginal()), empleadoConverter.entityToModel(pedido.getVendedorAuxiliar()), localConverter.entityToModel(pedido.getLocal()), pedido.isAceptado());
	}
	
	public Pedido modelToEntity(PedidoModel pedidoModel) {
		return new Pedido(pedidoModel.getIdPedido(), pedidoModel.getFecha(), productoConverter.modelToEntity(pedidoModel.getProducto()), pedidoModel.getCantidad(), clienteConverter.modelToEntity(pedidoModel.getCliente()), empleadoConverter.modelToEntity(pedidoModel.getVendedorOriginal()), empleadoConverter.modelToEntity(pedidoModel.getVendedorAuxiliar()), localConverter.modelToEntity(pedidoModel.getLocal()), pedidoModel.isAceptado());
	}

}
