package br.com.agencia1615.sisalvo.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.agencia1615.sisalvo.util.DateUtils;

/**
 * Entity implementation class for Entity: FinanceiroAluno
 *
 * @author Izri Miranda
 */
@Entity
@Table(name = "TBL_FINANCEIRO_ALUNO")
@NamedQueries({
		@NamedQuery(name = "FinanceiroAluno.findAll", query = "SELECT fa FROM FinanceiroAluno fa WHERE fa.excluido = :excluido"),
		@NamedQuery(name = "FinanceiroAluno.findById", query = "SELECT fa FROM FinanceiroAluno fa WHERE fa.id = :id AND fa.excluido = :excluido"),
		@NamedQuery(name = "FinanceiroAluno.findByIntervaloDataCadastro", query = "SELECT fa FROM FinanceiroAluno fa WHERE fa.dataCadastro BETWEEN :dataIntervaloUm AND :dataIntervaloDois AND fa.excluido = :excluido"),
		@NamedQuery(name = "FinanceiroAluno.findByAlunoId", query = "SELECT fa FROM FinanceiroAluno fa WHERE fa.aluno.id = :id AND fa.excluido = :excluido") })
public class FinanceiroAluno implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "FinanceiroAluno.findAll";
	public static final String FIND_BY_ID = "FinanceiroAluno.findById";
	public static final String FIND_BY_INTERVALO_DATA_CADASTRO = "FinanceiroAluno.findByIntervaloDataCadastro";
	public static final String FIND_BY_ALUNO_ID = "FinanceiroAluno.findByAlunoId";

	private Long id;

	private Calendar dataCadastro;
	private int quantParcelas;
	private Campanha campanha;
	private List<ParcelasFinanceiroAluno> parcelasFinanceiroAluno;

	private boolean excluido;

	private Aluno aluno;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ALUNO_ID")
	public Aluno getAluno() {
		if (this.aluno == null) {
			this.aluno = new Aluno();
		}
		return this.aluno;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CAMPANHA_ID")
	public Campanha getCampanha() {
		if (this.campanha == null) {
			this.campanha = new Campanha();
		}
		return this.campanha;
	}

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

	@Id
	@Column(name = "ID_FINANCEIRO_ALUNO")
	public Long getId() {
		return this.id;
	}

	@OneToMany(targetEntity = ParcelasFinanceiroAluno.class, cascade = {
			CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "financeiroAluno")
	public List<ParcelasFinanceiroAluno> getParcelasFinanceiroAluno() {
		if (this.parcelasFinanceiroAluno == null) {
			this.parcelasFinanceiroAluno = new ArrayList<ParcelasFinanceiroAluno>();
		}
		return this.parcelasFinanceiroAluno;
	}

	@Column(name = "QUANT_PARCELAS")
	public int getQuantParcelas() {
		return this.quantParcelas;
	}

	@Column(name = "EXCLUIDO", columnDefinition = "boolean default false")
	public boolean isExcluido() {
		return this.excluido;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public void setCampanha(Campanha campanha) {
		this.campanha = campanha;
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setParcelasFinanceiroAluno(List<ParcelasFinanceiroAluno> parcelasFinanceiroAluno) {
		this.parcelasFinanceiroAluno = parcelasFinanceiroAluno;
	}

	public void setQuantParcelas(int quantParcelas) {
		this.quantParcelas = quantParcelas;
	}

}
