package com.example.livraria.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.livraria.model.Autor;
import com.example.livraria.model.Estoque;
import com.example.livraria.service.EstoqueService;
import com.example.livraria.service.LivroService;

@Controller
public class EstoqueController {

	@Autowired
	LivroService livroService;
	
	@Autowired
	EstoqueService estoqueService;
	
	@GetMapping("/gerenciar-estoque")
	public String crudEstoque(Model model) {
		Estoque estoque = new Estoque();
		List<Estoque> estoques = estoqueService.findAll(0);
		model.addAttribute("listaEstoques", estoques);
		model.addAttribute(estoque);
		return "estoque/crud-estoque";
	}
	
	@PostMapping("/adicionar-estoque")
	public String adicionarEstoque(@ModelAttribute(name="estoque") Estoque estoque, Model model){
		estoqueService.criarEstoque(estoque.getId().toString(), estoque.getQuantidade());
		List<Estoque> estoques = estoqueService.findAll(0);
		model.addAttribute("listaEstoques", estoques);
		return "redirect:gerenciar-estoque";
	}
	
	@GetMapping("/excluir-estoque")
	public String RemoverAutor(@RequestParam(name="id") Integer id, Model model){ 
		estoqueService.deleteById(id);
		List<Estoque> estoques = estoqueService.findAll(0);
		model.addAttribute("listaEstoques", estoques);
		model.addAttribute("estoque", new Estoque());
		return "redirect:gerenciar-estoque";
	}
	
	
}
