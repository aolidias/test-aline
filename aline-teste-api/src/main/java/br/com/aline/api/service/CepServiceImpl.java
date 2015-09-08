package br.com.aline.api.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aline.api.model.Cep;
import br.com.aline.api.repository.CepRepository;

/**
 * Classe que contém o serviço do cep
 * 
 * @author aline.dias
 *
 */
@Service
@Transactional
public class CepServiceImpl implements CepService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CepServiceImpl.class);
	
	@Autowired
	private CepRepository repo;
	
	/**
	 * Método responsável pela busca de cep.
	 * 
	 * @param cep
	 * @return Endereco
	 */
	@Override
	public Cep getByCep(String cep) {
		LOGGER.debug("buscado cep: {} na base", cep);
		return repo.findByCep(cep);
	}
	

}
