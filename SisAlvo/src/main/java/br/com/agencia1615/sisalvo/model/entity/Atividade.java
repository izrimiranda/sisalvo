package br.com.agencia1615.sisalvo.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Izrí
 *
 */
@Entity
@Table(name = "TBL_ATIVIDADE")
@NamedQueries({
		@NamedQuery(name = "Atividade.findAll", query = "SELECT a FROM Atividade a WHERE a.excluido = :excluido") })
public class Atividade implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -1556959258533667428L;

	public static final String FIND_ALL = "Atividade.findAll";

	private Long id;
	private String nomeAtividade;
	private String nomeUsuarioExclusao;
	private String nomeUsuarioCad;

	private Calendar dataAtividade;
	private double valorTotal;

	private List<DiarioAvaliacao> listaDiarioAvaliacao;

	private boolean excluido;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "NOME_ATIVIDADE")
	public String getNomeAtividade() {
		return this.nomeAtividade;
	}

	public void setNomeAtividade(String nomeAtividade) {
		this.nomeAtividade = nomeAtividade;
	}

	@Column(name = "NOME_USUARIO_EXCLUSAO")
	public String getNomeUsuarioExclusao() {
		return this.nomeUsuarioExclusao;
	}

	public void setNomeUsuarioExclusao(String nomeUsuarioExclusao) {
		this.nomeUsuarioExclusao = nomeUsuarioExclusao;
	}

	@Column(name = "NOME_USUARIO_CAD")
	public String getNomeUsuarioCad() {
		return this.nomeUsuarioCad;
	}

	public void setNomeUsuarioCad(String nomeUsuarioCad) {
		this.nomeUsuarioCad = nomeUsuarioCad;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_ATIVIDADE")
	public Calendar getDataAtividade() {
		return this.dataAtividade;
	}

	public void setDataAtividade(Calendar dataAtividade) {
		this.dataAtividade = dataAtividade;
	}

	@Column(name = "VALOR_TOTAL")
	public double getValorTotal() {
		return this.valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	@OneToMany(targetEntity = DiarioAvaliacao.class, cascade = {
			CascadeType.ALL }, fetch = FetchType.EAGER, mappedBy = "atividade")
	public List<DiarioAvaliacao> getListaDiarioAvaliacao() {
		if (this.listaDiarioAvaliacao == null) {
			this.listaDiarioAvaliacao = new ArrayList<DiarioAvaliacao>();
		}
		return this.listaDiarioAvaliacao;
	}

	public void setListaDiarioAvaliacao(List<DiarioAvaliacao> listaDiarioAvaliacao) {
		this.listaDiarioAvaliacao = listaDiarioAvaliacao;
	}

	@Column(name = "EXCLUIDO")
	public boolean isExcluido() {
		return this.excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

}
