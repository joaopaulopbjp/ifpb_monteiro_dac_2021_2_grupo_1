package com.example.livraria.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Temporal(TemporalType.DATE)
	private Date data;
	@ManyToOne
	private Usuario usuario;
	@ManyToMany
	private List<ItemPedido> itemPedido;
	private float valorTotal;
	private String formaDePagamento; 
	@ManyToOne
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
	public List<ItemPedido> getItemPedido() {
		return itemPedido;
	}
	public void setItemPedido(List<ItemPedido> itemPedido) {
		this.itemPedido = itemPedido;
	}
	public float getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
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
