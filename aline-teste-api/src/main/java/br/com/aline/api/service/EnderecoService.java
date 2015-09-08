package br.com.aline.api.service;

import java.util.List;

import br.com.aline.api.model.Endereco;

public interface EnderecoService {

	Endereco criarEndereco(Endereco endereco);

	List<Endereco> listarEnderecos();
	
	Endereco buscarEnderecoById(Long id);
	
	Endereco alterarEndereco(Endereco endereco);
	
	void removerEndereco(Long id);
	
}

