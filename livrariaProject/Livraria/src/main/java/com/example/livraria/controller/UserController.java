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

/**
 * Controller de usuário 
 * 
 * @author Agemiro Neto
 * @author Jordielson Silva
 * @author Victor Macêdo
 */
@Controller
public class UserController {

	//Classe que contém os serviços de usuário, onde o mesmo faz referência ao repository específico. 
	@Autowired
	UsuarioService usuarioService;
	
	//Interface que vai servir parar codificar as senhas antes de serem salvas no banco.
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * Método que vai exibir a tela de login e também a de cadastro de usuário caso não tenha ninguém logado.
	 * @param model
	 * @return tela com formularios de login e cadastro
	 */
	@GetMapping("/login") 
	public String cadastrarUsuario(Model model) {
		
		model.addAttribute("usuario", new Usuario());
		return "usuario/form_user";
		
	}
	/**
	 * Método que vai salvar um novo usuário e também o alterar o mesmo quando solicitado.
	 * @param usuario
	 * @param result
	 * @param modelo
	 * @return tela do perfil, home ou o formulario de volta
	 */
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
		
		return "/usuario/form_user";
		
	}
	
	/**
	 * Método pra ver o perfil do usuário logado, contendo os dados e a opção de alterar dados.
	 * @param model
	 * @return tela do perfil do usuário logado
	 */
	@GetMapping("/perfil")
    public String verPerfil(Model model) {
		
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		
		String email = autenticado.getName();
		
		Usuario usuario = usuarioService.getUsuario(email);
		
		model.addAttribute("usuario", usuario);
		
		model.addAttribute("historicoPedidos", usuario.getHistoricoDePedidos());
		
		return "usuario/perfil_usuario";
		
	}
	
	/**
	 * Método que chama a tela de alterar dados
	 * @param email
	 * @param model
	 * @return formulario de alterar dados do usuário
	 */
	@GetMapping("/usuario/alterar")
	public String alterarUsuario(@RequestParam(name="email") String email, Model model) {
		
		Usuario usuario = usuarioService.getUsuario(email);

        model.addAttribute("usuario", usuario);
        
        return "usuario/form_user";
	}
	
}
