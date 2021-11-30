package com.example.livraria.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.livraria.model.Autor;
import com.example.livraria.model.Categoria;
import com.example.livraria.model.Livro;
import com.example.livraria.service.AutorService;
import com.example.livraria.service.CategoriaService;
import com.example.livraria.service.EstoqueService;
import com.example.livraria.service.LivroService;

@Controller
public class HelloController {

	@Autowired
	LivroService livroService;
	
	@Autowired
	CategoriaService categoriaService;
	
	@Autowired
	AutorService autorService;
	
	@Autowired
	EstoqueService estoqueService;
	
	@PostMapping("/adicionar-autor")
	public String adicionarAutor(@ModelAttribute(name="autor") Autor autor, Model model){
		autorService.salvar(autor.getNome());
		List<Autor> autores = autorService.getAll(0);
		model.addAttribute("listaAutores", autores);
		return "redirect:/gerenciar-autores";
	}

	@PostMapping("/alterar-autor")
	public String alterarAutor(@ModelAttribute(name="autor") Autor autor, Model model){
		autorService.update(autor);
		return "redirect:/gerenciar-autores";
	}

	@GetMapping("/excluir")
	public String RemoverAutor(@RequestParam(name="id") Integer id, Model model){
		Autor autor = autorService.getAutor(id);
		autorService.remover(autor);
		List<Autor> autores = autorService.getAll(0);
		model.addAttribute("listaAutores", autores);

		model.addAttribute("autor", new Autor());

		return "redirect:/gerenciar-autores";
	}
	
	@GetMapping("/gerenciar-autores")
	public String crudAutores(Model model){
		Autor autor = new Autor();
		List<Autor> autores = autorService.getAll(0);
		model.addAttribute("listaAutores", autores);
		model.addAttribute(autor);
		List<Categoria> listaCategorias = categoriaService.obterCategorias();
		model.addAttribute("categorias",listaCategorias);
		return "Autor/crud-autor";
	}
	
	@GetMapping("/home")
	public String hello(Model model) {
		List<Categoria> listaCategorias = categoriaService.obterCategorias();
		model.addAttribute("categorias",listaCategorias);
		List<Livro> listaLivros = livroService.consultarPorPaginaAleatoria(0);
		model.addAttribute("livros", listaLivros);
		List<Livro> listaLivrosMaisBaratos = estoqueService.consultarLivrosBaratos();
		model.addAttribute("livrosMaisBaratos", listaLivrosMaisBaratos);
		
		return "index";
	}

	@GetMapping("/cadastrar-endereco")
	public String cadastrarEndereco() {
		return "cadastro_endereco";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam(name="pesquisa") String pesquisa,@RequestParam(name="page", required = false) Integer page, Model model) {
		if(page == null) {
			page = 0;
		}
		List<Categoria> listaCategorias = categoriaService.obterCategorias();
		Page<Livro> listaLivros = livroService.findByTitulo(pesquisa,page);
		model.addAttribute("pageAtual", listaLivros.getNumber());
		model.addAttribute("livros", listaLivros.getContent());
		model.addAttribute("categorias",listaCategorias);
		model.addAttribute("pesquisa", pesquisa);
		
		int totalPages = listaLivros.getTotalPages();
		model.addAttribute("totalPages", totalPages);
		if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            
			model.addAttribute("numPages", pageNumbers);
        }
		return "livro-search";
	}
	
	@GetMapping("/search/categoria")
	public String categoria(@RequestParam(name="pesquisa") String pesquisa, @RequestParam(name="page", required = false) Integer page, Model model) {
		if(page == null) {
			page = 0;
		}
		List<Categoria> listaCategorias = categoriaService.obterCategorias();
		Page<Livro> listaLivros = livroService.findByCategoria(pesquisa, page);
		model.addAttribute("pageAtual", listaLivros.getNumber());
		model.addAttribute("livros", listaLivros.getContent());
		model.addAttribute("categorias",listaCategorias);
		model.addAttribute("pesquisa", pesquisa);
		
		int totalPages = listaLivros.getTotalPages();
		model.addAttribute("totalPages", totalPages);
		if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            
			model.addAttribute("numPages", pageNumbers);
        }
		model.addAttribute("livros", listaLivros);
		model.addAttribute("categorias",listaCategorias);
		return "categoria/categoria-search";
	}
}
