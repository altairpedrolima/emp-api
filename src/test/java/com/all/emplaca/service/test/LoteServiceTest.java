package com.all.emplaca.service.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.all.emplaca.builder.test.LoteRequestTestBuilder;
import com.all.emplaca.controller.LoteRequest;
import com.all.emplaca.entities.Fabricante;
import com.all.emplaca.entities.Lote;
import com.all.emplaca.enums.EstadoLote;
import com.all.emplaca.repository.LoteRepository;
import com.all.emplaca.service.FabricanteService;
import com.all.emplaca.service.LoteService;
import com.all.emplaca.service.utils.GeradorNumeroLote;

@RunWith(SpringRunner.class)
// @DataJpaTest
public class LoteServiceTest {

	private static final Long LOTE_ID = 100L;
	private static final Long FABRICANTE_ID = 105L;
	private static final Long QUANTIDADE_BLANKS = 70L;
	private static final String NUMERO_LOTE = "0000123352222255222225425564532157532";

	@TestConfiguration
	static class LoteServiceImplTestContextConfiguration {
		
		@Bean
		public LoteService loteService() {
			return new LoteService();
		}
		
		@Bean
		public FabricanteService fabricanteService() {
			return new FabricanteService();
		}
		
		@Bean
		public GeradorNumeroLote geradorNumeroLote() {
			return new GeradorNumeroLote();
		}
		
	}

	@Autowired
	private LoteService loteService;
	
	@MockBean
	private FabricanteService fabricanteService;
	
	@MockBean
    private LoteRepository loteRepository;
	
	@MockBean
	private GeradorNumeroLote geradorNumeroLote;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private Fabricante fabricante;
	
	private Lote loteSalvo;

	private LoteRequest loteRequest;


	@Before
	public void setUp() {

		fabricante = new Fabricante();
		
		fabricante.setId(FABRICANTE_ID);
		fabricante.setCnpj("10746245000116");
		fabricante.setNome("Blanks Quotation Marks S/A");
		
		loteSalvo = new Lote();
		loteSalvo.setId(LOTE_ID);
		loteSalvo.setNumeroLote(NUMERO_LOTE);;
		loteSalvo.setQuantidadeDeBlanksSolicitados(QUANTIDADE_BLANKS);
		loteSalvo.setEstadoLote(EstadoLote.SOLICITADO);
		loteSalvo.setFabricante(fabricante);

		LoteRequestTestBuilder builderRequest = new LoteRequestTestBuilder();

		loteRequest = builderRequest.withFabricanteId(FABRICANTE_ID).withquantidadeDeBlanks(QUANTIDADE_BLANKS).Build();

	}

	@Test
	public void criarLoteDeBlanksShouldCreateLote() {

  		/*Mockito.when(fabricanteService.fabricanteById(loteRequest))
  		.thenReturn(fabricante);*/
  		
  		//loteRepository.save(lote)
  		
  		// Lote lote = new Lote();
  		
  		/*Mockito
  		.when(loteRepository.save(lote))
  		.thenReturn(loteSalvo);*/
  			
		Lote lote = this.loteService.criarLoteDeBlanks(loteRequest);

		// assertThat(lote.getLoteId()).isNotNull();
		assertThat(lote.getFabricante().getId()).isEqualTo(FABRICANTE_ID);
		assertThat(lote.getQuantidadeDeBlanksSolicitados()).isEqualTo(QUANTIDADE_BLANKS);

	}

	@Test
	public void criarLoteDeBlanksWenFAbricanteIsNullShouldThrowConstraintViolationException() {
		
		LoteRequestTestBuilder builderRequest = new LoteRequestTestBuilder();
		loteRequest = builderRequest.withFabricanteId(null).withquantidadeDeBlanks(QUANTIDADE_BLANKS).Build();
		
		thrown.expect(MethodArgumentNotValidException.class);
		thrown.expectMessage("Erro em Validação de Campo");
		this.loteService.criarLoteDeBlanks(loteRequest);

	}

}
