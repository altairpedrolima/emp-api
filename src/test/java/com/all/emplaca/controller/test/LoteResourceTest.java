package com.all.emplaca.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.all.emplaca.builder.test.LoteRequestTestBuilder;
import com.all.emplaca.controller.LoteRequest;
import com.all.emplaca.entities.Fabricante;
import com.all.emplaca.repository.FabricanteRepository;
import com.all.emplaca.repository.LoteRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class LoteResourceTest {

	private static final Long QUANTIDADE_BLANKS = 70L;
	private static final Long FABRICANTE_ID = 1L;
	private static final Long LIMITE_DE_BLANKS = 500L;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private LoteRepository loteRepository;
	
	@Autowired
	private FabricanteRepository FabricanteRepository;

	@Autowired
	ObjectMapper objectMapper;
	
	private Fabricante fabricanteLegado;

	@Before
	public void deleteAllBeforeTests() throws Exception {
		loteRepository.deleteAll();
				
	}

	@Test
	public void shouldCreateEntity() throws Exception {

		LoteRequestTestBuilder builderRequest = new LoteRequestTestBuilder();

		LoteRequest loteRequest = builderRequest.withFabricanteId(1L).withquantidadeDeBlanks(10L).Build();

		String request = toJson(loteRequest);

		mockMvc.perform(post("/lotes").content(request).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.quantidadeDeBlanksSolicitados").value("10"));
	}

	@Ignore
	@Test
	public void shouldReturnRepositoryIndex() throws Exception {

		LoteRequestTestBuilder builderRequest = new LoteRequestTestBuilder();

		LoteRequest loteRequest = builderRequest.withFabricanteId(1L).withquantidadeDeBlanks(10L).Build();

		mockMvc.perform(post("/lotes").content(toJson(loteRequest)).contentType(MediaType.APPLICATION_JSON));

		mockMvc.perform(get("/lotes")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$[0]['loteId']").value("1"));
	}

	@Test
	public void criarLoteDeBlanksWenFabricanteIsNullShouldThrowConstraintViolationException() throws Exception {

		LoteRequestTestBuilder builderRequest = new LoteRequestTestBuilder();

		LoteRequest loteRequest = builderRequest.withFabricanteId(null).withquantidadeDeBlanks(QUANTIDADE_BLANKS)
				.Build();

		String request = toJson(loteRequest);

		mockMvc.perform(post("/lotes").content(request).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.fieldMessage").value("fabricanteId não pode ser nulo"))
				.andExpect(jsonPath("$.title").value("Erro em Validação de Campo"));

	}

	@Test
	public void criarLoteDeBlanksWenQuantidadeIsZeroShouldThrowConstraintViolationException() throws Exception {

		LoteRequestTestBuilder builderRequest = new LoteRequestTestBuilder();

		LoteRequest loteRequest = builderRequest.withFabricanteId(FABRICANTE_ID).withquantidadeDeBlanks(0L).Build();

		String request = toJson(loteRequest);

		mockMvc.perform(post("/lotes").content(request).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.title").value("Erro em Validação de Campo"))
				.andExpect(jsonPath("$.fieldMessage").value("quandidadeDeBlanks deve ser maior que zero"));

	}

	@Test
	public void criarLoteDeBlanksWenQuantidadeIsMaiorQueLimiteShouldThrowConstraintViolationException()
			throws Exception {

		LoteRequestTestBuilder builderRequest = new LoteRequestTestBuilder();

		LoteRequest loteRequest = builderRequest.withFabricanteId(FABRICANTE_ID)
				.withquantidadeDeBlanks(LIMITE_DE_BLANKS + 10).Build();

		String request = toJson(loteRequest);

		mockMvc.perform(post("/lotes").content(request).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.title").value("Erro em Validação de Campo"))
				.andExpect(jsonPath("$.fieldMessage").value("quantidade de blanks solicitados excede limite definido"));

	}

	@Test
	public void criarLoteDeBlanksWenQuantidadeIsMaiorQueQuotaShouldThrowValidationStandardException() throws Exception {
		
		fabricanteLegado = new Fabricante("Fabricante", "21584949000156", false, 50l);
		FabricanteRepository.save(fabricanteLegado);
		
		LoteRequestTestBuilder builderRequest = new LoteRequestTestBuilder();

		LoteRequest loteRequest = builderRequest.withFabricanteId(fabricanteLegado.getId())
				.withquantidadeDeBlanks(QUANTIDADE_BLANKS).Build();

		String request = toJson(loteRequest);

		mockMvc.perform(post("/lotes").content(request).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.title").value("Erro em Validação de Campo"))
				.andExpect(jsonPath("$.fieldMessage").value("Quantidade de blanks solicitado excede quota de blanks disponível para o fabricante."));

	}

	private String toJson(LoteRequest loteRequest) throws JsonProcessingException {
		return objectMapper.writeValueAsString(loteRequest);
	}
}
