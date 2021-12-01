package com.example.livraria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.livraria.model.Usuario;
import com.example.livraria.service.UsuarioService;

@Controller
public class UserController {

	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/login") 
	public String cadastrarUsuario(Model model) {
		
		model.addAttribute("usuario", new Usuario());
		return "usuario/login_user";
		
	}
	
	@PostMapping("/adicionar-usuario") 
	public String cadastrarUsuario(@ModelAttribute("usuario") Usuario usuario, BindingResult result, Model modelo) {
		if(!result.hasErrors()) {
			
			usuarioService.salvar(usuario);
			
			return "redirect:/home";
		}
		
		return "/usuario/login_user";
		
	}
	
}
