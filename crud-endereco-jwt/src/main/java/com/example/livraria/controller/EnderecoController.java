package com.example.livraria.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.livraria.model.Endereco;
import com.example.livraria.security.TokenFilter;
import com.example.livraria.security.util.JwtUtils;
import com.example.livraria.service.EnderecoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	TokenFilter filter;

	@Autowired
	JwtUtils jwtUtils;

    @Autowired
	EnderecoService enderecoService;

    @PostMapping("/cadastrar-endereco")
	public ResponseEntity<Endereco> cadastrarEndereco(@RequestBody Endereco endereco) {
		return new ResponseEntity<Endereco>(enderecoService.adicionarEndereco(endereco), HttpStatus.CREATED);
	}
    @GetMapping("/lista")
	public List<Endereco> getAll(HttpServletRequest request){
		String jwt = filter.parseJwt(request);
		if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
			String userid = jwtUtils.getUserNameFromJwtToken(jwt);
			List<Endereco> listaEnderecos = enderecoService.listarEnderecos(userid);
			return listaEnderecos;
		}
		return null;

	}
	
	@PutMapping("/atualizar-endereco/{id}")
	public ResponseEntity<Endereco> updateEndereco(@RequestBody Endereco endereco, Integer id) {
		return new ResponseEntity<Endereco>(enderecoService.atualizarEndereco(id, endereco), HttpStatus.OK);
	}
	
	@DeleteMapping("/remover-endereco/{id}")
	public ResponseEntity<?> deleteEndereco(@PathVariable(value="id") Integer id) {
		Endereco endereco = enderecoService.buscarPeloId(id);
		if(endereco == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			enderecoService.removerEndereco(endereco);
			return new ResponseEntity<>(HttpStatus.OK);		
		}
	}
	
}
