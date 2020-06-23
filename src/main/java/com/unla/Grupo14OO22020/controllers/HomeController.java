package com.unla.Grupo14OO22020.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.Grupo14OO22020.helpers.ViewRouteHelpers;

@Controller
@RequestMapping("/")
public class HomeController {
	
	
	
	//GET Example: SERVER/index
	
	
	@GetMapping("/home")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelpers.HOME);
		return modelAndView;
	}
	
	
	
	@GetMapping("/homeEmpleado")
	public ModelAndView homeEmpleado() {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelpers.HOME_EMPLEADO);
		return modelAndView;
	}
	
}



