package com.example.livraria.service;

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

}
