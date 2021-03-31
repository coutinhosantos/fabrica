package com.fabrica.coutinho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fabrica.coutinho.model.Prontuario;

@Repository
public interface ProntuarioRepository extends JpaRepository<Prontuario, Integer> {

}
