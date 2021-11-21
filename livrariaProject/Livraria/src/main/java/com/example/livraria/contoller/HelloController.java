package com.example.livraria.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.livraria.model.Autor;
import com.example.livraria.model.Livro;
import com.example.livraria.service.AutorService;
import com.example.livraria.service.LivroService;

@Controller
public class HelloController {

	@Autowired
	LivroService livroService;
	
	@GetMapping("/home")
	public String hello(Model model) {
		List<Livro> listaLivros = livroService.getAll();
		model.addAttribute("livros", listaLivros);
		return "index";
	}

	@GetMapping("/cadastrar-endereco")
	public String cadastrarEndereco() {
		return "cadastro_endereco";
	}
}
