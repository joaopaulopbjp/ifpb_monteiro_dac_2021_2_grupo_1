package com.example.livraria.repository;

import com.example.livraria.model.Endereco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface que representa o repositório do endereço
 * 
 * @author Agemiro Neto
 * @author Jordielson Silva
 * @author Victor Macêdo
 */

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    
}
