package com.fabrica.coutinho.vo;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fabrica.coutinho.model.Medico;
import com.fabrica.coutinho.model.Paciente;
import com.fabrica.coutinho.model.Prontuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonPropertyOrder({"id","sintomas","diagnostico","paciente","medico"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProntuarioVO extends RepresentationModel<ProntuarioVO> implements Serializable{

	private static final long serialVersionUID = -2640058615246566758L;

	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("sintomas")
	private String sintomas;
	
	@JsonProperty("diagnostico")
	private String diagnostico;
	
	@JsonProperty("paciente")
	private Paciente paciente;
	
	@JsonProperty("medico")
	private Medico medico;
	
	public static ProntuarioVO create(Prontuario prontuario) {
		return new ModelMapper().map(prontuario, ProntuarioVO.class);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSintomas() {
		return sintomas;
	}

	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
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
