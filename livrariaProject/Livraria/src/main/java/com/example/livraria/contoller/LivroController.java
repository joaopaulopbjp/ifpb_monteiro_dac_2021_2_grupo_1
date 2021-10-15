package com.example.livraria.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LivroController {
    
    @GetMapping("/cadastrar-livro") 
    public String cadastrarLivro() {
        return "cadastro_livro";
    }
}
