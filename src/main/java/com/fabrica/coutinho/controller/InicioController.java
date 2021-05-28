package com.fabrica.coutinho.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fabrica.coutinho.model.Especialidade;
import com.fabrica.coutinho.model.Medico;
import com.fabrica.coutinho.model.Paciente;
import com.fabrica.coutinho.model.Receita;
import com.fabrica.coutinho.model.Usuario;
import com.fabrica.coutinho.repository.EspecialidadeRepository;
import com.fabrica.coutinho.repository.MedicoRepository;
import com.fabrica.coutinho.repository.PacienteRepository;
import com.fabrica.coutinho.repository.ReceitaRepository;
import com.fabrica.coutinho.repository.UsuarioRepository;

@Controller
public class InicioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private EspecialidadeRepository especialidadeRepository;
	
	@Autowired
	private ReceitaRepository receitaRepository;
	
	@GetMapping("/inicio")
	public String inicio(Model model){
		Optional<Usuario> usuario = usuarioRepository.findById(1);
		
		List<Receita> receitas = receitaRepository.findAll();
		
		model.addAttribute("receitas", receitas);
		
		if(!usuario.isEmpty()) {
			model.addAttribute("usuario", usuario.get().getNome());
			return "receitas";
		}else {
			return "inicio";
		}
	}
	
	@GetMapping("/cadastro/medico")
	public String cadastroMedico(Model model){
		List<Medico> medicos = medicoRepository.findAll();
		
		model.addAttribute("medicos", medicos);
		return "medicos";
	}
	
	@GetMapping("/cadastro/paciente")
	public String cadastroPaciente(Model model){
		List<Paciente> pacientes = pacienteRepository.findAll();
		
		model.addAttribute("pacientes", pacientes);
		return "pacientes";
	}
	
	@GetMapping("/cadastro/especialidade")
	public String cadastroEspecialidade(Model model){
		List<Especialidade> especialidades = especialidadeRepository.findAll();
		
		model.addAttribute("especialidades", especialidades);
		return "especialidades";
	}
	
	@GetMapping("/cadastro/receita")
	public String cadastroReceita(Model model){
		List<Receita> receitas = receitaRepository.findAll();
		
		model.addAttribute("receitas", receitas);
		return "especialidades";
	}
}
