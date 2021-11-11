package com.example.livraria.contoller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.livraria.model.Autor;
import com.example.livraria.model.Livro;
import com.example.livraria.service.AutorService;
import com.example.livraria.service.LivroService;



@Controller
public class LivroController {
    @Autowired
	LivroService livroService;
    
    @Autowired
    AutorService autorService;
    
//    @Autowired
//    EditoraService editoraService;
	
    @GetMapping("/cadastrar-livro") 
    public String cadastrarLivro(Model model) {
    	List<Autor> listaAutores = autorService.getAll();
//    	List<Editora> listaEditoras = editoraService.getAll();
//    	model.addAttribute("editoras",listaEditoras);
    	model.addAttribute("autores",listaAutores);
    	model.addAttribute("livro", new Livro());
        return "cadastro_livro";
    }
    
    @PostMapping("/adicionar-livro")
    public String adicionarLivro(@ModelAttribute("livro") Livro livro, BindingResult result, Model modelo) {
    	
    	if(!result.hasErrors()) {
			
			livroService.salvar("","","","","",1,Float.parseFloat("0.1"),"","",new ArrayList<Integer>());
			return "redirect:/cadastrar-livro";
			
		}
    	
        return "cadastro_livro";
    }
}
