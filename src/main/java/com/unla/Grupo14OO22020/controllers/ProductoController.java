package com.unla.Grupo14OO22020.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

import com.unla.Grupo14OO22020.converters.LocalConverter;
import com.unla.Grupo14OO22020.converters.PedidoConverter;
import com.unla.Grupo14OO22020.entities.Local;
import com.unla.Grupo14OO22020.entities.Pedido;
import com.unla.Grupo14OO22020.entities.Producto;
import com.unla.Grupo14OO22020.helpers.ViewRouteHelpers;
import com.unla.Grupo14OO22020.models.ProductoModel;
import com.unla.Grupo14OO22020.repositories.ILocalRepository;
import com.unla.Grupo14OO22020.repositories.IPedidoRepository;
import com.unla.Grupo14OO22020.services.ILocalService;
import com.unla.Grupo14OO22020.services.IPedidoService;
import com.unla.Grupo14OO22020.services.IProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {
	
	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;
	
	@Autowired
	@Qualifier("pedidoRepository")
	private IPedidoRepository pedidoRepository;
	
	@Autowired
	@Qualifier("pedidoService")
	private IPedidoService pedidoService;

	@Autowired
	@Qualifier("pedidoConverter")
	private PedidoConverter pedidoConverter;
	
	@Autowired
	@Qualifier("localService")
	private ILocalService localService;

	@Autowired
	@Qualifier("localRepository")
	private ILocalRepository localRepository;

	
	@Autowired
	@Qualifier("localConverter")
	private LocalConverter localConverter;
	
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_INDEX);
		mAV.addObject("productos", productoService.getAll());
		mAV.addObject("producto", new ProductoModel());
		
		return mAV;
	}
	
	@GetMapping("/new")
	public ModelAndView crear() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_ADD);
		mAV.addObject("producto", new Producto());
		
		return mAV;
	}
	
	@PostMapping("/create")
	public RedirectView agregar(@ModelAttribute(name="productos") ProductoModel producto ) {
		productoService.insertOrUpdate(producto);
		return new RedirectView(ViewRouteHelpers.PRODUCTO_ROOT);
	}
	
	@PostMapping("/delete/{id}")
	public RedirectView eliminar(@PathVariable("id") int id) {
		productoService.remove(id);
		return new RedirectView(ViewRouteHelpers.PRODUCTO_ROOT);
	}
	
	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int idProducto) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_UPDATE);
		mAV.addObject("producto", productoService.findByIdProducto(idProducto));
		return mAV;
	}
	
	@PostMapping("/update")
	public RedirectView update(@ModelAttribute("producto") ProductoModel productoModel) {
		productoService.insertOrUpdate(productoModel);
		return new RedirectView(ViewRouteHelpers.PRODUCTO_ROOT);
	}

	
//**********************************************************************	
	public List<Producto> productosVendidosEntreFechas(Local local, LocalDate menor, LocalDate mayor) {
		List<Producto> listaProductos = new ArrayList<Producto>();
		for (Pedido pedido : local.getPedidos()) {
			if (pedido.getFecha().isAfter(menor) && pedido.getFecha().isBefore(mayor)){
				if(listaProductos.isEmpty()) { listaProductos.add(pedido.getProducto()); }//la primera vez que entro guardo el primero en la lista
			    for(Producto producto: listaProductos) {	
			    	if(producto.getNombre()==pedido.getProducto().getNombre()) {
			    	   listaProductos.add(pedido.getProducto());
			    	}//if 3
			    }//for 2	
			}//if 1
		}//for
		return listaProductos;
	}
	
	
	public List<Producto> rankingDeProductosVendidos() {
		List<Producto> productos = new ArrayList<Producto>();
		for (Pedido pedido : pedidoService.getAll()) {
//			if (pedido.getFecha().isAfter(menor) && pedido.getFecha().isBefore(mayor)){
//				productos.add(pedido.getProducto());
//			}
		}
		return productos;
	}
//**********************************************************************	
}//Fin class
