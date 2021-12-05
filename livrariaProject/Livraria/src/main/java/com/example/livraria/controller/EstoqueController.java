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
import com.example.livraria.model.Estoque;
import com.example.livraria.service.CategoriaService;
import com.example.livraria.service.EstoqueService;

/**
 * Controller de Estoque que possui os metodos para fazer o CRUD de estoque.
 * 
 * @author Agemiro Neto
 * @author Jordielson Silva
 * @author Victor Macêdo
 */
@Controller
public class EstoqueController {
	//Classe que contém os serviços de Estoque.
	@Autowired
	EstoqueService estoqueService;
	
	//Classe que contém os serviços da Categoria. Usado para listar as categorias no menu.
	@Autowired
	CategoriaService categoriaService;
	
	/**
	 * Método para obter a página de gereciamento de estoque 
	 * @param model
	 * @return página de gereciamento de estoque
	 */
	@GetMapping("/gerenciar-estoque")
	public String crudEstoque(Model model) {
		Estoque estoque = new Estoque();
		List<Estoque> estoques = estoqueService.findAll(0);
		model.addAttribute("listaEstoques", estoques);
		model.addAttribute(estoque);
		List<Categoria> listaCategorias = categoriaService.obterCategorias();
		model.addAttribute("categorias",listaCategorias);
		return "estoque/crud-estoque";
	}
	
	/**
	 * Método para adicionar ou alterar estoque
	 * @param estoque estoque a ser adicionado ou alterado
	 * @param model
	 * @return redirecionamento para página de gerenciamento de estoque
	 */
	@PostMapping("/adicionar-estoque")
	public String adicionarEstoque(@ModelAttribute(name="estoque") Estoque estoque, Model model){
		estoqueService.criarEstoque(estoque.getId().toString(), estoque.getQuantidade());
		List<Estoque> estoques = estoqueService.findAll(0);
		model.addAttribute("listaEstoques", estoques);
		return "redirect:gerenciar-estoque";
	}
	
	/**
	 * Método para excluir estoque
	 * @param id id do estoque a ser excluído
	 * @param model
	 * @return redirecionamento para página de gerenciamento de estoque
	 */
	@GetMapping("/excluir-estoque")
	public String RemoverAutor(@RequestParam(name="id") Integer id, Model model){ 
		estoqueService.deleteById(id);
		List<Estoque> estoques = estoqueService.findAll(0);
		model.addAttribute("listaEstoques", estoques);
		model.addAttribute("estoque", new Estoque());
		return "redirect:gerenciar-estoque";
	}
	
	
}
