package br.com.aline.api.test;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

/**
 * Classe que contém os testes da cep-api.
 * 
 * @author aline.dias
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Application.class)
public class CepTest {

	@Autowired
	WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void initTests() {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	/**
	 * Método que testa a api para encontrar o cep com sucesso substituindo 7 zeros.
	 * 
	 * @throws Exception
	 */
	@Test
	public void cepFoundWithAllZeroTest() throws Exception{
		String cep = "99999999";
		mvc.perform(get("/api/v1/cep/" + cep)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.rua", is("Rua Felicidade")));
	}
	
	/**
	 * Método que testa a api para retornar o cep com sucesso.
	 * 
	 * @throws Exception
	 */
	@Test
	public void cepFoundTest() throws Exception{
		String cep = "03232000";
		mvc.perform(get("/api/v1/cep/" + cep)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.rua", is("Rua Doutor Jose")));
	}
	
	/**
	 * Método que testa api para retornar o cep com sucesso substituindo o zero uma vez.
	 * 
	 * @throws Exception
	 */
	@Test
	public void cepFoundWithZeroTest() throws Exception{
		String cep = "03232001";
		mvc.perform(get("/api/v1/cep/" + cep)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.rua", is("Rua Doutor Jose")));
	}
	
	/**
	 * Testa a api caso não encontre o cep.
	 *  
	 * @throws Exception
	 */
	@Test
	public void cepNotFoundTest() throws Exception{
		String cep = "29999999";
		mvc.perform(get("/api/v1/cep/" + cep)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(jsonPath("$.errorMessage", is("cep nao encontrado.")));
	}
	
	/**
	 * Testa a api caso o cep da request não contenha o tamanho obrigatório.
	 * 
	 * @throws Exception
	 */
	@Test
	public void invalidCepTest() throws Exception{
		String cep = "299999";
		mvc.perform(get("/api/v1/cep/" + cep)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isBadRequest());
	}
	
	/**
	 * Testa a api caso o cep da request não contenha apenas númericos.
	 * 
	 * @throws Exception
	 */
	@Test
	public void invalidCharacterCepTest() throws Exception{
		String cep = "2999-99";
		mvc.perform(get("/api/v1/cep/" + cep)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isBadRequest());
	}
	
}