package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.model.Merchandise;
import com.example.demo.service.MerchandiseService;

@Controller
@RequestMapping("/shop")
public class ShopController {
	
	@Autowired
	MerchandiseService merchandiseService; 
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String shopAll(Model model) {
		
		List<Merchandise> merchandiseList = merchandiseService.getALLMerchandise();
		model.addAttribute("merchandiseList", merchandiseList);
		
		return "shop";
	}
}
