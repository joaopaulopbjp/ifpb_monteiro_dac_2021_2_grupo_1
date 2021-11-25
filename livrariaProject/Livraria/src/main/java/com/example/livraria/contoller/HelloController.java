package com.example.livraria.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@Autowired
	AutorService autorService;
	
	@PostMapping("/adicionar-autor")
	public String adicionarAutor(@ModelAttribute(name="autor") Autor autor, Model model){
		autorService.salvar(autor.getNome());
		List<Autor> autores = autorService.getAll(0);
		model.addAttribute("listaAutores", autores);
		return "Autor/crud-autor";
	}
	
	@GetMapping("/excluir")
	public String RemoverAutor(@RequestParam(name="id") Integer id, Model model){
		Autor autor = autorService.getAutor(id);
		autorService.remover(autor);
		List<Autor> autores = autorService.getAll(0);
		model.addAttribute("listaAutores", autores);
		return "Autor/crud-autor";
	}
	
	@GetMapping("/gerenciar-autores")
	public String crudAutores(Model model){
		Autor autor = new Autor();
		List<Autor> autores = autorService.getAll(0);
		model.addAttribute("listaAutores", autores);
		model.addAttribute(autor);
		return "Autor/crud-autor";
	}
	
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
		return "livro-search";
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
