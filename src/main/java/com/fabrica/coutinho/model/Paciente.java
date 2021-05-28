package com.fabrica.coutinho.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.fabrica.coutinho.vo.PacienteVO;

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

	public static Paciente create(PacienteVO pacienteVO) {
		return new ModelMapper().map(pacienteVO, Paciente.class);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getConvenio() {
		return convenio;
	}

	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}

	public String getNumero_convenio() {
		return numero_convenio;
	}

	public void setNumero_convenio(String numero_convenio) {
		this.numero_convenio = numero_convenio;
	}
	
	
}
