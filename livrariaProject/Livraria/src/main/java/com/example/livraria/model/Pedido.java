package com.example.livraria.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Classe Pedido mapeada para a persistencia no banco de dados.
 * 
 * @author Agemiro Neto
 * @author Jordielson Silva
 * @author Victor Macêdo
 */
@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Temporal(TemporalType.DATE)
	private Date data = new Date();
	@ManyToOne
	private Usuario usuario;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "pedido")
	private List<ItemPedido> itemPedido = new ArrayList<ItemPedido>();
	private BigDecimal valorTotal = new BigDecimal(0);
	@ManyToOne
	private FormaDePagamento formaDePagamento; 
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
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public FormaDePagamento getFormaDePagamento() {
		return formaDePagamento;
	}
	public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
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
	public boolean isAberto() {
		return dataDefechamento == null;
	}
	public void finalizar() {
		this.dataDefechamento = new Date();
		for (ItemPedido ip : itemPedido) {
			ip.finalizar();
			this.valorTotal.add(ip.obterValorTotal());
		}
	}
	public ItemPedido adicionarItem(ItemPedido itemPedido) {
		if (!isAberto()) {
			throw new RuntimeException("Pedido esta fechado!");
		}
		boolean adicionado = false;
		ItemPedido ip = null;
		for( ItemPedido i: this.itemPedido) {
			if(i.getLivro().getISBN().equals(itemPedido.getLivro().getISBN())) {
				if(i.getQuantidade()+itemPedido.getQuantidade() > 10) {
					i.setQuantidade(10);
				} else {
					i.setQuantidade(i.getQuantidade()+itemPedido.getQuantidade());
				}
				ip = i;
				adicionado = true;
				break;
			}
		}
		if(!adicionado) {
			this.itemPedido.add(itemPedido);
			ip = itemPedido;
		}
		this.atualizarValorTotal();
		return ip;
	}
	public void atualizarValorTotal() {
		this.valorTotal = new BigDecimal(0);
		for (ItemPedido ip : itemPedido) {
			this.valorTotal = this.valorTotal.add(ip.obterValorTotal());
		}
	}
}
