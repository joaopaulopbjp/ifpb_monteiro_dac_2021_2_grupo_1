package com.example.livraria.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livraria.model.Endereco;
import com.example.livraria.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public List<Endereco> listarEnderecos(String email){
		return (List<Endereco>)enderecoRepository.listarEnderecos(email);
	}

	public Endereco adicionarEndereco(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}
	
	public void removerEndereco(Endereco endereco) {
        enderecoRepository.delete(endereco);
    }

	public Endereco buscarPeloId(Integer id) {
		return enderecoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Integer.toString(id)));
	}
	
	public Endereco atualizarEndereco(Integer id, Endereco endereco) {
		
		Optional<Endereco> enderecoRecuperado = enderecoRepository.findById(id);
		
		if(enderecoRecuperado.isPresent()) {
			
			Endereco e = enderecoRecuperado.get();
			e.setCep(endereco.getCep());
			e.setEstado(endereco.getEstado());
			e.setCidade(endereco.getCidade());
			e.setBairro(endereco.getBairro());
			e.setRua(endereco.getRua());
			e.setNumero(endereco.getNumero());
			e.setComplemento(endereco.getComplemento());
			
			return enderecoRepository.save(e);
			
		}
		return null;
		
	}
	
}
