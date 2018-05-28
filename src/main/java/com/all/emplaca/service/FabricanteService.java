package com.all.emplaca.service;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.all.emplaca.entities.Fabricante;
import com.all.emplaca.repository.FabricanteRepository;

@Service
public class FabricanteService {

	@Autowired
	FabricanteRepository fabricanteRepository;

	public Fabricante findByCnpj(String cnpj) {
		Optional<Fabricante> obj = fabricanteRepository.findByCnpj(cnpj);
		return obj.orElseThrow(() -> new ObjectNotFoundException(cnpj, Fabricante.class.getName()));
	}
	
	public Fabricante findById(Long id) {
		Optional<Fabricante> obj = fabricanteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(id, Fabricante.class.getName()));
	}
	

}
