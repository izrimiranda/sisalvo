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
 * Entity implementation class for Entity: Professor
 *
 * @author Izri Miranda
 */
@Entity
@Table(name = "TBL_PROFESSOR")
@NamedQueries({
		@NamedQuery(name = "Professor.findAll", query = "SELECT p FROM Professor p WHERE p.excluido = :excluido"),
		@NamedQuery(name = "Professor.findById", query = "SELECT p FROM Professor p WHERE p.id = :id AND p.excluido = :excluido"),
		@NamedQuery(name = "Professor.findByNomeCompleto", query = "SELECT p FROM Professor p WHERE p.nomeCompleto LIKE :nomeCompleto AND p.excluido = :excluido"),
		@NamedQuery(name = "Professor.findByContatoWhatsapp", query = "SELECT p FROM Professor p WHERE p.contatoWhatsapp LIKE :contatoWhatsapp AND p.excluido = :excluido"),
		@NamedQuery(name = "Professor.findByContatoCelular", query = "SELECT p FROM Professor p WHERE p.contatoCelular LIKE :contatoCelular AND p.excluido = :excluido"),
		@NamedQuery(name = "Professor.findByEmail", query = "SELECT p FROM Professor p WHERE p.email LIKE :email AND p.email = :email"),
		@NamedQuery(name = "Professor.findByStatus", query = "SELECT p FROM Professor p WHERE p.status LIKE :status AND p.excluido = :excluido"),
		@NamedQuery(name = "Professor.findByIntervaloDataCadastro", query = "SELECT p FROM Professor p WHERE p.dataCadastro BETWEEN :dataIntevaloUm AND :dataIntervaloDois AND p.excluido = :excluido") })
public class Professor implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "Professor.findAll";
	public static final String FIND_BY_ID = "Professor.findById";
	public static final String FIND_BY_NOME_COMPLETO = "Professor.findById";
	public static final String FIND_BY_CONTATO_WHATSAPP = "Professor.findByContatoWhatsapp";
	public static final String FIND_BY_CONTATO_CELULAR = "Professor.findByContatoCelular";
	public static final String FIND_BY_EMAIL = "Professor.findByEmail";
	public static final String FIND_BY_STATUS = "Professor.findByStatus";
	public static final String FIND_BY_INTERVALO_DATA_CADASTRO = "Professor.findByIntervaloDataCadastro";
	public static final String FIND_BY_USUARIO_CADASTRO_ID = "Professor.findByUsuarioCadastroId";
	public static final String FIND_BY_USUARIO_EXCLUSAO_ID = "Professor.findByUsuarioExclusaoId";

	private Long id;

	private int idade;
	private String nomeCompleto;
	private String sexo;
	private String PNE;
	private String contatoWhatsapp;
	private String contatoCelular;
	private String email;
	private String status;
	private String nomeUsuarioCadastro;
	private String nomeUsuarioExclusao;

	private Calendar dataCadastro;

	private boolean excluido;

	private Endereco endereco;
	private Usuario usuario;
	private List<Disciplina> listaDisciplina;

	@Id
	@Column(name = "ID_PROFESSOR")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public Long getId() {
		return this.id;
	}

	@Column(name = "CONTATO_CELULAR")
	public String getContatoCelular() {
		return this.contatoCelular;
	}

	@Column(name = "CONTATO_WHATSAPP")
	public String getContatoWhatsapp() {
		return this.contatoWhatsapp;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_CADASTRO")
	public Calendar getDataCadastro() {
		return this.dataCadastro;
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

	@Column(name = "IDADE")
	public int getIdade() {
		return this.idade;
	}

	@OneToMany(targetEntity = Disciplina.class, cascade = {
			CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "professor")
	public List<Disciplina> getListaDisciplina() {
		if (this.listaDisciplina == null) {
			this.listaDisciplina = new ArrayList<Disciplina>();
		}
		return this.listaDisciplina;
	}

	@Column(name = "NOME_COMPLETO")
	public String getNomeCompleto() {
		return this.nomeCompleto;
	}

	@Column(name = "PNE")
	public String getPNE() {
		return this.PNE;
	}

	@Column(name = "SEXO")
	public String getSexo() {
		return this.sexo;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return this.status;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USUARIO_ID")
	public Usuario getUsuario() {
		if (this.usuario == null) {
			this.usuario = new Usuario();
		}
		return this.usuario;
	}

	@Column(name = "NOME_USUARIO_EXCLUSAO")
	public String getNomeUsuarioExclusao() {
		return this.nomeUsuarioExclusao;
	}

	@Column(name = "NOME_USUARIO_CADASTRO")
	public String getNomeUsuarioCadastro() {
		return this.nomeUsuarioCadastro;
	}

	@Column(name = "EXCLUIDO", columnDefinition = "boolean default false")
	public boolean isExcluido() {
		return this.excluido;
	}

	public void setContatoCelular(String contatoCelular) {
		this.contatoCelular = contatoCelular;
	}

	public void setContatoWhatsapp(String contatoWhatsapp) {
		this.contatoWhatsapp = contatoWhatsapp;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public void setListaDisciplina(List<Disciplina> listaDisciplina) {
		this.listaDisciplina = listaDisciplina;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public void setPNE(String pNE) {
		this.PNE = pNE;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setNomeUsuarioCadastro(String nomeUsuarioCadastro) {
		this.nomeUsuarioCadastro = nomeUsuarioCadastro;
	}

	public void setNomeUsuarioExclusao(String nomeUsuarioExclusao) {
		this.nomeUsuarioExclusao = nomeUsuarioExclusao;
	}

	public void setDataCadastroStr(String data) {
		final Calendar calendar = DateUtils.parseToCalendar(data, false);
		this.setDataCadastro(calendar);
	}

	@Transient
	public String getDataCadastroStr() {
		String data = "";

		if (this.getDataCadastro() != null) {
			data = DateUtils.format(this.getDataCadastro(), DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN);
		}

		return data;
	}
}
