package com.example.livraria.service;

import com.example.livraria.model.ItemPedido;
import com.example.livraria.repository.ItemPedidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemPedidoService {
    @Autowired
    ItemPedidoRepository itemPedidoRepository;

    public ItemPedido salvar(ItemPedido itemPedido) {
        return itemPedidoRepository.save(itemPedido);
    }

    public ItemPedido alterar(ItemPedido itemPedido) {
        return itemPedidoRepository.save(itemPedido);
    }

    public void deletar(ItemPedido itemPedido) {
        itemPedidoRepository.delete(itemPedido);
    }

    public ItemPedido obterItemPedido(int idItem) {
        return itemPedidoRepository.findById(idItem).get();
    }
}
