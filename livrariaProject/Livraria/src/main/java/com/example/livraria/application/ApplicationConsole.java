package com.example.livraria.application;

import com.example.livraria.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationConsole implements CommandLineRunner {
    @Autowired
    UsuarioService usuarioService;

    @Override
    public void run(String... args) throws Exception {
//        System.out.println("\nNome: " + usuarioService.getUsuario("joao@gmail.com").getNome());
		// Usuario user = new Usuario();
		// user.setNome("Joao");
		// user.setCPF("156153");
		// user.setEmail("joao4@gmail.com");
		// user.setSenha("joao123");
		// usuarioService.salvar(user);
    }
    
}
