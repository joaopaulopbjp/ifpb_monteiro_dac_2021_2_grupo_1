package com.example.livraria.controller;

import com.example.livraria.model.Categoria;
import com.example.livraria.model.Livro;
import com.example.livraria.model.Pedido;
import com.example.livraria.service.CategoriaService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.example.livraria.model.Usuario;

import com.example.livraria.service.PedidoService;
import com.example.livraria.service.UsuarioService;


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


    @GetMapping("/carrinho-compras")
    public String carrinhoCompras(Model model) {
    	
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    	String nome;    

    	if (principal instanceof UserDetails) {
    	    nome = ((UserDetails)principal).getUsername();
    	} else {
    	    nome = principal.toString();
    	}
        Usuario usuario = usuarioService.getUsuario(nome);

        List<Categoria> listaCategorias = categoriaService.obterCategorias();
   		model.addAttribute("categorias",listaCategorias);
        Pedido pedido = pedidoService.consultarCarrinhoCompras(usuario);
        
        if(pedido == null) {
            pedido = new Pedido(usuario);
        }
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
   		List<Pedido> pedidosEmAberto = new ArrayList<>();
   		for(Pedido pedido: pedidos) {
   			if(pedido.isAberto())
   				pedidosEmAberto.add(pedido);
   		}
   		model.addAttribute("listaPedidos", pedidosEmAberto);
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
    
    @GetMapping("/search-pedido")
    public String search(@RequestParam(name="pesquisa") Integer id, Model model) {
    	List<Pedido> listaPedidos = new ArrayList<Pedido>();
		Pedido pedido = pedidoService.findById(id);
		if(pedido != null) {
			listaPedidos.add(pedido);
		}
		model.addAttribute("listaPedidos", listaPedidos);
		
		List<Categoria> listaCategorias = categoriaService.obterCategorias();
   		model.addAttribute("categorias",listaCategorias);

		return "pedido/cancelar-pedido";
    }
    
    
    @PostMapping("/atualizar-pedido")
    public String createItemPedido(@ModelAttribute("pedido") Pedido pedido, BindingResult result, Model modelo) {
        if(!result.hasErrors()) {
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
