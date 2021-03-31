package com.fabrica.coutinho.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fabrica.coutinho.exception.ResourceNotFoundException;
import com.fabrica.coutinho.model.Prontuario;
import com.fabrica.coutinho.repository.ProntuarioRepository;
import com.fabrica.coutinho.vo.ProntuarioVO;

@Service
public class ProntuarioService {

	private ProntuarioRepository prontuarioRepository;

	public ProntuarioService() {
	}

	@Autowired
	public ProntuarioService(ProntuarioRepository prontuarioRepository) {
		this.prontuarioRepository = prontuarioRepository;
	}

	public ProntuarioVO create(ProntuarioVO prontuarioVO) {
		return ProntuarioVO.create(prontuarioRepository.save(Prontuario.create(prontuarioVO)));
	}

	public Page<ProntuarioVO> findAll(Pageable pageable) {
		var page = prontuarioRepository.findAll(pageable);
		return page.map(this::convertToProntuarioVO);
	}

	private ProntuarioVO convertToProntuarioVO(Prontuario prontuario) {
		return ProntuarioVO.create(prontuario);
	}

	public ProntuarioVO findById(Integer id) {
		var entity = prontuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Prontuario não encontrado"));
		return ProntuarioVO.create(entity);
	}

	public ProntuarioVO update(ProntuarioVO prontuarioVO) {
		final Optional<Prontuario> optional = prontuarioRepository.findById(prontuarioVO.getId());

		if (!optional.isPresent()) {
			new ResourceNotFoundException("Prontuario não encontrado");
		}

		return ProntuarioVO.create(prontuarioRepository.save(Prontuario.create(prontuarioVO)));
	}

	public void delete(Integer id) {
		var entity = prontuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Prontuario não encontrado"));
		prontuarioRepository.delete(entity);
	}

}

