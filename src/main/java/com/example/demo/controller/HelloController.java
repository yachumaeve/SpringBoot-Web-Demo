package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HelloController {
	
	//@GetMapping("/home")
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(HttpSession session, Model model) {
		
		String userId = (String) session.getAttribute("userId");
		String userRole = (String) session.getAttribute("userRole");
		
		model.addAttribute("userId", userId);
		model.addAttribute("userRole", userRole);
		
		return "home";
	}
	
	//@GetMapping("/coming")
	@RequestMapping(value = "/coming", method = RequestMethod.GET)
	public String coming() {
		return "coming";
	}
	
}