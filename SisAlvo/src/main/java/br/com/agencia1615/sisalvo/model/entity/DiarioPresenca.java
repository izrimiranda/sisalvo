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
 * Entity implementation class for Entity: DiarioPresenca
 *
 * @author Izri Miranda
 */
@Entity
@Table(name = "TBL_DIARIO_PRESENCA")
@NamedQueries({
		@NamedQuery(name = "DiarioPresenca.findAll", query = "SELECT dp FROM DiarioPresenca dp WHERE dp.excluido = :excluido"),
		@NamedQuery(name = "DiarioPresenca.findById", query = "SELECT dp FROM DiarioPresenca dp WHERE dp.id = :id AND dp.excluido = :excluido"),
		@NamedQuery(name = "DiarioPresenca.findByIntervaloDataAula", query = "SELECT dp FROM DiarioPresenca dp WHERE dp.dataAula BETWEEN :dataIntervaloUm AND :dataIntervaloDois AND dp.excluido = :excluido"),
		@NamedQuery(name = "DiarioPresenca.findByObservacao", query = "SELECT dp FROM DiarioPresenca dp WHERE dp.observacao LIKE :observacao AND dp.excluido = :excluido"),
		@NamedQuery(name = "DiarioPresenca.findByAlunoId", query = "SELECT dp FROM DiarioPresenca dp WHERE dp.aluno.id = :id AND dp.excluido = :excluido") })
public class DiarioPresenca implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "DiarioPresenca.findAll";
	public static final String FIND_BY_ID = "DiarioPresenca.findById";
	public static final String FIND_BY_INTERVALO_DATA_AULA = "DiarioPresenca.findByIntervaloDataAula";
	public static final String FIND_BY_OBSERVACAO = "DiarioPresenca.findByObservacao";
	public static final String FIND_BY_ALUNO_ID = "DiarioPresenca.findByAlunoId";

	private Long id;

	private Calendar dataAula;

	private boolean presenca;
	private String observacao;

	private Aluno aluno;

	private Disciplina disciplina;

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
	@Column(name = "DATA_AULA")
	public Calendar getDataAula() {
		return this.dataAula;
	}

	@Transient
	public String getDataAulaStr() {
		String data = "";
		if (this.getDataAula() != null) {
			data = DateUtils.format(this.getDataAula(), DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN);
		}

		return data;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DISCIPLINA_ID")
	public Disciplina getDisciplina() {
		if (this.disciplina == null) {
			this.disciplina = new Disciplina();
		}
		return this.disciplina;
	}

	@Id
	@Column(name = "ID_DIARIO_PRESENCA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public Long getId() {
		return this.id;
	}

	@Column(name = "OBSERVACAO")
	public String getObservacao() {
		return this.observacao;
	}

	@Column(name = "EXCLUIDO", columnDefinition = "boolean default false")
	public boolean isExcluido() {
		return this.excluido;
	}

	@Column(name = "PRESENCA")
	public boolean isPresenca() {
		return this.presenca;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public void setDataAula(Calendar dataAula) {
		this.dataAula = dataAula;
	}

	public void setDataAulaStr(String data) {
		final Calendar calendar = DateUtils.parseToCalendar(data, false);
		this.setDataAula(calendar);
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
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

	public void setPresenca(boolean presenca) {
		this.presenca = presenca;
	}

	@Transient
	public String getPresencaStr() {
		String retorno = "";

		if (this.isPresenca()) {
			retorno = "Presente";
		} else {
			retorno = "Falta";
		}

		return retorno;
	}

}
