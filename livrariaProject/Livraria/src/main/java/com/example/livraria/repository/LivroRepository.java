package com.example.livraria.repository;

import com.example.livraria.model.Livro;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface que representa o repositório do livro.
 * 
 * @author Agemiro Neto
 * @author Jordielson Silva
 * @author Victor Macêdo
 */
@Repository
public interface LivroRepository extends JpaRepository<Livro, String>{
	
}
