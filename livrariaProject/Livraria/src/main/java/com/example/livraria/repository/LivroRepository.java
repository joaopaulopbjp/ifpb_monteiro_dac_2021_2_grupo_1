package com.example.livraria.repository;

import com.example.livraria.model.Categoria;
import com.example.livraria.model.Livro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
	
	@Query("SELECT l FROM Livro l WHERE l.titulo LIKE %?1%")
	Page<Livro> findByTitulo(@Param("titulo") Pageable pageable, String titulo);
	
	
	Page<Livro> findByCategoria(Categoria categoria, Pageable pageable);
}
