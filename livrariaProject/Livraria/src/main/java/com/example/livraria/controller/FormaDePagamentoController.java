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


@Controller
public class FormaDePagamentoController {

	@Autowired
	FormaDePagamentoService formaDePagamentoService;
	
	@Autowired
	CategoriaService categoriaService;
	
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
	
	@PostMapping("/adicionar-pagamento")
	public String adicionarFormaDePagamento(@ModelAttribute(name="formaDePagamento") FormaDePagamento formaDePagamento, Model model){
		formaDePagamentoService.salvar(formaDePagamento);
		List<Categoria> categorias = categoriaService.obterCategorias();
		model.addAttribute("listaCategorias", categorias);
		return "redirect:/gerenciar-pagamento";
	}

	@GetMapping("/excluir-pagamento")
	public String RemoverFormaDePagamento(@RequestParam(name="id") Integer id, Model model){
		FormaDePagamento formaDePagamento = formaDePagamentoService.getFormaDePagamentoID(id);
		formaDePagamentoService.deletarFormaDePagamento(formaDePagamento);
		List<Categoria> categorias = categoriaService.obterCategorias();
		model.addAttribute("listaCategorias", categorias);
		return "redirect:/gerenciar-pagamento";
	}
	
	@PostMapping("/alterar-pagamento")
	public String alterarFormaDePagamento(@ModelAttribute(name="FormaDePagamento") FormaDePagamento formaDePagamento, Model model){
		formaDePagamentoService.alterarFormaDePagamento(formaDePagamento);
		return "redirect:/gerenciar-pagamento";
	}
}
