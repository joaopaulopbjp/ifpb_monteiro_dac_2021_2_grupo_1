package com.example.livraria.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

/**
 * Controller de Livro que retorna as paginas html referente aos livros
 *  e os metodos para fazer o CRUD do livro.
 * 
 * @author Agemiro Neto
 * @author Jordielson Silva
 * @author Victor Macêdo
 */
@Controller
public class LivroController {
    //Classe que contém os serviços de Livro.
    @Autowired
	LivroService livroService;
    
    //Classe que contém os serviços da Categoria. Usado para listar as categorias no menu.
    @Autowired
    CategoriaService categoriaService;
    
    //Classe que contém os serviços de Estoque. Usado para obter a quantidade de livro no estoque.
    @Autowired
    EstoqueService estoqueService;

    //Classe que contém os serviços de Autor. Usado para obter os autores e listar eles nas páginas html.
    @Autowired
    AutorService autorService;
    
    //Classe que contém os serviços de Editora. Usado para obter as edidoras e listar nas páginas html.
   @Autowired
   EditoraService editoraService;
	
   /**
    * Cadastro do livro pelo adminstrador
    * @param model
    * @return página de cadastro do livro
    */
    @GetMapping("/livro/cadastrar") 
    public String cadastrarLivro(Model model) {
    	List<Autor> listaAutores = autorService.getAll();
        List<Editora> listaEditoras = editoraService.getAll();
        model.addAttribute("editoras",listaEditoras);
        List<Categoria> listaCategorias = categoriaService.obterCategorias();
        model.addAttribute("listaCategorias", listaCategorias);
    	model.addAttribute("listaAutores",listaAutores);
    	model.addAttribute("livro", new Livro());
        return "livro/cadastro_livro";
    }

    /**
    * Alteração da informações do livro pelo adminstrador
    * @param ISBN isbn do livro aser alterado
    * @param model
    * @return página de atualização do livro
    */
    @GetMapping("/livro/alterar") 
    public String alterarLivro(@RequestParam(name="ISBN") String ISBN, Model model) {
    	List<Autor> listaAutores = autorService.getAll();
        List<Editora> listaEditoras = editoraService.getAll();
        model.addAttribute("editoras",listaEditoras);
        List<Categoria> listaCategorias = categoriaService.obterCategorias();
        model.addAttribute("listaCategorias", listaCategorias);
    	model.addAttribute("listaAutores",listaAutores);
        model.addAttribute("livro", livroService.getLivro(ISBN));
        return "livro/cadastro_livro";
    }

    /**
     * Médoto para salvar um livro
     * @param livro livro a ser salvo
     * @param result
     * @param modelo
     * @return
     */
    @PostMapping("/adicionar-livro")
    public String adicionarLivro(@ModelAttribute("livro") Livro livro, BindingResult result, Model modelo) {
    	if(!result.hasErrors()) {
			livroService.salvar(livro);
			return "redirect:/gerenciar-livros";
		}
    	
        return "livro/cadastro_livro";
    }

    /**
     * Página para ver as informações do livro e adicionar o livro ao pedido.
     * @param ISBN isbn do livro a ver as informações
     * @param model
     * @return página com os detalhes do livro
     */
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

    /**
     * Método para pesquisar um livro
     * @param titulo titulo do livro usado como filtro na pesquisa
     * @param page página
     * @param model
     * @return página com o resultado da pesquisa
     */
    @GetMapping("/search-livro")
    public String search(@RequestParam(name="pesquisa") String titulo, @RequestParam(name="page", required = false) Integer page, Model model) {
        if(page == null) {
			page = 0;
		}
        List<Categoria> listaCategorias = categoriaService.obterCategorias();
		Page<Livro> listaLivros = livroService.findByTitulo(titulo,page);
		model.addAttribute("pageAtual", listaLivros.getNumber());
		model.addAttribute("listaLivros", listaLivros.getContent());
		model.addAttribute("categorias",listaCategorias);
		model.addAttribute("pesquisa", titulo);
		
		int totalPages = listaLivros.getTotalPages();
		model.addAttribute("totalPages", totalPages);
		if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            
			model.addAttribute("numPages", pageNumbers);
        }
		return "livro/crud-livro";
    }

    /**
     * Método para obter a página que faz o gerenciamento dos livros
     * @param model
     * @return página de gerenciar livro
     */
    @GetMapping("/gerenciar-livros")
	public String crudLivros(Model model, @RequestParam(name="page", required = false) Integer page){
        if(page == null) {
			page = 0;
		}
		Page<Livro> livros = livroService.getAll(page);

        model.addAttribute("pageAtual", livros.getNumber());
		model.addAttribute("listaLivros", livros.getContent());

        int totalPages = livros.getTotalPages();
		model.addAttribute("totalPages", totalPages);
		if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            
			model.addAttribute("numPages", pageNumbers);
        }
		// model.addAttribute("listaLivros", livros);
		List<Categoria> listaCategorias = categoriaService.obterCategorias();
		model.addAttribute("categorias",listaCategorias);
		return "livro/crud-livro";
	}

    /**
     * Método para excluir um livro
     * @param isbn isbn do livro a ser excluído
     * @param model
     * @return redirecionamento para a página de gerenciamento de livros
     */
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
