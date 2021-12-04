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
		
	/**
	 * Cria um estoque para o livro escolhido com a quantidade do livro a ser disponibilizada indormada.
	 * se ja houver um estoque do livro criado a quantidade informada será somada ao estoque existente.
	 * @param id do livro
	 * @param quantidade de livro a ser asicionado no estoque
	 */
	public void criarEstoque(String ISBN, Integer quantidade){
		Livro livro = livroRepository.getById(ISBN);
		Estoque estoque;
		
		if(estoqueRepository.findByLivro(livro).isEmpty()) {
			
			estoque = new Estoque();
			estoque.setLivro(livro);
			
		}else {
			
			estoque = estoqueRepository.findByLivro(livro).get(0);
			
		}
		
		estoque.setQuantidade(quantidade);
		estoqueRepository.save(estoque);
		
	}
	
	public void deleteById(Integer id) {
		estoqueRepository.deleteById(id);
	}

	/**
	 * Retorna os 5 licvros mais baratos do banco de dados em estoque
	 */
	public List<Livro> consultarLivrosBaratos(){
		Pageable OrdenadoPorPreco = PageRequest.of(0, 5, Sort.by("preco"));
		return estoqueRepository.findAllLivrosMaisBaratosNoEstoque(OrdenadoPorPreco).getContent();
	}

	/**
	 * Verifica se a quantidade de livros requerida contem no estoque
	 */
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
	
	public List<Estoque> findAll(Integer page) {
		Pageable pageable = PageRequest.of(page, 21, Sort.by("id").descending());
			
			
		return estoqueRepository.findAll(pageable).getContent();
	}

	public Estoque findByLivro(Livro livro) {
		List<Estoque> estoques = estoqueRepository.findByLivro(livro);
		return estoques.isEmpty() ? null : estoques.get(0);
	}

	public void delete(Estoque estoque) {
		estoqueRepository.delete(estoque);
	}

	public void alterarEstoque(Livro livro, int quantidade) {
		Estoque estoque;
		List<Estoque> list = estoqueRepository.findByLivro(livro);
		if(list.size() == 0){
			estoque = new Estoque();
			estoque.setLivro(livro);
		}else {
			estoque = list.get(0);
		}

		estoque.setQuantidade(quantidade);
		if(estoque.getQuantidade()==0) {
			estoqueRepository.delete(estoque);
		}else {
			estoqueRepository.save(estoque);
		}
	}

}
