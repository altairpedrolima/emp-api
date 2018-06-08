package com.all.emplaca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.all.emplaca.controller.LoteRequest;
import com.all.emplaca.entities.Fabricante;
import com.all.emplaca.entities.Lote;
import com.all.emplaca.enums.EstadoLote;
import com.all.emplaca.exception.DataIntegrityException;
import com.all.emplaca.repository.LoteRepository;
import com.all.emplaca.service.utils.GeradorNumeroLote;

@Service
public class LoteService {

	@Autowired
	private LoteRepository loteRepository;

	@Autowired
	FabricanteService fabricanteService;

	public Lote criarLoteDeBlanks(LoteRequest loteRequest) {
				
		Fabricante fabricante = fabricanteService.fabricanteById(loteRequest);;

		Long quotaDeBlanks = fabricante.getQuotaDeBlanks();
		
		Optional<Long> quotaOptional = Optional.ofNullable(quotaDeBlanks);
		
		if(!quotaOptional.isPresent()) {
			throw new DataIntegrityException(
					"Quota de lotes para fabricante está vazia.");		
		}

		if (loteRequest.getQuantidadeDeBlanks() > quotaDeBlanks) {
			throw new DataIntegrityException(
					"Quantidade de blanks solicitado excede quota de blanks disponível para o fabricante.");
		}

		String numeroDoLote = GeradorNumeroLote.gerarNumeroLotePara(fabricante.getId()).toString();
		Long quantidadeDeBlanksSolicitados = loteRequest.getQuantidadeDeBlanks();
		EstadoLote estadoDoLote = EstadoLote.SOLICITADO;

		// TODO tratar quota de quantidade de blanks por fabricante
		Lote lote = new Lote(quantidadeDeBlanksSolicitados, fabricante, numeroDoLote, estadoDoLote);

		// TODO iniciar processo de criação de blanks

		return loteRepository.save(lote);
	}

	public List<Lote> findAll() {
		List<Lote> lotes = loteRepository.findAll();
		return lotes;
	}

}
