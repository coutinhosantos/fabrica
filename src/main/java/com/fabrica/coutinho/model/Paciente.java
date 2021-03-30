//package com.fabrica.coutinho.model;
//
//import java.util.List;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Entity
//@Table(name = "paciente")
//public class Paciente {
//	
//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	private Integer id;
//
//	@Column(name = "nome", length = 200)
//	private String nome;
//	
//	@Column(name = "email", length = 200)
//	private String email;
//	
//	@Column(name = "telefone", length = 200)
//	private String telefone;
//	
//	@Column(name = "convenio", length = 200)
//	private String convenio;
//	
//	@Column(name = "numero_convenio", length = 200)
//	private String numero_convenio;
//	
//	private List<Medico> medicos;
//}
