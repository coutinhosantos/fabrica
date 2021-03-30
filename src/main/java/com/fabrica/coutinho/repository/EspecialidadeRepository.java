package com.fabrica.coutinho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fabrica.coutinho.model.Especialidade;

@Repository
public interface EspecialidadeRepository extends JpaRepository<Especialidade, Integer> {

}
