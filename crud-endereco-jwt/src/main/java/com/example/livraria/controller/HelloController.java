package com.example.livraria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.livraria.model.Endereco;
import com.example.livraria.service.EnderecoService;

/**
 * Controller hello que basicamente serve para exibir tudo que é publico. 
 * 
 * @author Agemiro Neto
 * @author Jordielson Silva
 * @author Victor Macêdo
 */

@CrossOrigin(origins = "http://localhost:8080/")
@RestController
@RequestMapping(value = "/endereco")
public class HelloController {
	
	@Autowired
	EnderecoService enderecoService;
	
	@PostMapping("/cadastrar-endereco")
	public ResponseEntity<Endereco> cadastrarEndereco(@RequestBody Endereco endereco) {
		return new ResponseEntity<Endereco>(enderecoService.adicionarEndereco(endereco), HttpStatus.CREATED);
	}
	
	@GetMapping("/enderecos")
	public List<Endereco> getAll(){
		
		List<Endereco> listaEnderecos = enderecoService.listarEnderecos();
		
//		model.addAttribute("enderecos", listaEnderecos);
//		model.addAttribute("endereco", endereco);
//		model.addAttribute("endereco", new Endereco());

		return listaEnderecos;

	}

}
