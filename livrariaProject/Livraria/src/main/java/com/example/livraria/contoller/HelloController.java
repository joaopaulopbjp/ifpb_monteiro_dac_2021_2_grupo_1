package com.example.livraria.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.livraria.model.Autor;
import com.example.livraria.service.AutorService;

@Controller
public class HelloController {

	@Autowired
	AutorService autorService;
	
	@GetMapping("/home")
	public String hello(Model model) {
		List<Autor> listaAutores = autorService.getAll();
		model.addAttribute("autores", listaAutores);
		return "fragments";
	}

	@GetMapping("/cadastrar-endereco")
	public String cadastrarEndereco() {
		return "cadastro_endereco";
	}
}
