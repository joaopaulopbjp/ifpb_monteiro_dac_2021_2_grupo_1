package com.example.livraria.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Livro {

	@Id
	@Column(length = 20)
	private String ISBN;
	private String categoria;
	
	@NotNull
	@ManyToMany
	private List<Autor> autores;
	@NotBlank
	private String titulo;
	private String descricao;
	private String edicao;
	private Integer ano;
	@ManyToOne
	private Editora editora;
	@Column(nullable = false)
	private float preco;
	
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public List<Autor> getAutor() {
		return autores;
	}
	public void setAutor(List<Autor> autor) {
		this.autores = autor;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getEdicao() {
		return edicao;
	}
	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public Editora getEditora() {
		return editora;
	}
	public void setEditora(Editora editora) {
		this.editora = editora;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	@Override
	public String toString() {
		return "Livro [ISBN=" + ISBN + ", categoria=" + categoria + ", titulo=" + titulo
				+ ", descricao=" + descricao + ", edicao=" + edicao + ", ano=" + ano
				+ ", preco=" + preco + "]";
	}
	
}
