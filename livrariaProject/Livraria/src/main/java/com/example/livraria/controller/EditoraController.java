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
import com.example.livraria.model.Editora;
import com.example.livraria.service.CategoriaService;
import com.example.livraria.service.EditoraService;

/**
 * Controller de editora
 * 
 * @author Agemiro Neto
 * @author Jordielson Silva
 * @author Victor Macêdo
 */
@Controller
public class EditoraController {

	//Classe que contém os serviços de editora, onde o mesmo faz referência ao repository específico. 
	@Autowired
	EditoraService editoraService;
	
	//Classe que contém os serviços de categoria, onde o mesmo faz referência ao repository específico. 
	@Autowired
	CategoriaService categoriaService;
	
	/**
	 * Método que mostra a tela de CRUD de editora.
	 * @param model
	 * @return tela de crud de editora
	 */
	@GetMapping("/gerenciar-editoras")
	public String crudEditoras(Model model){
		Editora categoria = new Editora();
		List<Editora> editoras = editoraService.getAll();
		model.addAttribute("listaEditoras", editoras);
		model.addAttribute(categoria);
		List<Categoria> listaCategorias = categoriaService.obterCategorias();
		model.addAttribute("categorias",listaCategorias);
		return "editora/crud-editora";
	}
	
	/**
	 * Método que salva a editora
	 * @param editora
	 * @param model
	 * @return retorna um redirecionamento para o método que tem a anotação gerenciar-editoras
	 */
	@PostMapping("/adicionar-editora")
	public String adicionarEditora(@ModelAttribute(name="editora") Editora editora, Model model){
		editoraService.salvar(editora);
		List<Editora> editoras = editoraService.getAll();
		model.addAttribute("listaEditoras", editoras);
		return "redirect:/gerenciar-editoras";
	}
	
	/**
	 * Método que exclui a editora.
	 * @param id
	 * @param model
	 * @return retorna um redirecionamento para o método que tem a anotação gerenciar-editoras
	 */
	@GetMapping("/excluir-editora")
	public String RemoverEditora(@RequestParam(name="id") Integer id, Model model){
		Editora editora = editoraService.getEditora(id);
		editoraService.remover(editora);
		List<Editora> editoras = editoraService.getAll();
		model.addAttribute("listaEditoras", editoras);
		return "redirect:/gerenciar-editoras";
	}
	
	/**
	 * 
	 * @param Método de alterar editora
	 * @param model
	 * @return retorna um redirecionamento para o método que tem a anotação gerenciar-editoras
	 */
	@PostMapping("/alterar-editora")
	public String alterarEditora(@ModelAttribute(name="editora") Editora editora, Model model){
		editoraService.alterarEditora(editora);
		return "redirect:/gerenciar-editoras";
	}
	
}
