package com.example.livraria.service;

import java.util.Date;
import java.util.List;

import com.example.livraria.model.ItemPedido;
import com.example.livraria.model.Livro;
import com.example.livraria.model.Pedido;
import com.example.livraria.model.Usuario;
import com.example.livraria.repository.ItemPedidoRepository;
import com.example.livraria.repository.LivroRepository;
import com.example.livraria.repository.PedidoRepository;
import com.example.livraria.repository.UsuarioRepository;

public class PedidoService {
	
	PedidoRepository pedidoRepository;
	UsuarioRepository usuarioRepository;
	LivroRepository livroRepository;
	ItemPedidoRepository itemPedidoRepository;
	
	public void criarPedido(String email) {
		Usuario usuario = usuarioRepository.getById(email); 
		Pedido pedido = new Pedido();
		pedido.setData(new Date());
		pedido.setUsuario(usuario);
		pedidoRepository.save(pedido);
	}
	
	public boolean adicionarLivroNoPedido(Integer idPedido, String ISBN, Integer quantidade) {
		Pedido pedido = pedidoRepository.getById(idPedido);
		if(pedido != null && pedido.getDataDefechamento() == null ) {
			List<ItemPedido> itens = pedido.getItemPedido();
			for(ItemPedido i: itens) {
				if(i.getLivro().getISBN().equals(ISBN)) {
					i.setQuantidade(i.getQuantidade()+quantidade);
				}else {
					ItemPedido ip = new ItemPedido();
					Livro livro = livroRepository.getById(ISBN);
					ip.setLivro(livro);
					ip.setQuantidade(quantidade);
					itens.add(ip);
					itemPedidoRepository.save(ip);
				}
			}
			pedido.setItemPedido(itens);
			pedidoRepository.save(pedido);
			
			return true;
		}else
			return false;
	}
}
