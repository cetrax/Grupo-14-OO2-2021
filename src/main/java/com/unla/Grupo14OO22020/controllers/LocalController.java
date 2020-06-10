package com.unla.Grupo14OO22020.controllers;

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
import com.unla.Grupo14OO22020.entities.Localito;
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
	@Qualifier("localConverter")
	private LocalConverter localConverter;
	
	@Autowired
	@Qualifier("localService")
	private ILocalService localService;
	
	@Autowired
	@Qualifier("loteService")
	private ILoteService loteService;
	
	@Autowired
	@Qualifier("loteRepository")
	private ILoteRepository loteRepository;
	

	/*NOTA: Mediante la anotación @RequestMapping mapearemos la petición que muestra el 
formulario a un método java (será con GET) y la que lo procesa a otro distinto (POST):*/

	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.LOCAL_INDEX);
		mAV.addObject("locales", localService.getAll());
		mAV.addObject("local", new LocalModel());
		try {
			mAV.addObject("stock",localRepository.calculoStock());
		}catch(Exception e)
		{
			mAV.addObject("stock",0);
		}
		
		return mAV;
	}

	@GetMapping("/new")
	public ModelAndView crear() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.LOCAL_ADD);
		mAV.addObject("local", new Localito());
		return mAV;
	}

	@PostMapping("/create")
	public RedirectView agregar(@ModelAttribute(name="locales") LocalModel local ) {
		localService.insert(local);
		return new RedirectView(ViewRouteHelpers.LOCAL_ROOT);
	}

	@PostMapping("/delete/{id}")
	public RedirectView eliminar(@PathVariable("id") int id) {
		localService.remove(id);
		return new RedirectView(ViewRouteHelpers.LOCAL_ROOT);
	}

	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int idLocal) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.LOCAL_UPDATE);
		mAV.addObject("local", localService.findByIdLocal(idLocal));
		return mAV;
	}

	@PostMapping("/update")
	public RedirectView update(@ModelAttribute("local") LocalModel localModel) {
		localService.update(localModel);
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

}//Fin class
