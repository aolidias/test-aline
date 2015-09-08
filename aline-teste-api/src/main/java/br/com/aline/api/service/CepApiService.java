package br.com.aline.api.service;

import br.com.aline.api.model.Endereco;


public interface CepApiService {
	
	Endereco consultarCep(String cep);

	
}
