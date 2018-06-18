package com.all.emplaca.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.all.emplaca.entities.Blank;
import com.all.emplaca.entities.Lote;
import com.all.emplaca.repository.BlankRepository;
import com.all.emplaca.service.utils.GeradorDeSerialDeBlanks;


@Service
public class BlankService {

	@Autowired
	private BlankRepository blankRepository;
	
	public Blank createBlank (Lote lote) {
		Blank blank = new Blank(GeradorDeSerialDeBlanks.gerarNumeroSerial(lote.getFabricante().getId()));
		return blankRepository.save(blank);
	}
		
	
}
