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

import com.fabrica.coutinho.services.PacienteService;
import com.fabrica.coutinho.vo.PacienteVO;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

	private final PacienteService pacienteService;
	private final PagedResourcesAssembler<PacienteVO> assembler;
	
	@Autowired
	public PacienteController(PacienteService pacienteService, PagedResourcesAssembler<PacienteVO> assembler) {
		this.pacienteService = pacienteService;
		this.assembler = assembler;
	}
	
	@GetMapping(value = "/{id}", produces = {"application/json","application/xml","application/x-yaml"})
	public PacienteVO findById(@PathVariable("id")  Integer id) {
		PacienteVO pacienteVO = pacienteService.findById(id);
		pacienteVO.add(linkTo(methodOn(PacienteController.class).findById(id)).withSelfRel());
		return pacienteVO;
	}
	
	@GetMapping(produces = {"application/json","application/xml","application/x-yaml"})
	public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page,limit, Sort.by(sortDirection,"nome"));
		
		Page<PacienteVO> pacientes = pacienteService.findAll(pageable);
		pacientes.stream().forEach(p -> p.add(linkTo(methodOn(PacienteController.class).findById(p.getId())).withSelfRel()));
		
		PagedModel<EntityModel<PacienteVO>> pagedModel = assembler.toModel(pacientes);
		
		return new ResponseEntity<>(pagedModel,HttpStatus.OK);
	}
	
	@PostMapping(produces = {"application/json","application/xml","application/x-yaml"}, 
			     consumes = {"application/json","application/xml","application/x-yaml"})
	public PacienteVO create(@RequestBody PacienteVO pacienteVO) {
		PacienteVO vo = pacienteService.create(pacienteVO);
		vo.add(linkTo(methodOn(PacienteController.class).findById(vo.getId())).withSelfRel());
		return vo;
	}
	
	@PutMapping(produces = {"application/json","application/xml","application/x-yaml"}, 
		     consumes = {"application/json","application/xml","application/x-yaml"})
	public PacienteVO update(@RequestBody PacienteVO pacienteVO) {
		PacienteVO vo = pacienteService.update(pacienteVO);
		vo.add(linkTo(methodOn(PacienteController.class).findById(pacienteVO.getId())).withSelfRel());
		return vo;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id){
		pacienteService.delete(id);
		return ResponseEntity.ok().build();
	}
}

