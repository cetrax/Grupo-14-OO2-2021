package com.unla.Grupo14OO22020.controllers;

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
import com.unla.Grupo14OO22020.entities.Lote;
import com.unla.Grupo14OO22020.helpers.ViewRouteHelpers;
import com.unla.Grupo14OO22020.models.LocalModel;
import com.unla.Grupo14OO22020.models.PedidoModel;
import com.unla.Grupo14OO22020.repositories.ILocalRepository;
import com.unla.Grupo14OO22020.repositories.IPedidoRepository;
import com.unla.Grupo14OO22020.services.IClienteService;
import com.unla.Grupo14OO22020.services.IEmpleadoService;
import com.unla.Grupo14OO22020.services.ILocalService;
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
	@Qualifier("localRepository")
	private ILocalRepository localRepository;
	
	@Autowired
	@Qualifier("pedidoRepository")
	private IPedidoRepository pedidoRepository;
	
	@Autowired
	@Qualifier("localConverter")
	private LocalConverter localConverter;
	
	@Autowired
	@Qualifier("pedidoConverter")
	private PedidoConverter pedidoConverter;
	
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
		aceptarPedido(pedido);
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
		mAV.addObject("locales",localService.getAll());
		
		PedidoModel pedido=pedidoService.findByIdPedido(idPedido);
		
		mAV.addObject("localesOrd",localesDistanciaStock(pedido));
		
		
		return mAV;
	}
	
	@PostMapping("/update")
	public RedirectView update(@ModelAttribute("pedido") PedidoModel pedidoModel) {
		pedidoService.Update(pedidoModel);
		return new RedirectView(ViewRouteHelpers.PEDIDO_ROOT);
	}
	public void aceptarPedido(PedidoModel pedido)
	{
		//lista de locales con stock ordenados por distancia
		List<LocalModel> locales = localesDistanciaStock(pedido);
		//guardo la cantidad de cierto producto que tengo en el local elegido
		int cantidadBase=pedidoRepository.StockLocal(pedido.getProducto().getIdProducto(),pedido.getLocal().getIdLocal());
		//en caso de que el local actual no tenga stock
		int stockLocal=0;
		//se usa para que no entre mas a setear los lotes cuando ya llegamos a reponer el restante
		boolean banderaLote=false;
		//se usa cuando el local que tenemos puede abastecer la cantidad pedida
		boolean banderaLocalActual=false;
		//si el lote da negativo le resto al faltante la cantidad de ese lote
		int resta1=0;
		//si la cantidad del pedido excede la cantidad actual del local
		if(pedido.getCantidad()>cantidadBase)
		{
			//primero pongo en cero la cantidad que si puede abastecer
			for(Lote lote:pedido.getLocal().getLotes())
			{
				if(lote.getProducto().getIdProducto()==pedido.getProducto().getIdProducto())
				{lote.setCantidadActual(0);}
			}
			//actualizo la base de datos
			localService.insertOrUpdate(pedido.getLocal());
			
			//guardo lo que me falta para completar el pedido
			int restante=pedido.getCantidad()-cantidadBase;
			
			for(LocalModel local:locales)
			{
				
				//guardo stock de cada local de ese producto empezando por el mas cercano
				stockLocal=pedidoRepository.StockLocal(pedido.getProducto().getIdProducto(),local.getIdLocal());
				//pregunto si el stock del producto en el local mas cercano llega a completar restante
				if(stockLocal>=restante && banderaLote==false)
				{
					for(Lote lote:local.getLotes())
					{
						//si cuando le saco la cantidad no da negativo no entra mas al if
						if(lote.getCantidadActual()-(restante-resta1)>=0 && banderaLote==false)
						{
							lote.setCantidadActual(lote.getCantidadActual()-(restante-resta1));
							banderaLote=true;
						}
						else
							//si es true no deberia seguir ya que se llego a la cantidad necesitada
						if(banderaLote==false)
						{
							resta1=restante-lote.getCantidadActual();
							//si da negativo queda en 0
							lote.setCantidadActual(0);
						}
					}
					//actualizo la base de datos
					localService.insertOrUpdate(local);
				}
				
				
				
			}
			
			if(banderaLote==false)
			{System.out.println("No se pudo abastecer la cantidad del producto pedido");}
			
			
			//UNA VEZ QUE TERMINAMOS DE ABASTECER LA CANTIDAD PONEMOS EL PEDIDO EN TRUE
			pedido.setAceptado(true);
			pedidoService.Update(pedido);
		
		}
		//si el local actual puede abastecer el pedido solamente descantamos de sus lotes
		else
		{	//si el lote da negativo le resto al faltante la cantidad de ese lote
			int resta2=0;	
			for(Lote l:pedido.getLocal().getLotes())
			{
				
				
				//si el idProducto del pedido es igual al idProducto de los lotes
				if(l.getProducto().getIdProducto()==pedido.getProducto().getIdProducto()&& banderaLocalActual==false)
				{
					//si ese lote no da negativo seteamos la cantidad sobrante y ya no seguimos restando
					if(l.getCantidadActual()-(pedido.getCantidad()-resta2)>=0)
						{l.setCantidadActual(l.getCantidadActual()-(pedido.getCantidad()-resta2));
						 banderaLocalActual=true;
						 }
					else{
						//guardo la cantidad ese lote para restarle a la cantidad faltante
						resta2=pedido.getCantidad()-l.getCantidadActual();
						l.setCantidadActual(0);
					}
					
				}
			}
			//UNA VEZ QUE TERMINAMOS DE ABASTECER LA CANTIDAD PONEMOS EL PEDIDO EN TRUE
			pedido.setAceptado(true);
			pedidoService.Update(pedido);
		
			
		}
		
	}

	
	public List<LocalModel> localesDistanciaStock(PedidoModel pedido){
	List<LocalModel> listadistancia=new ArrayList<LocalModel>();
	List<Local> listaquery=new ArrayList<Local>();
	List<LocalModel> aux=new ArrayList<LocalModel>();
	
	listadistancia=traerLocalesMasCercanos(pedido.getLocal().getIdLocal());
	listaquery=localRepository.localesConEseProducto(pedido.getProducto().getIdProducto(),pedido.getCantidad());
	
	//traemos los locales ordenados por distancia y nos fijamos cuales tienen stock de ese producto
	for(LocalModel l :listadistancia)
	{
		for(Local l1 :listaquery)
		{
			if(l.getIdLocal()==l1.getIdLocal())
			{
				aux.add(l);
			}
				
		}
		
	}	
	
	return aux;

	}
	
	
	
	 //PRUEBA
		public List<LocalModel> traerLocalesMasCercanos(int idPedido){
		  LocalModel localBase = pedidoService.findByIdPedido(idPedido).getLocal();  
		 // double distancias[]= new double [localService.getAll().size()-1];//es -1 por el local que voy a comparar con los demás
		  LocalModel localVector[]= new LocalModel [localService.getAll().size()-1];
		  int i =0;
		  double aux;//es solo para usarlo en acortar los decimales del resultado y que no quede muy larga la linea
		  for(Local loc: localService.getAll()){//lo traigo de la entitie para usar el getAll()
			  if(loc.getIdLocal()!=localBase.getIdLocal()) {//no se compara con el mismo local
			      LocalModel local =localConverter.entityToModel(loc);//lo convierto a model
			      aux = calcularDistancia(localBase , local);//para cada local calulo la distancia con respecto a localBase
			      aux=(double)Math.round(aux * 100d) / 100d; //reduzco	los decimale del resultado a 2 (por eso son los 00) y los casteo como double	
			      local.setLatitud(aux);//en latitud de cada local guardo la distancia con respecto a localBase, pero no guarda en la BD ya que es solo para mostrarlo asociado al local 
			      localVector[i] = local;//paso la lista (de a uno) a un vector, para odenar más fácil
			      i++;//si estaría fuera del if contaria uno de más y sobrepasaría el valor del array
			  } 
		  }
	    return odenarLocalesPorDistancia(localVector);//ordeno y retorno como lista (como lista a pedido de Franco, jaja)
	  }
	  
	 //----------------------------------------------------------------------------------- 
	  public List<LocalModel> odenarLocalesPorDistancia(LocalModel vecLocales[]){
		  List<LocalModel> localesCercanos = new ArrayList<LocalModel>();
		  LocalModel localAuxiliar=null;
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


}
