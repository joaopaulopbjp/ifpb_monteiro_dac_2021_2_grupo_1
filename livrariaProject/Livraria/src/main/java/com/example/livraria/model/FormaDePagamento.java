package com.example.livraria.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FormaDePagamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
    

    public FormaDePagamento() {
    }

    public FormaDePagamento(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public FormaDePagamento nome(String nome) {
        setNome(nome);
        return this;
    }
    
	 @Override
	    public boolean equals(Object o) {
	        if (o == this)
	            return true;
	        if (!(o instanceof Categoria)) {
	            return false;
	        }
	        FormaDePagamento formaDePagamento = (FormaDePagamento) o;
	        return Objects.equals(id, formaDePagamento.id) && Objects.equals(nome, formaDePagamento.nome);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(id, nome);
	    }

	    @Override
	    public String toString() {
	        return "{" +
	            " id='" + getId() + "'" +
	            ", nome='" + getNome() + "'" +
	            "}";
	    }


}
