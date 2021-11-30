package com.example.livraria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livraria.model.Editora;
import com.example.livraria.repository.EditoraRepository;

@Service
public class EditoraService {
	
	@Autowired
	EditoraRepository editoraRepository;
	
	public void salvar(Editora editora) {
		editoraRepository.save(editora);
	}
	
	public List<Editora> getAll() {
        return editoraRepository.findAll();
    }
	
	public void remover(Editora editora) {
    	editoraRepository.delete(editora);
    }
	
	public Editora getEditora(Integer id) {
    	Optional<Editora> editora = editoraRepository.findById(id);
        return editora.isPresent() ? editora.get() : null;
    }
	
	public Editora alterarEditora(Editora editora) {
        return editoraRepository.save(editora);
    }

}
