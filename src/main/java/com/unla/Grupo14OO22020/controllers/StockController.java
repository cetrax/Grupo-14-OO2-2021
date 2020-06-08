package com.unla.Grupo14OO22020.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.Grupo14OO22020.converters.StockConverter;
import com.unla.Grupo14OO22020.entities.Lote;
import com.unla.Grupo14OO22020.entities.Stock;
import com.unla.Grupo14OO22020.helpers.ViewRouteHelpers;
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
	@Qualifier("stockConverter")
	private StockConverter stockConverter;
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.STOCK_INDEX);
		List<Stock> stocks = stockService.getAll();
		List<Lote> lotes = loteService.getAll();
		
		for(Stock stock: stocks) {
			int cant =0;
			for(Lote lote: lotes) {	
			        if(stock.getIdStock()==lote.getStock().getIdStock()) {
			            cant += lote.getCantidadActual();
			            stock.setCantidad(cant);
			//		stockService.Update(stockConverter.entityToModel(stock));
					}
			}
		}
		mAV.addObject("stocks", stocks);
		return mAV;
	}

	
//	@GetMapping("/{id}")
//	public ModelAndView get(@PathVariable("id") int idStock) {
//		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.STOCK_UPDATE);
//		mAV.addObject("stock", stockService.findByIdStock(idStock));
//		mAV.addObject("lotes", loteService.getAll());
//		return mAV;
//	}
	
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
