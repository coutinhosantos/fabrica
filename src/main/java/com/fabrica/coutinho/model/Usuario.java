package com.fabrica.coutinho.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name = "nome", length = 200)
	private String nome;
	
	@Column(name = "email", length = 200)
	private String email;
	
	@Column(name = "senha", length = 200)
	private String senha;
	
	@Column(name = "tipo", length = 1)
	private Integer tipo;

}

