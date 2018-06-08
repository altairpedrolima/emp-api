package com.all.emplaca.controller;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class LoteRequest {
	
	@NotNull(message="fabricanteId não pode ser nulo")
	@Min(value = 1L, message = "fabricanteId inválido")
	private Long fabricanteId;
	
	@NotNull(message="quantidadeDeBlanks Nao pode ser nula")
	@Min(value = 1L, message = "quandidadeDeBlanks deve ser maior que zero")
	@Max(value=500, message="quantidade de blanks solicitados excede limite definido")
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
