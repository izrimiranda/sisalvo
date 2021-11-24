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
import javax.persistence.Lob;
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
 * Entity implementation class for Entity: Disciplina
 *
 * @author Izri Miranda
 */
@Entity
@Table(name = "TBL_DISCIPLINA")
@NamedQueries({
		@NamedQuery(name = "Disciplina.findAll", query = "SELECT d FROM Disciplina d WHERE d.excluido = :excluido"),
		@NamedQuery(name = "Disciplina.findByid", query = "SELECT d FROM Disciplina d WHERE d.id = :id AND d.excluido = :excluido"),
		@NamedQuery(name = "Disciplina.findByNomeDisciplina", query = "SELECT d FROM Disciplina d WHERE d.nomeDisciplina LIKE :nomeDisciplina AND d.excluido = :excluido"),
		@NamedQuery(name = "Disciplina.findBySiglaDisciplina", query = "SELECT d FROM Disciplina d WHERE d.siglaDisciplina LIKE :siglaDisiciplina AND d.excluido = :excluido"),
		@NamedQuery(name = "Disciplina.findByIntervaloDataCadastro", query = "SELECT d FROM Disciplina d WHERE d.dataCadastro BETWEEN :dataIntervaloUm AND :dataIntervaloDois AND d.excluido = :excluido"),
		@NamedQuery(name = "Disciplina.findByCursoId", query = "SELECT d FROM Disciplina d WHERE d.curso.id = :id AND d.excluido = :excluido"),
		@NamedQuery(name = "Disciplina.findByTurmaId", query = "SELECT d FROM Disciplina d WHERE d.turma.id = :id AND d.excluido = :excluido"),
		@NamedQuery(name = "Disciplina.findByProfessorId", query = "SELECT d FROM Disciplina d WHERE d.professor.id = :id AND d.excluido = :excluido") })
public class Disciplina implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "Disciplina.findAll";
	public static final String FIND_BY_ID = "Disciplina.findByid";
	public static final String FIND_BY_NOME_DISCIPLINA = "Disciplina.findByNomeDisciplina";
	public static final String FIND_BY_SIGLA_DISCIPLINA = "Disciplina.findBySiglaDisciplina";
	public static final String FIND_BY_INTERVALO_DATA_CADASTRO = "Disciplina.findByIntervaloDataCadastro";
	public static final String FIND_BY_CURSO_ID = "Disciplina.findByCursoId";
	public static final String FIND_BY_TURMA_ID = "Disciplina.findByTurmaId";
	public static final String FIND_BY_PROFESSOR_ID = "Disciplina.findByProfessorId";

	private Long id;

	private String nomeDisciplina;
	private String siglaDisciplina;
	private String nomeUsuarioCadastro;
	private String nomeUsuarioExclusao;
	private String tipoArquivoEmenta;

	private byte[] ementaDoc;

	private int serie;
	private boolean excluido;
	private Calendar dataCadastro;

	private List<DiarioPresenca> listaDiarioPresenca;

	private Curso curso;
	private Turma turma;
	private Professor professor;

	private List<Atividade> listaAtividade;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CURSO_ID")
	public Curso getCurso() {
		if (this.curso == null) {
			this.curso = new Curso();
		}
		return this.curso;
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
	@Column(name = "ID_DISCIPLINA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public Long getId() {
		return this.id;
	}

	@OneToMany(targetEntity = DiarioPresenca.class, cascade = {
			CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "disciplina")
	public List<DiarioPresenca> getListaDiarioPresenca() {
		if (this.listaDiarioPresenca == null) {
			this.listaDiarioPresenca = new ArrayList<DiarioPresenca>();
		}
		return this.listaDiarioPresenca;
	}

	@Column(name = "NOME_DISCIPLINA")
	public String getNomeDisciplina() {
		return this.nomeDisciplina;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PROFESSOR_ID")
	public Professor getProfessor() {
		if (this.professor == null) {
			this.professor = new Professor();
		}
		return this.professor;
	}

	@Column(name = "SERIE")
	public int getSerie() {
		return this.serie;
	}

	@Column(name = "SIGLA_DISCIPLINA")
	public String getSiglaDisciplina() {
		return this.siglaDisciplina;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "TURMA_ID")
	public Turma getTurma() {
		return this.turma;
	}

	@Column(name = "EXCLUIDO", columnDefinition = "boolean default false")
	public boolean isExcluido() {
		return this.excluido;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public void setDataCadastroStr(String dataCadastro) {
		Calendar calendar = DateUtils.parseToCalendar(dataCadastro, false);
		this.setDataCadastro(calendar);
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setListaDiarioPresenca(List<DiarioPresenca> listaDiarioPresenca) {
		this.listaDiarioPresenca = listaDiarioPresenca;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public void setSerie(int serie) {
		this.serie = serie;
	}

	public void setSiglaDisciplina(String siglaDisciplina) {
		this.siglaDisciplina = siglaDisciplina;
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

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	@Lob
	@Column(name = "EMENTA_DOC", columnDefinition = "LONGBLOB")
	public byte[] getEmentaDoc() {
		return this.ementaDoc;
	}

	public void setEmentaDoc(byte[] ementaDoc) {
		this.ementaDoc = ementaDoc;
	}

	@Column(name = "TIPO_ARQUIVO_EMENTA", length = 5)
	public String getTipoArquivoEmenta() {
		return this.tipoArquivoEmenta;
	}

	public void setTipoArquivoEmenta(String tipoArquivoEmenta) {
		this.tipoArquivoEmenta = tipoArquivoEmenta;
	}

	@OneToMany
	public List<Atividade> getListaAtividade() {
		if (this.listaAtividade == null) {
			this.listaAtividade = new ArrayList<Atividade>();
		}
		return this.listaAtividade;
	}

	public void setListaAtividade(List<Atividade> listaAtividade) {
		this.listaAtividade = listaAtividade;
	}

}
