package com.example.livraria.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Classe Usuario mapeada para a persistencia no banco de dados.
 * 
 * @author Agemiro Neto
 * @author Jordielson Silva
 * @author Victor Macêdo
 */
@Entity
public class Usuario implements UserDetails {
	
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

	@ManyToMany
	@JoinTable(name = "role_usuarios", 
		joinColumns = @JoinColumn(
			name = "usuario_id", 
			referencedColumnName = "email"
		),
		inverseJoinColumns = @JoinColumn(
			name = "role_id",
			referencedColumnName = "papel"
		)
	)
	private List<Role> papeis = new ArrayList<Role>();
	
	/*
	 * Atributo de lista de endereços que usuário pode ter.
	 * Anotação um para muitos com propriedade cascade usando o tipo Merge.
	 */
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
	public List<Role> getPapelUsuario() {
		return papeis;
	}
	public void setPapelUsuario(List<Role>  papeis) {
		this.papeis = papeis;
	}
	public void addPapel(Role papel) {
		papeis.add(papel);
	}
	@Override
	public String toString() {
		return "Usuario [email=" + email + ", nome=" + nome + "]";
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return (Collection<? extends GrantedAuthority>) this.papeis;
	}
	@Override
	public String getPassword() {
		return this.senha;
	}
	@Override
	public String getUsername() {
		return this.email;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
