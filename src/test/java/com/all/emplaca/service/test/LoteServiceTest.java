package com.all.emplaca.service.test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.all.emplaca.repository.LoteRepository;
import com.all.emplaca.service.FabricanteService;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LoteServiceTest {
	@Autowired
	private LoteRepository loteRepository;
	@Autowired
 	private FabricanteService fabricanteService;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
 	
	@Test
	public void criarLoteDeBlanksShouldCreateLote() {
		
		
	}

}
