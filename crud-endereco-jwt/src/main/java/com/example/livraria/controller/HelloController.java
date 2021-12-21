package com.example.livraria.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * Controller hello que basicamente serve para exibir tudo que é publico. 
 * 
 * @author Agemiro Neto
 * @author Jordielson Silva
 * @author Victor Macêdo
 */
@Controller
public class HelloController {



	/**
	 * Método de cadastrar endereço
	 * @return cadastro_endereco
	 */
	@GetMapping("/cadastrar-endereco")
	public String cadastrarEndereco() {
		return "cadastro_endereco";
	}
	

	
}
