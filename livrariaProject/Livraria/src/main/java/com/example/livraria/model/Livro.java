package com.example.livraria.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.Length;

/**
 * Classe Livro mapeada para a persistencia no banco de dados.
 * 
 * @author Agemiro Neto
 * @author Jordielson Silva
 * @author Victor Macêdo
 */
@Entity
public class Livro {
	@ISBN(message = "ISBN invalido")
	@Id
	@Column(length = 50)
	private String ISBN;
	@ManyToOne
	private Categoria categoria;
	private String capa;
	@NotEmpty
	@ManyToMany
	private List<Autor> autores;
	@NotBlank
	private String titulo;
	@Lob
	@Column(length=3500)
	private String descricao;
	private String edicao;
	private Integer ano;
	@ManyToOne
	private Editora editora;
	@Column(nullable = false)
	private BigDecimal preco;
	
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public List<Autor> getAutores() {
		return autores;
	}
	public void setAutores(List<Autor> autor) {
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
	public BigDecimal getPreco() {
		return preco.setScale(2);
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	public String getCapa() {
		return capa;
	}
	public void setCapa(String capa) {
		this.capa = capa;
	}
	@Override
	public String toString() {
		return "Livro [ISBN=" + ISBN + ", categoria=" + categoria + ", titulo=" + titulo
				+ ", descricao=" + descricao + ", edicao=" + edicao + ", ano=" + ano
				+ ", preco=" + preco + "]";
	}
	
}
