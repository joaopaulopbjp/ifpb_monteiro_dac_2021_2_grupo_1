package com.example.livraria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livraria.model.Autor;
import com.example.livraria.repository.AutorRepository;

@Service
public class AutorService {
	
	@Autowired
    AutorRepository autorRepository;

    public void salvar(String nome) {
    	Autor autor = new Autor();
    	autor.setNome(nome);
 
        autorRepository.save(autor);
    }

    public void remover(Autor autor) {
    	autorRepository.delete(autor);
    }

    public Autor getAutor(Integer id) {
    	Optional<Autor> autor = autorRepository.findById(id);
        return autor.isPresent() ? autor.get() : null;
    }

	public List<Autor> getAll() {
        List<Autor> autores = autorRepository.findAll();
        return autores;
    }
	
	public void update (Integer id, String nome) {	
		Optional<Autor> autor = autorRepository.findById(id);
		Autor existe = autor.isPresent() ? autor.get() : null;
		
		existe.setNome(nome);
		
		autorRepository.save(existe);
	}
    
}
