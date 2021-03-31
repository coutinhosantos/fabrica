package com.fabrica.coutinho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fabrica.coutinho.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer>  {

}
