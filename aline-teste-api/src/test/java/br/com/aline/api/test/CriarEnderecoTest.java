package br.com.aline.api.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;

import br.com.aline.api.exception.CepNotFoundException;
import br.com.aline.api.model.Cep;
import br.com.aline.api.model.Endereco;
import br.com.aline.api.repository.EnderecoRepository;
import br.com.aline.api.service.CepApiService;
import br.com.aline.api.service.EnderecoService;
import br.com.aline.configuration.Application;

import com.google.gson.Gson;

/**
 * Classe que contém os testes de endereço.
 * 
 * @author aline.dias
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class CriarEnderecoTest {

	 private MockRestServiceServer mockServer;
	  
	@Autowired
	private EnderecoService enderecoService;
	
    
	@Mock
	private EnderecoRepository enderecoRepository;
	
	@Mock
	private CepApiService cepApiService;
	
	private Gson gson = new Gson();
	
	@Autowired
	private RestTemplate restTemplate;

	@Before
	public void initTests() {
		mockServer = MockRestServiceServer.createServer(restTemplate);
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Método que testa a criação de endereço.
	 * 
	 * @throws Exception
	 */
	@Test
	public void criarEnderecoTest() throws Exception {
		Endereco e = new Endereco("teste", 23, "vila madalena", "teste","São Paulo", "SP", "03232000");
		Cep cep = new Cep(1L, "Rua Doutor Jose", "São Paulo", "SP", "03232000", "Bras");
		String cepJson = gson.toJson(cep);;
		when(enderecoRepository.save(Mockito.any(Endereco.class))).thenReturn(e);
		when(cepApiService.consultarCep(Mockito.any(String.class))).thenReturn(cep);
		mockServer.expect(MockRestRequestMatchers.requestTo("http://localhost:8080/api/v1/cep/03232000"))
		.andExpect(MockRestRequestMatchers.method(HttpMethod.GET))
		.andRespond(MockRestResponseCreators.withSuccess(cepJson, MediaType.APPLICATION_JSON));
		Endereco endereco = enderecoService.criarEndereco(e);
		assertEquals(new Long(4), endereco.getId());
	}
	
	
	/**
	 * Método que testa a criação de endereço com cep inválido.
	 * 
	 * @throws Exception
	 */
	@Test(expected=CepNotFoundException.class)
	public void criarEnderecoComCepInvalidoTest() throws Exception {
		Endereco e = new Endereco("teste", 23, "vila madalena", "teste","São Paulo", "SP", "03232000");
		when(enderecoRepository.save(Mockito.any(Endereco.class))).thenReturn(e);
		when(cepApiService.consultarCep(Mockito.any(String.class))).thenReturn(null);
		mockServer.expect(MockRestRequestMatchers.requestTo("http://localhost:8080/api/v1/cep/03232000"))
		.andExpect(MockRestRequestMatchers.method(HttpMethod.GET))
		.andRespond(MockRestResponseCreators.withBadRequest());
		enderecoService.criarEndereco(e);
	}
	
	
}