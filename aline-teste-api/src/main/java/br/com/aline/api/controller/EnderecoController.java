package br.com.aline.api.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.aline.api.model.Endereco;
import br.com.aline.api.model.EnderecoList;
import br.com.aline.api.service.CepApiService;
import br.com.aline.api.service.EnderecoService;
import br.com.aline.api.validator.group.EnderecoUpdateValidator;
/**
 * Classe que contém o controller para o CRUD de endereços.
 * 
 * @author aline.dias
 *
 */
@RequestMapping("/api/v1/endereco")
@RestController
public class EnderecoController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EnderecoController.class);
	
	@Autowired
	private EnderecoService service;
	
	@Autowired 
	private CepApiService cepApiService;
	
	/**
	 * Método que cria um endereço novo na base de acordo com a request.
	 * @param endereco
	 * @return endereco salvo com id
	 */
	@RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Endereco criarEndereco(@RequestBody @Valid Endereco endereco) {
		LOGGER.debug("nova solitação para criar endereço.");
		return service.criarEndereco(endereco);
	}
	
	/**
	 * Método que lista todos os endereços cadastrados. 
	 * @return lista de endereço da base.
	 */
	@RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody EnderecoList listarEnderecos() {
		LOGGER.debug("nova solitação para listar os endereços.");
		return new EnderecoList(service.listarEnderecos());
	}
	
	/**
	 * Método que busca endereço pelo id.
	 * @param id
	 * @return endereço da base.
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Endereco buscarEnderecoById(@PathVariable Long id) {
		LOGGER.debug("nova solitação para listar o endereço pelo id.");
		return service.buscarEnderecoById(id);
	}
	
	/**
	 * Método que altera endereço na base.
	 * @param endereco
	 * @return endereço alterado da base
	 */
	@RequestMapping(method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Endereco alterarEndereco(@RequestBody @Validated(value={EnderecoUpdateValidator.class}) Endereco endereco) {
		LOGGER.debug("nova solitação para alterar um endereço.");
		return service.alterarEndereco(endereco);
	}
	
	
	/**
	 * Método que remove o endereço da base pelo id.
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void removerEndereco(@PathVariable Long id) {
		LOGGER.debug("nova solitação para remover um endereço.");
		service.removerEndereco(id);
	}

}
