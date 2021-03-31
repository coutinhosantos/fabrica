package com.fabrica.coutinho.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fabrica.coutinho.exception.ResourceNotFoundException;
import com.fabrica.coutinho.model.Receita;
import com.fabrica.coutinho.repository.ReceitaRepository;
import com.fabrica.coutinho.vo.ReceitaVO;

@Service
public class ReceitaService {

	private ReceitaRepository receitaRepository;

	public ReceitaService() {
	}

	@Autowired
	public ReceitaService(ReceitaRepository receitaRepository) {
		this.receitaRepository = receitaRepository;
	}

	public ReceitaVO create(ReceitaVO receitaVO) {
		return ReceitaVO.create(receitaRepository.save(Receita.create(receitaVO)));
	}

	public Page<ReceitaVO> findAll(Pageable pageable) {
		var page = receitaRepository.findAll(pageable);
		return page.map(this::convertToReceitaVO);
	}

	private ReceitaVO convertToReceitaVO(Receita receita) {
		return ReceitaVO.create(receita);
	}

	public ReceitaVO findById(Integer id) {
		var entity = receitaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Receita não encontrado"));
		return ReceitaVO.create(entity);
	}

	public ReceitaVO update(ReceitaVO receitaVO) {
		final Optional<Receita> optional = receitaRepository.findById(receitaVO.getId());

		if (!optional.isPresent()) {
			new ResourceNotFoundException("Receita não encontrado");
		}

		return ReceitaVO.create(receitaRepository.save(Receita.create(receitaVO)));
	}

	public void delete(Integer id) {
		var entity = receitaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Receita não encontrado"));
		receitaRepository.delete(entity);
	}

}

