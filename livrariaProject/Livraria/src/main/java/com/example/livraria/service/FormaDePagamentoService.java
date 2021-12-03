package com.example.livraria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livraria.model.FormaDePagamento;
import com.example.livraria.repository.FormaDePagamentoRepository;

@Service
public class FormaDePagamentoService {
	@Autowired
	FormaDePagamentoRepository formaDePagamentoRepository;

    public FormaDePagamento salvar(FormaDePagamento formaDePagamento) {
        return formaDePagamentoRepository.save(formaDePagamento);
    }

    public FormaDePagamento salvarFormaDePagamentoPorNome(String nome) {
    	FormaDePagamento formaDePagamento = buscarFormaDePagamentoPorNome(nome);
        if(formaDePagamento == null){
        	formaDePagamento = new FormaDePagamento();
            return salvar(formaDePagamento.nome(nome));
        } else {
            return formaDePagamento;
        }
    }

    public FormaDePagamento buscarFormaDePagamentoPorNome(String nome) {
    	FormaDePagamento formaDePagamento = formaDePagamentoRepository.findByName(nome);
        return formaDePagamento;
    }
    
    public FormaDePagamento getFormaDePagamentoID(Integer id) {
    	Optional<FormaDePagamento> formaDePagamento = formaDePagamentoRepository.findById(id);
        return formaDePagamento.isPresent() ? formaDePagamento.get() : null;
    }

    public List<FormaDePagamento> obterFormasDePagamento() {
        return formaDePagamentoRepository.findAll();
    }

    public Optional<FormaDePagamento> obterFormaDePagamento(int id) {
        return formaDePagamentoRepository.findById(id);
    }

    public void deletarFormaDePagamento(FormaDePagamento formaDePagamento) {
    	formaDePagamentoRepository.delete(formaDePagamento);
    }

    public FormaDePagamento alterarFormaDePagamento(FormaDePagamento formaDePagamento) {
        return formaDePagamentoRepository.save(formaDePagamento);
    }

}
