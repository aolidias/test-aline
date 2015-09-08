package br.com.aline.api.validator;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.aline.api.exception.InvalidCepException;

/**
 * Classe que contém validações do cep.
 *  
 * @author aline.dias
 *
 */
public class CepValidator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CepValidator.class);
	
	public static final void validaCep(String cep) throws InvalidCepException {
		LOGGER.info("validando cep: {}", cep);
		if(!StringUtils.isNumeric(cep)|| cep.length() != 8 ){
			throw new InvalidCepException("cep invalido.");
		}
	}
}
