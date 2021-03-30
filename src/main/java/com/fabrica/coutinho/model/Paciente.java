package com.fabrica.coutinho.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
@Table(name = "paciente")
public class Paciente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nome", length = 200)
	private String nome;
	
	@Column(name = "email", length = 200)
	private String email;
	
	@Column(name = "telefone", length = 200)
	private String telefone;
	
	@Column(name = "convenio", length = 200)
	private String convenio;
	
	@Column(name = "numero_convenio", length = 200)
	private String numero_convenio;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_medico")
	private List<Medico> medicos;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_consulta")
	private Consulta consulta;
}
