package com.all.emplaca.controller;

import javax.validation.Valid;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.all.emplaca.entities.Lote;
import com.all.emplaca.entities.LoteResourceSuport;
import com.all.emplaca.service.LoteService;

@RestController
@RequestMapping(value = "/api")
public class LoteResource {

	@Autowired
	LoteService loteService;
	
	 @GetMapping("/{id}")
	  public ResponseEntity<LoteResourceSuport> get(@PathVariable final long id) {
	    return loteService
	        .findById(id)
	        .map(l -> ResponseEntity.ok(new LoteResourceSuport(l)))
	        .orElseThrow(() -> new ObjectNotFoundException(id, LoteResourceSuport.class.getName()));
	}
	
	@PostMapping
	@RequestMapping(value = "/lotes")
	public ResponseEntity<?> lote(@Valid @RequestBody LoteRequest loteRequest)  {

		// TODO obter fabricante do header da requisição,
		// TODO verificar autenticação e autorização do fabricante

		// TODO validar entrada
		// Long idFabricante = 1l; //TODO obter id do fabricante já cadastrado.
		Lote lote = loteService.criarLoteDeBlanks(loteRequest);
		        		
		return ResponseEntity.status(HttpStatus.CREATED).body(lote);

	}

}
