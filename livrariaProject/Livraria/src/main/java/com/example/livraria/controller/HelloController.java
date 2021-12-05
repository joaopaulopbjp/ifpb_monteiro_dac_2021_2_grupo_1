package com.example.livraria.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.livraria.model.Categoria;
import com.example.livraria.model.Livro;
import com.example.livraria.service.CategoriaService;
import com.example.livraria.service.EstoqueService;
import com.example.livraria.service.LivroService;

/**
 * Controller hello que basicamente serve para exibir tudo que é publico. 
 * 
 * @author Agemiro Neto
 * @author Jordielson Silva
 * @author Victor Macêdo
 */
@Controller
public class HelloController {

	//Classe que contém os serviços de livro, onde o mesmo faz referência ao repository específico. 
	@Autowired
	LivroService livroService;
	
	//Classe que contém os serviços de categoria, onde o mesmo faz referência ao repository específico. 
	@Autowired
	CategoriaService categoriaService;
	
	//Classe que contém os serviços de estoque, onde o mesmo faz referência ao repository específico. 
	@Autowired
	EstoqueService estoqueService;
	
	
	/**
	 * Método que chama o index básico da interface com tudo que deve conter, inclusive os livros.
	 * @param model
	 * @return index
	 */
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

	/**
	 * Método de cadastrar endereço
	 * @return cadastro_endereco
	 */
	@GetMapping("/cadastrar-endereco")
	public String cadastrarEndereco() {
		return "cadastro_endereco";
	}
	
	/**
	 * Método de pesquisa um livro pelo título.
	 * @param pesquisa
	 * @param page
	 * @param model
	 * @return livro-search
	 */
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
		return "livro/livro-search";
	}
	
	/**
	 * Método que pesquisa pela categoria do livro
	 * @param pesquisa
	 * @param page
	 * @param model
	 * @return página com os livros pela categoria escolhida.
	 */
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
