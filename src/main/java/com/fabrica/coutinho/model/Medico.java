package com.fabrica.coutinho.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "medico")
public class Medico {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "medicos", cascade = CascadeType.REFRESH)
	private List<Paciente> pacientes;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "medicos", cascade = CascadeType.REFRESH)
	private List<Especialidade> especialidades;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_consulta")
	private Consulta consulta;
}
