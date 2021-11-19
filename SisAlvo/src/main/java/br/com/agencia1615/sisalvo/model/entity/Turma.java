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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.agencia1615.sisalvo.util.DateUtils;

/**
 * Entity implementation class for Entity: Turma
 *
 * @author Izri Miranda
 */
@Entity
@Table(name = "TBL_TURMA")
@NamedQueries({ @NamedQuery(name = "Turma.findAll", query = "SELECT t FROM Turma t WHERE t.excluido = :excluido"),
		@NamedQuery(name = "Turma.findById", query = "SELECT t FROM Turma t WHERE t.id = :id AND t.excluido = :excluido"),
		@NamedQuery(name = "Turma.findBySiglaTurma", query = "SELECT t FROM Turma t WHERE t.siglaTurma LIKE :siglaTurma AND t.excluido = :excluido"),
		@NamedQuery(name = "Turma.findByNomeTurma", query = "SELECT t FROM Turma t WHERE t.nomeTurma LIKE :nomeTurma AND t.excluido = :excluido"),
		@NamedQuery(name = "Turma.findByPeriodo", query = "SELECT t FROM Turma t WHERE t.periodo = :periodo AND t.excluido = :excluido"),
		@NamedQuery(name = "Turma.findBySerie", query = "SELECT t FROM Turma t WHERE t.serie = :serie AND t.excluido = :excluido"),
		@NamedQuery(name = "Turma.findByIntervaloDataInicio", query = "SELECT t FROM Turma t WHERE t.dataInicio BETWEEN :dataIntervalorUm AND :dataIntervaloDois AND t.excluido = :excluido"),
		@NamedQuery(name = "Turma.findByIntervaloDataTermino", query = "SELECT t FROM Turma t WHERE t.dataTermino BETWEEN :dataIntervaloUm AND :dataIntervaloDois AND t.excluido = :excluido"),
		@NamedQuery(name = "Turma.findByCursoId", query = "SELECT t FROM Turma t WHERE t.curso.id = :id AND t.excluido = :excluido"),
		@NamedQuery(name = "Turma.findByCursoNome", query = "SELECT t FROM Turma t WHERE t.curso.nomeCurso LIKE :cursoNome AND t.excluido = :excluido"),
		@NamedQuery(name = "Turma.findByAno", query = "SELECT t FROM Turma t WHERE t.ano = :ano AND t.excluido = :excluido") })
public class Turma implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "Turma.findAll";
	public static final String FIND_BY_ID = "Turma.findById";
	public static final String FIND_BY_SIGLA_TURMA = "Turma.findBySiglaTurma";
	public static final String FIND_BY_NOME_TURMA = "Turma.findByNomeTurma";
	public static final String FIND_BY_PERIODO = "Turma.findByPeriodo";
	public static final String FIND_BY_SERIE = "Turma.findBySerie";
	public static final String FIND_BY_INTERVALO_DATA_INICIO = "Turma.findByIntervaloDataInicio";
	public static final String FIND_BY_INTERVALO_DATA_TERMINO = "Turma.findByIntervaloDataTermino";
	public static final String FIND_BY_CURSO_ID = "Turma.findByCursoId";
	public static final String FIND_BY_CURSO_NOME = "Turma.findByCursoNome";
	public static final String FIND_BY_ANO = "Turma.findByAno";

	private Long id;

	private String siglaTurma;
	private String nomeTurma;
	private String ano;
	private String periodo;

	private int capacidade;
	private int serie;

	private Calendar dataInicio;
	private Calendar dataTermino;

	private List<Disciplina> listaDisciplina;
	private List<Aluno> listaAluno;

	private boolean excluido;

	private Curso curso;

	private String nomeUsuarioCadastro;
	private String nomeUsuarioExclusao;

	@Column(name = "ANO")
	public String getAno() {
		return this.ano;
	}

	@Column(name = "CAPACIDADE")
	public int getCapacidade() {
		return this.capacidade;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CURSO_ID")
	public Curso getCurso() {
		if (this.curso == null) {
			this.curso = new Curso();
		}
		return this.curso;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_INICIO")
	public Calendar getDataInicio() {
		return this.dataInicio;
	}

	@Transient
	public String getDataInicioStr() {
		String data = "";

		if (this.getDataInicio() != null) {
			data = DateUtils.format(this.getDataInicio(), DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN);
		}

		return data;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_TERMINO")
	public Calendar getDataTermino() {
		return this.dataTermino;
	}

	@Id
	@Column(name = "ID_TURMA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public Long getId() {
		return this.id;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	public List<Aluno> getListaAluno() {
		if (this.listaAluno == null) {
			this.listaAluno = new ArrayList<Aluno>();
		}
		return this.listaAluno;
	}

	@OneToMany(targetEntity = Disciplina.class, cascade = {
			CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "turma")
	public List<Disciplina> getListaDisciplina() {
		if (this.listaDisciplina == null) {
			this.listaDisciplina = new ArrayList<Disciplina>();
		}
		return this.listaDisciplina;
	}

	@Column(name = "NOME_TURMA")
	public String getNomeTurma() {
		return this.nomeTurma;
	}

	@Column(name = "PERIODO")
	public String getPeriodo() {
		return this.periodo;
	}

	@Column(name = "SERIE")
	public int getSerie() {
		return this.serie;
	}

	@Column(name = "SIGLA_TURMA")
	public String getSiglaTurma() {
		return this.siglaTurma;
	}

	@Column(name = "EXCLUIDO", columnDefinition = "boolean default false")
	public boolean isExcluido() {
		return this.excluido;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public void setDataInicioStr(String data) {
		final Calendar calendar = DateUtils.parseToCalendar(data, false);
		this.setDataInicio(calendar);
	}

	public void setDataTermino(Calendar dataTermino) {
		this.dataTermino = dataTermino;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setListaAluno(List<Aluno> listaAluno) {
		this.listaAluno = listaAluno;
	}

	public void setListaDisciplina(List<Disciplina> listaDisciplina) {
		this.listaDisciplina = listaDisciplina;
	}

	public void setNomeTurma(String nomeTurma) {
		this.nomeTurma = nomeTurma;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public void setSerie(int serie) {
		this.serie = serie;
	}

	public void setSiglaTurma(String siglaTurma) {
		this.siglaTurma = siglaTurma;
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

	public void setDataTerminoStr(String data) {
		final Calendar calendar = DateUtils.parseToCalendar(data, false);
		this.setDataTermino(calendar);
	}

	@Transient
	public String getDataTerminoStr() {
		String data = "";

		if (this.getDataTermino() != null) {
			data = DateUtils.format(this.getDataTermino(), DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN);
		}

		return data;
	}
}
