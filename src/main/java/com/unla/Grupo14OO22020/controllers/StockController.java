package com.unla.Grupo14OO22020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.Grupo14OO22020.helpers.ViewRouteHelpers;
import com.unla.Grupo14OO22020.models.StockModel;
import com.unla.Grupo14OO22020.services.ILocalService;
import com.unla.Grupo14OO22020.services.ILoteService;
import com.unla.Grupo14OO22020.services.IStockService;

@Controller
@RequestMapping("/stocks")
public class StockController {

	@Autowired
	@Qualifier("stockService")
	private IStockService stockService;
	
	
	@Autowired
	@Qualifier("loteService")
	private ILoteService loteService; 
	
	@Autowired
	@Qualifier("localService")
	private ILocalService localService; 
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.STOCK_INDEX);
		mAV.addObject("stocks", stockService.getAll());
		mAV.addObject("stock", new StockModel());
		return mAV;
	}
	
	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int idStock) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.STOCK_UPDATE);
		mAV.addObject("stock", stockService.findByIdStock(idStock));
		mAV.addObject("lotes", loteService.getAll());
		return mAV;
	}
	
//	@GetMapping("/new")
//	public ModelAndView crear() {
//		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.STOCK_ADD);
//		mAV.addObject("stock", new StockModel());
//		mAV.addObject("locales", localService.getAll());
//		return mAV;
//	}
//	
//	@PostMapping("/create")
//	public RedirectView agregar(@ModelAttribute(name="stocks") StockModel stock) {
//		stockService.Insert(stock);
//		return new RedirectView(ViewRouteHelpers.STOCK_ROOT);
//	}
//	
//	@PostMapping("/delete/{id}")
//	public RedirectView eliminar(@PathVariable("id") int id) {
//		stockService.remove(id);
//		return new RedirectView(ViewRouteHelpers.STOCK_ROOT);
//	}
//	
//	@GetMapping("/{id}")
//	public ModelAndView get(@PathVariable("id") int idStock) {
//		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.STOCK_UPDATE);
//		mAV.addObject("stock", stockService.findByIdStock(idStock));
//		mAV.addObject("lotes", loteService.getAll());
//		return mAV;
//	}
//	
//	@PostMapping("/update")
//	public RedirectView update(@ModelAttribute("stock") StockModel stockModel) {
//		stockService.Update(stockModel);
//		return new RedirectView(ViewRouteHelpers.STOCK_ROOT);
//	}
	
}
