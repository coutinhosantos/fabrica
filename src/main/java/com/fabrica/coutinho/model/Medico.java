package com.fabrica.coutinho.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "medico")
public class Medico {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name = "nome", length = 200)
	private String nome;
	
	@Column(name = "email", length = 200)
	private String email;
	
	@Column(name = "telefone", length = 200)
	private String telefone;
	
	@Column(name = "especialidade", length = 200)
	private String especialidade;
	
	@Column(name = "crm", length = 200)
	private String crm;
	
	@Column(name = "id_paciente")
	private List<Paciente> pacientes;
	
	@Column(name = "id_especialidade")
	private List<Especialidade> especialidades;
}
