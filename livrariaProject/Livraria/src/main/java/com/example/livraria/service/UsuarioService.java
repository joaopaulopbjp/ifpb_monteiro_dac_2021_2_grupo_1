package com.example.livraria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livraria.model.Endereco;
import com.example.livraria.model.PapelUsuario;
import com.example.livraria.model.Usuario;
import com.example.livraria.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public void salvar(String nome, String cpf, String email, String senha, String cep, String estado, String cidade, String rua, Integer numero, String bairro, String complemento) {
    	Usuario user = new Usuario();
    	user.setNome(nome);
    	user.setCPF(cpf);
    	user.setEmail(email);
    	user.setSenha(senha);
        user.setPapelUsuario(PapelUsuario.CLIENTE);
        
        Endereco endereco = new Endereco();
        endereco.setCep(cep);
        endereco.setEstado(estado);
        endereco.setCidade(cidade);
        endereco.setRua(rua);
        endereco.setNumero(numero);
        endereco.setBairro(bairro);
        endereco.setComplemento(complemento);
        user.addEndereco(endereco);
        
        usuarioRepository.save(user);
    }
    
    public boolean verificarEmail(String email){
    	return usuarioRepository.existsById(email);
    }

    public void remover(Usuario user) {
        usuarioRepository.delete(user);
    }

    public Usuario getUsuario(String email) {
    	Optional<Usuario> user = usuarioRepository.findById(email);
        return user.isPresent() ? user.get() : null;
    }

	public List<Usuario> getAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios;
    }
}