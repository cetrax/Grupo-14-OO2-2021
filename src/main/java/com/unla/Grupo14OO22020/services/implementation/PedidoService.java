package com.unla.Grupo14OO22020.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo14OO22020.converters.PedidoConverter;
import com.unla.Grupo14OO22020.entities.Pedido;
import com.unla.Grupo14OO22020.models.PedidoModel;
import com.unla.Grupo14OO22020.repositories.IPedidoRepository;
import com.unla.Grupo14OO22020.services.IClienteService;
import com.unla.Grupo14OO22020.services.IEmpleadoService;
import com.unla.Grupo14OO22020.services.IPedidoService;
import com.unla.Grupo14OO22020.services.IProductoService;

@Service("pedidoService")
public class PedidoService implements IPedidoService{
	
	@Autowired
	@Qualifier("pedidoRepository")
	private IPedidoRepository pedidoRepository;
	
	@Autowired
	@Qualifier("pedidoConverter")
	private PedidoConverter pedidoConverter;
	
	@Qualifier("productoService")
	private IProductoService productoService;
	
	@Qualifier("clienteService")
	private IClienteService clienteService;
	
	@Qualifier("empleadoService")
	private IEmpleadoService empleadoService;

	@Override
	public List<Pedido> getAll() {
		return pedidoRepository.findAll();
	}

	@Override
	public PedidoModel Insert(PedidoModel pedidoModel) {
		Pedido pedido = pedidoRepository.save(pedidoConverter.modelToEntity(pedidoModel));
		return pedidoConverter.entityToModel(pedido);
	}
	
	@Override
	public PedidoModel Update(PedidoModel pedidoModel) {
		pedidoModel.setProducto(productoService.findByIdProducto(pedidoModel.getProducto().getIdProducto()));
		pedidoModel.setCliente(clienteService.findByIdPersona(pedidoModel.getCliente().getIdPersona()));
		pedidoModel.setVendedorOriginal(empleadoService.findByIdPersona(pedidoModel.getVendedorOriginal().getIdPersona()));
		pedidoModel.setVendedorAuxiliar(empleadoService.findByIdPersona(pedidoModel.getVendedorAuxiliar().getIdPersona()));
		Pedido pedido = pedidoRepository.save(pedidoConverter.modelToEntity(pedidoModel));
		return pedidoConverter.entityToModel(pedido);
	}
	
	
	@Override
	public PedidoModel findByIdPedido(int id) {
		return pedidoConverter.entityToModel(pedidoRepository.findByIdPedido(id));
	}
 
	@Override
	public boolean remove(int id) {
		try {
			pedidoRepository.deleteById(id);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}

}
