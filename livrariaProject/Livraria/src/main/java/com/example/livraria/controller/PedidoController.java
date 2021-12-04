package com.example.livraria.controller;

import com.example.livraria.model.Categoria;
import com.example.livraria.model.Estoque;
import com.example.livraria.model.Livro;
import com.example.livraria.model.Pedido;
import com.example.livraria.service.CategoriaService;
import com.example.livraria.service.ItemPedidoService;

import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.example.livraria.model.ItemPedido;
import com.example.livraria.model.Livro;
import com.example.livraria.model.Pedido;
import com.example.livraria.model.Usuario;

import com.example.livraria.service.PedidoService;
import com.example.livraria.service.UsuarioService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class PedidoController {
    
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    PedidoService pedidoService;
    @Autowired
    CategoriaService categoriaService;
    @Autowired
    ItemPedidoService itemPedidoService;


    @GetMapping("/carrinho-compras")
    public String carrinhoCompras(Model model) {
    	
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    	String nome;    

    	if (principal instanceof UserDetails) {
    	    nome = ((UserDetails)principal).getUsername();
    	} else {
    	    nome = principal.toString();
    	}

        Pedido pedido = pedidoService.consultarCarrinhoCompras(usuarioService.getUsuario(nome));
        model.addAttribute("pedido", pedido);

        List<Integer> qtdNum = IntStream.rangeClosed(0, 10)
            .boxed()
            .collect(Collectors.toList());
            
        model.addAttribute("qtdNum", qtdNum);
        return "pedido/carrinho_compra";
    }

    
    @GetMapping("/cancelar-pedido")
   	public String cancelarPedidos(Model model){
   		List<Pedido> pedidos = pedidoService.getAll();
   		model.addAttribute("listaPedidos", pedidos);
   		List<Categoria> listaCategorias = categoriaService.obterCategorias();
   		model.addAttribute("categorias",listaCategorias);
   		return "pedido/cancelar-pedido";
   	}
    
    @GetMapping("/cancelar")
   	public String cancelar(@RequestParam(name="id") Integer id, Model model){
        Pedido pedido = pedidoService.findById(id);
   		pedidoService.cancelarPedido(pedido);
   		return "redirect:/cancelar-pedido";
   	}
    

    @PostMapping("/atualizar-pedido")
    public String createItemPedido(@ModelAttribute("pedido") Pedido pedido, BindingResult result, Model modelo) {
        if(!result.hasErrors()) {
            ListIterator<ItemPedido> items = pedido.getItemPedido().listIterator();
            while (items.hasNext()) {
                ItemPedido itemPedido = items.next();
                if (itemPedido.getQuantidade() == 0) {
                    items.remove();
                    itemPedidoService.deletar(itemPedido);
                } else {
                    itemPedidoService.alterar(itemPedido);
                }
                
            }
            pedidoService.alterarPedido(pedido);
        }
        return "redirect:/carrinho-compras";
    }

    @GetMapping("/adicionar-ao-pedido")
    public String adicionarAoPedido(@ModelAttribute(name="livro") Livro livro , Model model) {
        Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		String email = autenticado.getName();
		Usuario user = usuarioService.getUsuario(email);
        pedidoService.adicionarLivroAoPedido(livro, user);
        return "redirect:/carrinho-compras";
    }

}
