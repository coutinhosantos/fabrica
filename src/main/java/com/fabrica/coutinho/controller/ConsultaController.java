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

import com.fabrica.coutinho.services.ConsultaService;
import com.fabrica.coutinho.vo.ConsultaVO;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

	private final ConsultaService consultaService;
	private final PagedResourcesAssembler<ConsultaVO> assembler;
	
	@Autowired
	public ConsultaController(ConsultaService consultaService, PagedResourcesAssembler<ConsultaVO> assembler) {
		this.consultaService = consultaService;
		this.assembler = assembler;
	}
	
	@GetMapping(value = "/{id}", produces = {"application/json","application/xml","application/x-yaml"})
	public ConsultaVO findById(@PathVariable("id")  Integer id) {
		ConsultaVO consultaVO = consultaService.findById(id);
		consultaVO.add(linkTo(methodOn(ConsultaController.class).findById(id)).withSelfRel());
		return consultaVO;
	}
	
	@GetMapping(produces = {"application/json","application/xml","application/x-yaml"})
	public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page,limit, Sort.by(sortDirection,"nome"));
		
		Page<ConsultaVO> consultas = consultaService.findAll(pageable);
		consultas.stream().forEach(p -> p.add(linkTo(methodOn(ConsultaController.class).findById(p.getId())).withSelfRel()));
		
		PagedModel<EntityModel<ConsultaVO>> pagedModel = assembler.toModel(consultas);
		
		return new ResponseEntity<>(pagedModel,HttpStatus.OK);
	}
	
	@PostMapping(produces = {"application/json","application/xml","application/x-yaml"}, 
			     consumes = {"application/json","application/xml","application/x-yaml"})
	public ConsultaVO create(@RequestBody ConsultaVO consultaVO) {
		ConsultaVO vo = consultaService.create(consultaVO);
		vo.add(linkTo(methodOn(ConsultaController.class).findById(vo.getId())).withSelfRel());
		return vo;
	}
	
	@PutMapping(produces = {"application/json","application/xml","application/x-yaml"}, 
		     consumes = {"application/json","application/xml","application/x-yaml"})
	public ConsultaVO update(@RequestBody ConsultaVO consultaVO) {
		ConsultaVO vo = consultaService.update(consultaVO);
		vo.add(linkTo(methodOn(ConsultaController.class).findById(consultaVO.getId())).withSelfRel());
		return vo;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id){
		consultaService.delete(id);
		return ResponseEntity.ok().build();
	}
}
