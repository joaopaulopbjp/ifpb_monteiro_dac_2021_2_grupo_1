package com.example.livraria.service;

import java.util.List;
import java.util.Optional;

import com.example.livraria.model.Usuario;
import com.example.livraria.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public void salvar(String nome, String cpf, String email, String senha) {
    	Usuario user = new Usuario();
    	user.setNome(nome);
    	user.setCPF(cpf);
    	user.setEmail(email);
    	user.setSenha(senha);
        usuarioRepository.save(user);
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