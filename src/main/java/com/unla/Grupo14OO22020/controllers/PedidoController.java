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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.Grupo14OO22020.converters.LocalConverter;
import com.unla.Grupo14OO22020.converters.LoteConverter;
import com.unla.Grupo14OO22020.converters.PedidoConverter;
import com.unla.Grupo14OO22020.converters.ProductoConverter;
import com.unla.Grupo14OO22020.entities.Cliente;
import com.unla.Grupo14OO22020.entities.Empleado;
import com.unla.Grupo14OO22020.entities.Local;
import com.unla.Grupo14OO22020.entities.Lote;
import com.unla.Grupo14OO22020.helpers.ViewRouteHelpers;
import com.unla.Grupo14OO22020.models.LocalModel;
import com.unla.Grupo14OO22020.entities.Pedido;
import com.unla.Grupo14OO22020.entities.Producto;
import com.unla.Grupo14OO22020.models.PedidoModel;
import com.unla.Grupo14OO22020.models.ProductoModel;
import com.unla.Grupo14OO22020.repositories.IClienteRepository;
import com.unla.Grupo14OO22020.repositories.IEmpleadoRepository;
import com.unla.Grupo14OO22020.repositories.ILocalRepository;
import com.unla.Grupo14OO22020.repositories.IPedidoRepository;
import com.unla.Grupo14OO22020.repositories.IProductoRepository;
import com.unla.Grupo14OO22020.services.IClienteService;
import com.unla.Grupo14OO22020.services.IEmpleadoService;
import com.unla.Grupo14OO22020.services.ILocalService;
import com.unla.Grupo14OO22020.services.ILoteService;
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
	@Qualifier("clienteService")
	private IClienteService clienteService;

	@Autowired
	@Qualifier("empleadoService")
	private IEmpleadoService empleadoService;

	@Autowired
	@Qualifier("localService")
	private ILocalService localService;

	@Autowired
	@Qualifier("loteService")
	private ILoteService loteService;

	@Autowired
	@Qualifier("loteConverter")
	private LoteConverter loteConverter;
	
	@Autowired
	@Qualifier("localRepository")
	private ILocalRepository localRepository;

	@Autowired
	@Qualifier("pedidoRepository")
	private IPedidoRepository pedidoRepository;

	@Autowired
	@Qualifier("empleadoRepository")
	private IEmpleadoRepository empleadoRepository;
	
	@Autowired
	@Qualifier("clienteRepository")
	private IClienteRepository clienteRepository;

	@Autowired
	@Qualifier("productoRepository")
	private IProductoRepository productoRepository;

	@Autowired
	@Qualifier("localConverter")
	private LocalConverter localConverter;



	@Autowired
	@Qualifier("pedidoConverter")
	private PedidoConverter pedidoConverter;

	@Autowired
	@Qualifier("productoConverter")
	private ProductoConverter productoConverter;


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
		mAV.addObject("locales",localService.getAll());
		return mAV;
	}

	@PostMapping("/create")
	public RedirectView agregar(@ModelAttribute(name="pedidos") PedidoModel pedido) {
		pedidoService.Insert(pedido);
		//Pedido pedidoInsertado = pedidoService.getAll().get(pedidoService.getAll().size()-1);
		// opción 2	Pedido pedidoInsertado = pedidoRepository.findAll().get(pedidoService.getAll().size()-1);
		//aceptarPedido(pedidoConverter.entityToModel(pedidoInsertado));
		return new RedirectView(ViewRouteHelpers.PEDIDO_ROOT);
	}/* la linea "Pedido pedidoInsertado... " es la única forma de obtener el Pedido que se está 
	    insertando ya que traigo el último de la lista, en enta faunción en "pedido" no hay un Pedido 
	    en si, ni siquiera tiene ID, solo tiene lo necesario (valores; como int, string, ID de clases 
	    que tenga como atributo), para que se inserte (se cree) un Pedido*/

	@PostMapping("/delete/{id}")
	public RedirectView eliminar(@PathVariable("id") int id) {
		pedidoService.remove(id);
		return new RedirectView(ViewRouteHelpers.PEDIDO_ROOT);
	}
	
	@PostMapping("/deleteEmpleado/{id}")
	public RedirectView eliminarEmpleado(@PathVariable("id") int id) {
		pedidoService.remove(id);
		return new RedirectView(ViewRouteHelpers.PEDIDO_ROOTEMPLEADO);
	}
//
//

	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int idPedido) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PEDIDO_UPDATE);
		mAV.addObject("pedido", pedidoService.findByIdPedido(idPedido));
		mAV.addObject("clientes", clienteService.getAll());
		mAV.addObject("empleados", empleadoService.getAll());
		mAV.addObject("productos", productoService.getAll());
		mAV.addObject("locales",localService.getAll());
//		PedidoModel pedido=pedidoService.findByIdPedido(idPedido);
	    return mAV;
	}


	@PostMapping("/update")
	public RedirectView update(@ModelAttribute("pedido") PedidoModel pedidoModel) {
		pedidoService.Update(pedidoModel);
		System.out.println("  ID "+pedidoModel.getIdPedido()+" Pre Aceptado "+pedidoPreActualizacionEsAceptado+"  Aceptado "+pedidoModel.isAceptado());
//		if(pedidoModel.isAceptado()==true && pedidoPreActualizacionEsAceptado==false) {
//			descontarStockDeUnProductoLocalAjeno(pedidoModel);		
//		}
		return new RedirectView(ViewRouteHelpers.PEDIDO_ROOT);//los RedirectView solo redirigen al index 
	}

	//*******************************METODOS ESPECIALES**************************************************************	

	@GetMapping("/empDelPed")//Con este voy a la vista para pedir el id
	public ModelAndView EmpleadoDelPedido() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PEDIDO_PEDIR_ID_EMPLEADO);//pido el id y lo devuelvo por  /devolverIdEmpleado
		System.out.println(" 1 DENTRO DE EmpleadoDelPed");
		mAV.addObject("empleados",empleadoService.getAll());
		return mAV;
	}
    int idGlobalEmpleado=0;
    @RequestMapping(value="/devolverIdEmpleado",method=RequestMethod.POST)//desde la vista PEDIDO_PEDIR_ID_EMPLEADO traigo el ID
	public ModelAndView traerIdEmpladoPrimerIngreso(@RequestParam("idEmpleado") int idEmpleado) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.MOSTRAR_PEDIDOS_LOCAL_EMPLEADO);//mando la lista de pedidos a pedido/pedEmpleado
		System.out.println(" 2 DENTRO DE traerIdEmpladoPrimerIngreso   Id del emple "+idEmpleado);
		idGlobalEmpleado=idEmpleado; 
		Empleado empleado = empleadoRepository.findByIdPersona(idEmpleado);// traigo al vendedor
		Local local = localRepository.findByIdLocal(empleado.getLocal().getIdLocal());//traigo el local del vendedor
		mAV = mostrarTodosLosPedDelLocal(local);//muestro todos los pedidos del local del vendedor
		return mAV;
	}//en esta tengo un botón que me lleva a /newPedidoEmpleado
	
	@GetMapping("/newPedidoEmpleado")
	public ModelAndView crearPorlocal() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.ADD_PEDIDO_DEL_EMPLEADO);// voy a pedido/newPedidoEmpleado llevando lo de abajo
		System.out.println(" 3 DENTRO DE   newPedidoEmpleado    crearPorlocal ");
		mAV.addObject("pedido", new PedidoModel());
		mAV.addObject("productos", productoService.getAll());
		mAV.addObject("clientes", clienteService.getAll());
		Empleado empleado = empleadoRepository.findByIdPersona(idGlobalEmpleado);
		Local local = localRepository.findByIdLocal(empleado.getLocal().getIdLocal());
		mAV.addObject("empleados",traerEmpleadosDelLocal(local));
		mAV.addObject("locales",traerLocalEnLista(local));
		return mAV;
	}//al pulsar enviar lleva los datos del nuevo pedido a /pedidos/crearunPedYmostrarDelEmpl
	
 
	@PostMapping("/crearunPedYmostrarDelEmpl")
	public ModelAndView agregarUnPedYmostrarTodosLosPedDelLocDelEmple(@ModelAttribute(name="pedidos") PedidoModel pedido) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.MOSTRAR_PEDIDOS_LOCAL_EMPLEADO);//mando la lista de pedidos a pedido/pedEmpleado
		Empleado empleado = empleadoRepository.findByIdPersona(pedido.getVendedorOriginal().getIdPersona());// (PARTE DE CREAR) punto 1: traigo al vendedor original
		System.out.println(" 4 DENTRO DE agregarUnPedYmostrarTodosLosPedDelLocDelEmple LOCAL del emple Origi "+empleado.getLocal().getIdLocal()+" CANTIDAD "+pedido.getCantidad());
		Local local = localRepository.findByIdLocal(empleado.getLocal().getIdLocal());//(PARTE DE CREAR) punto 2: como en pedido solo se carga el ID del emplado original para traer el local del empleado primero traje el empleado (punto 1)
		pedido.setLocal(localConverter.entityToModel(local));//(PARTE DE CREAR) punto 3: me aseguro de que quede el local del vendedor original como local del pedido
//	    globalCantidad = pedido.getCantidad();
		//se va a verificar si hay stock en el local del vendedor Original, si hay se marca como aceptado y se desuenta
		if(localTieneStockDeUnProducto(pedido.getLocal(),pedido.getProducto(),pedido.getCantidad())==true) {//(PARTE DE CREAR) punto 4: si hay stock el local propio
			pedido.setAceptado(true);//(PARTE DE CREAR) punto 5
			descontarStockDeUnProductoLocalPropio(pedido.getLocal(),pedido.getProducto(),pedido.getCantidad());//descuento
		}//punto 1, 2, 3, 4 y 5: son para que en el caso de haber stock en el local del vendedor original el aceptado quede en true 
		pedidoService.Insert_2(pedido);//(PARTE DE CREAR) inserto con el local del vendedor Original  
		mAV = mostrarTodosLosPedDelLocal(local);//(el Local es del vendedor original)... ... ... LLAMO AL MOSTRAR
		return mAV;
	}

	
	public ModelAndView mostrarTodosLosPedDelLocal(Local local) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.MOSTRAR_PEDIDOS_LOCAL_EMPLEADO);//mando la lista de pedidos a pedido/pedEmpleado
		System.out.println(" 5 en mostrarTodosLosPedDelLocal");
		if(!pedidosEntregadosPropios(local).isEmpty()) {mAV.addObject("mensaje1","Ventas con stock propio");}
		if(!pedidosEntregadosAgenos(local).isEmpty()) {mAV.addObject("mensaje2","Ventas con stock ajeno");}
		if(!pedidosEnviadosRechazados(local).isEmpty()) {mAV.addObject("mensaje3","Pedidos enviados de nuestro local, rechazados (eliminar luego de ver)");}
		if(!pedidosRecibidoPorLocal(local).isEmpty()) {mAV.addObject("mensaje4","Solicitud de stock que se deben aceptar o rechazar por este local");}
		if(!pedidosParaEnviarPorLocal(local).isEmpty()) {mAV.addObject("mensaje5","En este local no hay stock suficiente, enviar Solicitud de stock a otro local o cambiar cantidad");}
		mAV.addObject("pedidosEntrPropios",pedidosEntregadosPropios(local));
		mAV.addObject("pedidosEntrAgenos",pedidosEntregadosAgenos(local));
		mAV.addObject("pedidosRechazdos",pedidosEnviadosRechazados(local));
		mAV.addObject("pedidosRecibidos",pedidosRecibidoPorLocal(local));
		mAV.addObject("pedidosAEnviar",pedidosParaEnviarPorLocal(local));
		return mAV;
	}//en esta tengo un botón que me lleva a /newPedidoEmpleado si quiero agregar otro despues de ver
		

	@GetMapping("/empleaOrig/{id}")//me trae acá, si en la vista del mostrar pulso "ver" en alguno de los que son para enviar a otro local (por falta de stock)
	public ModelAndView getEmpleadoOriginal(@PathVariable("id") int idPedido) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PEDIDO_UPDATE_EMPL_ORIG);//va a esta vista
		mAV.addObject("pedido", pedidoService.findByIdPedido(idPedido));
		mAV.addObject("clientes", clienteService.getAll());
		Pedido pedido=pedidoRepository.findByIdPedido(idPedido);
		System.out.println(" TENGOOOOOOOOOOO UNA CATIDAD??? ="+pedido.getCantidad());
		Local local = localRepository.findByIdLocal(pedido.getLocal().getIdLocal());
		mAV.addObject("empleados",traerEmpleadosDelLocal(local));
		mAV.addObject("productos", productoService.getAll());
		mAV.addObject("locales",traerLocCercanosConStockOrdPorDistancia(pedido));
		mAV.addObject("localesOrd",traerLocCercanosConStockOrdPorDistancia(pedido));
		return mAV;
	}
	
	@PostMapping("/updateEmplOri")//acá viene cuan damos clic en edit en la vista pedido/updateEmpleadoOriginal a dónde llego  con el @GetMapping("/empleaOrig/{id}")
	public ModelAndView updateEmpleadoOriginal(@ModelAttribute("pedido") PedidoModel pedidoModel) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.MOSTRAR_PEDIDOS_LOCAL_EMPLEADO);//mando la lista de pedidos a pedido/pedEmpleado
		pedidoService.UpdateEmpleadoOrig(pedidoModel);//ACTUALIZO COMO EMPLEADO ORIGINAL
		Empleado empleadoOrig = empleadoRepository.findByIdPersona(pedidoModel.getVendedorOriginal().getIdPersona());//traigo el vendedor original del pedido
		Local localDelOriginal = localRepository.findByIdLocal(empleadoOrig.getLocal().getIdLocal());//traigo el local del vendedor original del pedido
		LocalModel locDelOrigModel = localConverter.entityToModel(localDelOriginal);
		PedidoModel pedido=pedidoService.findByIdPedido(pedidoModel.getIdPedido());
   System.out.println("FUERA       IF");
		if(pedido.getVendedorAuxiliar()==null && localTieneStockDeUnProducto(locDelOrigModel,pedido.getProducto(),pedido.getCantidad())==true) {//verifico si hay stock (por si cambia la cantidad en update)
			 System.out.println("DENTRO       IF");
			descontarStockDeUnProductoLocalPropio(pedido.getLocal(),pedido.getProducto(),pedido.getCantidad());//descuento
			pedidoModel.setLocal(localConverter.entityToModel(localDelOriginal));//por más que por error haya puesto otro, si tiene stock queda el local del empleado original
			pedidoModel.setAceptado(true);//llega acá se descuenta el stock dentro del "localTieneStockDeUnProducto"
			pedidoModel.setFecha(LocalDate.now());//seteo la fecha en que se aceptó
			pedidoService.UpdateEmpleadoOrig(pedidoModel);//ACTUALIZO  para guardar los cambios BD
	    }
		mAV = mostrarTodosLosPedDelLocal(localDelOriginal);/*muestro los pedidos local del vendedor original después del update (ya que en este update se entra como original solamente)*/
		return mAV;
	}//es el update en que puedo enviar la solicitud de stock a otro Local (entre otras opciones)

	boolean pedidoPreActualizacionEsAceptado;////me sirve para registrar el acetado=false (y luego comparar para saber cuando el auxiliar lo pone en true)
	@GetMapping("/empleaAuxi/{id}")//me trae acá, si en la vista del mostrar pulso "ver" en alguno de los que tenga para aceptar o rechazar
	public ModelAndView getEmpleadoAuxilar(@PathVariable("id") int idPedido) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PEDIDO_UPDATE_EMPL_AUXI);//va a esta vista
		PedidoModel pedido=pedidoService.findByIdPedido(idPedido);
		pedidoPreActualizacionEsAceptado = pedido.isAceptado();//me sirve para registrar el acetado=false (y luego comparar para saber cuando el auxiliar lo pone en true)
		System.out.println("PRE ACEPTADOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO??? ="+pedido.isAceptado());
		Empleado empleadoOrig = empleadoRepository.findByIdPersona(pedido.getVendedorOriginal().getIdPersona());
		Local local = localRepository.findByIdLocal(pedido.getLocal().getIdLocal());
		Cliente cliente = clienteRepository.findByIdPersona(pedido.getCliente().getIdPersona());
		Producto producto = productoRepository.findByIdProducto(pedido.getProducto().getIdProducto());
		mAV.addObject("pedido", pedido);
		mAV.addObject("clientes", traerClienteEnLista(cliente));
		mAV.addObject("empleado", traerEmpleadoEnLista(empleadoOrig));
		mAV.addObject("empleados",traerEmpleadosDelLocal(local));
		mAV.addObject("productos", traerProductoEnLista(producto));
		mAV.addObject("locales",traerLocalEnLista(empleadoOrig.getLocal()));
//		mAV.addObject("locales",localService.getAll());
		return mAV;
	}//los traje en lista para usar los selec para mantener los datos
	
	
	@PostMapping("/updateEmplAuxi")//acá viene cuan damos clic en edit en la vista pedido/updateEmpleadoAuxilar a dónde llego con el @GetMapping("/empleaAuxi/{id}")
	public ModelAndView updateEmpleadoAuxiliar(@ModelAttribute("pedido") PedidoModel pedidoModel) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.MOSTRAR_PEDIDOS_LOCAL_EMPLEADO);//mando la lista de pedidos a pedido/pedEmpleado
        Pedido pedidoPreUpdate = pedidoRepository.findByIdPedido(pedidoModel.getIdPedido());
		pedidoService.UpdateEmpleadoAuxi(pedidoModel);// (1) ACTUALIZO COMO EMPLEADO AUXILIAR
		System.out.println(" 9 en updateEmpleadoAuxiliar    "+"  ID "+pedidoModel.getIdPedido()+" Pre Aceptado "+pedidoPreActualizacionEsAceptado+"  Aceptado pediMo"+pedidoModel.isAceptado()+" aceptado de la BD "+pedidoPreUpdate.isAceptado());
		if(pedidoModel.isAceptado()==true && pedidoPreActualizacionEsAceptado==false) {//si en el update (1) se cambio el aceptado a  true
			pedidoModel.setFecha(LocalDate.now());//seteo la fecha en que se aceptó
			pedidoService.UpdateEmpleadoAuxi(pedidoModel);//ACTUALIZO COMO EMPLEADO AUXILIAR para guardar la fecha en la BD
			descontarStockDeUnProductoLocalAjeno(pedidoModel);		
		}
		Empleado empleadoAuxiliar = empleadoRepository.findByIdPersona(pedidoModel.getVendedorAuxiliar().getIdPersona());//traigo el vendedor Auxiliar del pedido
		Local local = localRepository.findByIdLocal(empleadoAuxiliar.getLocal().getIdLocal());//traigo el local del vendedor Auxiliar del pedido
		mAV = mostrarTodosLosPedDelLocal(local);/*muestro los pedidos local del vendedor auxiliar después del update (ya que en este update se entra como auxiliar solamente)*/
		return mAV;
	}//es el update en que puedo aceptar o rechazar la solicitud de stock de otro Local (entre otras opciones)
	

		
	public boolean localTieneStockDeUnProducto(LocalModel localM, ProductoModel productoM, int cantidadPedida){
		boolean hayStock=false;
		int sumaDeStockActual=0;
		Local local = localRepository.findByIdLocal(localM.getIdLocal());
		Producto producto = productoRepository.findByIdProducto(productoM.getIdProducto());
		for(Lote lote: local.getLotes()) {
			if(lote.getProducto().equals(producto)) {
				sumaDeStockActual+=lote.getCantidadActual();
			}
		}
		if(cantidadPedida<=sumaDeStockActual){
			hayStock=true;
		}		
		return hayStock;
	}
	
	public boolean descontarStockDeUnProductoLocalPropio(LocalModel localM, ProductoModel productoM, int cantidadPedida){
		boolean seDesconto=false;
		int sumaDeStockActual=0;
		int sumaDeStockInicial=0;
		Local local = localRepository.findByIdLocal(localM.getIdLocal());
		Producto producto = productoRepository.findByIdProducto(productoM.getIdProducto());
		for(Lote lote: local.getLotes()) {
			if(lote.getProducto().equals(producto)) {
				sumaDeStockActual+=lote.getCantidadActual();
				sumaDeStockInicial+=lote.getCantidadInicial();
			}
		}
		if(cantidadPedida<=sumaDeStockActual){
			descontarStockDeUnProducto(local, producto, cantidadPedida, sumaDeStockInicial, sumaDeStockActual);
			seDesconto=true;
		}		
		return seDesconto;
	}
	
	
	public boolean descontarStockDeUnProductoLocalAjeno(PedidoModel pedido){
		boolean seDesconto=false;
		int sumaDeStockActual=0;
		int sumaDeStockInicial=0;
		Empleado empleadoAuxiliar = empleadoRepository.findByIdPersona(pedido.getVendedorAuxiliar().getIdPersona());
		Local localDelEmplAux = localRepository.findByIdLocal(empleadoAuxiliar.getLocal().getIdLocal());
		Producto producto = productoRepository.findByIdProducto(pedido.getProducto().getIdProducto());
		for(Lote lote: localDelEmplAux.getLotes()) {
			if(lote.getProducto().equals(producto)) {
				sumaDeStockActual+=lote.getCantidadActual();
				sumaDeStockInicial+=lote.getCantidadInicial();
			}
		}
		if(pedido.getCantidad()<=sumaDeStockActual){
			System.out.println(" cant ped "+pedido.getCantidad()+" Producto "+producto.getNombre()+" sumaDeStockInicial "+sumaDeStockInicial+" sumaDeStockActual "+sumaDeStockActual);
			descontarStockDeUnProducto(localDelEmplAux, producto, pedido.getCantidad(), sumaDeStockInicial, sumaDeStockActual);
			seDesconto=false;
	}	
	  return seDesconto;
	}
	

	public void descontarStockDeUnProducto(Local local, Producto producto, int cantidadPedida, int sumaDeStockInicial, int sumaDeStockActual){
		List<Lote> lotesDeunProducto= new ArrayList<Lote>();		
		for(Lote lote : local.getLotes()) {//
			if(lote.getProducto().equals(producto)){//if ... si no es el último lote
				lotesDeunProducto.add(lote);
			}
		}
		Lote loteAux = lotesDeunProducto.get(lotesDeunProducto.size()-1);
		for(Lote lote : lotesDeunProducto) {//
			if(lote.getIdLote()!=loteAux.getIdLote()){//if ... si no es el último lote
				loteService.remove(lote.getIdLote());//lo borro
			}
		}//for
		loteAux.setCantidadInicial(sumaDeStockInicial);//en el lote que dejé guardo la suma de todas las cantidades iniciales (local/lotes/producto) 
		loteAux.setCantidadActual(sumaDeStockActual-cantidadPedida);
		loteAux.setLocal(local);
		loteService.Update(loteConverter.entityToModel(loteAux));
		localService.insertOrUpdate(localConverter.entityToModel(local));	
	}

	public List<Pedido> pedidosPorLocal(Local local){
		List<Pedido> listaPedido = new ArrayList<Pedido>();
		for(Pedido pedido: local.getPedidos()) {//recorro todos los pedidos
			listaPedido.add(pedido);//guardo el pedido
		}//for pedido
		return listaPedido;
	}

	public List<Pedido> pedidosParaEnviarPorLocal(Local local){//no hay stock propio; se tienen que enviar a otro local a la espera de aceptación o rechazo
		List<Pedido> listaPedido = new ArrayList<Pedido>();
		for(Pedido pedido: pedidosPorLocal(local)) {
			if(pedido.isAceptado()==false && pedido.getVendedorOriginal().getLocal().getIdLocal()==pedido.getLocal().getIdLocal()){//mientras no se envien tienen el local del empleado original
				listaPedido.add(pedido);
			}
		}
		return listaPedido;
	}	

	public List<Pedido> pedidosRecibidoPorLocal(Local local){//pedidos de otro local que esperan aceptación o rechazo
		List<Pedido> listaPedido = new ArrayList<Pedido>();		
		for(Pedido pedido: pedidosPorLocal(local)) {
			if(pedido.isAceptado()==false && pedido.getVendedorOriginal().getLocal().getIdLocal()!=pedido.getLocal().getIdLocal()) {//cuado se envia se cambia el local del pedido
				listaPedido.add(pedido);
			}	
		}
		return listaPedido;
	}

	public List<Pedido> pedidosEntregadosPropios(Local local){//se entregó con stock propio
		List<Pedido> listaPedido = new ArrayList<Pedido>();		
		for(Pedido pedido: pedidosPorLocal(local)) {
			if(pedido.isAceptado()==true && pedido.getVendedorAuxiliar()==null && pedido.getVendedorOriginal().getLocal().getIdLocal()==pedido.getLocal().getIdLocal()) {
				listaPedido.add(pedido);
			}	
		}
		return listaPedido;
	}

	public List<Pedido> pedidosEntregadosAgenos(Local local){//se entregó con stock de otro local
		List<Pedido> listaPedido = new ArrayList<Pedido>();		
		for(Pedido pedido: pedidosPorLocal(local)) {
			if(pedido.isAceptado()==true && pedido.getVendedorAuxiliar()!=null && pedido.getVendedorOriginal().getLocal().getIdLocal()==pedido.getLocal().getIdLocal()) {
				listaPedido.add(pedido);
			}	
		}
		return listaPedido;
	}

	public List<Pedido> pedidosEnviadosRechazados(Local local){//los enviamos a otro local y son devueltos (rechazados)
		List<Pedido> listaPedido = new ArrayList<Pedido>();
		for(Pedido pedido: pedidosPorLocal(local)) {
			if(pedido.isAceptado()==false && pedido.getVendedorAuxiliar()!=null && pedido.getVendedorOriginal().getLocal().getIdLocal()==pedido.getLocal().getIdLocal()){//mientras no se envien tienen el local del empleado original
				listaPedido.add(pedido);
			}
		}
		return listaPedido;
	}

	public List<Empleado> traerEmpleadosDelLocal(Local local){//
		List<Empleado> listaEmpleado = new ArrayList<Empleado>();		
		for(Empleado empleado: local.getEmpleados()) {
			listaEmpleado.add(empleado);
		}
		return listaEmpleado;
	}	

	public List<Empleado> traerEmpleadoEnLista(Empleado empleado){//
		List<Empleado> listaEmpleado = new ArrayList<Empleado>();		
			listaEmpleado.add(empleado);
		return listaEmpleado;
	}	
	
	public List<LocalModel> traerLocalEnLista(Local local){
		List<LocalModel> listLoc = new ArrayList<LocalModel>();
		listLoc.add(localConverter.entityToModel(local));
      return listLoc;
	}
	
	public List<Cliente> traerClienteEnLista(Cliente cliente){
		List<Cliente> listCliente = new ArrayList<Cliente>();
		listCliente.add(cliente);
      return listCliente;
	}
	
	public List<Producto> traerProductoEnLista(Producto producto){
		List<Producto> listProducto = new ArrayList<Producto>();
		listProducto.add(producto);
      return listProducto;
	}
	//***************FIN METODOS ESPECIALES*****************

//
//	public List<LocalModel> localesDistanciaStock(PedidoModel pedido){
//		List<LocalModel> listadistancia=new ArrayList<LocalModel>();
//		List<Local> listaquery=new ArrayList<Local>();
//		List<LocalModel> aux=new ArrayList<LocalModel>();
//
//		listadistancia=traerLocalesMasCercanos(pedido.getVendedorOriginal().getLocal().getIdLocal());
//		listaquery=localRepository.localesConEseProducto(pedido.getProducto().getIdProducto(),pedido.getCantidad());
//		//traemos los locales ordenados por distancia y nos fijamos cuales tienen stock de ese producto
//		for(LocalModel l :listadistancia)
//		{
//			for(Local l1 :listaquery)
//			{
//				if(l.getIdLocal()==l1.getIdLocal())
//				{
//					aux.add(l);
//				}
//
//			}
//
//		}	
//
//		return aux;
//
//	}



	//PRUEBA

	public static double calcularDistancia(LocalModel local1, LocalModel local2) {
		double radioTierra = 6371; //en kilómetros
		double dLat = Math.toRadians(local2.getLatitud() - local1.getLatitud());
		double dLng = Math.toRadians(local2.getLongitud() - local1.getLongitud());
		double sindLat = Math.sin(dLat / 2);
		double sindLng = Math.sin(dLng / 2);
		double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2) 
		* Math.cos(Math.toRadians(local1.getLatitud())) 
		* Math.cos(Math.toRadians(local2.getLatitud()));//fin del calculo de va1
		double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
		return radioTierra * va2;
	}	


	//**************************Traer LOCALES POR DISTANCIA Y STOCK****************************************************************	
		
	public List<Local> traerLocCercanosConStockOrdPorDistancia(Pedido pedido){
		LocalModel localBase = localService.findByIdLocal(pedido.getLocal().getIdLocal());  
		// double distancias[]= new double [localService.getAll().size()-1];//es -1 por el local que voy a comparar con los demás
		Local localVector[]= new Local [traerLocalesConSTOCK(pedido).size()];
		int i =0;
		double aux;//es solo para usarlo en acortar los decimales del resultado y que no quede muy larga la linea
		for(Local local: traerLocalesConSTOCK(pedido)){//traigo solo los que tienen stock (el localBase NO TIENE stock)
			    LocalModel localM = localConverter.entityToModel(local);
				aux = calcularDistancia(localBase , localM);//para cada local calulo la distancia con respecto a localBase
				aux=(double)Math.round(aux * 100d) / 100d; //reduzco los decimale del resultad (a 2 decimales, por eso son los 00) y los casteo como double	
				local.setLatitud(aux);//en latitud de cada local guardo la distancia con respecto a localBase, pero no se guarda en la BD, ya que es solo para mostrarlo asociado al local 
				localVector[i] = local;//paso la lista (de a uno) a un vector, para odenar más fácil
				i++;//si estaría fuera del if contaria uno de más y sobrepasaría el valor del array
		}
		return odenarVectorDeLocales(localVector);//ordeno y retorno como lista (como lista a pedido de Franco, jaja)
	}
	
	
	public List<Local> traerLocalesConSTOCK(Pedido pedido){
		List<Local> localesConStock = new ArrayList<Local>();
		ProductoModel producto = productoService.findByIdProducto(pedido.getProducto().getIdProducto());
		for(Local local: localService.getAll()){//traigo y recorro todos los locales
			LocalModel localM = localConverter.entityToModel(local);//al paso covierto a model
			if(localTieneStockDeUnProducto(localM,producto,pedido.getCantidad())==true) {//si tiene stock
				localesConStock.add(local);//lo guardo en la lista
			} 
		}
		return localesConStock;//rotorno los mas cercanos a mi local que tenga stock
	}

	public List<Local> odenarVectorDeLocales(Local vecLocales[]){
		List<Local> localesCercanos = new ArrayList<Local>();
		Local localAuxiliar=null;
		for(int i=1;i<vecLocales.length;i++){//:::ORDENAMIENTO BURBUJA::: usando el tamanño del vector no se consulta a la BD
			for(int j= 0;j<vecLocales.length-1;j++) {
				if(vecLocales[j].getLatitud()>vecLocales[j+1].getLatitud()){
					localAuxiliar = vecLocales[j];
					vecLocales[j] = vecLocales[j+1];
					vecLocales[j+1] = localAuxiliar;
				}//if
			}//for j
		}//for i
		for (int i=0;i<vecLocales.length;i++){
			localesCercanos.add(vecLocales[i]);// lo guardo de nuevo en una lista 
		}
		return localesCercanos;
	}
	//**************************FIN Traer LOCALES POR DISTANCIA****************************************************************


}//fin class
