package com.all.emplaca.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.all.emplaca.entities.Lote;
import com.all.emplaca.entities.LoteResourceSuport;
import com.all.emplaca.service.LoteService;

@RestController
@RequestMapping(value = "/api/lotes")
public class LoteResource {

	@Autowired
	LoteService loteService;

	@GetMapping
	public List<Lote> lote() {

		// TODO obter fabricante do header da requisição,
		// TODO verificar autenticação e autorização do fabricante

		// TODO validar entrada
		// Long idFabricante = 1l; //TODO obter id do fabricante já cadastrado.
		List<Lote> lotes = loteService.findAll();

		return lotes;

	}

	@PostMapping
	public ResponseEntity<?> lote(@Valid @RequestBody LoteRequest loteRequest) {

		// TODO obter fabricante do header da requisição,
		// TODO verificar autenticação e autorização do fabricante

		// TODO validar entrada
		// Long idFabricante = 1l; //TODO obter id do fabricante já cadastrado.
		Lote lote = loteService.criarLoteDeBlanks(loteRequest);
		
		final URI uri =
		        MvcUriComponentsBuilder.fromController(getClass())
		            .path("/{id}")
		            .buildAndExpand(lote.getId())
		.toUri(); 

		//return ResponseEntity.status(HttpStatus.CREATED).body(lote);
		
		return ResponseEntity.created(uri).body(new LoteResourceSuport(lote));

	}

	@GetMapping("/{id}")
	public ResponseEntity<LoteResourceSuport> get(Long id) {
		return loteService
		        .findById(id)
		        .map(lote -> ResponseEntity.ok(new LoteResourceSuport(lote)))
		.orElseThrow(() -> new ObjectNotFoundException(null,"Lote nao encontrado"));
	}
	
	@GetMapping("/{id}/fabricante/{fabricanteId}")
	public ResponseEntity<Resources<LoteResourceSuport>> getLotes(final Long fabricanteId) {
		final List<LoteResourceSuport> collection =
				loteService.findByFabricanteId(fabricanteId)
				.stream()
				.map(LoteResourceSuport::new).collect(Collectors.toList());
		final Resources<LoteResourceSuport> resources = new Resources<>(collection);
		final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
		
		resources.add(new Link(uriString, "self"));
		return ResponseEntity.ok(resources);
	}

}
