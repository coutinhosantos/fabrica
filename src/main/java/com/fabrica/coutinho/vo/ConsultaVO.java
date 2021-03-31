package com.fabrica.coutinho.vo;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fabrica.coutinho.model.Consulta;
import com.fabrica.coutinho.model.Especialidade;
import com.fabrica.coutinho.model.Medico;
import com.fabrica.coutinho.model.Paciente;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonPropertyOrder({"id","especialidade","medico","paciente","revisao"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ConsultaVO extends RepresentationModel<ConsultaVO>  implements Serializable{
	
	private static final long serialVersionUID = -2932663875560412142L;

	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("especialidade")
	private Especialidade especialidade;
	
	@JsonProperty("medico")
	private Medico medico;
	
	@JsonProperty("paciente")
	private Paciente paciente;

	@JsonProperty("revisao")
	private Boolean revisao;

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Especialidade getEspecialidade() {
		return especialidade;
	}



	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}



	public Medico getMedico() {
		return medico;
	}



	public void setMedico(Medico medico) {
		this.medico = medico;
	}



	public Paciente getPaciente() {
		return paciente;
	}



	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}



	public Boolean getRevisao() {
		return revisao;
	}



	public void setRevisao(Boolean revisao) {
		this.revisao = revisao;
	}



	public static ConsultaVO create(Consulta consulta) {
		return new ModelMapper().map(consulta, ConsultaVO.class);
	}
}
