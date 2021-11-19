package br.com.agencia1615.sisalvo.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Endereço
 *
 * @author Izri Miranda
 */
@Entity
@Table(name = "TBL_ENDERECO")
@NamedQueries({ @NamedQuery(name = "Endereco.findAll", query = "SELECT e FROM Endereco e WHERE e.excluido = :excluido"),
		@NamedQuery(name = "Endereco.findById", query = "SELECT e FROM Endereco e WHERE e.id = :id AND e.excluido = :excluido"),
		@NamedQuery(name = "Endereco.findByLogradouro", query = "SELECT e FROM Endereco e WHERE e.logradouro LIKE :logradouro AND e.excluido = :excluido"),
		@NamedQuery(name = "Endereco.findByBairro", query = "SELECT e FROM Endereco e WHERE e.bairro LIKE :bairro AND e.excluido = :excluido"),
		@NamedQuery(name = "Endereco.findByCidade", query = "SELECT e FROM Endereco e WHERE e.cidade LIKE :cidade AND e.excluido = :excluido") })
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "Endereco.findAll";
	public static final String FIND_BY_ID = "Endereco.findById";
	public static final String FIND_BY_LOGRADOURO = "Endereco.findByLogradouro";
	public static final String FIND_BY_BAIRRO = "Endereco.findByBairro";
	public static final String FIND_BY_CIDADE = "Endereco.findByCidade";

	private Long id;

	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String UF;
	private String CEP;

	private boolean excluido;

	@Id
	@Column(name = "ID_ENDERECO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public Long getId() {
		return this.id;
	}

	@Column(name = "BAIRRO")
	public String getBairro() {
		return this.bairro;
	}

	@Column(name = "CEP")
	public String getCEP() {
		return this.CEP;
	}

	@Column(name = "CIDADE")
	public String getCidade() {
		return this.cidade;
	}

	@Column(name = "COMPLEMENTO")
	public String getComplemento() {
		return this.complemento;
	}

	@Column(name = "LOGRADOURO")
	public String getLogradouro() {
		return this.logradouro;
	}

	@Column(name = "NUMERO")
	public String getNumero() {
		return this.numero;
	}

	@Column(name = "UF")
	public String getUF() {
		return this.UF;
	}

	@Column(name = "EXCLUIDO", columnDefinition = "boolean default false")
	public boolean isExcluido() {
		return this.excluido;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setCEP(String cEP) {
		this.CEP = cEP;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setUF(String uF) {
		this.UF = uF;
	}

}
