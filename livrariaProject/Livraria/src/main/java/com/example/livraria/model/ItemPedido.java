package com.example.livraria.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Classe ItemPedido mapeada para a persistencia no banco de dados.
 * 
 * @author Agemiro Neto
 * @author Jordielson Silva
 * @author Victor Macêdo
 */
@Entity
public class ItemPedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name = "ISBN")
	private Livro livro;
	private Integer quantidade;

	private BigDecimal valorUnidade;

	@NotNull
	@ManyToOne
	private Pedido pedido;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
		valorUnidade = livro.getPreco();
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public BigDecimal getValorUnidade() {
		if(pedido.isAberto()) {
			return livro.getPreco();
		} else {
			return valorUnidade.setScale(2);
		}
	}
	public void setValorUnidade(BigDecimal valorUnidade) {
		this.valorUnidade = valorUnidade;
	}
	public BigDecimal obterValorTotal() {
		if(pedido.isAberto()) {
			return livro.getPreco().multiply(BigDecimal.valueOf(quantidade));
		} else {
			return valorUnidade.multiply(BigDecimal.valueOf(quantidade));
		}
	}
	public void finalizar() {
		valorUnidade = livro.getPreco();
	}
	
	@Override
		public boolean equals(Object obj) {
			ItemPedido p = (ItemPedido)obj;
			
			return p.getId()==this.id;
		}
	
}
