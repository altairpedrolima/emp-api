package com.all.emplaca.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.all.emplaca.controller.LoteRequest;
import com.all.emplaca.entities.Fabricante;
import com.all.emplaca.entities.Lote;
import com.all.emplaca.enums.EstadoLote;
import com.all.emplaca.exception.ResourceNotFoundException;
import com.all.emplaca.repository.LoteRepository;
import com.all.emplaca.service.utils.GeradorNumeroLote;

@Service
public class LoteService {

	@Autowired
	private LoteRepository loteRepository;

	@Autowired
	FabricanteService fabricanteService;

	public Lote criarLoteDeBlanks(LoteRequest loteRequest) {

		Fabricante fabricante = fabricanteService.fabricanteById(loteRequest.getFabricanteId());

		Lote lote = gerarLotePara(fabricante, loteRequest.getQuantidadeDeBlanks());

		// TODO tratar quota de quantidade de blanks por fabricante
		// Lote lote = new Lote(loteRequest.getQuantidadeDeBlanks(), fabricante,
		// numeroLote, EstadoLote.SOLICITADO);

		// TODO gerar numero do lote
		// TODO iniciar processo de criação de blanks
		// TODO responder com o numero do lote

		return loteRepository.save(lote);
	}

	public List<Lote> findAll() {
		
		List<Lote> lotes = loteRepository.findAll();
		
		return Optional.ofNullable(lotes)
				.map(List::stream)
				.orElseThrow(() -> new ResourceNotFoundException("Lista de lotes não encontrada"))
				.collect(Collectors.toList());
	}

	public Lote findById(Long id) {
		return loteRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Lote não encontrado para o Id: " + id));
	}

	public Lote gerarLotePara(Fabricante fabricante, Long quantidade) {
		Lote lote = new Lote();
		lote.setNumeroLote(GeradorNumeroLote.gerarNumeroLotePara(fabricante.getId()));
		lote.setQuantidadeDeBlanksSolicitados(quantidade);
		lote.setEstadoLote(EstadoLote.SOLICITADO);
		lote.setFabricante(fabricante);
		lote.setDataHoraRegistro(LocalDateTime.now());
		return lote;
	}

	public List<Lote> findByFabricanteId(Long fabricanteId) {
		return loteRepository
				.findByFabricanteId(fabricanteId)
				.orElseThrow(() -> new ResourceNotFoundException("Lotes não encontrados para fabricante com id: " + fabricanteId));
		}

}
