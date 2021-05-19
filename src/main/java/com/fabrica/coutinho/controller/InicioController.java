package com.fabrica.coutinho.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fabrica.coutinho.model.Usuario;
import com.fabrica.coutinho.repository.UsuarioRepository;
import com.fabrica.coutinho.services.UsuarioService;
import com.fabrica.coutinho.vo.UsuarioVO;

@Controller
public class InicioController {

	private final UsuarioService usuarioService;
	
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	public InicioController(UsuarioService usuarioService, PagedResourcesAssembler<UsuarioVO> assembler, UsuarioRepository usuarioRepository) {
		this.usuarioService = usuarioService;
		this.usuarioRepository = usuarioRepository;
	}
	
	
	@GetMapping("/inicio")
	public String inicio(Model model){
		Optional<Usuario> usuario = usuarioRepository.findById(1);
		
		if(!usuario.isEmpty()) {
			model.addAttribute("usuario", usuario.get().getNome());
			return "index";
		}else {
			return "inicio";
		}
	}
}
