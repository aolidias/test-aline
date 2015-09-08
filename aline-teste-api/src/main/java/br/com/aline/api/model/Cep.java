package br.com.aline.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Classe de modelo para o cep.
 * 
 * @author aline.dias
 *
 */
@Entity
public class Cep {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotEmpty(message="campo rua obrigatório.")
	@Column(nullable = false)
	private String rua;

	@NotEmpty(message="campo cidade obrigatório.")
	@Column(nullable = false)
	private String cidade;
	
	private String bairro;
	
	@NotEmpty(message="campo estado obrigatório.")
	@Column(nullable = false)
	private String estado;
	
	@Size(max = 9, message = "Cep deve conter 8 caracteres.")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Cep deve conter apenas números")
	@NotEmpty(message="campo cep obrigatório.")
	@Column(nullable = false)
	private String cep;
	
	public Cep() {
		super();
	}

	public Cep(Long id, String rua, String cidade, String estado, String cep, String bairro) {
		super();
		this.id = id;
		this.rua = rua;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
		this.bairro = bairro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
}