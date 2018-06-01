package com.all.emplaca.entities;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.ResourceSupport;

import com.all.emplaca.controller.LoteResource;

public class LoteResourceSuport extends ResourceSupport	{

	private final Lote lote;

	public LoteResourceSuport(Lote lote) {
		this.lote = lote;
		final Long id = lote.getId();
		add(linkTo(LoteResource.class).withRel("lotes"));
		add(linkTo(methodOn(LoteResource.class).get(id)).withSelfRel());

	}

	public Lote getLote() {
		return lote;
	}
	
	

}
