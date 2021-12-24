package com.example.livraria.repository;

import java.util.List;

import com.example.livraria.model.Endereco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
    
    @Query(value = "SELECT * FROM endereco e WHERE e.usuario_email = ?1", nativeQuery = true)
    public List<Endereco> listarEnderecos(String email);
}
