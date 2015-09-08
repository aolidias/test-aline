package br.com.aline.api.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.aline.api.exception.CepNotFoundException;
import br.com.aline.api.exception.InvalidCepException;
import br.com.aline.api.model.Cep;
import br.com.aline.api.service.CepService;
import br.com.aline.api.validator.CepValidator;
/**
 * Classe responsável pelo rest controller do cep-api.
 * 
 * @author aline.dias
 *
 */
@RequestMapping("/api/v1/cep")
@RestController
public class CepController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CepController.class);
	
	@Autowired
	private CepService service;
	
	private static String ZERO = "0";

	/**
	 * Metódo responsável pela busca do cep.
	 * 
	 * @param cep
	 * @return cep
	 * @throws CepNotFoundException
	 * @throws InvalidCepException
	 */
	@RequestMapping(value = "/{cep}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Cep findByCep(@PathVariable String cep) throws CepNotFoundException, InvalidCepException {
		LOGGER.debug("novo requisição para achar o endereço de acordo com o cep : {}", cep);
		CepValidator.validaCep(cep);
		Cep cepResponse;
		cepResponse = service.getByCep(cep);
		if(cepResponse == null){
			int retry = 0;
			while(retry < cep.length()){
				LOGGER.debug("cep não encontrado, tentando com um zero a mais.");
				retry++;
				cepResponse = service.getByCep(replaceForZero(cep, retry));
				if(cepResponse != null){
					return cepResponse;
				}else if(retry == cep.length()){
					LOGGER.debug("cep não encontrado.");
					throw new CepNotFoundException("cep nao encontrado.");
				}
			}
		}
		return cepResponse;
	}
	
	/**
	 * Metódo que faz substituição do cep por zero de acordo com index.
	 * 
	 * @param cep
	 * @param index
	 * @return newcep
	 */
	private String replaceForZero(String cep, int index){
		LOGGER.debug("substiuindo zep por zero, vez: {}", index);
		String newCep = StringUtils.reverse(StringUtils.reverse(cep).substring(index));
		int count = 0;
		while(count < index){
			newCep = newCep.concat(ZERO);
			count ++;
		 }
		return newCep;
	}

}
