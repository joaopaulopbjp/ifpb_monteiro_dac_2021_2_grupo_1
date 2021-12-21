package com.example.livraria.security;

import com.example.livraria.model.Usuario;
import com.example.livraria.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario u = repository.findByEmail(username);
        if(u == null) {
            throw new UsernameNotFoundException("Usuario nao encontrado!");
        }
        return new User(u.getUsername(), u.getPassword(), true, true, true, true, u.getAuthorities());
    }
    
}
