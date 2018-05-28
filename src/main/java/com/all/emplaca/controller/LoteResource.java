package com.all.emplaca.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.all.emplaca.dto.PedidoLoteDto;
import com.all.emplaca.entities.Fabricante;
import com.all.emplaca.entities.Lote;
import com.all.emplaca.response.Response;
import com.all.emplaca.service.FabricanteService;
import com.all.emplaca.service.LoteService;

@RestController
@RequestMapping("/api/{fabricanteId}/lotes")
public class LoteResource {

	private final FabricanteService fabricanteService;
	private final LoteService loteService;

	@Autowired
	public LoteResource(FabricanteService fabricanteService, LoteService loteService) {
		this.fabricanteService = fabricanteService;
		this.loteService = loteService;
	}

	@PostMapping
	ResponseEntity<Response<Long>> add(@PathVariable Long fabricanteId, @RequestBody PedidoLoteDto input) {
		Fabricante fabricante = this.fabricanteService.findById(fabricanteId);
		
		Lote lote = new Lote();
		lote.setCodigo("123");
		lote.setFabricante(fabricante);
		lote.setQuantidadeBlanksSolicitados(input.getQuantidade());
		
		this.fabricanteService.findById(fabricanteId).getLotes().add(lote);
		

		return null;

	}

	private void getdateUser(Long userId) {
		this.fabricanteService.findById(userId);
	}

}
