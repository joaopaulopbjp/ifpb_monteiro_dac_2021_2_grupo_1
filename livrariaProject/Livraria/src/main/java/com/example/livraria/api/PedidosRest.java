package com.example.livraria.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.livraria.model.Pedido;
import com.example.livraria.repository.PedidoRepository;

@RestController
@RequestMapping("api/pedidos")
public class PedidosRest {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping("todos")
	public List<Pedido> getPedidos(){
		PageRequest paginacao = PageRequest.of(0, 10, Sort.by("data"));
		return pedidoRepository.findAll(paginacao).getContent();
	}

}
