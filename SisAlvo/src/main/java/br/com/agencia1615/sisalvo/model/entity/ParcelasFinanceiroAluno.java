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
 * Entity implementation class for Entity: ParcelasFinanceiroAluno
 *
 * @author Izri Miranda
 */
@Entity
@Table(name = "TBL_PARCELAS_FINANCEIRO_ALUNO")
@NamedQueries({
		@NamedQuery(name = "ParcelasFinanceiroAluno.findAll", query = "SELECT pfa FROM ParcelasFinanceiroAluno pfa WHERE pfa.excluido = :excluido"),
		@NamedQuery(name = "ParcelasFinanceiroAluno.findById", query = "SELECT pfa FROM ParcelasFinanceiroAluno pfa WHERE pfa.id = :id AND pfa.excluido = :excluido"),
		@NamedQuery(name = "ParcelasFinanceiroAluno.findByIntervaloDataVencimento", query = "SELECT pfa FROM ParcelasFinanceiroAluno pfa WHERE pfa.dataVencimento BETWEEN :dataIntervaloUm AND :dataIntervaloDois AND pfa.excluido = :excluido"),
		@NamedQuery(name = "ParcelasFinanceiroAluno.findByIntervaloDataPagamento", query = "SELECT pfa FROM ParcelasFinanceiroAluno pfa WHERE pfa.dataPagamento BETWEEN :dataIntervaloUm AND :dataIntervaloDois AND pfa.excluido = :excluido"),
		@NamedQuery(name = "ParcelasFinanceiroAluno.findByFinanceiroAlunoId", query = "SELECT pfa FROM ParcelasFinanceiroAluno pfa WHERE pfa.financeiroAluno.id = :id AND pfa.excluido = :excluido") })
public class ParcelasFinanceiroAluno implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "ParcelasFinanceiroAluno.findAll";
	public static final String FIND_BY_ID = "ParcelasFinanceiroAluno.findById";
	public static final String FIND_BY_INTERVALO_DATA_VENCIMENTO = "ParcelasFinanceiroAluno.findByIntervaloDataVencimento";
	public static final String FIND_BY_INTERVALO_DATA_PAGAMENTO = "ParcelasFinanceiroAluno.findByIntervaloDataPagamento";
	public static final String FIND_BY_FINANCEIRO_ALUNO_ID = "ParcelasFinanceiroAluno.findByFinanceiroAlunoId";

	private Long id;

	private Double valorParcela;
	private int numeroParcela;
	private Calendar dataVencimento;
	private Calendar dataPagamento;

	private FinanceiroAluno financeiroAluno;

	private boolean excluido;

	@Id
	@Column(name = "ID_PARCELAS_FINANCEIRO_ALUNO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
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
	@JoinColumn(name = "FINANCEIRO_ALUNO_ID")
	public FinanceiroAluno getFinanceiroAluno() {
		if (this.financeiroAluno == null) {
			this.financeiroAluno = new FinanceiroAluno();
		}
		return this.financeiroAluno;
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

	public void setDataPagamentoStr(String data) {
		final Calendar calendar = DateUtils.parseToCalendar(data, false);

		this.setDataPagamento(calendar);
	}

	public void setDataVencimento(Calendar dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public void setDataVencimentoStr(String data) {
		final Calendar calendar = DateUtils.parseToCalendar(data, false);
		this.setDataPagamento(calendar);
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

	public void setNumeroParcela(int numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	public void setValorParcela(Double valorParcela) {
		this.valorParcela = valorParcela;
	}

}
