package com.example.livraria.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.livraria.model.Livro;
import com.example.livraria.repository.LivroRepository;

public class LivroService {
	
	LivroRepository livroRepository;
	
	public Page<Livro> consultarPorPagina(Integer page){
		Pageable pageable = PageRequest.of(page, 5, Sort.by("titulo"));
		Page<Livro> livros = livroRepository.findAll(pageable);
		return livros;
	}
    
}
