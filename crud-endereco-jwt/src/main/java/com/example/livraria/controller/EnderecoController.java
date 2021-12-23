package com.example.livraria.controller;

import java.util.List;

import com.example.livraria.model.Endereco;
import com.example.livraria.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    UsuarioService usuarioService;
    
    @GetMapping("/lista")
    public List<Endereco> listar() {
        return usuarioService.getEndereco();
    }
}
