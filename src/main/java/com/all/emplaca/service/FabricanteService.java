package com.all.emplaca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.all.emplaca.controller.LoteRequest;
import com.all.emplaca.entities.Fabricante;
import com.all.emplaca.exception.ResourceNotFoundException;
import com.all.emplaca.repository.FabricanteRepository;

@Service
public class FabricanteService {

	@Autowired
	FabricanteRepository fabricanteRepository;

	public Fabricante fabricanteByLoteRequest(LoteRequest loteRequest) {
		Long id = loteRequest.getFabricanteId();
		return fabricanteById(id);		
	}
	
	public Fabricante fabricanteById(Long id) {
		return fabricanteRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Fabricante não encontrado para Id: " + id));
	}
	
}
