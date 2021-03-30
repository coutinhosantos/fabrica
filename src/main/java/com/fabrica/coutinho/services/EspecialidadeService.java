package com.fabrica.coutinho.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fabrica.coutinho.exception.ResourceNotFoundException;
import com.fabrica.coutinho.model.Especialidade;
import com.fabrica.coutinho.repository.EspecialidadeRepository;
import com.fabrica.coutinho.vo.EspecialidadeVO;

@Service
public class EspecialidadeService {

	private EspecialidadeRepository especialidadeRepository;
	
	public EspecialidadeService() {
	}
	
	@Autowired
	public EspecialidadeService(EspecialidadeRepository especialidadeRepository) {
		this.especialidadeRepository = especialidadeRepository;
	}

	public EspecialidadeVO create(EspecialidadeVO especialidadeVO) {
		return EspecialidadeVO.create(especialidadeRepository.save(Especialidade.create(especialidadeVO)));
	}

	public Page<EspecialidadeVO> findAll(Pageable pageable) {
		var page = especialidadeRepository.findAll(pageable);
		return page.map(this::convertToEspecialidadeVO);
	}

	private EspecialidadeVO convertToEspecialidadeVO(Especialidade especialidade) {
		return EspecialidadeVO.create(especialidade);
	}

	public EspecialidadeVO findById(Integer id) {
		var entity = especialidadeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Especialidade não encontrado"));
		return EspecialidadeVO.create(entity);
	}

	public EspecialidadeVO update(EspecialidadeVO especialidadeVO) {
		final Optional<Especialidade> optional = especialidadeRepository.findById(especialidadeVO.getId());

		if (!optional.isPresent()) {
			new ResourceNotFoundException("Especialidade não encontrado");
		}

		return EspecialidadeVO.create(especialidadeRepository.save(Especialidade.create(especialidadeVO)));
	}

	public void delete(Integer id) {
		var entity = especialidadeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Especialidade não encontrado"));
		especialidadeRepository.delete(entity);
	}
	
	
}
