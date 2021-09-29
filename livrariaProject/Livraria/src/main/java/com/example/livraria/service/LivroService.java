package com.example.livraria.service;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.livraria.model.Livro;
import com.example.livraria.repository.LivroRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livraria.model.Autor;
import com.example.livraria.model.Editora;
import com.example.livraria.repository.AutorRepository;
import com.example.livraria.repository.EditoraRepository;

@Service
public class LivroService {

	@Autowired
    LivroRepository livroRepository;
	
	@Autowired
	EditoraRepository editoraRepository;
	
	@Autowired
	AutorRepository autorRepository;
	
	public List<Livro> consultarPorPagina(Integer page){
		Pageable pageable = PageRequest.of(page, 5, Sort.by("titulo"));
		List<Livro> livros = livroRepository.findAll(pageable).getContent();
		return livros;
	}

    public void salvar(String isbn, String categoria, String titulo, String descricao, String edicao, 
    		Integer ano, float preco, String nomeEditora, String cidadeEditora, List<Integer> autoresIDs) {    
    	
    	Editora editora = new Editora();
    	editora.setNome(nomeEditora);
    	editora.setCidade(cidadeEditora);
    	
        editoraRepository.save(editora);
    	
    	Livro livro = new Livro();
    	livro.setISBN(isbn);
    	livro.setCategoria(categoria);
    	livro.setTitulo(titulo);
    	livro.setDescricao(descricao);
    	livro.setEdicao(edicao);
    	livro.setAno(ano);
    	livro.setPreco(preco);
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
        	
    		livro.setAutor(autores);

		}
 
        livroRepository.save(livro);
    }

    public void remover(String isbn) {
    	Livro livro = getLivro(isbn);
    	livroRepository.delete(livro);
    }

    public Livro getLivro(String isbn) {
    	Optional<Livro> livro = livroRepository.findById(isbn);
        return livro.isPresent() ? livro.get() : null;
    }

	public List<Livro> getAll() {
        List<Livro> livros = livroRepository.findAll();
        return livros;
    }
	
	public void updateCategoria (String isbn, String dado) {	
		Optional<Livro> livro = livroRepository.findById(isbn);
		Livro existe = livro.isPresent() ? livro.get() : null;
		
		existe.setCategoria(dado);
		
		livroRepository.save(existe);
	}
	
	public void updateTitulo (String isbn, String dado) {	
		Optional<Livro> livro = livroRepository.findById(isbn);
		Livro existe = livro.isPresent() ? livro.get() : null;
		
		existe.setTitulo(dado);
		
		livroRepository.save(existe);
	}
	
	public void updateDescricao (String isbn, String dado) {	
		Optional<Livro> livro = livroRepository.findById(isbn);
		Livro existe = livro.isPresent() ? livro.get() : null;
		
		existe.setDescricao(dado);
		
		livroRepository.save(existe);
	}
	
	public void updateEdicao (String isbn, String dado) {	
		Optional<Livro> livro = livroRepository.findById(isbn);
		Livro existe = livro.isPresent() ? livro.get() : null;
		
		existe.setEdicao(dado);
		
		livroRepository.save(existe);
	}
	
	public void updateAno (String isbn, Integer dado) {	
		Optional<Livro> livro = livroRepository.findById(isbn);
		Livro existe = livro.isPresent() ? livro.get() : null;
		
		existe.setAno(dado);
		
		livroRepository.save(existe);
	}
	
	public void updatePreco (String isbn, Float dado) {	
		Optional<Livro> livro = livroRepository.findById(isbn);
		Livro existe = livro.isPresent() ? livro.get() : null;
		
		existe.setPreco(dado);
		
		livroRepository.save(existe);

	}
    
}
