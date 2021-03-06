package br.com.aline.api.test;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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

import br.com.aline.api.model.Endereco;
import br.com.aline.configuration.Application;

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
	
	MockMvc mvc;
	
	private Gson gson = new Gson();

	@Before
	public void initTests() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	/**
	 * Método que testa a busca de endereço by ID.
	 * 
	 * @throws Exception
	 */
	@Test
	public void buscarEnderecoByIdTest() throws Exception {
		this.mvc.perform(
				get("/api/v1/endereco/3").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(jsonPath("$.rua", is("Av. Principal")));
	}
	
	
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
	
	/**
	 * Método que testa a tentativa de alterar o endereço sem o id.
	 * @throws Exception
	 */
	@Test
	public void alterarEnderecoSemIdTest() throws Exception {
		Endereco e = new Endereco("teste", 23, "vila madalena", "teste", "São Paulo", "SP", "1231313");
		String cepJson = gson.toJson(e);
		this.mvc.perform(put("/api/v1/endereco").content(cepJson).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andDo(MockMvcResultHandlers.print());
	}
	
	/**
	 * Método que testa  tentativa de alterar o endereço inexistente.
	 * @throws Exception
	 */
	@Test
	public void alterarEnderecoInexistenteTest() throws Exception {
		Endereco e = new Endereco(20L, "teste", 23, "vila madalena", "teste", "São Paulo", "SP", "1231313");
		String cepJson = gson.toJson(e);
		this.mvc.perform(put("/api/v1/endereco").content(cepJson).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andDo(MockMvcResultHandlers.print());
	}
		
	
	/**
	 * Método que testa  alteração do endereço com sucesso.
	 * @throws Exception
	 */
	@Test
	public void alterarEnderecoTest() throws Exception {
		Endereco e = new Endereco(1L, "teste", 23, "vila madalena", "teste", "São Paulo", "SP", "1231313");
		String cepJson = gson.toJson(e);
		this.mvc.perform(put("/api/v1/endereco").content(cepJson).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print());
		this.mvc.perform(
				get("/api/v1/endereco/1").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(jsonPath("$.rua", is("teste")));
	}
	
	/**
	 * Método que testa a alteração do endereço sem dados obrigatórios.
	 * @throws Exception
	 */
	@Test
	public void alterarEnderecoSemDadosObrigatoriosTest() throws Exception {
		Endereco e = new Endereco();
		e.setId(1L);
		String cepJson = gson.toJson(e);
		this.mvc.perform(put("/api/v1/endereco").content(cepJson).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andDo(MockMvcResultHandlers.print());
	}
	
	
	/**
	 * Método que testa a busca de endereço by ID que não existe.
	 * 
	 * @throws Exception
	 */
	@Test
	public void buscarEnderecoInexistenteByIdTest() throws Exception {
		this.mvc.perform(
				get("/api/v1/endereco/20").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isNotFound());
	}
	
	/**
	 * Método que testa remover endereço pelo ID.
	 * 
	 * @throws Exception
	 */
	@Test
	public void removerEnderecoByIdTest() throws Exception {
		this.mvc.perform(
				delete("/api/v1/endereco/2").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print());
		
		this.mvc.perform(
				get("/api/v1/endereco/2").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isNotFound());
	}
	
}