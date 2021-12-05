package com.example.livraria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.livraria.model.Categoria;
import com.example.livraria.model.FormaDePagamento;
import com.example.livraria.service.CategoriaService;
import com.example.livraria.service.FormaDePagamentoService;

/**
 * Controller de Forma de Pagamento que possui os metodos para fazer o CRUD da forma de pagamento.
 * 
 * @author Agemiro Neto
 * @author Jordielson Silva
 * @author Victor Macêdo
 */
@Controller
public class FormaDePagamentoController {
	//Classe que contém os serviços de Forma de Pagamento.
	@Autowired
	FormaDePagamentoService formaDePagamentoService;
	
	//Classe que contém os serviços de categoria. Usado para listar as categorias no menu.
	@Autowired
	CategoriaService categoriaService;
	
	/**
	 * Método para obter a página de gerenciamento da forma de pagamento.
	 * @param model
	 * @return página de gerenciamento das formas de pagamento
	 */
	@GetMapping("/gerenciar-pagamento")
	public String crudFormaDePagamento(Model model){
		FormaDePagamento formaDePagamento = new FormaDePagamento();
		List<FormaDePagamento> formasDePagamento = formaDePagamentoService.obterFormasDePagamento();
		model.addAttribute("listaPagamento", formasDePagamento);
		model.addAttribute(formaDePagamento);
		List<Categoria> listaCategorias = categoriaService.obterCategorias();
		model.addAttribute("categorias",listaCategorias);
		return "formaDePagamento/crud-formaDePagamento";
	}
	
	/**
	 * Salvar uma forma de pagamento.
	 * @param formaDePagamento forma de pagamento a ser salva
	 * @param model
	 * @return redirecionamento para página de gereciamento de pagamentos
	 */
	@PostMapping("/adicionar-pagamento")
	public String adicionarFormaDePagamento(@ModelAttribute(name="formaDePagamento") FormaDePagamento formaDePagamento, Model model){
		formaDePagamentoService.salvar(formaDePagamento);
		List<Categoria> categorias = categoriaService.obterCategorias();
		model.addAttribute("listaCategorias", categorias);
		return "redirect:/gerenciar-pagamento";
	}

	/**
	 * Método para excluir um pagamento
	 * @param id id da forma de pagamento a ser excluído
	 * @param model
	 * @return redirecionamento para página de gereciamento de pagamentos
	 */
	@GetMapping("/excluir-pagamento")
	public String RemoverFormaDePagamento(@RequestParam(name="id") Integer id, Model model){
		FormaDePagamento formaDePagamento = formaDePagamentoService.getFormaDePagamentoID(id);
		formaDePagamentoService.deletarFormaDePagamento(formaDePagamento);
		List<Categoria> categorias = categoriaService.obterCategorias();
		model.addAttribute("listaCategorias", categorias);
		return "redirect:/gerenciar-pagamento";
	}
	
	/**
	 * Método para alterar uma forma de pagamento
	 * @param formaDePagamento forma de pagamento com as novas informações sobre o pagamento
	 * @param model
	 * @return redirecionamento para página de gereciamento de pagamentos
	 */
	@PostMapping("/alterar-pagamento")
	public String alterarFormaDePagamento(@ModelAttribute(name="FormaDePagamento") FormaDePagamento formaDePagamento, Model model){
		formaDePagamentoService.alterarFormaDePagamento(formaDePagamento);
		return "redirect:/gerenciar-pagamento";
	}
}
