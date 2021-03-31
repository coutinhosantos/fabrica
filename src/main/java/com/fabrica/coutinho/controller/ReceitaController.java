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

import com.fabrica.coutinho.services.ReceitaService;
import com.fabrica.coutinho.vo.ReceitaVO;

@RestController
@RequestMapping("/receita")
public class ReceitaController {

	private final ReceitaService receitaService;
	private final PagedResourcesAssembler<ReceitaVO> assembler;
	
	@Autowired
	public ReceitaController(ReceitaService receitaService, PagedResourcesAssembler<ReceitaVO> assembler) {
		this.receitaService = receitaService;
		this.assembler = assembler;
	}
	
	@GetMapping(value = "/{id}", produces = {"application/json","application/xml","application/x-yaml"})
	public ReceitaVO findById(@PathVariable("id")  Integer id) {
		ReceitaVO receitaVO = receitaService.findById(id);
		receitaVO.add(linkTo(methodOn(ReceitaController.class).findById(id)).withSelfRel());
		return receitaVO;
	}
	
	@GetMapping(produces = {"application/json","application/xml","application/x-yaml"})
	public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page,limit, Sort.by(sortDirection,"nome"));
		
		Page<ReceitaVO> receitas = receitaService.findAll(pageable);
		receitas.stream().forEach(p -> p.add(linkTo(methodOn(ReceitaController.class).findById(p.getId())).withSelfRel()));
		
		PagedModel<EntityModel<ReceitaVO>> pagedModel = assembler.toModel(receitas);
		
		return new ResponseEntity<>(pagedModel,HttpStatus.OK);
	}
	
	@PostMapping(produces = {"application/json","application/xml","application/x-yaml"}, 
			     consumes = {"application/json","application/xml","application/x-yaml"})
	public ReceitaVO create(@RequestBody ReceitaVO receitaVO) {
		ReceitaVO vo = receitaService.create(receitaVO);
		vo.add(linkTo(methodOn(ReceitaController.class).findById(vo.getId())).withSelfRel());
		return vo;
	}
	
	@PutMapping(produces = {"application/json","application/xml","application/x-yaml"}, 
		     consumes = {"application/json","application/xml","application/x-yaml"})
	public ReceitaVO update(@RequestBody ReceitaVO receitaVO) {
		ReceitaVO vo = receitaService.update(receitaVO);
		vo.add(linkTo(methodOn(ReceitaController.class).findById(receitaVO.getId())).withSelfRel());
		return vo;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id){
		receitaService.delete(id);
		return ResponseEntity.ok().build();
	}
}

