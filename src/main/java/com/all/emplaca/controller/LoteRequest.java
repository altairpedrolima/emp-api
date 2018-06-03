package com.all.emplaca.controller;

import javax.validation.constraints.NotNull;

public class LoteRequest {
	
	@NotNull
	private Long fabricanteId;
	
	private Long quantidadeDeBlanks;

	public Long getFabricanteId() {
		return fabricanteId;
	}

	public void setFabricanteId(Long fabricanteId) {
		this.fabricanteId = fabricanteId;
	}

	public Long getQuantidadeDeBlanks() {
		return quantidadeDeBlanks;
	}

	public void setQuantidadeDeBlanks(Long quantidadeDeBlanks) {
		this.quantidadeDeBlanks = quantidadeDeBlanks;
	}
	
	
}
