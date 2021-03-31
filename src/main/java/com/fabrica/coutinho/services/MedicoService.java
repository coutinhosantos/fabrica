package com.fabrica.coutinho.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fabrica.coutinho.exception.ResourceNotFoundException;
import com.fabrica.coutinho.model.Medico;
import com.fabrica.coutinho.repository.MedicoRepository;
import com.fabrica.coutinho.vo.MedicoVO;

@Service
public class MedicoService {

	private MedicoRepository medicoRepository;

	public MedicoService() {
	}

	@Autowired
	public MedicoService(MedicoRepository medicoRepository) {
		this.medicoRepository = medicoRepository;
	}

	public MedicoVO create(MedicoVO medicoVO) {
		return MedicoVO.create(medicoRepository.save(Medico.create(medicoVO)));
	}

	public Page<MedicoVO> findAll(Pageable pageable) {
		var page = medicoRepository.findAll(pageable);
		return page.map(this::convertToMedicoVO);
	}

	private MedicoVO convertToMedicoVO(Medico medico) {
		return MedicoVO.create(medico);
	}

	public MedicoVO findById(Integer id) {
		var entity = medicoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Medico não encontrado"));
		return MedicoVO.create(entity);
	}

	public MedicoVO update(MedicoVO medicoVO) {
		final Optional<Medico> optional = medicoRepository.findById(medicoVO.getId());

		if (!optional.isPresent()) {
			new ResourceNotFoundException("Medico não encontrado");
		}

		return MedicoVO.create(medicoRepository.save(Medico.create(medicoVO)));
	}

	public void delete(Integer id) {
		var entity = medicoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Medico não encontrado"));
		medicoRepository.delete(entity);
	}

}
