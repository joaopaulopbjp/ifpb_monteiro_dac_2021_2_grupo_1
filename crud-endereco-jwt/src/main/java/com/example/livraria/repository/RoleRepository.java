package com.example.livraria.repository;

import com.example.livraria.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface que representa o repositório do papel do usuario
 * 
 * @author Agemiro Neto
 * @author Jordielson Silva
 * @author Victor Macêdo
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    
}
