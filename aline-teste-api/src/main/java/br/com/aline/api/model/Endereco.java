package br.com.aline.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.aline.api.validator.group.EnderecoUpdateValidator;

/**
 * Modelo de endereço.
 * 
 * @author aline.dias
 *
 */
@Entity
public class Endereco {
	
	@Id
	@NotNull(groups={EnderecoUpdateValidator.class}, message="campo id obrigatório")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotEmpty(message="campo rua obrigatório.")
	@Column(nullable = false)
	private String rua;
	
	@NotNull(message="campo numero obrigatório.")
	@Column(nullable = false)
	private Integer numero;
	
	private String bairro;
	
	private String complemento;
	
	@NotEmpty(message="campo cidade obrigatório.")
	@Column(nullable = false)
	private String cidade;
	
	@NotEmpty(message="campo estado obrigatório.")
	@Column(nullable = false)
	private String estado;
	
	@NotEmpty(message="campo cep obrigatório.")
	@Column(nullable = false)
	private String cep;
	
	public Endereco(Long id, String rua, Integer numero, String bairro,
			String complemento, String cidade, String estado, String cep) {
		super();
		this.id = id;
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.complemento = complemento;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
	}
	
	public Endereco(String rua, Integer numero, String bairro,
			String complemento, String cidade, String estado, String cep) {
		super();
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.complemento = complemento;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
	}

	public Endereco(){
		super();
	}
	
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
    @Override
    public String toString() {
        return "endereco rua=" + rua + ", numero=" + numero + ", complemento=" + complemento + ", cep=" + cep + ", bairro=" + bairro + ", cidade=" + cidade+ ", estado=" + estado + "]";
    }
}
