package com.all.emplaca;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.all.emplaca.entities.Fabricante;
import com.all.emplaca.entities.Lote;
import com.all.emplaca.enums.EstadoLote;
import com.all.emplaca.repository.FabricanteRepository;
import com.all.emplaca.repository.LoteRepository;
import com.all.emplaca.service.LoteService;
import com.all.emplaca.service.utils.GeradorNumeroLote;

@SpringBootApplication
public class EmplacaApiApplication implements CommandLineRunner{
	private static final Logger logger = 
			LogManager.getLogger(EmplacaApiApplication.class);
	@Autowired
	FabricanteRepository fabricanteRepository;
		
	@Autowired
	LoteRepository loteRepository;
	
	@Autowired
	LoteService loteService;

	public static void main(String[] args) {
		SpringApplication.run(EmplacaApiApplication.class, args);
		logger.debug("--Application Started--");
	}

	@Override
	public void run(String... args) throws Exception {
		logger.debug("Debugging log");
        logger.info("Info log");
        logger.warn("Hey, This is a warning!");
        logger.error("Oops! We have an Error. OK");
        logger.fatal("Damn! Fatal error. Please fix me.");
		
		loteRepository.deleteAllInBatch();
		fabricanteRepository.deleteAllInBatch();
		Fabricante fabricante = new Fabricante();
		fabricante.setCnpj("10746245000116");
		fabricante.setNome("Snow Blanks S/A");
		fabricanteRepository.save(fabricante);
		
		Lote lote = new Lote();
		lote.setNumeroLote(GeradorNumeroLote.gerarNumeroLotePara(fabricante.getId()));
		lote.setQuantidadeDeBlanksSolicitados(50l);
		lote.setEstadoLote(EstadoLote.SOLICITADO);
		lote.setFabricante(fabricante);
		// loteService.save(lote);
		loteRepository.save(lote);

		
		
	}
}
