package com.all.emplaca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.all.emplaca.entities.Lote;
import com.all.emplaca.repository.LoteRepository;

@Service
public class LoteService {
	
	@Autowired
	private LoteRepository loteRepository;
	
	public void criarLote(Lote lote) {		
		loteRepository.save(lote);
		
	}

}
