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

@Controller
public class CategoriaController {
	
	@Autowired
	CategoriaService categoriaService;
	
	@GetMapping("/gerenciar-categorias")
	public String crudCategoria(Model model){
		Categoria categoria = new Categoria();
		List<Categoria> categorias = categoriaService.obterCategorias();
		model.addAttribute("listaCategorias", categorias);
		model.addAttribute(categoria);
		return "categoria/crud-categoria";
	}
	
	@PostMapping("/adicionar-categoria")
	public String adicionarCategoria(@ModelAttribute(name="categoria") Categoria categoria, Model model){
		categoriaService.salvar(categoria);
		List<Categoria> categorias = categoriaService.obterCategorias();
		model.addAttribute("listaCategorias", categorias);
		return "redirect:/gerenciar-categorias";
	}

	@GetMapping("/excluir-categoria")
	public String RemoverCategoria(@RequestParam(name="id") Integer id, Model model){
		Categoria categoria = categoriaService.getCategoriaID(id);
		categoriaService.deletarCategoria(categoria);
		List<Categoria> categorias = categoriaService.obterCategorias();
		model.addAttribute("listaCategorias", categorias);
		return "redirect:/gerenciar-categorias";
	}
	
}
