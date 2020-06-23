package com.unla.Grupo14OO22020.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.Grupo14OO22020.converters.PedidoConverter;
import com.unla.Grupo14OO22020.entities.Cliente;
import com.unla.Grupo14OO22020.entities.Empleado;
import com.unla.Grupo14OO22020.entities.Pedido;
import com.unla.Grupo14OO22020.entities.Producto;
import com.unla.Grupo14OO22020.models.PedidoModel;
import com.unla.Grupo14OO22020.repositories.IClienteRepository;
import com.unla.Grupo14OO22020.repositories.IEmpleadoRepository;
import com.unla.Grupo14OO22020.repositories.IPedidoRepository;
import com.unla.Grupo14OO22020.repositories.IProductoRepository;
import com.unla.Grupo14OO22020.services.IClienteService;
import com.unla.Grupo14OO22020.services.IEmpleadoService;
import com.unla.Grupo14OO22020.services.ILocalService;
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
	
	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;
	
	@Autowired
	@Qualifier("clienteService")
	private IClienteService clienteService;
	
	@Autowired
	@Qualifier("localService")
	private ILocalService localService;

	@Autowired
	@Qualifier("empleadoService")
	private IEmpleadoService empleadoService;

	@Autowired
	@Qualifier("empleadoRepository")
	private IEmpleadoRepository empleadoRepository;
	
	
	@Autowired
	@Qualifier("clienteRepository")
	private IClienteRepository clienteRepository;
	
	
	@Autowired
	@Qualifier("productoRepository")
	private IProductoRepository productoRepository;
	
	
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
	public PedidoModel UpdateEmpleadoOrig(PedidoModel pedidoModel) {
		pedidoModel.setProducto(productoService.findByIdProducto(pedidoModel.getProducto().getIdProducto()));
		pedidoModel.setCliente(clienteService.findByIdPersona(pedidoModel.getCliente().getIdPersona()));
		pedidoModel.setVendedorOriginal(empleadoService.findByIdPersona(pedidoModel.getVendedorOriginal().getIdPersona()));
		pedidoModel.setLocal(localService.findByIdLocal(pedidoModel.getLocal().getIdLocal()));
		pedidoModel.setFecha(pedidoModel.getFecha());
		Pedido pedido = pedidoRepository.save(pedidoConverter.modelToEntity(pedidoModel));
		return pedidoConverter.entityToModel(pedido);
	}

	
	@Override
	public PedidoModel UpdateEmpleadoAuxi(PedidoModel pedidoModel) {
		pedidoModel.setProducto(productoService.findByIdProducto(pedidoModel.getProducto().getIdProducto()));
		pedidoModel.setCliente(clienteService.findByIdPersona(pedidoModel.getCliente().getIdPersona()));
		pedidoModel.setVendedorOriginal(empleadoService.findByIdPersona(pedidoModel.getVendedorOriginal().getIdPersona()));
		pedidoModel.setVendedorAuxiliar(empleadoService.findByIdPersona(pedidoModel.getVendedorAuxiliar().getIdPersona()));
	    System.out.println(" ********************* Cantidad en UpdateEmpleadoAuxi "+pedidoModel.getCantidad());
		pedidoModel.setCantidad(pedidoModel.getCantidad());
		pedidoModel.setFecha(pedidoModel.getFecha());
		Pedido pedido = pedidoRepository.save(pedidoConverter.modelToEntity(pedidoModel));
		return pedidoConverter.entityToModel(pedido);
	}
	
	@Override
	public PedidoModel Insert_2(PedidoModel pedidoModel) {
		System.out.println("DENTRO DEL INSERT 2"); 
        Empleado empleado = empleadoRepository.findByIdPersona(pedidoModel.getVendedorOriginal().getIdPersona());
		Cliente cliente = clienteRepository.findByIdPersona(pedidoModel.getCliente().getIdPersona());
		Producto producto = productoRepository.findByIdProducto(pedidoModel.getProducto().getIdProducto());
        Pedido pedido = pedidoConverter.modelToEntity(pedidoModel);
        pedido.setVendedorOriginal(empleado);
    	pedido.setVendedorAuxiliar(null);
		pedido.setCliente(cliente);
		pedido.setProducto(producto);
    	pedido = pedidoRepository.save(pedido);
		return pedidoConverter.entityToModel(pedido);
	}
	
	@Override
	public PedidoModel Update_2(PedidoModel pedidoModel) {
		pedidoModel.setProducto(productoService.findByIdProducto(pedidoModel.getProducto().getIdProducto()));
		pedidoModel.setCliente(clienteService.findByIdPersona(pedidoModel.getCliente().getIdPersona()));
		pedidoModel.setVendedorOriginal(empleadoService.findByIdPersona(pedidoModel.getVendedorOriginal().getIdPersona()));
//		pedidoModel.setVendedorAuxiliar(empleadoService.findByIdPersona(pedidoModel.getVendedorAuxiliar().getIdPersona()));
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
