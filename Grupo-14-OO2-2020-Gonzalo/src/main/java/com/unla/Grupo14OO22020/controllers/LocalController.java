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

import com.unla.Grupo14OO22020.entities.Cliente;
import com.unla.Grupo14OO22020.entities.Local;
import com.unla.Grupo14OO22020.helpers.ViewRouteHelpers;
import com.unla.Grupo14OO22020.models.ClienteModel;
import com.unla.Grupo14OO22020.models.LocalModel;
import com.unla.Grupo14OO22020.services.ILocalService;

@Controller
@RequestMapping("/local")
public class LocalController {
	
	@Autowired
	@Qualifier("localService")
	private ILocalService localService;
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.LOCAL_INDEX);
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
	public RedirectView agregar(@ModelAttribute(name="locales") LocalModel local) {
		localService.insertOrUpdate(local);
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
		mAV.addObject("cliente", localService.findByIdLocal(idLocal));
		return mAV;
	}
	

	@PostMapping("/update")
	public RedirectView update(@ModelAttribute("local") LocalModel localModel) {
		localService.insertOrUpdate(localModel);
		return new RedirectView(ViewRouteHelpers.LOCAL_ROOT);
	}

}//Fin class
