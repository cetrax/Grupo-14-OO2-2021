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
import com.unla.Grupo14OO22020.models.LoteModel;
import com.unla.Grupo14OO22020.services.ILocalService;
import com.unla.Grupo14OO22020.services.ILoteService;
import com.unla.Grupo14OO22020.services.IProductoService;




@Controller
@RequestMapping("/lotes")
public class LoteController {
	
	@Autowired
	@Qualifier("loteService")
	private ILoteService loteService;
	
	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;
	
	
	@Autowired
	@Qualifier("localService")
	private ILocalService localService;
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////
	

	@GetMapping("/indexEmpleado")
	public ModelAndView indexEmpleado() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.LOTE_INDEXEMPLEADO);
		mAV.addObject("lotes", loteService.getAll());
		mAV.addObject("lote", new LoteModel());
		
		return mAV;
	}
	
	@GetMapping("/newEmpleado")
	public ModelAndView crearEmpleado() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.LOTE_ADDEMPLEADO);
		mAV.addObject("lote", new LoteModel());
		mAV.addObject("productos", productoService.getAll());
		mAV.addObject("locales", localService.getAll());
		return mAV;
	}
	
	@PostMapping("/createEmpleado")
	public RedirectView agregarEmpleado(@ModelAttribute(name="lotes") LoteModel lote) {
		lote.setCantidadActual(lote.getCantidadInicial());
		loteService.Insert(lote);
		return new RedirectView(ViewRouteHelpers.LOTE_ROOTEMPLEADO);
	}
	
	@GetMapping("/updateEmpleado/{id}")
	public ModelAndView getEmpleado(@PathVariable("id") int idLote) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.LOTE_UPDATEEMPLEADO);
		mAV.addObject("lote", loteService.findByIdLote(idLote));
		mAV.addObject("productos", productoService.getAll());
		return mAV;
	}
	
	@PostMapping("/updateEmpleado")
	public RedirectView updateEmpleado(@ModelAttribute("lotes") LoteModel loteModel) {
		loteService.Update(loteModel);
		return new RedirectView(ViewRouteHelpers.LOTE_ROOTEMPLEADO);
	}
	
	@PostMapping("/deleteEmpleado/{id}")
	public RedirectView eliminarEmpleado(@PathVariable("id") int id) {
		loteService.remove(id);
		return new RedirectView(ViewRouteHelpers.LOTE_ROOTEMPLEADO);
	}	
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.LOTE_INDEX);
		mAV.addObject("lotes", loteService.getAll());
		mAV.addObject("lote", new LoteModel());
		
		return mAV;
	}
	
	@GetMapping("/new")
	public ModelAndView crear() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.LOTE_ADD);
		mAV.addObject("lote", new LoteModel());
		mAV.addObject("productos", productoService.getAll());
		mAV.addObject("locales", localService.getAll());
		return mAV;
	}
	
	@PostMapping("/create")
	public RedirectView agregar(@ModelAttribute(name="lotes") LoteModel lote) {
		lote.setCantidadActual(lote.getCantidadInicial());
		loteService.Insert(lote);
		return new RedirectView(ViewRouteHelpers.LOTE_ROOT);
	}
	
	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int idLote) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.LOTE_UPDATE);
		mAV.addObject("lote", loteService.findByIdLote(idLote));
		mAV.addObject("productos", productoService.getAll());
		return mAV;
	}
	
	@PostMapping("/update")
	public RedirectView update(@ModelAttribute("lotes") LoteModel loteModel) {
		loteService.Update(loteModel);
		return new RedirectView(ViewRouteHelpers.LOTE_ROOT);
	}
	
	@PostMapping("/delete/{id}")
	public RedirectView eliminar(@PathVariable("id") int id) {
		loteService.remove(id);
		return new RedirectView(ViewRouteHelpers.LOTE_ROOT);
	}	
	

}