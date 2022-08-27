package br.com.alura.mvc.mudi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

class LoginController {
	
	@GetMapping
	@RequestMapping("/login")
	String login() {
		return "login";
	}
}