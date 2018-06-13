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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

		return loteToResource(loteService.findAll(), selfLink);

	}

	@GetMapping("/{id}")
	public Resource<Lote> getById(Long id) {
		Lote lote = loteService.getBiId(id);

		URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}").buildAndExpand(lote.getId()).toUri();
		Link selfLink = new Link(uri.toString(), "self");

		return loteToResource(lote, selfLink);
	}

	@PostMapping
	public Resource<Lote> createLoteOfBlanks(@Valid @RequestBody LoteRequest loteRequest) {

		// TODO obter fabricante do header da requisição,
		// TODO verificar autenticação e autorização do fabricante

		// TODO validar entrada
		// Long idFabricante = 1l; //TODO obter id do fabricante já cadastrado.

		Lote lote = loteService.criarLoteDeBlanks(loteRequest);

		URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}").buildAndExpand(lote.getId()).toUri();

		Link selfLink = new Link(uri.toString(), "self");

		return loteToResource(lote, selfLink);

	}

	// TODO implementar obtenção de lotes para fabricante

	@GetMapping("/fabricante/{id}")
	public Resources<Resource<Lote>> getLotesByFabricanteId(final Long fabricanteId) {

		Link selfLink = linkTo(methodOn(LoteResource.class).getLotesByFabricanteId(fabricanteId)).withSelfRel();

		return loteToResource(loteService.findByFabricanteId(fabricanteId), selfLink);
	}

	
	private Resource<Lote> loteToResource(Lote lote) {
		Link linkById = linkTo(methodOn(LoteResource.class).getById(lote.getId())).withSelfRel();

		URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}").buildAndExpand(lote.getId()).toUri();

		Link selfLink = new Link(uri.toString(), "self");

		// Link allInvoiceLink =
		// entityLinks.linkToCollectionResource(Invoice.class).withRel("all-invoices");
		// Link invoiceLink =
		// linkTo(methodOn(LoteResouce.class).getInvoiceByCustomerId(customer.getId())).withRel("invoice");

		return new Resource<>(lote, linkById, selfLink);

	}

	private Resource<Lote> loteToResource(Lote lote, Link selfLink) {
		Link linkById = linkTo(methodOn(LoteResource.class).getById(lote.getId())).withSelfRel();

		// Link allInvoiceLink =
		// entityLinks.linkToCollectionResource(Invoice.class).withRel("all-invoices");
		// Link invoiceLink =
		// linkTo(methodOn(LoteResouce.class).getInvoiceByCustomerId(customer.getId())).withRel("invoice");

		return new Resource<>(lote, selfLink, linkById);

	}

	private Resources<Resource<Lote>> loteToResource(List<Lote> lotes, Link selfLink) {

		// Link self = linkTo(methodOn(LoteResource.class).findAll()).withSelfRel();

		List<Resource<Lote>> loteResources = lotes.stream().map(lote -> loteToResource(lote))
				.collect(Collectors.toList());

		return new Resources<>(loteResources, selfLink);

	}

}
