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

import com.fabrica.coutinho.services.ProntuarioService;
import com.fabrica.coutinho.vo.ProntuarioVO;

@RestController
@RequestMapping("/prontuario")
public class ProntuarioController {

	private final ProntuarioService prontuarioService;
	private final PagedResourcesAssembler<ProntuarioVO> assembler;
	
	@Autowired
	public ProntuarioController(ProntuarioService prontuarioService, PagedResourcesAssembler<ProntuarioVO> assembler) {
		this.prontuarioService = prontuarioService;
		this.assembler = assembler;
	}
	
	@GetMapping(value = "/{id}", produces = {"application/json","application/xml","application/x-yaml"})
	public ProntuarioVO findById(@PathVariable("id")  Integer id) {
		ProntuarioVO prontuarioVO = prontuarioService.findById(id);
		prontuarioVO.add(linkTo(methodOn(ProntuarioController.class).findById(id)).withSelfRel());
		return prontuarioVO;
	}
	
	@GetMapping(produces = {"application/json","application/xml","application/x-yaml"})
	public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page,limit, Sort.by(sortDirection,"nome"));
		
		Page<ProntuarioVO> prontuarios = prontuarioService.findAll(pageable);
		prontuarios.stream().forEach(p -> p.add(linkTo(methodOn(ProntuarioController.class).findById(p.getId())).withSelfRel()));
		
		PagedModel<EntityModel<ProntuarioVO>> pagedModel = assembler.toModel(prontuarios);
		
		return new ResponseEntity<>(pagedModel,HttpStatus.OK);
	}
	
	@PostMapping(produces = {"application/json","application/xml","application/x-yaml"}, 
			     consumes = {"application/json","application/xml","application/x-yaml"})
	public ProntuarioVO create(@RequestBody ProntuarioVO prontuarioVO) {
		ProntuarioVO vo = prontuarioService.create(prontuarioVO);
		vo.add(linkTo(methodOn(ProntuarioController.class).findById(vo.getId())).withSelfRel());
		return vo;
	}
	
	@PutMapping(produces = {"application/json","application/xml","application/x-yaml"}, 
		     consumes = {"application/json","application/xml","application/x-yaml"})
	public ProntuarioVO update(@RequestBody ProntuarioVO prontuarioVO) {
		ProntuarioVO vo = prontuarioService.update(prontuarioVO);
		vo.add(linkTo(methodOn(ProntuarioController.class).findById(prontuarioVO.getId())).withSelfRel());
		return vo;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id){
		prontuarioService.delete(id);
		return ResponseEntity.ok().build();
	}
}

