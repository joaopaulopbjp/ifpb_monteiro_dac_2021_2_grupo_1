package com.example.livraria.model;

import java.util.List;

public class JwtResposta {
	private String token;
	private String type = "Bearer";
	private String id;
	private String login;
	private String email;
	private List<Endereco> enderecos;
	private String nome;
	private List<String> roles;
	
	public JwtResposta(String token, String id, String nome, String username, String email, List<Endereco> enderecos, List<String> roles) {
		super();
		this.token = token;
		this.id = id;
		this.nome = nome;
		this.login = username;
		this.email = email;
		this.enderecos = enderecos;
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String acessToken) {
		this.token = acessToken;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	
}
