package com.example.livraria.controller;

import java.util.List;

import com.example.livraria.model.Endereco;
import com.example.livraria.model.Usuario;
import com.example.livraria.service.EnderecoService;
import com.example.livraria.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    UsuarioService userService;

    @Autowired
    EnderecoService enderecoService;
    
    @PostMapping("/seguranca/login")
	public ResponseEntity<?> login(@RequestBody Usuario data) {
        System.out.println("OK");
		Usuario u = userService.login(data.getEmail(), data.getPassword());
		if(u == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email nao cadastrado ou Senha invalida!");
		} else {
			return new ResponseEntity<Usuario>(u, HttpStatus.OK);
		}
	}

    @GetMapping("/endereco/todos")
    public ResponseEntity<List<Endereco>> getAll(){
		List<Endereco> listaEnderecos = enderecoService.listarEnderecos();
		return new ResponseEntity<List<Endereco>>(listaEnderecos, HttpStatus.OK);
	}
}
