package br.com.aline.api.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aline.api.exception.CepNotFoundException;
import br.com.aline.api.exception.EnderecoNotFoundException;
import br.com.aline.api.model.Cep;
import br.com.aline.api.model.Endereco;
import br.com.aline.api.repository.EnderecoRepository;

/**
 * Classe que contém o serviço do endereço.
 * 
 * @author aline.dias
 *
 */
@Service
@Transactional
public class EnderecoServiceImpl implements EnderecoService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EnderecoServiceImpl.class);
	
	@Autowired
	private EnderecoRepository repo;
	
	@Autowired 
	private CepApiService cepApiService;
	
	/**
	 * Método responsável por criar o endereço na base.
	 * @param endereco
	 * @return endereco
	 */
	@Override
	public Endereco criarEndereco(Endereco endereco) {
		LOGGER.debug("buscando o cep na api de busca de cep.");
		Cep cep = cepApiService.consultarCep(endereco.getCep());
		if(cep == null){
			LOGGER.debug("cep não encontrado.");
			throw new CepNotFoundException("cep nao existe.");
		}
		LOGGER.debug("salvando endereço na base.");
		return repo.save(endereco);
	}
	
	/**
	 * Método responsável por listar todos os endereços da base.
	 * @return List<Endereco>
	 */
	@Override
	public List<Endereco> listarEnderecos() {
		LOGGER.debug("buscando todos os endereços na base.");
		return repo.findAll();
	}
	
	/**
	 * Método responsável por buscar o endereço por determinado id.
	 * @param id
	 * @return endereco
	 */
	@Override
	public Endereco buscarEnderecoById(Long id) {
		LOGGER.debug("buscando endereço de acordo com id dado.");
		Endereco enderecoEncontrado = repo.findOne(id);
		if(enderecoEncontrado == null)
			throw new EnderecoNotFoundException("Endereço não encotrado.");
		return enderecoEncontrado;
	}
	
	/**
	 * Método responsável por alterar o endereço na base.
	 * @param endereco
	 * @return endereco
	 */
	@Override
	public Endereco alterarEndereco(Endereco endereco) {
		LOGGER.debug("alterando endereço de id: {}  na base.", endereco.getId());
		Endereco enderecoEncontrado = repo.findOne(endereco.getId());
		if(enderecoEncontrado == null)
			throw new EnderecoNotFoundException("Endereço não encotrado.");
		return repo.save(endereco);
	}
	
	/**
	 * Método responsável por deletar o endereço da base de acordo com o id.
	 * @param id
	 */
	@Override
	public void removerEndereco(Long id) {
		LOGGER.debug("deletando endereço de id: {}  na base.", id);
		repo.delete(id);
	}

}
