package com.fabrica.coutinho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fabrica.coutinho.model.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Integer> {

}
