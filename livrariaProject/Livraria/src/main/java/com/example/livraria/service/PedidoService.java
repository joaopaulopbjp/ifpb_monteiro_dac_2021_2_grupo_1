package com.example.livraria.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livraria.model.Estoque;
import com.example.livraria.model.ItemPedido;
import com.example.livraria.model.Livro;
import com.example.livraria.model.Pedido;
import com.example.livraria.model.Usuario;
import com.example.livraria.repository.EstoqueRepository;
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
	EstoqueRepository estoqueRepository;
	@Autowired
	EstoqueService estoqueService;
	
	/**
	 *Retorna um novo pedido para o usuário
	 */
	public Pedido criarPedido(String email) {
		Usuario usuario = usuarioRepository.getById(email); 
		Pedido pedido = new Pedido();
		pedido.setUsuario(usuario);
		return pedidoRepository.save(pedido);
	}
	
	/**
	 * Adiciona um livro e sua quantidade a um pedido e subtrai a quantidade do livro informada do estoque excluindo o mesmo caso o estoque zere.
	 */
	public void adicionarLivroNoPedido(String email, Integer idPedido, String ISBN, Integer quantidade) {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(idPedido);
		Optional<Livro> livroO = livroRepository.findById(ISBN);
		if(!livroO.isPresent()) {
			throw new RuntimeException("Livro nao esta cadastrado");
		}

		if(pedidoOptional.isPresent() && pedidoOptional.get().isAberto()) {
			Pedido pedido = pedidoOptional.get();
			Estoque estoque = estoqueRepository.findByLivro(livroO.get()).get(0);
			
			if(estoqueService.verificarLivroEstoque(ISBN, quantidade)) {
				estoque.setQuantidade(estoque.getQuantidade() - quantidade);
				
				ItemPedido ip = new ItemPedido();
				ip.setLivro(livroO.get());
				ip.setQuantidade(quantidade);
				ip.setPedido(pedido);
	
				ip = pedido.adicionarItem(ip);
//				this.verificarEstoque(ISBN, ip.getQuantidade());
				pedidoRepository.save(pedido);
				itemPedidoRepository.save(ip);
				if(estoque.getQuantidade()==0) {
					estoqueRepository.delete(estoque);
				}else {
					estoqueRepository.save(estoque);
				}
			}else {
				throw new RuntimeException("Não há livros suficientes para o pedido");
			}
			
		}else {
			Pedido pedido = criarPedido(email);
			adicionarLivroNoPedido(email, pedido.getId(), ISBN, quantidade);
			
		}
			
	}

//	public void verificarEstoque(String ISBN, int quantidade) {
//		if(!estoqueService.verificarLivroEstoque(ISBN, quantidade)) {
//			throw new RuntimeException("Nao ha livros suficiente no estoque ");
//		}
//	}
}


/**
 * consultar pedidos 
 * cancelar pedido
 * alterar pedido
 * consultar carrinhos de compras
 * fechar pedido
 * adicionar livro ao pedido
 * 
 * criar tabela categoria
 */