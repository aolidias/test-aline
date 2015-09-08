package br.com.aline.api.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de modelo para a lista de endereços.
 * 
 * @author aline.dias
 *
 */
public class EnderecoList {

	private List<Endereco> enderecos = new ArrayList<>();
	
	public EnderecoList(List<Endereco> enderecos) {
		super();
		this.enderecos = enderecos;
	}
	public EnderecoList() {
	}


	public List<Endereco> getEnderecos() {
		return enderecos;
	}

}
