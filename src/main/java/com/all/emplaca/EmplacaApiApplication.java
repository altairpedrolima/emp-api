package com.all.emplaca;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger LOGGER = LoggerFactory
			.getLogger(EmplacaApiApplication.class);

	@Autowired
	FabricanteRepository fabricanteRepository;
		
	@Autowired
	LoteRepository loteRepository;
	
	@Autowired
	LoteService loteService;

	public static void main(String[] args) {
		SpringApplication.run(EmplacaApiApplication.class, args);
		LOGGER.debug("--Application Started--");
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.debug("Debugging log");
        LOGGER.info("Info log");
        LOGGER.warn("Hey, This is a warning!");
        LOGGER.error("Oops! We have an Error. OK");
        // LOGGER.fatal("Damn! Fatal error. Please fix me.");

	}
}
