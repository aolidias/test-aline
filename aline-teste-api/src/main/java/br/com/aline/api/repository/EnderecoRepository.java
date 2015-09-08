package br.com.aline.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aline.api.model.Endereco;

/**
 * Interface que contém as operações de banco para entidade Endereço.
 * 
 * @author aline.dias
 *
 */
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
	
}
