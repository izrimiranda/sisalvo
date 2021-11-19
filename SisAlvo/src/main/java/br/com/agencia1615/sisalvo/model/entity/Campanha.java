package br.com.agencia1615.sisalvo.model.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.agencia1615.sisalvo.util.DateUtils;

/**
 * Entity implementation class for Entity: Campanha
 *
 * @author Izri Miranda
 */
@Entity
@Table(name = "TBL_CAMPANHA")
@NamedQueries({ @NamedQuery(name = "Campanha.findAll", query = "SELECT c FROM Campanha c WHERE c.excluido = :excluido"),
		@NamedQuery(name = "Campanha.findById", query = "SELECT c FROM Campanha c WHERE c.id = :id AND c.excluido = :excluido"),
		@NamedQuery(name = "Campanha.findByNomeCampanha", query = "SELECT c FROM Campanha c WHERE c.nomeCampanha LIKE :nomeCampanha AND c.excluido = :excluido"),
		@NamedQuery(name = "Campanha.findBySigla", query = "SELECT c FROM Campanha c WHERE c.sigla LIKE :sigla AND c.excluido = :excluido"),
		@NamedQuery(name = "Campanha.findByIntervaloDataCadastro", query = "SELECT c FROM Campanha c WHERE c.dataCadastro BETWEEN :dataIntervaloUm AND :dataIntervaloDois AND c.excluido = :excluido") })
public class Campanha implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "Campanha.findAll";
	public static final String FIND_BY_ID = "Campanha.findById";
	public static final String FIND_BY_NOME_CAMPANHA = "Campanha.findByNomeCampanha";
	public static final String FIND_BY_SIGLA = "Campanha.findBySigla";
	public static final String FIND_BY_USUARIO_CADASTRO_ID = "Campanha.findByUsuarioCadastroId";
	public static final String FIND_BY_INTERVALO_DATA_CADASTRO = "Campanha.findByIntervaloDataCadastro";

	private Long id;

	private String nomeCampanha;
	private String sigla;
	private String nomeUsuarioCadastro;
	private String nomeUsuarioExclusao;

	private Double taxaInscricao;
	private Double valorMensalidade;
	private Double valorTotal;

	private boolean excluido;

	private FinanceiroAluno financeiroAluno;

	private Calendar dataCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_CADASTRO")
	public Calendar getDataCadastro() {
		return this.dataCadastro;
	}

	@Transient
	public String getDataCadastroStr() {
		String data = "";

		if (this.getDataCadastro() != null) {
			data = DateUtils.format(this.getDataCadastro(), DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN);
		}

		return data;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FINANCEIRO_ALUNO_ID")
	public FinanceiroAluno getFinanceiroAluno() {
		if (this.financeiroAluno == null) {
			this.financeiroAluno = new FinanceiroAluno();
		}
		return this.financeiroAluno;
	}

	@Id
	@Column(name = "ID_CAMPANHA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public Long getId() {
		return this.id;
	}

	@Column(name = "NOME_CAMPANHA")
	public String getNomeCampanha() {
		return this.nomeCampanha;
	}

	@Column(name = "SIGLA")
	public String getSigla() {
		return this.sigla;
	}

	@Column(name = "TAXA_INSCRICAO")
	public Double getTaxaInscricao() {
		return this.taxaInscricao;
	}

	@Column(name = "VALOR_MENSALIDADE")
	public Double getValorMensalidade() {
		return this.valorMensalidade;
	}

	@Column(name = "VALOR_TOTAL")
	public Double getValorTotal() {
		return this.valorTotal;
	}

	@Column(name = "EXCLUIDO", columnDefinition = "boolean default false")
	public boolean isExcluido() {
		return this.excluido;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public void setDataCadastroStr(String data) {
		final Calendar calendar = DateUtils.parseToCalendar(data, false);
		this.setDataCadastro(calendar);
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public void setFinanceiroAluno(FinanceiroAluno financeiroAluno) {
		this.financeiroAluno = financeiroAluno;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNomeCampanha(String nomeCampanha) {
		this.nomeCampanha = nomeCampanha;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public void setTaxaInscricao(Double taxaInscricao) {
		this.taxaInscricao = taxaInscricao;
	}

	public void setValorMensalidade(Double valorMensalidade) {
		this.valorMensalidade = valorMensalidade;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	@Column(name = "NOME_USUARIO_CADASTRO")
	public String getNomeUsuarioCadastro() {
		return this.nomeUsuarioCadastro;
	}

	public void setNomeUsuarioCadastro(String nomeUsuarioCadastro) {
		this.nomeUsuarioCadastro = nomeUsuarioCadastro;
	}

	@Column(name = "NOME_USUARIO_EXCLUSAO")
	public String getNomeUsuarioExclusao() {
		return this.nomeUsuarioExclusao;
	}

	public void setNomeUsuarioExclusao(String nomeUsuarioExclusao) {
		this.nomeUsuarioExclusao = nomeUsuarioExclusao;
	}

}
