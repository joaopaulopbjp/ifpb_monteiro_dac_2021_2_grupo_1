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
import org.springframework.web.bind.annotation.RequestParam;

import com.example.livraria.model.Autor;
import com.example.livraria.model.Categoria;
import com.example.livraria.model.Livro;
import com.example.livraria.service.AutorService;
import com.example.livraria.service.CategoriaService;
import com.example.livraria.service.EstoqueService;
import com.example.livraria.service.LivroService;



@Controller
public class LivroController {
    @Autowired
	LivroService livroService;
    
    @Autowired
    CategoriaService categoriaService;
    
    @Autowired
    EstoqueService estoqueService;

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
        return "livro/cadastro_livro";
    }
    
    @PostMapping("/adicionar-livro")
    public String adicionarLivro(@ModelAttribute("livro") Livro livro, BindingResult result, Model modelo) {
    	
    	if(!result.hasErrors()) {
			
			livroService.salvar("","","","","",1,Float.parseFloat("0.1"),"","",new ArrayList<Integer>());
			return "redirect:/cadastrar-livro";
			
		}
    	
        return "livro/cadastro_livro";
    }

    @GetMapping("/livro-info")
    public String findLivro(@RequestParam(name="ISBN") String ISBN, Model model) {
		List<Categoria> listaCategorias = categoriaService.obterCategorias();
		Livro livro = livroService.getLivro(ISBN);
		boolean estoque = estoqueService.verificarLivroEstoque(ISBN, 1);
		model.addAttribute("livro", livro);
		model.addAttribute("categorias",listaCategorias);
		model.addAttribute("estoque", estoque);
		return "livro/livro_info";
	}
}
