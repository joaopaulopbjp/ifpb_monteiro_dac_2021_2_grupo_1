package com.example.livraria.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.livraria.model.Editora;
import com.example.livraria.service.EditoraService;

@Controller
public class EditoraController {

	@Autowired
	EditoraService editoraService;
	
	@GetMapping("/gerenciar-editoras")
	public String crudAutores(Model model){
		Editora categoria = new Editora();
		List<Editora> editoras = editoraService.getAll();
		model.addAttribute("listaEditoras", editoras);
		model.addAttribute(categoria);
		return "editora/crud-editora";
	}
	
	@PostMapping("/adicionar-editora")
	public String adicionarAutor(@ModelAttribute(name="editora") Editora editora, Model model){
		editoraService.salvar(editora);
		List<Editora> editoras = editoraService.getAll();
		model.addAttribute("listaEditoras", editoras);
		return "redirect:/gerenciar-editoras";
	}
	
	@GetMapping("/excluir-editora")
	public String RemoverEditora(@RequestParam(name="id") Integer id, Model model){
		Editora editora = editoraService.getEditora(id);
		editoraService.remover(editora);
		List<Editora> editoras = editoraService.getAll();
		model.addAttribute("listaEditoras", editoras);
		return "redirect:/gerenciar-editoras";
	}
	
}
