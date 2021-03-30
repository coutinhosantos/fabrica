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

import com.fabrica.coutinho.services.EspecialidadeService;
import com.fabrica.coutinho.vo.EspecialidadeVO;

@RestController
@RequestMapping("/especialidade")
public class EspecialidadeController {

	private final EspecialidadeService especialidadeService;
	private final PagedResourcesAssembler<EspecialidadeVO> assembler;
	
	@Autowired
	public EspecialidadeController(EspecialidadeService especialidadeService, PagedResourcesAssembler<EspecialidadeVO> assembler) {
		this.especialidadeService = especialidadeService;
		this.assembler = assembler;
	}
	
	@GetMapping(value = "/{id}", produces = {"application/json","application/xml","application/x-yaml"})
	public EspecialidadeVO findById(@PathVariable("id")  Integer id) {
		EspecialidadeVO especialidadeVO = especialidadeService.findById(id);
		especialidadeVO.add(linkTo(methodOn(EspecialidadeController.class).findById(id)).withSelfRel());
		return especialidadeVO;
	}
	
	@GetMapping(produces = {"application/json","application/xml","application/x-yaml"})
	public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page,limit, Sort.by(sortDirection,"nome"));
		
		Page<EspecialidadeVO> especialidades = especialidadeService.findAll(pageable);
		especialidades.stream().forEach(p -> p.add(linkTo(methodOn(EspecialidadeController.class).findById(p.getId())).withSelfRel()));
		
		PagedModel<EntityModel<EspecialidadeVO>> pagedModel = assembler.toModel(especialidades);
		
		return new ResponseEntity<>(pagedModel,HttpStatus.OK);
	}
	
	@PostMapping(produces = {"application/json","application/xml","application/x-yaml"}, 
			     consumes = {"application/json","application/xml","application/x-yaml"})
	public EspecialidadeVO create(@RequestBody EspecialidadeVO especialidadeVO) {
		EspecialidadeVO vo = especialidadeService.create(especialidadeVO);
		vo.add(linkTo(methodOn(EspecialidadeController.class).findById(vo.getId())).withSelfRel());
		return vo;
	}
	
	@PutMapping(produces = {"application/json","application/xml","application/x-yaml"}, 
		     consumes = {"application/json","application/xml","application/x-yaml"})
	public EspecialidadeVO update(@RequestBody EspecialidadeVO especialidadeVO) {
		EspecialidadeVO vo = especialidadeService.update(especialidadeVO);
		vo.add(linkTo(methodOn(EspecialidadeController.class).findById(especialidadeVO.getId())).withSelfRel());
		return vo;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id){
		especialidadeService.delete(id);
		return ResponseEntity.ok().build();
	}
}

