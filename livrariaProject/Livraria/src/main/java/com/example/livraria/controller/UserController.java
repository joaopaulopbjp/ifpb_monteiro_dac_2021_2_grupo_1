package com.example.livraria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.livraria.model.Usuario;
import com.example.livraria.service.UsuarioService;

@Controller
public class UserController {

	@Autowired
	UsuarioService usuarioService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/login") 
	public String cadastrarUsuario(Model model) {
		
		model.addAttribute("usuario", new Usuario());
		return "usuario/login_user";
		
	}
	
	@PostMapping("/adicionar-usuario") 
	public String cadastrarUsuario(@ModelAttribute("usuario") Usuario usuario, BindingResult result, Model modelo) {
		if(!result.hasErrors()) {
			
			Usuario usuarioJaExisteEmail = usuarioService.getUsuario(usuario.getEmail());
			if(usuarioJaExisteEmail != null) {
				
				usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
				
				usuarioService.alterar(usuario, usuarioJaExisteEmail.getPapelUsuario());
				
				return "redirect:/perfil";
			}
			
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
			
			usuarioService.salvar(usuario);
			
			return "redirect:/home";
		}
		
		return "/usuario/login_user";
		
	}
	
	@GetMapping("/perfil")
    public String verPerfil(Model model) {
		
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		
		String email = autenticado.getName();
		
		Usuario usuario = usuarioService.getUsuario(email);
		
		model.addAttribute("usuario", usuario);
		
		return "usuario/perfil_usuario";
		
	}
	
	@GetMapping("/usuario/alterar")
	public String alterarUsuario(@RequestParam(name="email") String email, Model model) {
		
		Usuario usuario = usuarioService.getUsuario(email);

        model.addAttribute("usuario", usuario);
        
        return "usuario/login_user";
	}
	
}
