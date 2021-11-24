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
 * Entity implementation class for Entity: DiarioAvaliacao
 *
 * @author Izri Miranda
 */
@Entity
@Table(name = "TBL_DIARIO_AVALIACAO")
@NamedQueries({
		@NamedQuery(name = "DiarioAvaliacao.findAll", query = "SELECT da FROM DiarioAvaliacao da WHERE da.excluido = :excluido"),
		@NamedQuery(name = "DiarioAvaliacao.findById", query = "SELECT da FROM DiarioAvaliacao da WHERE da.id = :id AND da.excluido = :excluido"),
		@NamedQuery(name = "DiarioAvaliacao.findByIntervaloDataAtividade", query = "SELECT da FROM DiarioAvaliacao da WHERE da.dataAtividade BETWEEN :dataIntervaloUm AND :dataIntervaloDois AND da.excluido = :excluido"),
		@NamedQuery(name = "DiarioAvaliacao.findByObservacao", query = "SELECT da FROM DiarioAvaliacao da WHERE da.observacao LIKE :observaco AND da.excluido = :excluido"),
		@NamedQuery(name = "DiarioAvaliacao.findByAlunoId", query = "SELECT da FROM DiarioAvaliacao da WHERE da.aluno.id = :id AND da.excluido = :excluido") })
public class DiarioAvaliacao implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "DiarioAvaliacao.findAll";
	public static final String FIND_BY_ID = "DiarioAvaliacao.findById";
	public static final String FIND_BY_INTERVALO_DATA_ATIVIDADE = "DiarioAvaliacao.findByIntervaloDataAtividade";
	public static final String FIND_BY_OBSERVACAO = "DiarioAvaliacao.findByObservacao";
	public static final String FIND_BY_ALUNO_ID = "DiarioAvaliacao.findByAlunoId";

	private Long id;

	private Calendar dataAtividade;

	private Double valorTotal;

	private Double valorObtido;
	private String observacao;

	private Atividade atividade;

	private Aluno aluno;

	private boolean excluido;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ALUNO_ID")
	public Aluno getAluno() {
		if (this.aluno == null) {
			this.aluno = new Aluno();
		}
		return this.aluno;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_ATIVIDADE")
	public Calendar getDataAtividade() {
		return this.dataAtividade;
	}

	@Transient
	public String getDataAtividadeStr() {
		String data = "";

		if (this.getDataAtividade() != null) {
			data = DateUtils.format(this.getDataAtividade(), DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN);
		}

		return data;
	}

	@Id
	@Column(name = "ID_DIARIO_AVALIACAO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public Long getId() {
		return this.id;
	}

	@Column(name = "OBSERCACAO")
	public String getObservacao() {
		return this.observacao;
	}

	@Column(name = "VALOR_OBTIDO")
	public Double getValorObtido() {
		return this.valorObtido;
	}

	@Column(name = "VALOR_TOTAL")
	public Double getValorTotal() {
		return this.valorTotal;
	}

	@Column(name = "EXCLUIDO", columnDefinition = "boolean default false")
	public boolean isExcluido() {
		return this.excluido;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public void setDataAtividade(Calendar dataAtividade) {
		this.dataAtividade = dataAtividade;
	}

	public void setDataAtividadeStr(String data) {
		final Calendar calendar = DateUtils.parseToCalendar(data, false);
		this.setDataAtividade(calendar);
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public void setValorObtido(Double valorObtido) {
		this.valorObtido = valorObtido;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ATIVIDADE_ID")
	public Atividade getAtividade() {
		if (this.atividade == null) {
			this.atividade = new Atividade();
		}
		return this.atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

}
