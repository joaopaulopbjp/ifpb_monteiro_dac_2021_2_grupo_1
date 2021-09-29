package com.example.livraria.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;


@Entity
public class Usuario {
	
	@Id
	@Email(message="email invalido!")
	private String email;

	@NotBlank
	private String nome;

	@NotEmpty
	@Size(min = 6, message = "A senha deve possuir mais 6 caracteres")
	private String senha;

	@CPF(message = "cpf invalido!")
	@Column(length = 20)
	private String CPF;

	@Enumerated(EnumType.STRING)
	@Column(length = 13, nullable = false)
	private PapelUsuario papelUsuario;
	
	@OneToMany(cascade = {CascadeType.MERGE})
	private List<Endereco> endereco = new ArrayList<>();
	
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
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public List<Endereco> getEndereco() {
		return endereco;
	}
	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}
	public void addEndereco(Endereco endereco) {
		this.endereco.add(endereco);
	}
	public PapelUsuario getPapelUsuario() {
		return papelUsuario;
	}
	public void setPapelUsuario(PapelUsuario papelUsuario) {
		this.papelUsuario = papelUsuario;
	}
	@Override
	public String toString() {
		return "Usuario [email=" + email + ", nome=" + nome + "]";
	}
	
}
