package com.all.emplaca.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.all.emplaca.controller.LoteRequest;
import com.all.emplaca.entities.Fabricante;
import com.all.emplaca.entities.Lote;
import com.all.emplaca.repository.LoteRepository;

@Service
public class LoteService {

	private static final Logger LOGGER = 
			LoggerFactory.getLogger(LoteService.class);
	
	
	@Autowired
	private LoteRepository loteRepository;

	@Autowired
	FabricanteService fabricanteService;

	public Lote criarLoteDeBlanks(LoteRequest loteRequest) {
		
		if(!(loteRequest.quantidaDeBlanks.longValue() <= 0l)) {
			throw new RuntimeException("Quantidade inválida");
		}
	
		LOGGER.info("LoteRequest: " + loteRequest.toString());
		System.out.println("LoteRequest: " + loteRequest.toString());

		Fabricante fabricante = fabricanteService.fabricanteByLoteRequest(loteRequest);
		
		if (fabricante.getCnpj().equals("10746245000116")){
			throw new RuntimeException("CNPJ invalido");
		}
		
		LOGGER.debug("$$$$$######$$$  Debugando aplicação");
		LOGGER.info("Info aplicação");
		LOGGER.trace("Traçando aplicação");
		

		// TODO tratar quota de quantidade de blanks por fabricante
		Lote lote = new Lote(loteRequest.quantidaDeBlanks, fabricante);

		// TODO gerar numero do lote
		// TODO iniciar processo de criação de blanks
		// TODO responder com o numero do lote

		return loteRepository.save(lote);
	}

	public Optional<Lote> findById(Long id) {
		Optional<Lote> lote = this.loteRepository.findById(id);
		return lote;
	}

	public Lote save(Lote lote) {
		return this.loteRepository.save(lote);
	}

}
