package com.unla.Grupo14OO22020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.Grupo14OO22020.helpers.ViewRouteHelpers;
import com.unla.Grupo14OO22020.models.PedidoModel;
import com.unla.Grupo14OO22020.repositories.IPedidoRepository;
import com.unla.Grupo14OO22020.services.IClienteService;
import com.unla.Grupo14OO22020.services.IEmpleadoService;
import com.unla.Grupo14OO22020.services.IPedidoService;
import com.unla.Grupo14OO22020.services.IProductoService;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	@Qualifier("pedidoService")
	private IPedidoService pedidoService;
	
	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;
	
	@Autowired
	@Qualifier("pedidoRepository")
	private IPedidoRepository pedidoRepository;
	
	@Autowired
	@Qualifier("clienteService")
	private IClienteService clienteService;
	
	@Autowired
	@Qualifier("empleadoService")
	private IEmpleadoService empleadoService;
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PEDIDO_INDEX);
		mAV.addObject("pedidos", pedidoService.getAll());
		mAV.addObject("pedido", new PedidoModel());
		mAV.addObject("subtotal",pedidoRepository.calcularSubtotal());
		
		return mAV;
	}
	
	@GetMapping("/new")
	public ModelAndView crear() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PEDIDO_ADD);
		mAV.addObject("pedido", new PedidoModel());
		mAV.addObject("productos", productoService.getAll());
		mAV.addObject("clientes", clienteService.getAll());
		mAV.addObject("empleados", empleadoService.getAll());

		return mAV;
	}
	
	@PostMapping("/create")
	public RedirectView agregar(@ModelAttribute(name="pedidos") PedidoModel pedido) {
		pedidoService.Insert(pedido);
		return new RedirectView(ViewRouteHelpers.PEDIDO_ROOT);
	}
	
	@PostMapping("/delete/{id}")
	public RedirectView eliminar(@PathVariable("id") int id) {
		pedidoService.remove(id);
		return new RedirectView(ViewRouteHelpers.PEDIDO_ROOT);
	}
	
	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int idPedido) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PEDIDO_UPDATE);
		mAV.addObject("pedido", pedidoService.findByIdPedido(idPedido));
		mAV.addObject("clientes", clienteService.getAll());
		mAV.addObject("empleados", empleadoService.getAll());
		mAV.addObject("productos", productoService.getAll());
		return mAV;
	}
	
	@PostMapping("/update")
	public RedirectView update(@ModelAttribute("pedido") PedidoModel pedidoModel) {
		pedidoService.Update(pedidoModel);
		return new RedirectView(ViewRouteHelpers.PEDIDO_ROOT);
	}

}
