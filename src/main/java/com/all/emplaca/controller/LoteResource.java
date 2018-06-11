package com.all.emplaca.controller;

import com.all.emplaca.entities.Lote;
import com.all.emplaca.entities.LoteResourceHateoas;
import com.all.emplaca.service.LoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
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
	
	@GetMapping
	public ResponseEntity<Resources<LoteResourceHateoas>> lote() {

		List<LoteResourceHateoas> lotesResourceHateoas =  loteService.findAll()
				.stream()
				.map(LoteResourceHateoas::new)
				.collect(Collectors.toList());

		Resources<LoteResourceHateoas> resourceDeLotes = new Resources<>(lotesResourceHateoas);

        String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();

        resourceDeLotes.add(new Link(uriString,"self"));


		// TODO obter fabricante do header da requisição,
		// TODO verificar autenticação e autorização do fabricante

		// TODO validar entrada
		// TODO obter id do fabricante já cadastrado.

		        		
		return ResponseEntity.ok(resourceDeLotes);

	}

	@GetMapping("/{id}")
    public ResponseEntity<LoteResourceHateoas> get(Long id){

	    Lote lote = loteService.getBiId(id);

	    return ResponseEntity.ok(new LoteResourceHateoas(lote));
    }


	@PostMapping
	public ResponseEntity<LoteResourceHateoas> lote(@Valid @RequestBody LoteRequest loteRequest) {

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

        LoteResourceHateoas loteResourceHateoas = new LoteResourceHateoas(lote);

        loteResourceHateoas.add(new Link(uri.toString(),"self"));


        return ResponseEntity.created(uri).body(loteResourceHateoas);

	}

	//TODO implementar obtenção de lotes para fabricante

    @GetMapping("/lotes/fabricante/{id}")
    public ResponseEntity<Resources<LoteResourceHateoas>> getLotesByFabricanteId(final Long fabricanteId) {


        final List<LoteResourceHateoas> collection =
                loteService.findByFabricanteId(fabricanteId).stream()
                        .map(LoteResourceHateoas::new).collect(Collectors.toList());
        final Resources<LoteResourceHateoas> resources = new Resources<>(collection);
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();

        resources.add(new Link(uriString, "self"));
        return ResponseEntity.ok(resources);
    }

}
