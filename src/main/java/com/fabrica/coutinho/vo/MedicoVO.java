package com.fabrica.coutinho.vo;

import java.io.Serializable;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fabrica.coutinho.model.Especialidade;
import com.fabrica.coutinho.model.Medico;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonPropertyOrder({"id","nome","email","telefone","especialidade","crm",""})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MedicoVO extends RepresentationModel<MedicoVO> implements Serializable{

	private static final long serialVersionUID = -5933556955357852746L;

	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("nome")
	private String nome;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("telefone")
	private String telefone;
	
	@JsonProperty("crm")
	private String crm;
	
	@JsonProperty("especialidades")
	private List<Especialidade> especialidades;

	
	public static MedicoVO create(Medico medico) {
		return new ModelMapper().map(medico, MedicoVO.class);
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

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public List<Especialidade> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<Especialidade> especialidades) {
		this.especialidades = especialidades;
	}
	
	
}
