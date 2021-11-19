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
import javax.persistence.Transient;

import br.com.agencia1615.sisalvo.util.DateUtils;

/**
 * Entity implementation class for Entity: Financeiro
 *
 */
@Entity
@Table(name = "TBL_FINANCEIRO")
@NamedQueries({
		@NamedQuery(name = "Financeiro.findAll", query = "SELECT f FROM Financeiro f WHERE f.excluido = :excluido"),
		@NamedQuery(name = "Financeiro.findById", query = "SELECT f FROM Financeiro f WHERE f.id = :id AND f.excluido = :excluido"),
		@NamedQuery(name = "Financeiro.findByTipoPagamento", query = "SELECT f FROM Financeiro f WHERE f.tipoPagamento LIKE :tipoPagamento AND f.excluido = :excluido"),
		@NamedQuery(name = "Financeiro.findByFormaPagamento", query = "SELECT f FROM Financeiro f WHERE f.formaPagamento LIKE :formaPagamento AND f.excluido = :excluido"),
		@NamedQuery(name = "Financeiro.findByIntervaloDataMovimentacao", query = "SELECT f FROM Financeiro f WHERE f.dataMovimentacao BETWEEN :dataIntervaloUm AND :dataIntervaloDois AND f.excluido = :excluido"),
		@NamedQuery(name = "Financeiro.findByIntervaloDataVencimento", query = "SELECT f FROM Financeiro f WHERE f.dataVencimento BETWEEN :dataIntervaloUm AND :dataIntervaloDois AND f.excluido = :excluido"),
		@NamedQuery(name = "Financeiro.findByIntervaloDataPagamento", query = "SELECT f FROM Financeiro f WHERE f.dataPagamento BETWEEN :dataIntervaloUm AND :dataIntervaloDois AND f.excluido = :excluido") })
public class Financeiro implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "Financeiro.findAll";
	public static final String FIND_BY_ID = "Financeiro.findById";
	public static final String FIND_BY_TIPO_PAGAMENTO = "Financeiro.findByTipoPagamento";
	public static final String FIND_BY_FORMA_PAGAMENTO = "Financeiro.findByFormaPagamento";
	public static final String FIND_BY_USUARIO_CADASTRO_ID = "Financeiro.findByUsuarioCadastroId";
	public static final String FIND_BY_USUARIO_EXCLUSAO_ID = "Financeiro.findByUsuarioExclusaoId";
	public static final String FIND_BY_INTERVALO_DATA_MOVIMENTACAO = "Financeiro.findByIntervaloDataMovimentacao";
	public static final String FIND_BY_INTERVALO_DATA_VENCIMENTO = "Financeiro.findByIntervaloDataVencimento";
	public static final String FIND_BY_INTERVALO_DATA_PAGAMENTO = "Financeiro.findByIntervaloDataPagamento";

	private Long id;

	private Double valorMovimentacao;

	private String observacao;
	private String tipoPagamento;
	private String formaPagamento;
	private String nomeUsuarioCadastro;
	private String nomeUsuarioExclusao;

	private boolean excluido;

	private List<ParcelasFinanceiro> listaParcelasFinanceiro;

	private Calendar dataMovimentacao;
	private Calendar dataVencimento;
	private Calendar dataPagamento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_MOVIMENTACAO")
	public Calendar getDataMovimentacao() {
		return this.dataMovimentacao;
	}

	@Transient
	public String getDataMovimentacaoStr() {
		String data = "";

		if (this.getDataMovimentacao() != null) {
			data = DateUtils.format(this.getDataMovimentacao(), DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN);
		}

		return data;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_PAGAMENTO")
	public Calendar getDataPagamento() {
		return this.dataPagamento;
	}

	@Transient
	public String getDataPagamentoStr() {
		String data = "";

		if (this.getDataPagamento() != null) {
			data = DateUtils.format(this.getDataPagamento(), DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN);
		}

		return data;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_VENCIMENTO")
	public Calendar getDataVencimento() {
		return this.dataVencimento;
	}

	@Transient
	public String getDataVencimentoStr() {
		String data = "";

		if (this.getDataVencimento() != null) {
			data = DateUtils.format(this.getDataMovimentacao(), DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN);
		}

		return data;
	}

	@Column(name = "FORMA_PAGAMENTO")
	public String getFormaPagamento() {
		return this.formaPagamento;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID_FINANCEIRO")
	public Long getId() {
		return this.id;
	}

	@OneToMany(targetEntity = ParcelasFinanceiro.class, cascade = {
			CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "financeiro")
	public List<ParcelasFinanceiro> getListaParcelasFinanceiro() {
		if (this.listaParcelasFinanceiro == null) {
			this.listaParcelasFinanceiro = new ArrayList<ParcelasFinanceiro>();
		}
		return this.listaParcelasFinanceiro;
	}

	@Column(name = "OBSERVACAO")
	public String getObservacao() {
		return this.observacao;
	}

	@Column(name = "TIPO_PAGAMENTO")
	public String getTipoPagamento() {
		return this.tipoPagamento;
	}

	@Column(name = "VALOR_MOVIMENTACAO")
	public Double getValorMovimentacao() {
		return this.valorMovimentacao;
	}

	@Column(name = "EXCLUIDO", columnDefinition = "boolean default false")
	public boolean isExcluido() {
		return this.excluido;
	}

	public void setDataMovimentacao(Calendar dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}

	public void setDataMovimentacaoStr(String data) {
		final Calendar calendar = DateUtils.parseToCalendar(data, false);
		this.setDataMovimentacao(calendar);
	}

	public void setDataPagamento(Calendar dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public void setDataPagamentoStr(String data) {
		final Calendar calendar = DateUtils.parseToCalendar(data, false);
		this.setDataPagamento(calendar);
	}

	public void setDataVencimento(Calendar dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public void setDataVencimentoStr(String data) {
		final Calendar calendar = DateUtils.parseToCalendar(data, false);
		this.setDataVencimento(calendar);
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setListaParcelasFinanceiro(List<ParcelasFinanceiro> listaParcelasFinanceiro) {
		this.listaParcelasFinanceiro = listaParcelasFinanceiro;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public void setValorMovimentacao(Double valorMovimentacao) {
		this.valorMovimentacao = valorMovimentacao;
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
