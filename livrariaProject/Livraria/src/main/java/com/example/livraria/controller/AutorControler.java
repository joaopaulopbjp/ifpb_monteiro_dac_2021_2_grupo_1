package com.example.livraria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.livraria.model.Autor;
import com.example.livraria.model.Categoria;
import com.example.livraria.service.AutorService;
import com.example.livraria.service.CategoriaService;

/**
 * Controller de autor
 * 
 * @author Agemiro Neto
 * @author Jordielson Silva
 * @author Victor Macêdo
 */
@Controller
public class AutorControler {
	
	//Classe que contém os serviços de autor, onde o mesmo faz referência ao repository específico. 
	@Autowired
	AutorService autorService;
	
	//Classe que contém os serviços de categoria, onde o mesmo faz referência ao repository específico. 
	@Autowired
	CategoriaService categoriaService;
	
	/**
	 * Método que exibe a tela do crud de autores
	 * @param model
	 * @return tela de crud de autores
	 */
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
	
	/**
	 * Método que salva um autor
	 * @param autor
	 * @param model
	 * @return redireciona para o método que tem a anotação gerenciar-autores
	 */
	@PostMapping("/adicionar-autor")
	public String adicionarAutor(@ModelAttribute(name="autor") Autor autor, Model model){
		autorService.salvar(autor.getNome());
		List<Autor> autores = autorService.getAll(0);
		model.addAttribute("listaAutores", autores);
		return "redirect:/gerenciar-autores";
	}

	/**
	 * Método que altera um autor
	 * @param autor
	 * @param model
	 * @return redireciona para o método que tem a anotação gerenciar-autores
	 */
	@PostMapping("/alterar-autor")
	public String alterarAutor(@ModelAttribute(name="autor") Autor autor, Model model){
		autorService.update(autor);
		return "redirect:/gerenciar-autores";
	}

	/**
	 * Método que exclui um autor
	 * @param id
	 * @param model
	 * @return redireciona para o método que tem a anotação gerenciar-autores
	 */
	@GetMapping("/excluir")
	public String RemoverAutor(@RequestParam(name="id") Integer id, Model model){
		Autor autor = autorService.getAutor(id);
		autorService.remover(autor);
		List<Autor> autores = autorService.getAll(0);
		model.addAttribute("listaAutores", autores);

		model.addAttribute("autor", new Autor());

		return "redirect:/gerenciar-autores";
	}

}
