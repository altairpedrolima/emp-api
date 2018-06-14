package com.all.emplaca.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.all.emplaca.entities.Lote;
import com.all.emplaca.service.LoteService;

@RestController
@ExposesResourceFor(Lote.class)
@RequestMapping(value = "/api/lotes")
public class LoteResource {

	/***
	 * Referencias para HATEOAS:
	 * https://docs.spring.io/spring-hateoas/docs/0.24.0.RELEASE/reference/html/
	 * https://lankydanblog.com/2017/09/10/applying-hateoas-to-a-rest-api-with-spring-boot/
	 *
	 */

	@Autowired
	private LoteService loteService;

	// @Autowired
	// private EntityLinks entityLinks;

	@GetMapping
	public Resources<Resource<Lote>> findAll() {

		// TODO obter fabricante do header da requisição,
		// TODO verificar autenticação e autorização do fabricante

		// TODO validar entrada
		// TODO obter id do fabricante já cadastrado.

		Link selfLink = linkTo(methodOn(LoteResource.class).findAll()).withSelfRel();
		return lotesToResource(loteService.findAll(), selfLink);

	}

	@GetMapping("/{id}")
	public Resource<Lote> findById(@PathVariable Long id) {
		Lote lote = loteService.findById(id);
		return loteToResource(lote);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Resource<Lote> createLoteDeBlanks(@Valid @RequestBody LoteRequest loteRequest) {
		
		// TODO obter fabricante do header da requisição,
		// TODO verificar autenticação e autorização do fabricante

		// TODO validar entrada
		// Long idFabricante = 1l; //TODO obter id do fabricante já cadastrado.

		Lote lote = loteService.criarLoteDeBlanks(loteRequest);

		return loteToResource(lote);

	}

	// TODO implementar obtenção de lotes para fabricante

	@GetMapping("/fabricante/{id}")
	public Resources<Resource<Lote>> getLotesByFabricanteId(@PathVariable Long fabricanteId) {

		Link selfLink = linkTo(methodOn(LoteResource.class).getLotesByFabricanteId(fabricanteId)).withSelfRel();
		return lotesToResource(loteService.findByFabricanteId(fabricanteId), selfLink);

	}

	
	private Resource<Lote> loteSelf(Lote lote){
		URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}").buildAndExpand(lote.getId()).toUri();
		Link selfLink = new Link(uri.toString(), "self");
		return new Resource<>(lote, selfLink);
	}
	

	private Resource<Lote> loteToResource(Lote lote) {
		
		LoteRequest loteRequest = new LoteRequest();

		URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}").buildAndExpand(lote.getId()).toUri();
		Link selfLink = new Link(uri.toString(), "self");
		
		Link linkById = linkTo(methodOn(LoteResource.class).findById(lote.getId())).withRel("Por Id");
		Link findAll = linkTo(methodOn(LoteResource.class).findAll()).withRel("Lista Todos");
		Link create = linkTo(methodOn(LoteResource.class).createLoteDeBlanks(loteRequest)).withRel("Criar lote");
		Link getByFabricante = linkTo(methodOn(LoteResource.class).getLotesByFabricanteId(lote.getFabricante().getId())).withRel("Lotes por Fabricante");

		return new Resource<>(lote, selfLink, linkById, findAll, create, getByFabricante);

	}

	private Resources<Resource<Lote>> lotesToResource(List<Lote> lotes, Link selfLink) {


		LoteRequest loteRequest = new LoteRequest();
		Long id = 0l;
		
		Link linkById = linkTo(methodOn(LoteResource.class).findById(id)).withRel("Por Id");
		Link findAll = linkTo(methodOn(LoteResource.class).findAll()).withRel("Lista Todos");
		Link create = linkTo(methodOn(LoteResource.class).createLoteDeBlanks(loteRequest)).withRel("Criar lote");
		Link getByFabricante = linkTo(methodOn(LoteResource.class).getLotesByFabricanteId(id)).withRel("Lotes por Fabricante");
		
		List<Resource<Lote>> loteResources = lotes.stream().map(lote -> loteSelf(lote))
				.collect(Collectors.toList());
		
		return new Resources<>(loteResources, selfLink, linkById, findAll, create, getByFabricante);

	}

}
