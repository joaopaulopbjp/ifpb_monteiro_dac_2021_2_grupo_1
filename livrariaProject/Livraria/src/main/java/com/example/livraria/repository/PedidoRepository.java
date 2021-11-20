package com.example.livraria.repository;

import com.example.livraria.model.Pedido;
import com.example.livraria.model.Usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interface que representa o repositório do pedido
 * 
 * @author Agemiro Neto
 * @author Jordielson Silva
 * @author Victor Macêdo
 */
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	
	@Query("SELECT p FROM Pedido p WHERE p.usuario = ?1")
	List<Pedido> findByUsuario(Usuario user);

}
