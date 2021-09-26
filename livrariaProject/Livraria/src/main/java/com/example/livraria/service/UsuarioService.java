package com.example.livraria.service;

import java.util.List;

import com.example.livraria.model.Usuario;
import com.example.livraria.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public void salvar(Usuario user) {
        usuarioRepository.save(user);
    }

    public void remover(Usuario user) {
        usuarioRepository.delete(user);
    }

    public Usuario getUsuario(String email) {
        return usuarioRepository.findById(email).get();
    }

    public List<Usuario> getAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios;
    }
}