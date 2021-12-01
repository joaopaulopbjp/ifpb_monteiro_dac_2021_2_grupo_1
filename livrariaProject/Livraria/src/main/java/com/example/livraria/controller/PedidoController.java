package com.example.livraria.controller;

import com.example.livraria.model.Pedido;
import com.example.livraria.service.PedidoService;
import com.example.livraria.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PedidoController {
    
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    PedidoService pedidoService;

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
}
