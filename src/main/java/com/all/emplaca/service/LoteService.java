package com.all.emplaca.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.all.emplaca.controller.LoteRequest;
import com.all.emplaca.entities.Blank;
import com.all.emplaca.entities.Fabricante;
import com.all.emplaca.entities.Lote;
import com.all.emplaca.enums.EstadoLote;
import com.all.emplaca.exception.DataIntegrityException;
import com.all.emplaca.repository.LoteRepository;
import com.all.emplaca.service.utils.GeradorDeSerialDeBlanks;
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
		Lote lote = new Lote(quantidadeDeBlanksSolicitados, fabricante, numeroDoLote, estadoDoLote, LocalDateTime.now());

		// TODO iniciar processo de criação de blanks
		
		List<Blank> blanks = new ArrayList<Blank>();
		for (int i = 1; i <= quantidadeDeBlanksSolicitados; i++) {
			blanks.add(new Blank(GeradorDeSerialDeBlanks.gerarNumeroSerial(fabricante.getId())));
		}
		
		lote.setBlanks(blanks);
		
		return loteRepository.save(lote);
		
		// TODO iniciar processo de geração de qrCode de blanks
		
	}

	public List<Lote> findAll() {
		List<Lote> lotes = loteRepository.findAll();
		return lotes;
	}

	public Lote findById(Long id){

		return Optional
                .of(loteRepository.findById(id))
                .orElseThrow(ResourceNotFoundException::new).get();

	}

    public List<Lote> findByFabricanteId(Long fabricanteId) {
        return Optional.of(loteRepository.findByFabricanteId(fabricanteId))
                .orElseThrow(ResourceNotFoundException::new).get();
    }



}
