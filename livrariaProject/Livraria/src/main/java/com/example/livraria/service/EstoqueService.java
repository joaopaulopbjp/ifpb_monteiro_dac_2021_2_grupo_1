package com.example.livraria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.livraria.model.Estoque;
import com.example.livraria.model.Livro;
import com.example.livraria.repository.EstoqueRepository;
import com.example.livraria.repository.LivroRepository;

@Service
public class EstoqueService {
	
	@Autowired
	EstoqueRepository estoqueRepository;
	@Autowired
	LivroRepository livroRepository;
		
	
	public boolean criarEstoque(String ISBN, Integer quantidade){
		Livro livro = livroRepository.getById(ISBN);
		
		if(estoqueRepository.findByLivro(livro).isEmpty()) {
			
			Estoque estoque = new Estoque();
			estoque.setLivro(livro);
			estoque.setQuantidade(quantidade);
			estoqueRepository.save(estoque);
			return true;
			
		}else
			return false;
	}

	
	public List<Livro> consultarLivrosBaratos(){
		Pageable OrdenadoPorPreco = PageRequest.of(0, 5, Sort.by("preco"));
		return estoqueRepository.findAllLivrosMaisBaratosNoEstoque(OrdenadoPorPreco).getContent();
	}

	public boolean verificarLivroEstoque(String ISBN, int quantidade) {
		Livro livro = livroRepository.getById(ISBN);
		List<Estoque> livrosEstoque = estoqueRepository.findByLivro(livro);
		if(livrosEstoque.isEmpty()) {
			return false;
		} else {
			int quantEstoque = 0;
			for (Estoque estoque : livrosEstoque) {
				quantEstoque += estoque.getQuantidade();
			}
			return quantidade <= quantEstoque;
		}
	}

}
