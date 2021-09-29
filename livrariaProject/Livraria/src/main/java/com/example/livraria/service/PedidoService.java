package com.example.livraria.service;

import java.util.Date;
import java.util.List;
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
	
	public Pedido criarPedido(String email) {
		Usuario usuario = usuarioRepository.getById(email); 
		Pedido pedido = new Pedido();
		pedido.setData(new Date());
		pedido.setUsuario(usuario);
		return pedidoRepository.save(pedido);
	}
	
	public void adicionarLivroNoPedido(String email, Integer idPedido, String ISBN, Integer quantidade) {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(idPedido);
		
		if(pedidoOptional.isPresent() && pedidoOptional.get().getDataDefechamento() == null ) {
			boolean adicionado = false;
			Pedido pedido = pedidoOptional.get();
			List<ItemPedido> itens = pedido.getItemPedido();
			ItemPedido ip = null;
			for( ItemPedido i: itens) {
				if(i.getLivro().getISBN().equals(ISBN)) {
					i.setQuantidade(i.getQuantidade()+quantidade);
					ip = i;
					adicionado = true;
				}
			}
			if(!adicionado) {
				ip = new ItemPedido();
				Livro livro = livroRepository.findById(ISBN).get();
				ip.setLivro(livro);
				ip.setQuantidade(quantidade);
				ip.setPedido(pedido);
				itens.add(ip);
			}
			pedidoRepository.save(itemPedidoRepository.save(ip).getPedido());
		}else {
			Pedido pedido = criarPedido(email);
			adicionarLivroNoPedido(email, pedido.getId(), ISBN, quantidade);
			
		}
			
	}
}
