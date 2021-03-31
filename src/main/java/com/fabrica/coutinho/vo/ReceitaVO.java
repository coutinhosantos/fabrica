package com.fabrica.coutinho.vo;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fabrica.coutinho.model.Medico;
import com.fabrica.coutinho.model.Paciente;
import com.fabrica.coutinho.model.Receita;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonPropertyOrder({"id","anotacoes","paciente","medico"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReceitaVO extends RepresentationModel<ReceitaVO> implements Serializable{

	private static final long serialVersionUID = 7522261799216794237L;

	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("anotacoes")
	private String anotacoes;
	
	@JsonProperty("paciente")
	private Paciente paciente;
	
	@JsonProperty("medico")
	private Medico medico;

	public static ReceitaVO create(Receita receita) {
		return new ModelMapper().map(receita, ReceitaVO.class);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAnotacoes() {
		return anotacoes;
	}

	public void setAnotacoes(String anotacoes) {
		this.anotacoes = anotacoes;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
	
	
}
