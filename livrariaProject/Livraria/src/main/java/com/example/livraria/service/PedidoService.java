package com.example.livraria.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livraria.model.ItemPedido;
import com.example.livraria.model.Livro;
import com.example.livraria.model.Pedido;
import com.example.livraria.model.Usuario;
import com.example.livraria.repository.ItemPedidoRepository;
import com.example.livraria.repository.LivroRepository;
import com.example.livraria.repository.PedidoRepository;
import com.example.livraria.repository.UsuarioRepository;

@Service
public class PedidoService {
	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	LivroRepository livroRepository;
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	@Autowired
	EstoqueService estoqueService;
	
	public Pedido criarPedido(String email) {
		Usuario usuario = usuarioRepository.getById(email); 
		Pedido pedido = new Pedido();
		pedido.setUsuario(usuario);
		return pedidoRepository.save(pedido);
	}
	
	public void adicionarLivroNoPedido(String email, Integer idPedido, String ISBN, Integer quantidade) {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(idPedido);
		Optional<Livro> livroO = livroRepository.findById(ISBN);
		if(!livroO.isPresent()) {
			throw new RuntimeException("Livro nao esta cadastrado");
		}

		if(pedidoOptional.isPresent() && pedidoOptional.get().isAberto()) {
			Pedido pedido = pedidoOptional.get();
			
			ItemPedido ip = new ItemPedido();
			ip.setLivro(livroO.get());
			ip.setQuantidade(quantidade);
			ip.setPedido(pedido);

			ip = pedido.adicionarItem(ip);
			this.verificarEstoque(ISBN, ip.getQuantidade());

			pedidoRepository.save(pedido);
			itemPedidoRepository.save(ip);
		}else {
			Pedido pedido = criarPedido(email);
			adicionarLivroNoPedido(email, pedido.getId(), ISBN, quantidade);
			
		}
			
	}

	public void verificarEstoque(String ISBN, int quantidade) {
		if(!estoqueService.verificarLivroEstoque(ISBN, quantidade)) {
			throw new RuntimeException("Nao ha livros suficiente no estoque ");
		}
	}
}
