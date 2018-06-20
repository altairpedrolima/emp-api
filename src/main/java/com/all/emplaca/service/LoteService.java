package com.all.emplaca.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.all.emplaca.controller.LoteRequest;
import com.all.emplaca.entities.Blank;
import com.all.emplaca.entities.Fabricante;
import com.all.emplaca.entities.Lote;
import com.all.emplaca.enums.EstadoLote;
import com.all.emplaca.exception.DataIntegrityException;
import com.all.emplaca.repository.BlankRepository;
import com.all.emplaca.repository.LoteRepository;
import com.all.emplaca.service.utils.GeradorNumeroLote;

@Service
public class LoteService {

	@Autowired
	private LoteRepository loteRepository;

	@Autowired
	FabricanteService fabricanteService;

	@Autowired
	private BlankRepository blankRepository;

	private Long quantidadeDeBlanksSolicitados;

	private Fabricante fabricante;

	public Lote criarLoteDeBlanks(LoteRequest loteRequest) {

		fabricante = fabricanteService.fabricanteById(loteRequest);

		Long quotaDeBlanks = fabricante.getQuotaDeBlanks();

		Optional<Long> quotaOptional = Optional.ofNullable(quotaDeBlanks);

		if (!quotaOptional.isPresent()) {
			throw new DataIntegrityException("Quota de lotes para fabricante está vazia.");
		}

		if (loteRequest.getQuantidadeDeBlanks() > quotaDeBlanks) {
			throw new DataIntegrityException(
					"Quantidade de blanks solicitado excede quota de blanks disponível para o fabricante.");
		}

		String numeroDoLote = GeradorNumeroLote.gerarNumeroLotePara(fabricante.getId()).toString();
		quantidadeDeBlanksSolicitados = loteRequest.getQuantidadeDeBlanks();
		EstadoLote estadoDoLote = EstadoLote.SOLICITADO;

		// TODO tratar quota de quantidade de blanks por fabricante
		Lote lote = new Lote(quantidadeDeBlanksSolicitados, fabricante, numeroDoLote, estadoDoLote,
				LocalDateTime.now());

		// TODO iniciar processo de criação de blanks

		lote.setBlanks(listaDeBlanksDoLote());

		return loteRepository.save(lote);

		// TODO iniciar processo de geração de qrCode de blanks

	}

	private List<Blank> listaDeBlanksDoLote() {
		List<Blank> blanks = new ArrayList<Blank>();

		for (int i = 1; i <= quantidadeDeBlanksSolicitados; i++) {
			blanks.add(blankComSerial());
		}
		
		return blanks;
	}

	public Blank blankComSerial() {


		if (this.fabricante.getId() > 99) {
			throw new DataIntegrityException("Não é possível gerar serial para fabricante com id maior que 99.");
		}

		String strAnoFull = Integer.toString(LocalDate.now().getYear());

		String strAno = strAnoFull.substring(strAnoFull.length() - 2);
		String strMes = Integer.toString(LocalDate.now().getMonthValue());

		String strFabricante = StringUtils.leftPad(Long.toString(fabricante.getId()), 2, '0');

		Long sequenceSerial = blankRepository.GetNextValOfSerialBlankSeq();
		String strSequencial = StringUtils.leftPad(Long.toString(sequenceSerial), 9, '0');


		StringBuilder serial = new StringBuilder();
		serial.append(strAno).append(strMes).append(strFabricante).append(strSequencial);

		return new Blank(serial.toString());

	}

	public List<Lote> findAll() {
		List<Lote> lotes = loteRepository.findAll();
		return lotes;
	}

	public Lote findById(Long id) {

		return Optional.of(loteRepository.findById(id)).orElseThrow(ResourceNotFoundException::new).get();

	}

	public List<Lote> findByFabricanteId(Long fabricanteId) {
		return Optional.of(loteRepository.findByFabricanteId(fabricanteId)).orElseThrow(ResourceNotFoundException::new)
				.get();
	}

}
