package br.com.aline.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aline.api.model.Cep;

/**
 * Interface que contém as operações de banco para entidade Cep.
 * 
 * @author aline.dias
 *
 */
public interface CepRepository extends JpaRepository<Cep, Long>{
	
	/**
	 * Metódo para buscar pelo cep 
	 * @param cep
	 * @return endereco
	 */
	Cep findByCep(String cep);
}
