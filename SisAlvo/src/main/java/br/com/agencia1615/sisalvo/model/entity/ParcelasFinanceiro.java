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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.agencia1615.sisalvo.util.DateUtils;

/**
 * Entity implementation class for Entity: ParcelasFinanceiro
 *
 * @author Izri Miranda
 */
@Entity
@Table(name = "TBL_PARCELAS_FINANCEIRO")
@NamedQueries({
		@NamedQuery(name = "ParcelasFinanceiro.findAll", query = "SELECT pf FROM ParcelasFinanceiro pf WHERE pf.excluido = :excluido"),
		@NamedQuery(name = "ParcelasFinanceiro.findById", query = "SELECT pf FROM ParcelasFinanceiro pf WHERE pf.id = :id AND pf.excluido = :excluido"),
		@NamedQuery(name = "ParcelasFinanceiro.findByIntervaloDataVencimento", query = "SELECT pf FROM ParcelasFinanceiro pf WHERE pf.dataVencimento BETWEEN :dataIntervaloUm AND :dataIntervaloDoi AND pf.excluido = :excluido"),
		@NamedQuery(name = "ParcelasFinanceiro.findByIntervaloDataPagamento", query = "SELECT pf FROM ParcelasFinanceiro pf WHERE pf.dataPagamento BETWEEN :dataIntervaloUm AND :dataIntervaloDois AND pf.excluido = :excluido"),
		@NamedQuery(name = "ParcelasFinanceiro.findByFinanceiroId", query = "SELECT pf FROM ParcelasFinanceiro pf WHERE pf.financeiro.id = :financeiroId AND pf.excluido = :excluido") })
public class ParcelasFinanceiro implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "ParcelasFinanceiro.findAll";
	public static final String FIND_BY_ID = "ParcelasFinanceiro.findById";
	public static final String FIND_BY_INTERVALO_DATA_VENCIMENTO = "ParcelasFinanceiro.findByIntervaloDataVencimento";
	public static final String FIND_BY_INTERVALO_DATA_PAGAMENTO = "ParcelasFinanceiro.findByIntervaloDataPagamento";
	public static final String FIND_BY_FINANCEIRO_ID = "ParcelasFinanceiro.findByFinanceiroId";

	private Long id;

	private Calendar dataVencimento;
	private Calendar dataPagamento;
	private Double valorParcela;
	private int numeroParcela;

	private Financeiro financeiro;

	private boolean excluido;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID_PARCELAS_FINANCEIRO", nullable = false)
	public Long getId() {
		return this.id;
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
			data = DateUtils.format(this.getDataVencimento(), DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN);
		}

		return data;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FINANCEIRO_ID")
	public Financeiro getFinanceiro() {
		if (this.financeiro == null) {
			this.financeiro = new Financeiro();
		}
		return this.financeiro;
	}

	@Column(name = "NUMERO_PARCELA")
	public int getNumeroParcela() {
		return this.numeroParcela;
	}

	@Column(name = "VALOR_PARCELA")
	public Double getValorParcela() {
		return this.valorParcela;
	}

	@Column(name = "EXCLUIDO", columnDefinition = "boolean default false")
	public boolean isExcluido() {
		return this.excluido;
	}

	public void setDataPagamento(Calendar dataPagamento) {
		this.dataPagamento = dataPagamento;
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

	public void setFinanceiro(Financeiro financeiro) {
		this.financeiro = financeiro;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNumeroParcela(int numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	public void setValorParcela(Double valorParcela) {
		this.valorParcela = valorParcela;
	}

	public void steDataPagamentoStr(String data) {
		final Calendar calendar = DateUtils.parseToCalendar(data, false);
		this.setDataPagamento(calendar);
	}

}
