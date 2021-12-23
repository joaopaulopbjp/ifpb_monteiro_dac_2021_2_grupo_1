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

/**
 * Controller de Pedido que retorna as paginas html referente ao pedido
 *  e os metodos para cancelar, alterar e pesquisar um pedido.
 * 
 * @author Agemiro Neto
 * @author Jordielson Silva
 * @author Victor Macêdo
 */
@Controller
public class PedidoController {
    //Classe que contém os serviços de usuario. Para obter o pedido do usuário.
    @Autowired
    UsuarioService usuarioService;

    //Classe que contém os serviços de pedido.
    @Autowired
    PedidoService pedidoService;

    //Classe que contém os serviços de categoria. Usado para listar as categorias no menu.
    @Autowired
    CategoriaService categoriaService;

    /**
     * Metodo para exibir a página de carrinho de compras
     * @param model
     * @return a página de carrinho de compras com o pedido o usuário.
     */
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

    /**
     * Método usado apenas pelo adiministrador que lista todos 
     * os pedido abertos que permite ao administrador cancelar um pedido.
     * @param model
     * @return página html de cancelar o pedido
     */
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
    
    
    
    /**
     * Método para cancelar um pedido de um cliente
     * @param id id do pedido a ser cancelado
     * @param model
     * @return redirecionamento para a página de cancelar pedido
     */
    @GetMapping("/cancelar")
   	public String cancelar(@RequestParam(name="id") Integer id, Model model){
        Pedido pedido = pedidoService.findById(id);
   		pedidoService.cancelarPedido(pedido);
   		return "redirect:/cancelar-pedido";
   	}
    @GetMapping("/finalizar")
	public String finalizar(@RequestParam(name="id") Integer id, Model model){
        Pedido pedido = pedidoService.findById(id);
   		pedidoService.fecharPedido(pedido);
   		System.out.println("chamou finalizar");
   		return "redirect:/carrinho-compras";
   	}
    /**
     * Pesquisa de um pedido pelo id dele.
     * @param id id do pedido a ser retornado
     * @param model
     * @return página de cancelar-pedido com apenas o pedido pesquisado
     */
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
    
    /**
     * Alteração de um pedido, permitindo também alterar os itens do pedido.
     * @param pedido pedido com os novos dados a serem atualizados
     * @param result
     * @param modelo
     * @return redirecionamento para o carrinho de compras
     */
    @PostMapping("/atualizar-pedido")
    public String createItemPedido(@ModelAttribute("pedido") Pedido pedido, BindingResult result, Model modelo) {
        if(!result.hasErrors()) {
            pedidoService.alterarPedido(pedido);
        }
        System.out.println("chamou atualizar");
        return "redirect:/carrinho-compras";
    }

    /**
     * Método para adicionar um livro no pedido
     * @param livro livro a ser adicionado
     * @param model
     * @return redirecionamento para o carrinho de compras
     */
    @GetMapping("/adicionar-ao-pedido")
    public String adicionarAoPedido(@ModelAttribute(name="livro") Livro livro , Model model) {
        Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		String email = autenticado.getName();
		Usuario user = usuarioService.getUsuario(email);
        pedidoService.adicionarLivroAoPedido(livro, user);
        return "redirect:/carrinho-compras";
    }

}
