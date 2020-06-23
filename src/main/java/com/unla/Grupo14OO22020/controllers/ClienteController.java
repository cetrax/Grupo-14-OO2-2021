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
import com.unla.Grupo14OO22020.helpers.ViewRouteHelpers;
import com.unla.Grupo14OO22020.models.ClienteModel;
import com.unla.Grupo14OO22020.services.IClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	@Qualifier("clienteService")
	private IClienteService clienteService;
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@GetMapping("/indexEmpleado")
	public ModelAndView indexEmpleado() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.CLIENT_INDEXEMPLEADO);
		mAV.addObject("clientes", clienteService.getAll());
		mAV.addObject("cliente", new ClienteModel());
		
		return mAV;
	}
	
	@GetMapping("/newEmpleado")
	public ModelAndView crearEmpleado() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.CLIENT_ADDEMPLEADO);
		mAV.addObject("cliente", new Cliente());
		
		return mAV;
	}
	
	@PostMapping("/createEmpleado")
	public RedirectView agregarEmpleado(@ModelAttribute(name="clientes") ClienteModel cliente) {
		clienteService.insertOrUpdate(cliente);
		return new RedirectView(ViewRouteHelpers.CLIENT_ROOTEMPLEADO);
	}
	
	@PostMapping("/deleteEmpleado/{id}")
	public RedirectView eliminarEmpleado(@PathVariable("id") int id) {
		clienteService.remove(id);
		return new RedirectView(ViewRouteHelpers.CLIENT_ROOTEMPLEADO);
	}
	
	@GetMapping("/updateEmpleado/{id}")
	public ModelAndView getEmpleado(@PathVariable("id") int idPersona) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.CLIENT_UPDATEEMPLEADO);
		mAV.addObject("cliente", clienteService.findByIdPersona(idPersona));
		return mAV;
	}
	
	@PostMapping("/updateEmpleado")
	public RedirectView updateEmpleado(@ModelAttribute("cliente") ClienteModel clienteModel) {
		clienteService.insertOrUpdate(clienteModel);
		return new RedirectView(ViewRouteHelpers.CLIENT_ROOTEMPLEADO);
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.CLIENT_INDEX);
		mAV.addObject("clientes", clienteService.getAll());
		mAV.addObject("cliente", new ClienteModel());
		
		return mAV;
	}
	
	@GetMapping("/new")
	public ModelAndView crear() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.CLIENT_ADD);
		mAV.addObject("cliente", new Cliente());
		
		return mAV;
	}
	
	@PostMapping("/create")
	public RedirectView agregar(@ModelAttribute(name="clientes") ClienteModel cliente) {
		clienteService.insertOrUpdate(cliente);
		return new RedirectView(ViewRouteHelpers.CLIENT_ROOT);
	}
	
	@PostMapping("/delete/{id}")
	public RedirectView eliminar(@PathVariable("id") int id) {
		clienteService.remove(id);
		return new RedirectView(ViewRouteHelpers.CLIENT_ROOT);
	}
	
	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int idPersona) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.CLIENT_UPDATE);
		mAV.addObject("cliente", clienteService.findByIdPersona(idPersona));
		return mAV;
	}
	
	@PostMapping("/update")
	public RedirectView update(@ModelAttribute("cliente") ClienteModel clienteModel) {
		clienteService.insertOrUpdate(clienteModel);
		return new RedirectView(ViewRouteHelpers.CLIENT_ROOT);
	}
	
	
	
	
}	
	
	
	

