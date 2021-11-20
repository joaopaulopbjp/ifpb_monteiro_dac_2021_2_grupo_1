package com.example.livraria.repository;

import com.example.livraria.model.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    
    @Query(value = "SELECT * FROM categoria  c WHERE c.nome = ?1 LIMIT 1", nativeQuery = true)
    public Categoria findByName(String nome);
}