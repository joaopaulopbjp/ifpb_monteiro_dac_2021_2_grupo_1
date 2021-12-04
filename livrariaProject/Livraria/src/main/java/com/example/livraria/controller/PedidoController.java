package com.example.livraria.controller;

import com.example.livraria.model.Categoria;
import com.example.livraria.model.Estoque;
import com.example.livraria.model.Livro;
import com.example.livraria.model.Pedido;
import com.example.livraria.service.CategoriaService;
import com.example.livraria.service.PedidoService;
import com.example.livraria.service.UsuarioService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PedidoController {
    
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    PedidoService pedidoService;
    @Autowired
    CategoriaService categoriaService;

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
    
}
