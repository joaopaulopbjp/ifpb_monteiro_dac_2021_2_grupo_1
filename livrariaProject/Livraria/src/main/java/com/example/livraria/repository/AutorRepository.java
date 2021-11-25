package com.example.livraria.repository;

import com.example.livraria.model.Autor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 /**
  * Interface que representa o repositório do autor
  * 
  * @author Agemiro Neto
  * @author Jordielson Silva
  * @author Victor Macêdo
  */
@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer>{
	
	Page<Autor> findAll(Pageable pageable);
    
}
