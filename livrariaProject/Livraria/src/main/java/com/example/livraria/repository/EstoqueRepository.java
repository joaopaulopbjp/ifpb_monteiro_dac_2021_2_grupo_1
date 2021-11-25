
package com.example.livraria.repository;

import com.example.livraria.model.Estoque;
import com.example.livraria.model.Livro;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Interface que representa o repositório do estoque 
 * 
 * @author Agemiro Neto
 * @author Jordielson Silva
 * @author Victor Macêdo
 */

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {
	
	List<Estoque> findByLivro(Livro livro);
	
	@Query("SELECT l FROM Livro  l, Estoque e WHERE l.ISBN = e.livro ORDER BY l.preco ASC")
	Page<Livro> findAllLivrosMaisBaratosNoEstoque(Pageable pageable);
	
	Page<Estoque> findAll(Pageable pageable);
	
	

}