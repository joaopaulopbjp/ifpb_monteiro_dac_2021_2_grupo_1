package com.example.livraria.controller;

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
import com.example.livraria.model.Editora;
import com.example.livraria.model.Estoque;
import com.example.livraria.model.Livro;
import com.example.livraria.service.AutorService;
import com.example.livraria.service.CategoriaService;
import com.example.livraria.service.EditoraService;
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
    
   @Autowired
   EditoraService editoraService;
	
    @GetMapping("/livro/cadastrar") 
    public String cadastrarLivro(Model model) {
    	List<Autor> listaAutores = autorService.getAll(0);
        List<Editora> listaEditoras = editoraService.getAll();
        model.addAttribute("editoras",listaEditoras);
        List<Categoria> listaCategorias = categoriaService.obterCategorias();
        model.addAttribute("listaCategorias", listaCategorias);
    	model.addAttribute("autores",listaAutores);
    	model.addAttribute("livro", new Livro());
        return "livro/cadastro_livro";
    }

    @GetMapping("/livro/alterar") 
    public String alterarLivro(@RequestParam(name="ISBN") String ISBN, Model model) {
    	List<Autor> listaAutores = autorService.getAll(0);
        List<Editora> listaEditoras = editoraService.getAll();
        model.addAttribute("editoras",listaEditoras);
        List<Categoria> listaCategorias = categoriaService.obterCategorias();
        model.addAttribute("listaCategorias", listaCategorias);
    	model.addAttribute("listaAutores",listaAutores);
        model.addAttribute("livro", livroService.getLivro(ISBN));
        return "livro/cadastro_livro";
    }

    @PostMapping("/adicionar-livro")
    public String adicionarLivro(@ModelAttribute("livro") Livro livro, BindingResult result, Model modelo) {
    	
    	if(!result.hasErrors()) {
			livroService.salvar(livro);
			return "redirect:/gerenciar-livros";
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

    @GetMapping("/search-livro")
    public String search(@RequestParam(name="titulo") String titulo, Model model) {
        List<Livro> livros = livroService.findByTitulo(titulo, 0);
        model.addAttribute("listaLivros", livros);
		return "livro/crud-livro";
    }

    @GetMapping("/gerenciar-livros")
	public String crudLivros(Model model){
    	Categoria categoria = new Categoria();
    	model.addAttribute(categoria);
    	Editora editora = new Editora();
    	model.addAttribute(editora);
    	Autor autor = new Autor();
    	model.addAttribute(autor);
    	Livro livro = new Livro();
    	model.addAttribute(livro);
		List<Livro> livros = livroService.getAll();
		model.addAttribute("listaLivros", livros);
		List<Categoria> listaCategorias = categoriaService.obterCategorias();
		model.addAttribute("categorias",listaCategorias);
		return "livro/crud-livro";
	}

    @GetMapping("/livro/excluir")
	public String RemoverLivro(@RequestParam(name="ISBN") String isbn, Model model){
        Livro livro = livroService.getLivro(isbn);
        Estoque estoque = estoqueService.findByLivro(livro);
        if(estoque != null) {
            estoqueService.delete(estoque);
        }
		livroService.remover(livro);
		return "redirect:/gerenciar-livros";
	}
}
