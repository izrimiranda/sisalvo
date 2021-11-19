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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.agencia1615.sisalvo.util.DateUtils;

/**
 * Entity implementation class for Entity: Aluno
 *
 * @author Izri Miranda
 */
@Entity
@Table(name = "TBL_ALUNO")
@NamedQueries({ @NamedQuery(name = "Aluno.findAll", query = "SELECT a FROM Aluno a WHERE a.excluido = :excluido"),
		@NamedQuery(name = "Aluno.findById", query = "SELECT a FROM Aluno a WHERE a.id = :id AND a.excluido = :excluido"),
		@NamedQuery(name = "Aluno.findByNomeCompleto", query = "SELECT a FROM Aluno a WHERE a.nomeCompleto LIKE :nomeCompleto AND a.excluido = :excluido"),
		@NamedQuery(name = "Aluno.findByContatoWhatsapp", query = "SELECT a FROM Aluno a WHERE a.contatoWhatsapp LIKE :contatoWhatsapp AND a.excluido = :excluido"),
		@NamedQuery(name = "Aluno.findByContatoCelular", query = "SELECT a FROM Aluno a WHERE a.contatoCelular LIKE :contatoCelular AND a.excluido = :excluido"),
		@NamedQuery(name = "Aluno.findByEmail", query = "SELECT a FROM Aluno a WHERE a.email LIKE :email AND a.excluido = :excluido"),
		@NamedQuery(name = "Aluno.findByStatus", query = "SELECT a FROM Aluno a WHERE a.status = :status AND a.excluido = :excluido"),
		@NamedQuery(name = "Aluno.findByNumIdentidade", query = "SELECT a FROM Aluno a WHERE a.numIdentidade LIKE :numIdentidade AND a.excluido = :excluido"),
		@NamedQuery(name = "Aluno.findByCPF", query = "SELECT a FROM Aluno a WHERE a.CPF LIKE :cpf AND a.excluido =:excluido"),
		@NamedQuery(name = "Aluno.findByIntervaloDataCadastro", query = "SELECT a FROM Aluno a WHERE a.dataCadastro BETWEEN :datIntervaloUm AND :dataIntervaloDois AND a.excluido = :excluido"),
		@NamedQuery(name = "Aluno.findByIntevaloDataNascimento", query = "SELECT a FROM Aluno a WHERE a.dataNascimento BETWEEN :dataIntervaloUm AND :dataIntervaloDois AND a.excluido = :excluido"),
		@NamedQuery(name = "Aluno.findByUsuarioLogin", query = "SELECT a FROM Aluno a WHERE a.usuario.login LIKE :login AND a.excluido = :excluido") })
public class Aluno implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "Aluno.findAll";
	public static final String FIND_BY_ID = "Aluno.findById";
	public static final String FIND_BY_NOME_COMPLETO = "Aluno.findByNomeCompleto";
	public static final String FIND_BY_CONTATO_WHATSAPP = "Aluno.findByContatoWhatsapp";
	public static final String FIND_BY_CONTATO_CELULAR = "Aluno.findByContatoCelular";
	public static final String FIND_BY_EMAIL = "Aluno.findByEmail";
	public static final String FIND_BY_STATUS = "Aluno.findByStatus";
	public static final String FIND_BY_NUM_IDENTIDADE = "Aluno.findByNumIdentidade";
	public static final String FIND_BY_CPF = "Aluno.findByCPF";
	public static final String FIND_BY_INTERVALO_DATA_CADASTRO = "Aluno.findByIntervaloDataCadastro";
	public static final String FIND_BY_INTERVALO_DATA_NASCIMENTO = "Aluno.findByIntevaloDataNascimento";
	public static final String FIND_BY_USUARIO_LOGIN = "Aluno.findByUsuarioLogin";

	private Long id;

	private int numRegistroAluno;
	private int idade;

	private String nomeCompleto;
	private String sexo;
	private String contatoWhatsapp;
	private String contatoCelular;
	private String email;
	private String status;
	private String naturalidade;
	private String filiacaoPaterna;
	private String filiacaoMaterna;
	private String numIdentidade;
	private String CPF;
	private String nomeUsuarioCadastro;
	private String nomeUsuarioExclusao;

	private List<Turma> listaTurma;
	private List<FinanceiroAluno> listaFinanceiroAluno;

	private boolean PNE;
	private boolean excluido;

	private Calendar dataCadastro;
	private Calendar dataNascimento;

	private Endereco endereco;
	private Usuario usuario;
	private Turma turma;

	@Column(name = "CONTATO_CELULAR")
	public String getContatoCelular() {
		return this.contatoCelular;
	}

	@Column(name = "CONTATO_WHATSAPP")
	public String getContatoWhatsapp() {
		return this.contatoWhatsapp;
	}

	@Column(name = "CPF")
	public String getCPF() {
		return this.CPF;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_CADASTRO")
	public Calendar getDataCadastro() {
		return this.dataCadastro;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_NASCIMENTO")
	public Calendar getDataNascimento() {
		return this.dataNascimento;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return this.email;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ENDERECO_ID")
	public Endereco getEndereco() {
		if (this.endereco == null) {
			this.endereco = new Endereco();
		}
		return this.endereco;
	}

	@Column(name = "FILIACAO_MATERNA")
	public String getFiliacaoMaterna() {
		return this.filiacaoMaterna;
	}

	@Column(name = "FILIACAO_PATERNA")
	public String getFiliacaoPaterna() {
		return this.filiacaoPaterna;
	}

	@Id
	@Column(name = "ID_ALUNO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public Long getId() {
		return this.id;
	}

	@Column(name = "IDADE")
	public int getIdade() {
		return this.idade;
	}

	@OneToMany(targetEntity = FinanceiroAluno.class, cascade = {
			CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "aluno")
	public List<FinanceiroAluno> getListaFinanceiroAluno() {
		return this.listaFinanceiroAluno;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	public List<Turma> getListaTurma() {
		if (this.listaTurma == null) {
			this.listaTurma = new ArrayList<Turma>();
		}
		return this.listaTurma;
	}

	@Column(name = "NATURALIDADE")
	public String getNaturalidade() {
		return this.naturalidade;
	}

	@Column(name = "NOME_COMPLETO")
	public String getNomeCompleto() {
		return this.nomeCompleto;
	}

	@Column(name = "NUM_IDENTIDADE")
	public String getNumIdentidade() {
		return this.numIdentidade;
	}

	@Column(name = "NUM_REGISTRO_ALUNO")
	public int getNumRegistroAluno() {
		return this.numRegistroAluno;
	}

	@Column(name = "SEXO")
	public String getSexo() {
		return this.sexo;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return this.status;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "TURMA_ID")
	public Turma getTurma() {
		if (this.turma == null) {
			this.turma = new Turma();
		}
		return this.turma;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USUARIO_ID")
	public Usuario getUsuario() {
		if (this.usuario == null) {
			this.usuario = new Usuario();
		}
		return this.usuario;
	}

	@Column(name = "EXCLUIDO", columnDefinition = "boolean default false")
	public boolean isExcluido() {
		return this.excluido;
	}

	@Column(name = "PNE")
	public boolean isPNE() {
		return this.PNE;
	}

	public void setContatoCelular(String contatoCelular) {
		this.contatoCelular = contatoCelular;
	}

	public void setContatoWhatsapp(String contatoWhatsapp) {
		this.contatoWhatsapp = contatoWhatsapp;
	}

	public void setCPF(String cPF) {
		this.CPF = cPF;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public void setFiliacaoMaterna(String filiacaoMaterna) {
		this.filiacaoMaterna = filiacaoMaterna;
	}

	public void setFiliacaoPaterna(String filiacaoPaterna) {
		this.filiacaoPaterna = filiacaoPaterna;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public void setListaFinanceiroAluno(List<FinanceiroAluno> listaFinanceiroAluno) {
		this.listaFinanceiroAluno = listaFinanceiroAluno;
	}

	public void setListaTurma(List<Turma> listaTurma) {
		this.listaTurma = listaTurma;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public void setNumIdentidade(String numIdentidade) {
		this.numIdentidade = numIdentidade;
	}

	public void setNumRegistroAluno(int numRegistroAluno) {
		this.numRegistroAluno = numRegistroAluno;
	}

	public void setPNE(boolean pNE) {
		this.PNE = pNE;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	@Transient
	public String getDataCadastroStr() {
		String data = "";

		if (this.getDataCadastro() != null) {
			data = DateUtils.format(this.getDataCadastro(), DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN);
		}

		return data;
	}

	public void setDataCadastroStr(String data) {
		Calendar calendar = DateUtils.parseToCalendar(data, false);
		this.setDataCadastro(calendar);
	}

	@Transient
	public String getDataNascimentoStr() {
		String data = "";

		if (this.getDataNascimento() != null) {
			data = DateUtils.format(this.getDataNascimento(), DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN);
		}

		return data;
	}

	public void setDataNascimentoStr(String data) {
		Calendar calendar = DateUtils.parseToCalendar(data, false);
		this.setDataNascimento(calendar);
	}

}
