package com.all.emplaca.entities;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.ResourceSupport;

import com.all.emplaca.controller.LoteRequest;
import com.all.emplaca.controller.LoteResource;

public class LoteResourceHateoas extends ResourceSupport	{

	private final Lote lote;
	private final Long id;
	private final Long fabricanteId;

	public LoteResourceHateoas(Lote lote) {
		this.lote = lote;
		this.id = lote.getId();
		this.fabricanteId = lote.getFabricante().getId();
		final LoteRequest loteRequest = new LoteRequest();

		/***
		 * add metodo herdado que adiciona um link
		 * linkTo cria o link
		 * methodOn obtém o uri
		 * withrel
		 */

        add(linkTo(LoteResource.class).withRel("lotes"));
        add(linkTo(methodOn(LoteResource.class).lote()).withRel("lotes"));
		add(linkTo(methodOn(LoteResource.class).lote(loteRequest)).withRel("loteCreate"));
        add(linkTo(methodOn(LoteResource.class).get(id)).withRel("lote"));
		add(linkTo(methodOn(LoteResource.class).getLotesByFabricanteId(fabricanteId)).withRel("lotesByFabricanteId"));
	}

	public Lote getLote() {
		return lote;
	}
	
	

}
