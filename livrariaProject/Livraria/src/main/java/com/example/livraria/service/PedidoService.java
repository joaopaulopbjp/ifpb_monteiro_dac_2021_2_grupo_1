package com.example.livraria.service;

import java.util.Date;
import java.util.List;
import java.util.ListIterator;
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
    @Autowired
    ItemPedidoService itemPedidoService;
	
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
				estoque.setQuantidade(-quantidade);
				
				ItemPedido ip = new ItemPedido();
				ip.setLivro(livroO.get());
				ip.setQuantidade(quantidade);
				ip.setPedido(pedido);
	
				ip = pedido.adicionarItem(ip);
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

	public void adicionarLivroAoPedido(Livro livro, Usuario user) {
		Pedido pedido = consultarCarrinhoCompras(user);
		if (pedido == null) {
			adicionarLivroNoPedido(user.getEmail(), 0, livro.getISBN(), 1);			
		} else {
			adicionarLivroNoPedido(user.getEmail(), pedido.getId(), livro.getISBN(), 1);			
		}

	}

	public List<Pedido> obterPedidos(Usuario user) {
		return pedidoRepository.findByUsuario(user);
	}
	
	public List<Pedido> getAll(){
		return pedidoRepository.findAll();
	}

	public void cancelarPedido(Pedido pedido) {
		pedido.cancelar();
		Usuario user = usuarioRepository.findByEmail(pedido.getUsuario().getEmail());
		List<Pedido> pedidos = user.getHistoricoDePedidos();
		pedidos.add(pedido);
		user.setHistoricoDePedidos(pedidos);
		usuarioRepository.save(user);
		
		Pedido pedido2 = new Pedido();
		pedido2.setData(new Date());
		pedido2.setUsuario(user);
		pedidoRepository.save(pedido2);
		
		List<ItemPedido> itens = pedido.getItemPedido();
		for (ItemPedido ip : itens) {
			Integer quantidade = 0;
			quantidade+=ip.getQuantidade();
			// Estoque estoque;
			// if(estoqueRepository.findByLivro(ip.getLivro()).size() == 0){
			// 	estoque = new Estoque();
			// 	estoque.setLivro(ip.getLivro());
			// 	estoque.setQuantidade(ip.getQuantidade());
			// 	estoqueRepository.save(estoque);
			// }else {
			// 	estoque = estoqueRepository.findByLivro(ip.getLivro()).get(0);
			// 	estoque.setQuantidade(quantidade);
			// 	estoqueRepository.save(estoque);
			// }
			estoqueService.alterarEstoque(ip.getLivro(), quantidade);
		}
			
	}

	public Pedido alterarPedido(Pedido pedido) {
		Pedido pedidoAntigo = pedidoRepository.findById(pedido.getId()).get();
		Integer quantidade=0;
		ListIterator<ItemPedido> itens = pedido.getItemPedido().listIterator();
		
		while (itens.hasNext()) {
			ItemPedido ip = itens.next();
			ItemPedido ipAntigo = null;
			for(ItemPedido ipAnt : pedidoAntigo.getItemPedido()) {
				if(ip.equals(ipAnt)) {
					quantidade = ip.getQuantidade() - ipAnt.getQuantidade();
					ipAntigo = ipAnt;
				}
			}
			if(estoqueService.verificarLivroEstoque(ip.getLivro().getISBN(), quantidade)) {
				estoqueService.alterarEstoque(ip.getLivro(), -quantidade);
				if (ip.getQuantidade() == 0) {
					itens.remove();
					itemPedidoService.deletar(ip);
				} else {
					itemPedidoService.alterar(ip);
				}
			} else {
				ip.setQuantidade(ipAntigo.getQuantidade());
			}
		}
		
		pedido.atualizarValorTotal();
		return pedidoRepository.save(pedido);
	}

	public Pedido consultarCarrinhoCompras(Usuario user) {
		List<Pedido> pedidos = obterPedidos(user);
		for (Pedido pedido : pedidos) {
			if(pedido.getDataDefechamento() == null) {
				return pedido;
			}
		}
		return null;
	}

	public Pedido fecharPedido(Pedido pedido) {
		pedido.finalizar();
		Usuario user = usuarioRepository.findByEmail(pedido.getUsuario().getEmail());
		List<Pedido> pedidos = user.getHistoricoDePedidos();
		pedidos.add(pedido);
		user.setHistoricoDePedidos(pedidos);
		usuarioRepository.save(user);
		
		Pedido pedido2 = new Pedido();
		pedido2.setData(new Date());
		pedido2.setUsuario(pedido.getUsuario());
		pedidoRepository.save(pedido2);
		
		return pedidoRepository.save(pedido);
	}

	public Pedido findById(Integer id) {
		if(pedidoRepository.findById(id).isPresent())
			return pedidoRepository.findById(id).get();
		else {
			return null;
		}
	}

}