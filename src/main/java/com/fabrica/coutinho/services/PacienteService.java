package com.fabrica.coutinho.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fabrica.coutinho.exception.ResourceNotFoundException;
import com.fabrica.coutinho.model.Paciente;
import com.fabrica.coutinho.repository.PacienteRepository;
import com.fabrica.coutinho.vo.PacienteVO;

@Service
public class PacienteService {

	private PacienteRepository pacienteRepository;

	public PacienteService() {
	}

	@Autowired
	public PacienteService(PacienteRepository pacienteRepository) {
		this.pacienteRepository = pacienteRepository;
	}

	public PacienteVO create(PacienteVO pacienteVO) {
		return PacienteVO.create(pacienteRepository.save(Paciente.create(pacienteVO)));
	}

	public Page<PacienteVO> findAll(Pageable pageable) {
		var page = pacienteRepository.findAll(pageable);
		return page.map(this::convertToPacienteVO);
	}

	private PacienteVO convertToPacienteVO(Paciente paciente) {
		return PacienteVO.create(paciente);
	}

	public PacienteVO findById(Integer id) {
		var entity = pacienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));
		return PacienteVO.create(entity);
	}

	public PacienteVO update(PacienteVO pacienteVO) {
		final Optional<Paciente> optional = pacienteRepository.findById(pacienteVO.getId());

		if (!optional.isPresent()) {
			new ResourceNotFoundException("Paciente não encontrado");
		}

		return PacienteVO.create(pacienteRepository.save(Paciente.create(pacienteVO)));
	}

	public void delete(Integer id) {
		var entity = pacienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));
		pacienteRepository.delete(entity);
	}

}
