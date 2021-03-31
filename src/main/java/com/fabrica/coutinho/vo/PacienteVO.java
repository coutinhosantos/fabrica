package com.fabrica.coutinho.vo;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fabrica.coutinho.model.Paciente;
import com.fabrica.coutinho.model.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonPropertyOrder({"id","nome","email","telefone","convenio","numero_convenio"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PacienteVO extends RepresentationModel<PacienteVO> implements Serializable{

	private static final long serialVersionUID = 1278952363336636914L;

	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("nome")
	private String nome;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("telefone")
	private String telefone;
	
	@JsonProperty("convenio")
	private String convenio;
	
	@JsonProperty("numero_convenio")
	private String numero_convenio;
	
	public static PacienteVO create(Paciente paciente ) {
		return new ModelMapper().map(paciente, PacienteVO.class);
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
