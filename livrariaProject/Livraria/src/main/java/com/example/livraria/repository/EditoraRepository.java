package com.example.livraria.repository;

import com.example.livraria.model.Editora;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface que representa o repositório da editora
 * 
 * @author Agemiro Neto
 * @author Jordielson Silva
 * @author Victor Macêdo
 */
@Repository
public interface EditoraRepository extends JpaRepository<Editora, Integer> {
    
}
