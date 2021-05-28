package com.fabrica.coutinho.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fabrica.coutinho.repository.MedicoRepository;
import com.fabrica.coutinho.repository.UsuarioRepository;
import com.fabrica.coutinho.services.MedicoService;
import com.fabrica.coutinho.vo.MedicoVO;

@RestController
@RequestMapping("/medico")
public class MedicoController {

	private final MedicoService medicoService;
	private final PagedResourcesAssembler<MedicoVO> assembler;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private MedicoRepository medicoRepository;

	
	@Autowired
	public MedicoController(MedicoService medicoService, PagedResourcesAssembler<MedicoVO> assembler) {
		this.medicoService = medicoService;
		this.assembler = assembler;
	}
	
	@GetMapping(value = "/{id}", produces = {"application/json","application/xml","application/x-yaml"})
	public MedicoVO findById(@PathVariable("id")  Integer id) {
		MedicoVO medicoVO = medicoService.findById(id);
		medicoVO.add(linkTo(methodOn(MedicoController.class).findById(id)).withSelfRel());
		return medicoVO;
	}
	
	@GetMapping(produces = {"application/json","application/xml","application/x-yaml"})
	public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page,limit, Sort.by(sortDirection,"nome"));
		
		Page<MedicoVO> medicos = medicoService.findAll(pageable);
		medicos.stream().forEach(p -> p.add(linkTo(methodOn(MedicoController.class).findById(p.getId())).withSelfRel()));
		
		PagedModel<EntityModel<MedicoVO>> pagedModel = assembler.toModel(medicos);
		
		return new ResponseEntity<>(pagedModel,HttpStatus.OK);
	}
	
	@PostMapping(produces = {"application/json","application/xml","application/x-yaml"}, 
			     consumes = {"application/json","application/xml","application/x-yaml"})
	public MedicoVO create(@RequestBody MedicoVO medicoVO) {
		MedicoVO vo = medicoService.create(medicoVO);
		vo.add(linkTo(methodOn(MedicoController.class).findById(vo.getId())).withSelfRel());
		return vo;
	}
	
	@PutMapping(produces = {"application/json","application/xml","application/x-yaml"}, 
		     consumes = {"application/json","application/xml","application/x-yaml"})
	public MedicoVO update(@RequestBody MedicoVO medicoVO) {
		MedicoVO vo = medicoService.update(medicoVO);
		vo.add(linkTo(methodOn(MedicoController.class).findById(medicoVO.getId())).withSelfRel());
		return vo;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id){
		medicoService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("novo")
	public String novo(MedicoVO medicoVO) {
		create(medicoVO);
		return "redirect:/inicio";

	}
}

