package com.all.emplaca.controller;

import javax.validation.constraints.NotNull;

public class LoteRequest {
	
	@NotNull
	public Long idFabricante;
	
	public Long quantidaDeBlanks;

}
