package com.example.livraria.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.livraria.model.JwtResposta;
import com.example.livraria.model.LoginDto;
import com.example.livraria.model.Usuario;
import com.example.livraria.security.util.JwtUtils;


@RestController
@RequestMapping("/seguranca")
public class SecurityController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/login")
	public JwtResposta login(@Valid @RequestBody LoginDto loginRequest){
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getSenha()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		Usuario userDetails = (Usuario) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		JwtResposta resposta = new JwtResposta(jwt, 
				 userDetails.getCPF(), 
				 userDetails.getNome(),
				 userDetails.getUsername(), 
				 userDetails.getEmail(), 
				 userDetails.getEndereco(), 
				 roles);
		
		return resposta;
	}
}

