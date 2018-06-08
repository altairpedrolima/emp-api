package com.all.emplaca.builder.test;

import com.all.emplaca.controller.LoteRequest;

public class LoteRequestTestBuilder {
	private LoteRequest _loteRequest;

	public LoteRequestTestBuilder() {
		_loteRequest = new LoteRequest();
	}

	public LoteRequestTestBuilder Default() {
		_loteRequest.setFabricanteId(1L);
		_loteRequest.setQuantidadeDeBlanks(10L);

		return this;
	}

	public LoteRequestTestBuilder withFabricanteId(Long fabricanteId) {
		_loteRequest.setFabricanteId(fabricanteId);
		return this;
	}

	public LoteRequestTestBuilder withquantidadeDeBlanks(Long quantidadeDeBlanks) {
		_loteRequest.setQuantidadeDeBlanks(quantidadeDeBlanks);
		return this;
	}

	public LoteRequest Build() {
		return _loteRequest;
	}

}
