package br.com.aline.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.aline.api.model.Endereco;

/**
 * 
 * Classe que contém a chamada para a api de consulta de cep
 * @author aline.dias
 *
 */
@Service
public class CepApiServiceImpl implements CepApiService {
	@Value("${cep.api.url}")
	private String apiUrl;
	
	@Autowired
	@Qualifier(value="restTemplate")
	private RestTemplate restTemplate;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CepApiServiceImpl.class);
	
	@Override
	public Endereco consultarCep(String cep) {	
		String url = apiUrl + "/" + cep;
		try {
			return restTemplate.getForObject(url , Endereco.class);
		} catch (Exception e) {
			LOGGER.debug("Erro na busca de cep na api, erro : []", e.getMessage());
		}
		return null;
	}
}
