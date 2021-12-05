package com.example.livraria.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.livraria.model.Livro;
import com.example.livraria.repository.LivroRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livraria.model.Autor;
import com.example.livraria.model.Categoria;
import com.example.livraria.model.Editora;
import com.example.livraria.repository.AutorRepository;
import com.example.livraria.repository.EditoraRepository;

/**
 * Classe que representa os serviços do Livro, esta vai poder acessar o devido repositório,
 * no caso o LivroRepository.
 * 
 * @author Agemiro Neto
 * @author Jordielson Silva
 * @author Victor Macêdo
 */

@Service
public class LivroService {

	/*
	 * Contém a anotação @Autowired para que quando a classe LivroService for usada o 
	 * Spring chame livroRepository.
	 */
	@Autowired
    LivroRepository livroRepository;
	
	
	/*
	 * Contém a anotação @Autowired para que quando a classe LivroService for usada o 
	 * Spring chame editoraRepository, já que todos os livros precisam ter uma editora.
	 */
	@Autowired
	EditoraRepository editoraRepository;
	
	/*
	 * Contém a anotação @Autowired para que quando a classe LivroService for usada o 
	 * Spring chame autorRepository, já que todos os livros precisam ter pelo menos um autor.
	 */
	@Autowired
	AutorRepository autorRepository;

	@Autowired
	CategoriaService categoriaService;
	
	/**
	 * Comentario de Victor
	 * @param page
	 * @return
	 */
	public List<Livro> consultarPorPagina(Integer page){
		Pageable pageable = PageRequest.of(page, 21, Sort.by("titulo"));
		List<Livro> livros = livroRepository.findAll(pageable).getContent();
		return livros;
	}
	
	public List<Livro> consultarPorPaginaAleatoria(Integer page){
		Pageable pageable = PageRequest.of(page, 21, Sort.by("ano").descending());
		List<Livro> livros = livroRepository.findAll(pageable).getContent();
		return livros;
	}
	
	public Page<Livro> findByTitulo(String titulo,Integer page){
		Pageable pageable = PageRequest.of(page, 21, Sort.by("titulo"));
		Page<Livro> livros = livroRepository.findByTitulo(pageable, titulo);
		return livros;
	}

	/**
	 *  Método feito pra receber os valores passados lá no menu para depois serem 'setados'
     * e consequentemente virar um objeto para assim poder ser salvo o livro usando o método 
     * 'save' da interface CrudRepository que é estendida pela interface LivroRepository.
     * 
	 * @param isbn
	 * @param categoria
	 * @param titulo
	 * @param descricao
	 * @param edicao
	 * @param ano
	 * @param preco
	 * @param nomeEditora
	 * @param cidadeEditora
	 * @param autoresIDs
	 */
    public void salvar(String isbn, String categoria, String titulo, String descricao, String edicao, 
    		Integer ano, float preco, String nomeEditora, String cidadeEditora, List<Integer> autoresIDs) {    
    	
    	Editora editora = new Editora();
    	editora.setNome(nomeEditora);
    	editora.setCidade(cidadeEditora);
    	
        editoraRepository.save(editora);
    	
    	Livro livro = new Livro();
    	livro.setISBN(isbn);
    	livro.setCategoria(categoriaService.salvarCategoriaPorNome(categoria));
    	livro.setTitulo(titulo);
    	livro.setDescricao(descricao);
    	livro.setEdicao(edicao);
    	livro.setAno(ano);
    	livro.setPreco(BigDecimal.valueOf(preco));
    	livro.setEditora(editora); 
    	
    	List<Autor> autores = new ArrayList<Autor>();
    	
        for (Integer id : autoresIDs) {
        
        	Optional<Autor> autor = autorRepository.findById(id);
        	Autor existe = autor.isPresent() ? autor.get() : null;
        	
        	if(existe != null) {
        		System.out.println("autor: "+ autor.get().getNome());
        		System.out.println("ID: "+id);
        		autores.add(existe);
        	}else {
        		System.out.println("O ID "+ id + " do autor está inválido!");
        	}
        	
    		livro.setAutores(autores);

		}
 
        livroRepository.save(livro);
    }

	public Livro salvar(Livro livro) {
		return livroRepository.save(livro);
	}

    /**
     * Metódo que vai remover um livro quando for passado o 'isbn' por parametro existente na base de dados.
     * 
     * @param isbn
     */
    public void remover(String isbn) {
    	Livro livro = getLivro(isbn);
    	livroRepository.delete(livro);
    }

	/**
     * Metódo que vai remover o livro que foi passado por parametro se ele existir na base de dados.
     * 
     * @param livro livro a ser removido
     */
	public void remover(Livro livro) {
    	livroRepository.delete(livro);
    }

    /**
     * Método usado para procurar um livro que pode existir ou não na base de dados.
     * 
     * @param isbn
     * @return o próprio livro encontrado ou null
     */
    public Livro getLivro(String isbn) {
    	Optional<Livro> livro = livroRepository.findById(isbn);
        return livro.isPresent() ? livro.get() : null;
    }

    /**
     * Método usado para retornar todos livros existentes na base de dados.
     * 
     * @return todos os livros
     */
	public List<Livro> getAll() {
        List<Livro> livros = livroRepository.findAll();
        return livros;
    }

	/**
	 * Método usado para retornar os livros existentes na base de dados de forma paginada.
     * @param page pagina a ser retornada
     * @return pagina de livro
	 */
	public Page<Livro> getAll(int page) {
		Pageable pageable = PageRequest.of(page, 21, Sort.by("titulo"));
        Page<Livro> livros = livroRepository.findAll(pageable);
        return livros;
    }
	
	/**
	 * Método utilizado para editar/atualizar dado da categoria de um determindado livro.
	 * 
	 * @param isbn
	 * @param dado
	 */
	public void updateCategoria (String isbn, String dado) {
		Optional<Livro> livro = livroRepository.findById(isbn);
		Livro existe = livro.isPresent() ? livro.get() : null;

		existe.setCategoria(categoriaService.salvarCategoriaPorNome(dado));
		
		livroRepository.save(existe);
	}
	
	/**
	 * Método utilizado para editar/atualizar dado do titulo de um determindado livro.
	 * 
	 * @param isbn
	 * @param dado
	 */
	public void updateTitulo (String isbn, String dado) {	
		Optional<Livro> livro = livroRepository.findById(isbn);
		Livro existe = livro.isPresent() ? livro.get() : null;
		
		existe.setTitulo(dado);
		
		livroRepository.save(existe);
	}
	
	/**
	 * Método utilizado para editar/atualizar dado da descrição de um determindado livro.
	 * 
	 * @param isbn
	 * @param dado
	 */
	public void updateDescricao (String isbn, String dado) {	
		Optional<Livro> livro = livroRepository.findById(isbn);
		Livro existe = livro.isPresent() ? livro.get() : null;
		
		existe.setDescricao(dado);
		
		livroRepository.save(existe);
	}
	
	/**
	 * Método utilizado para editar/atualizar dado da edição de um determindado livro.
	 * 
	 * @param isbn
	 * @param dado
	 */
	public void updateEdicao (String isbn, String dado) {	
		Optional<Livro> livro = livroRepository.findById(isbn);
		Livro existe = livro.isPresent() ? livro.get() : null;
		
		existe.setEdicao(dado);
		
		livroRepository.save(existe);
	}
	
	/**
	 * Método utilizado para editar/atualizar dado do ano de um determindado livro.
	 *
	 * @param isbn
	 * @param dado
	 */
	public void updateAno (String isbn, Integer dado) {	
		Optional<Livro> livro = livroRepository.findById(isbn);
		Livro existe = livro.isPresent() ? livro.get() : null;
		
		existe.setAno(dado);
		
		livroRepository.save(existe);
	}
	
	/**
	 * Método utilizado para editar/atualizar dado do preço de um determindado livro.
	 * 
	 * @param isbn
	 * @param dado
	 */
	public void updatePreco (String isbn, Float dado) {	
		Optional<Livro> livro = livroRepository.findById(isbn);
		Livro existe = livro.isPresent() ? livro.get() : null;
		
		existe.setPreco(BigDecimal.valueOf(dado));
		
		livroRepository.save(existe);

	}
	
	public Page<Livro> findByCategoria(String nome, int page){
		Pageable pageable = PageRequest.of(page, 21);
		Categoria categoria = categoriaService.buscarCategoriaPorNome(nome);
		return livroRepository.findByCategoria(categoria, pageable);
	}
    
}
