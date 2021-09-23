package com.example.livraria.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pedido {
	
	private Integer id;
	@Temporal(TemporalType.DATE)
	private Date data;
	private Usuario usuario;
	
	private List<Livro> livros;
	private float valorTotal;
	private Integer quantidadeLivro;
	private String formaDePagamento; 
	private Endereco localDeEntrega; 
	@Temporal(TemporalType.DATE)
	private Date dataDefechamento;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Livro> getLivros() {
		return livros;
	}
	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
	public float getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Integer getQuantidadeLivro() {
		return quantidadeLivro;
	}
	public void setQuantidadeLivro(Integer quantidadeLivro) {
		this.quantidadeLivro = quantidadeLivro;
	}
	public String getFormaDePagamento() {
		return formaDePagamento;
	}
	public void setFormaDePagamento(String formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}
	public Endereco getLocalDeEntrega() {
		return localDeEntrega;
	}
	public void setLocalDeEntrega(Endereco localDeEntrega) {
		this.localDeEntrega = localDeEntrega;
	}
	public Date getDataDefechamento() {
		return dataDefechamento;
	}
	public void setDataDefechamento(Date dataDefechamento) {
		this.dataDefechamento = dataDefechamento;
	}
	
	
}
