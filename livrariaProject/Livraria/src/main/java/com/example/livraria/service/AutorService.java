package com.example.livraria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.livraria.model.Autor;
import com.example.livraria.repository.AutorRepository;

/**
 * Classe que representa os serviços do Autor, esta vai poder acessar o devido repositório,
 * no caso o AutorRepository.
 * 
 * @author Agemiro Neto
 * @author Jordielson Silva
 * @author Victor Macêdo
 */

@Service
public class AutorService {
	
	/*
	 * Contém a anotação @Autowired para que quando a classe AutorService for usada o 
	 * Spring chame autorRepository.
	 */
	@Autowired
    AutorRepository autorRepository;

	/**
     * Método feito pra receber o nome passado lá no menu para depois 'setar'
     * e consequentemente virar um objeto para assim poder ser salvo o autor usando o método 
     * 'save' da interface CrudRepository que é estendida pela interface AutorRepository.
     * 
     * @param dado do tipo String que vai compor o objeto Autor
     */
    public void salvar(String nome) {
    	Autor autor = new Autor();
    	autor.setNome(nome);
 
        autorRepository.save(autor);
    }

    /**
     * Método que serve para remover um autor passado por parametro.
     * 
     * @param o próprio autor
     */
    public void remover(Autor autor) {
    	autorRepository.delete(autor);
    }

    /**
     * Método feito pra retornar um autor através do id passado por parametro. 
     * 
     * @param id do Autor
     * @return objeto Autor ou null
     */
    public Autor getAutor(Integer id) {
    	Optional<Autor> autor = autorRepository.findById(id);
        return autor.isPresent() ? autor.get() : null;
    }

    /**
     * Método feito pra retornar todos os autores existentes sem exceção.
     * 
     * @return Lista de todos os objetos de Autores
     */
	public List<Autor> getAll(Integer page) {
		Pageable pageable = PageRequest.of(page, 20);
        List<Autor> autores = autorRepository.findAll(pageable).getContent();
        return autores;
    }

    public List<Autor> getAll() {
        List<Autor> autores = autorRepository.findAll();
        return autores;
    }
	
	/**
	 * Método utilizado para editar/atualizar dados de um determindado autor.
	 * @param id do autor
	 * @param nome do autor
	 */
	public Autor update (Integer id, String nome) {	
		Optional<Autor> autor = autorRepository.findById(id);
		Autor existe = autor.isPresent() ? autor.get() : null;
		
		existe.setNome(nome);
		
		return update(existe);
	}

    public Autor update(Autor autor) {
        return autorRepository.save(autor);
    }


    
}
