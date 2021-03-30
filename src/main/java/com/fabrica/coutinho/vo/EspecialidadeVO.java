package com.fabrica.coutinho.vo;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fabrica.coutinho.model.Especialidade;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonPropertyOrder({"id","nome"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EspecialidadeVO extends RepresentationModel<UsuarioVO> implements Serializable{

	private static final long serialVersionUID = -8322882634866839205L;

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("nome")
	private String nome;
	
	
	
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



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public static EspecialidadeVO create(Especialidade especialidade) {
		return new ModelMapper().map(especialidade, EspecialidadeVO.class);
	}
}
