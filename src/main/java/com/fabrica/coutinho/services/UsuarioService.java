package com.fabrica.coutinho.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fabrica.coutinho.exception.ResourceNotFoundException;
import com.fabrica.coutinho.model.Usuario;
import com.fabrica.coutinho.repository.UsuarioRepository;
import com.fabrica.coutinho.vo.UsuarioVO;

@Service
public class UsuarioService {

	private UsuarioRepository usuarioRepository;

	public UsuarioService() {
	}

	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public UsuarioVO create(UsuarioVO usuarioVO) {
		return UsuarioVO.create(usuarioRepository.save(Usuario.create(usuarioVO)));
	}

	public Page<UsuarioVO> findAll(Pageable pageable) {
		var page = usuarioRepository.findAll(pageable);
		return page.map(this::convertToUsuarioVO);
	}

	private UsuarioVO convertToUsuarioVO(Usuario usuario) {
		return UsuarioVO.create(usuario);
	}

	public UsuarioVO findById(Integer id) {
		var entity = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
		return UsuarioVO.create(entity);
	}

	public UsuarioVO update(UsuarioVO usuarioVO) {
		final Optional<Usuario> optional = usuarioRepository.findById(usuarioVO.getId());

		if (!optional.isPresent()) {
			new ResourceNotFoundException("Usuário não encontrado");
		}

		return UsuarioVO.create(usuarioRepository.save(Usuario.create(usuarioVO)));
	}

	public void delete(Integer id) {
		var entity = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
		usuarioRepository.delete(entity);
	}

}
