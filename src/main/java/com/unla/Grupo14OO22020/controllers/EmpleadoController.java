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

import com.unla.Grupo14OO22020.entities.Empleado;
import com.unla.Grupo14OO22020.helpers.ViewRouteHelpers;
import com.unla.Grupo14OO22020.models.EmpleadoModel;
import com.unla.Grupo14OO22020.services.IEmpleadoService;
import com.unla.Grupo14OO22020.services.ILocalService;


@Controller
@RequestMapping("/empleados")
public class EmpleadoController {
	
	@Autowired
	@Qualifier("empleadoService")
	private IEmpleadoService empleadoService;
	
	@Autowired
	@Qualifier("localService")
	private ILocalService localService;
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.EMPLEADO_INDEX);
		mAV.addObject("empleados", empleadoService.getAll());
		mAV.addObject("empleado", new EmpleadoModel());
		return mAV;
	}
	
	@GetMapping("/new")
	public ModelAndView crear() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.EMPLEADO_ADD);
		mAV.addObject("empleado", new Empleado());
		mAV.addObject("locales", localService.getAll());
		return mAV;
	}
	
	@PostMapping("/create")
	public RedirectView agregar(@ModelAttribute(name="empleados") EmpleadoModel empleado) {
		empleadoService.insertOrUpdate(empleado);
		return new RedirectView(ViewRouteHelpers.EMPLEADO_ROOT);
	}
	
	@PostMapping("/delete/{id}")
	public RedirectView eliminar(@PathVariable("id") int id) {
		empleadoService.remove(id);
		return new RedirectView(ViewRouteHelpers.EMPLEADO_ROOT);
	}
	
	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int idPersona) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.EMPLEADO_UPDATE);
		mAV.addObject("empleado", empleadoService.findByIdPersona(idPersona));
		mAV.addObject("locales", localService.getAll());
		return mAV;
	}
	
	@PostMapping("/update")
	public RedirectView update(@ModelAttribute("empleado") EmpleadoModel empleadoModel) {
		empleadoService.insertOrUpdate(empleadoModel);
		return new RedirectView(ViewRouteHelpers.EMPLEADO_ROOT);
	}
	
}
