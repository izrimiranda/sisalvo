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
 * Entity implementation class for Entity: Curso
 *
 * @author Izri Miranda
 */
@Entity
@Table(name = "TBL_CURSO")
@NamedQueries({ @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c WHERE c.excluido = :excluido"),
		@NamedQuery(name = "Curso.findById", query = "SELECT c FROM Curso c WHERE c.id = :id AND c.excluido = :excluido"),
		@NamedQuery(name = "Curso.findByNomeCurso", query = "SELECT c FROM Curso c WHERE c.nomeCurso LIKE :nomeCurso AND c.excluido = :excluido"),
		@NamedQuery(name = "Curso.findByTipoCurso", query = "SELECT c FROM Curso c WHERE c.tipoCurso LIKE :tipoCurso AND c.excluido = :excluido"),
		@NamedQuery(name = "Curso.findByIntervaloDataCadastro", query = "SELECT c FROM Curso c WHERE c.dataCadastro BETWEEN :dataIntervaloUM AND :dataIntervaloDois AND c.excluido = :excluido") })
public class Curso implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "Curso.findAll";
	public static final String FIND_BY_ID = "Curso.findById";
	public static final String FIND_BY_NOME_CURSO = "Curso.findByNomeCurso";
	public static final String FIND_BY_TIPO_CURSO = "Curso.findByTipoCurso";
	public static final String FIND_BY_INTERVALO_DATA_CADASTRO = "Curso.findByIntervaloDataCadastro";
	public static final String FIND_BY_USUARIO_CADASTRO_ID = "Curso.findByUsuarioCadastroId";

	private Long id;

	private String nomeCurso;
	private String tipoCurso;
	private String nomeUsuarioCadatro;
	private String nomeUsuarioExclusao;

	private int cargaHoraria;
	private int cargaHorariaEstagio;

	private Calendar dataCadastro;
	private boolean excluido;

	private List<Turma> listaTurma;
	private List<Disciplina> listaDisciplina;

	@Column(name = "CARGA_HORARIA")
	public int getCargaHoraria() {
		return this.cargaHoraria;
	}

	@Column(name = "CARGA_HORARIA_ESTAGIO")
	public int getCargaHorariaEstagio() {
		return this.cargaHorariaEstagio;
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
	@Column(name = "ID_CURSO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public Long getId() {
		return this.id;
	}

	@OneToMany(targetEntity = Disciplina.class, cascade = {
			CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "curso")
	public List<Disciplina> getListaDisciplina() {
		return this.listaDisciplina;
	}

	@OneToMany(targetEntity = Turma.class, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "curso")
	public List<Turma> getListaTurma() {
		if (this.listaTurma == null) {
			this.listaTurma = new ArrayList<Turma>();
		}
		return this.listaTurma;
	}

	@Column(name = "NOME_CURSO")
	public String getNomeCurso() {
		return this.nomeCurso;
	}

	@Column(name = "TIPO_CURSO")
	public String getTipoCurso() {
		return this.tipoCurso;
	}

	@Column(name = "EXCLUIDO", columnDefinition = "boolean default false")
	public boolean isExcluido() {
		return this.excluido;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public void setCargaHorariaEstagio(int cargaHorariaEstagio) {
		this.cargaHorariaEstagio = cargaHorariaEstagio;
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

	public void setListaDisciplina(List<Disciplina> listaDisciplina) {
		this.listaDisciplina = listaDisciplina;
	}

	public void setListaTurma(List<Turma> listaTurma) {
		this.listaTurma = listaTurma;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	@Column(name = "NOME_USUARIO_CADASTRO")
	public String getNomeUsuarioCadatro() {
		return this.nomeUsuarioCadatro;
	}

	public void setNomeUsuarioCadatro(String nomeUsuarioCadatro) {
		this.nomeUsuarioCadatro = nomeUsuarioCadatro;
	}

	@Column(name = "NOME_USUARIO_EXCLUSAO")
	public String getNomeUsuarioExclusao() {
		return this.nomeUsuarioExclusao;
	}

	public void setNomeUsuarioExclusao(String nomeUsuarioExclusao) {
		this.nomeUsuarioExclusao = nomeUsuarioExclusao;
	}

	public void setTipoCurso(String tipoCurso) {
		this.tipoCurso = tipoCurso;
	}

}
