package com.example.livraria.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.livraria.model.Usuario;
import com.example.livraria.repository.UsuarioRepository;



@Service
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRep;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuarioRecuperado = usuarioRep.findByEmail(username);
		if (usuarioRecuperado != null) {
			return usuarioRecuperado;
		}
		
		throw new UsernameNotFoundException("Usuário ou senha inválidos.");
	}

	public UserDetails loadUserByUserId(String userid) throws UsernameNotFoundException {
		Optional<Usuario> usuarioRecuperado = usuarioRep.findById(userid);
		if (usuarioRecuperado.isPresent()) {
			return usuarioRecuperado.get();
		}
		
		throw new UsernameNotFoundException("Usuário ou senha inválidos.");
	}

}