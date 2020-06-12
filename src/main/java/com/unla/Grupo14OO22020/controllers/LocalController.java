package com.unla.Grupo14OO22020.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.unla.Grupo14OO22020.entities.Local;
import com.unla.Grupo14OO22020.entities.Lote;
import com.unla.Grupo14OO22020.helpers.ViewRouteHelpers;
import com.unla.Grupo14OO22020.models.LocalModel;
import com.unla.Grupo14OO22020.models.LocalesModel;
import com.unla.Grupo14OO22020.repositories.ILocalRepository;
import com.unla.Grupo14OO22020.repositories.ILoteRepository;
import com.unla.Grupo14OO22020.services.ILocalService;
import com.unla.Grupo14OO22020.services.ILoteService;


@Controller
@RequestMapping("/locales")
public class LocalController {

	@Autowired
	@Qualifier("localRepository")
	private ILocalRepository localRepository;
	
	@Autowired
	@Qualifier("localService")
	private ILocalService localService;
	
	@Autowired
	@Qualifier("loteRepository")
	private ILoteRepository loteRepository;
	
	@Autowired
	@Qualifier("loteService")
	private ILoteService loteService;

	@Autowired
	@Qualifier("localConverter")
	private LocalConverter localConverter;

	/*NOTA: Mediante la anotación @RequestMapping mapearemos la petición que muestra el 
formulario a un método java (será con GET) y la que lo procesa a otro distinto (POST):*/

	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.LOCAL_INDEX);
		 //traerLocalesMasCercanos(1);
		mAV.addObject("locales", localService.getAll());
		mAV.addObject("local", new LocalModel());
		return mAV;
	}

	@GetMapping("/new")
	public ModelAndView crear() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.LOCAL_ADD);
		mAV.addObject("local", new Local());
		return mAV;
	}

	@PostMapping("/create")
	public RedirectView agregar(@ModelAttribute(name="locales") LocalModel local ) {
		localService.insertOrUpdate(local);
		return new RedirectView(ViewRouteHelpers.LOCAL_ROOT);
	}

	@PostMapping("/delete/{id}")
	public RedirectView eliminar(@PathVariable("id") int id) {
		localService.remove(id);
		return new RedirectView(ViewRouteHelpers.LOCAL_ROOT);
	}
	
	
	public int calcularStockDelLocal(int idLocal) {
         int stock = 0;
       	 for(Lote lote:loteService.getAll()){//traigo todoss los lotes de un local
           if(lote.getLocal().getIdLocal()==idLocal) {        		
      	      stock += lote.getCantidadActual(); //sumo la cantidadActual de todos los lotes del local
           }	
        }
	 return stock;
	}

	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int idLocal) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.LOCAL_UPDATE);
		
		mAV.addObject("stock", calcularStockDelLocal(idLocal));
		mAV.addObject("lotes", loteService.lostesPorLocal(idLocal));
		mAV.addObject("local", localService.findByIdLocal(idLocal));
		mAV.addObject("localesCer",traerLocalesMasCercanos(idLocal));
		return mAV;
	}

	@PostMapping("/update")
	public RedirectView update(@ModelAttribute("local") LocalModel localModel) {
		localService.insertOrUpdate(localModel);
		return new RedirectView(ViewRouteHelpers.LOCAL_ROOT);
	}

	// DE ESTA FORMA SE HARÍA SI USARAMOS EL calcularDistancia IMPLENTADO EN EL LOCALMODEL
	//	@RequestMapping(value="/calculodistResult",method=RequestMethod.POST)
	//	public ModelAndView distancia(@RequestParam("id1") int id1, @RequestParam("id2") int id2,Model model) {
	//		LocalModel local1=localService.findByIdLocal(id1);
	//		LocalModel local2=localService.findByIdLocal(id2);
	//
	//		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.LOCAL_MOSTRAR);
	//		mAV.addObject("resultado",(local1.calcularDistancia(local2)));
	//		
	//		return mAV;
	//	}


	//calcularDistancia IMPLEMENTADO ACÁ (en el LocalController) POR CONSEJO DEL PROFE
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

	//***************para calcular distancia seleccionando desde lista***PEDIDO************
	@GetMapping("/comenzarCalculoDistancia")//a través de esta ruta (agrgada a "/locales") voy a la vista de LOCAL_CALCULAR_DIS
	public ModelAndView distancia_L() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.LOCAL_CALCULAR_DIS);//creo un "ModelAndView" que va a enviar por la ruta "LOCAL_CALCULAR_DIS que está implementada en ViewRouteHelpers
		mAV.addObject("locales",localService.getAll());//agrego al "ModelAndView" una lista de locales que se recibirá a trvés de "locales"
		return mAV;
	}
 /*Explicación del "distanciaObtener": Mediante la anotación @RequestMapping mapearemos con un formulario y 
 a través de -value="/obtenerDistancia"- del formulario voy a obtener los atributos de un Objeto de la 
 Clase "LocalesModel" y creo el Objeto "LocalesModel". Con "method=RequestMethod.POST" le digo que acá 
 quiero que llegue lo procesado (un "LocalesModel" creado)... "LocalesModel" tiene 2 LocalModel como 
 atributos pero estos 2 "LocalesModel" solo van a tener los ids cargados*/
	@RequestMapping(value="/obtenerDistancia",method=RequestMethod.POST)
	public ModelAndView distanciaObtener(LocalesModel locales) {
		LocalModel local1=localService.findByIdLocal(locales.getLocal1().getIdLocal());// saco un LocalModel de LocalesModel (que tiene 2)
		LocalModel local2=localService.findByIdLocal(locales.getLocal2().getIdLocal());// saco el otro LocalModel de LocalesModel (que tiene 2)... los podia pasar directamente, pero queda mas entedible así
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.LOCAL_MOSTRAR);//creo un "ModelAndView" que va a enviar por la ruta "LOCAL_CALCULAR_DIS que está implementada en ViewRouteHelpers
		mAV.addObject("resultado",(calcularDistancia(local1 ,local2)));//agrego al "ModelAndView" el resultado del "calcularDistancia" que se recibirá a trvés de "resutado"		
		return mAV;
	}
	//***************fin para calcular distancia seleccionando desde lista*****************


	//***************para calcular distancia pidiendo ingreso de IDs***EXTRA****************	
	@GetMapping("/calculodist")
	public ModelAndView distancia_L2() {// aunque parezca igual al "distancia_L" van por rutas distintas 
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.LOCAL_CALCULAR);
		mAV.addObject("locales", localService.getAll());
		return mAV;
	}
/*Explicación del "distancia": a diferencia del "distanciaObtener" el "distancia" no necesita que se cree 
	una clase especial, solo recibe dos int y con eso trae los locales*/
	@RequestMapping(value="/calculodistResult",method=RequestMethod.POST)
	public ModelAndView distancia(@RequestParam("id1") int id1, @RequestParam("id2") int id2,Model model) {
		LocalModel local1=localService.findByIdLocal(id1);
		LocalModel local2=localService.findByIdLocal(id2);
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.LOCAL_MOSTRAR);
		mAV.addObject("resultado",(calcularDistancia(local1 ,local2)));

		return mAV;
	}
	//***************fin para calcular distancia pidiendo ingreso de IDs******************	

	
//**************************Traer LOCALES POR DISTANCIA****************************************************************	
 
	public List<LocalModel> traerLocalesMasCercanos(int idLocal){
	  LocalModel localBase = localService.findByIdLocal(idLocal);  
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
//**************************FIN Traer LOCALES POR DISTANCIA****************************************************************	  

}//Fin class
