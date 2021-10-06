package com.example.livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.livraria.model.ItemPedido;

/**
 * Interface que representa o repositório do item pedido
 * 
 * @author Agemiro Neto
 * @author Jordielson Silva
 * @author Victor Macêdo
 */

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer>{

}
