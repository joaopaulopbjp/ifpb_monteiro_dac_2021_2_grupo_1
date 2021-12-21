package com.example.livraria.repository;

import com.example.livraria.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface que representa o repositório do usuário.
 * 
 * @author Agemiro Neto
 * @author Jordielson Silva
 * @author Victor Macêdo
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{
    
    Usuario findByEmail(String email);
}
