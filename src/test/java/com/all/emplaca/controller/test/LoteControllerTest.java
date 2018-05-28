package com.all.emplaca.controller.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.all.emplaca.dto.PedidoLoteDto;
import com.all.emplaca.entities.Fabricante;
import com.all.emplaca.repository.FabricanteRepository;
import com.all.emplaca.repository.LoteRepository;
import com.all.emplaca.service.FabricanteService;
import com.all.emplaca.service.LoteService;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class LoteControllerTest {

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private static final String CNPJ = "30765428000196";
	private static final Long ID_FABRICANTE = 1l;
	private static final String NOME_FABRICANTE = "Snow Blanks S/A";

	private MockMvc mockMvc;

	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	private Fabricante fabricante;

	// private List<Lote> lotes = new ArrayList<Lote>();

	@Autowired
	private LoteService loteService;

	@Autowired
	private FabricanteService fabricanteService;

	@Autowired
	private FabricanteRepository fabricanteRepository;

	@Autowired
	private LoteRepository loteRepository;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().orElse(null);

		assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
	}

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();

		this.fabricanteRepository.deleteAllInBatch();
		this.loteRepository.deleteAllInBatch();

		this.fabricante = new Fabricante();
		fabricante.setCnpj(CNPJ);
		fabricante.setId(ID_FABRICANTE);
		fabricante.setNome(NOME_FABRICANTE);
	}

	@Test
	public void contexLoads() throws Exception {
		assertThat(fabricanteService).isNotNull();
		assertThat(loteService).isNotNull();
	}

	@Test
	public void deveInserirLote() throws Exception {

		PedidoLoteDto pedidoLoteDto = new PedidoLoteDto();
		pedidoLoteDto.setFabricanteId(ID_FABRICANTE);
		pedidoLoteDto.setQuantidade(100l);

		System.out.println(pedidoLoteDto.toString());

		String pedidoLoteJson = json(pedidoLoteDto);

		this.mockMvc.perform(post("/" + fabricante.getId() + "/lotes").contentType(contentType).content(pedidoLoteJson))
				.andExpect(status().isCreated());
	}

	@Test
	public void insereLoteDeFabricante() {

	}

	protected String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}

}
