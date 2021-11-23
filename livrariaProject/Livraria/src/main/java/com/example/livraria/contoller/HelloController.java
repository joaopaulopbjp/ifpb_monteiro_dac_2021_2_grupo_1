package com.example.livraria.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.livraria.model.Autor;
import com.example.livraria.model.Categoria;
import com.example.livraria.model.Livro;
import com.example.livraria.service.AutorService;
import com.example.livraria.service.CategoriaService;
import com.example.livraria.service.LivroService;

@Controller
public class HelloController {

	@Autowired
	LivroService livroService;
	
	@Autowired
	CategoriaService categoriaService;
	
	
	@GetMapping("/home")
	public String hello(Model model) {
		List<Categoria> listaCategorias = categoriaService.obterCategorias();
		List<Livro> listaLivros = livroService.consultarPorPaginaAleatoria(0);
		model.addAttribute("livros", listaLivros);
		model.addAttribute("categorias",listaCategorias);
		return "index";
	}

	@GetMapping("/cadastrar-endereco")
	public String cadastrarEndereco() {
		return "cadastro_endereco";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam(name="pesquisa") String pesquisa, Model model) {
		List<Categoria> listaCategorias = categoriaService.obterCategorias();
		List<Livro> listaLivros = livroService.findByTitulo(pesquisa,0);
		model.addAttribute("livros", listaLivros);
		model.addAttribute("categorias",listaCategorias);
		return "index";
	}
	
	@GetMapping("/search/categoria")
	public String categoria(@RequestParam(name="categoria") String pesquisa, Model model) {
		List<Categoria> listaCategorias = categoriaService.obterCategorias();
		List<Livro> listaLivros = livroService.findByCategoria(pesquisa);
		model.addAttribute("livros", listaLivros);
		model.addAttribute("categorias",listaCategorias);
		return "index";
	}
}
