package com.example.livraria.contoller;

import com.example.livraria.model.Pedido;
import com.example.livraria.service.PedidoService;
import com.example.livraria.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
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

        Pedido pedido = pedidoService.consultarCarrinhoCompras(usuarioService.getUsuario("carlos@gmail.com"));
        model.addAttribute("pedido", pedido);
        return "pedido/carrinho_compra";
    }
}
