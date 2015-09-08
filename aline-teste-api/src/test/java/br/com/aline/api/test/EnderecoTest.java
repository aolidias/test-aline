package br.com.aline.api.test;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.aline.api.configuration.Application;
import br.com.aline.api.model.Endereco;
import br.com.aline.api.service.CepApiService;

import com.google.gson.Gson;

/**
 * Classe que contém os testes de endereço.
 * 
 * @author aline.dias
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Application.class)
public class EnderecoTest {

	@Autowired
	WebApplicationContext context;
	
	private Gson gson = new Gson();
	
	@Autowired
	private CepApiService cepApiService;

	private MockMvc mvc;

	@Before
	public void initTests() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	/**
	 * Método que testa a criação de endereço via api.
	 * 
	 * @throws Exception
	 */
//	@Test
//	public void criarEnderecoTest() throws Exception {
//		Gson gson = new Gson();
//		Endereco e = new Endereco("teste", 23, "vila madalena", "teste","São Paulo", "SP", "03232000");
//		String cepJson = gson.toJson(e);
//		mvc.perform(
//				post("/api/v1/endereco").content(cepJson).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk())
//				.andDo(MockMvcResultHandlers.print())
//				.andExpect(jsonPath("id", notNullValue()));
//	}

	/**
	 * Método que testa a listagem de endereços
	 * 
	 * @throws Exception
	 */
	@Test
	public void listarTodosEnderecosTest() throws Exception {
		this.mvc.perform(
				get("/api/v1/endereco/").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(
						jsonPath("enderecos", hasSize(greaterThanOrEqualTo(1))));
	}
	
	@Test
	public void alterarEnderecoSemIdTest() throws Exception {
		Endereco e = new Endereco("teste", 23, "vila madalena", "teste", "São Paulo", "SP", "1231313");
		String cepJson = gson.toJson(e);
		this.mvc.perform(put("/api/v1/endereco").content(cepJson).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andDo(MockMvcResultHandlers.print());
	}
	
	/**
	 * Método que testa a criação de endereço via api.
	 * 
	 * @throws Exception
	 */
//	@Test
//	public void criarEnderecoComCepInexistenteTest() throws Exception {
//		Gson gson = new Gson();
//		Endereco e = new Endereco("teste", 23, "vila madalena", "teste",
//				"São Paulo", "SP", "1231312");
//		String cepJson = gson.toJson(e);
//		mvc.perform(
//				post("/api/v1/endereco").content(cepJson)
//						.contentType(MediaType.APPLICATION_JSON)
//						.accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isNotFound())
//				.andDo(MockMvcResultHandlers.print());
//	}
	
	/**
	 * Método que testa a busca de endereço by ID
	 * 
	 * @throws Exception
	 */
	@Test
	public void buscarEnderecoByIdTest() throws Exception {
		this.mvc.perform(
				get("/api/v1/endereco/1").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(jsonPath("$.rua", is("Rua Felicidade")));
	}
	
	
}