package com.fabrica.coutinho.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fabrica.coutinho.exception.ResourceNotFoundException;
import com.fabrica.coutinho.model.Consulta;
import com.fabrica.coutinho.repository.ConsultaRepository;
import com.fabrica.coutinho.vo.ConsultaVO;

@Service
public class ConsultaService {

	private ConsultaRepository consultaRepository;

	public ConsultaService() {
	}

	@Autowired
	public ConsultaService(ConsultaRepository consultaRepository) {
		this.consultaRepository = consultaRepository;
	}

	public ConsultaVO create(ConsultaVO consultaVO) {
		return ConsultaVO.create(consultaRepository.save(Consulta.create(consultaVO)));
	}

	public Page<ConsultaVO> findAll(Pageable pageable) {
		var page = consultaRepository.findAll(pageable);
		return page.map(this::convertToConsultaVO);
	}

	private ConsultaVO convertToConsultaVO(Consulta consulta) {
		return ConsultaVO.create(consulta);
	}

	public ConsultaVO findById(Integer id) {
		var entity = consultaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrado"));
		return ConsultaVO.create(entity);
	}

	public ConsultaVO update(ConsultaVO consultaVO) {
		final Optional<Consulta> optional = consultaRepository.findById(consultaVO.getId());

		if (!optional.isPresent()) {
			new ResourceNotFoundException("Consulta não encontrado");
		}

		return ConsultaVO.create(consultaRepository.save(Consulta.create(consultaVO)));
	}

	public void delete(Integer id) {
		var entity = consultaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrado"));
		consultaRepository.delete(entity);
	}

}

