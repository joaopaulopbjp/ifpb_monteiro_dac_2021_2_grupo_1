package com.example.livraria.service;

import java.util.List;
import java.util.Optional;

import com.example.livraria.model.Categoria;
import com.example.livraria.repository.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    @Autowired
    CategoriaRepository categoriaRepository;

    public Categoria salvar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria salvarCategoriaPorNome(String nome) {
        Categoria categoria = buscarCategoriaPorNome(nome);
        if(categoria == null){
            categoria = new Categoria();
            return salvar(categoria.nome(nome));
        } else {
            return categoria;
        }
    }

    public Categoria buscarCategoriaPorNome(String nome) {
        Categoria categoria = categoriaRepository.findByName(nome);
        return categoria;
    }
    
    public Categoria getCategoriaID(Integer id) {
    	Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.isPresent() ? categoria.get() : null;
    }

    public List<Categoria> obterCategorias() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> obterCategoria(int id) {
        return categoriaRepository.findById(id);
    }

    public void deletarCategoria(Categoria categoria) {
        categoriaRepository.delete(categoria);
    }

    public Categoria alterarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

}
