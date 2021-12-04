package com.example.livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.livraria.model.FormaDePagamento;

@Repository
public interface FormaDePagamentoRepository extends JpaRepository<FormaDePagamento, Integer> {

	@Query(value = "SELECT * FROM FormaDePagamento f WHERE f.nome = ?1 LIMIT 1", nativeQuery = true)
    public FormaDePagamento findByName(String nome);
	
}
