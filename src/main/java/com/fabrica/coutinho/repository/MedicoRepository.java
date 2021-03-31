package com.fabrica.coutinho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fabrica.coutinho.model.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {

}
