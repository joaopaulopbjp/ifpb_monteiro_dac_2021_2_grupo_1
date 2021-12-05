package com.example.livraria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.livraria.model.Categoria;
import com.example.livraria.service.CategoriaService;

/**
 * Controller de categoria
 * 
 * @author Agemiro Neto
 * @author Jordielson Silva
 * @author Victor Macêdo
 */
@Controller
public class CategoriaController {
	
	//Classe que contém os serviços de categoria, onde o mesmo faz referência ao repository específico. 
	@Autowired
	CategoriaService categoriaService;
	
	/**
	 * Método que exibe o crud de categoria
	 * @param model
	 * @return tela de crud de categoria
	 */
	@GetMapping("/gerenciar-categorias")
	public String crudCategoria(Model model){
		Categoria categoria = new Categoria();
		List<Categoria> categorias = categoriaService.obterCategorias();
		model.addAttribute("listaCategorias", categorias);
		model.addAttribute(categoria);
		List<Categoria> listaCategorias = categoriaService.obterCategorias();
		model.addAttribute("categorias",listaCategorias);
		return "categoria/crud-categoria";
	}

	/**
	 * Método de salvar categoria
	 * @param categoria
	 * @param model
	 * @return redireciona para o método que tem a anotação gerenciar-categorias
	 */
	@PostMapping("/adicionar-categoria")
	public String adicionarCategoria(@ModelAttribute(name="categoria") Categoria categoria, Model model){
		categoriaService.salvar(categoria);
		List<Categoria> categorias = categoriaService.obterCategorias();
		model.addAttribute("listaCategorias", categorias);
		return "redirect:/gerenciar-categorias";
	}

	/**
	 * Método de excluir categoria
	 * @param id
	 * @param model
	 * @return redireciona para o método que tem a anotação gerenciar-categorias
	 */
	@GetMapping("/excluir-categoria")
	public String RemoverCategoria(@RequestParam(name="id") Integer id, Model model){
		Categoria categoria = categoriaService.getCategoriaID(id);
		categoriaService.deletarCategoria(categoria);
		List<Categoria> categorias = categoriaService.obterCategorias();
		model.addAttribute("listaCategorias", categorias);
		return "redirect:/gerenciar-categorias";
	}

	/**
	 * Método de alterar categoria
	 * @param categoria
	 * @param model
	 * @return redireciona para o método que tem a anotação gerenciar-categorias
	 */
	@PostMapping("/alterar-categoria")
	public String alterarCategoria(@ModelAttribute(name="categoria") Categoria categoria, Model model){
		categoriaService.alterarCategoria(categoria);
		return "redirect:/gerenciar-categorias";
	}
	
}
