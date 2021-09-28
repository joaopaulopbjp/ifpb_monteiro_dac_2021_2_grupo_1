package com.example.livraria.repository;

import com.example.livraria.model.Pedido;
import com.example.livraria.model.Usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	
	List<Pedido> findByUsuario(Usuario usuario);
    
}
