package br.com.agencia1615.sisalvo.model.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.agencia1615.sisalvo.util.DateUtils;

/**
 * Entity implementation class for Entity: Usuario
 *
 */
/**
 *
 * @author Izri Miranda
 *
 */
@Entity
@Table(name = "TBL_USUARIO")
@NamedQueries({ @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u WHERE u.excluido = :excluido"),
		@NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id AND u.excluido = :excluido"),
		@NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email LIKE :email AND u.excluido = :excluido"),
		@NamedQuery(name = "Usuario.findByLogin", query = "SELECT u FROM Usuario u WHERE u.login LIKE :login AND u.excluido = :excluido"),
		@NamedQuery(name = "Usuario.findByIntervaloDataCadastro", query = "SELECT u FROM Usuario u WHERE u.dataCadastro = :dataCadastro AND u.excluido = :excluido"),
		@NamedQuery(name = "Usuario.findByLoginSenha", query = "SELECT u FROM Usuario u WHERE u.login LIKE :login AND u.senha = :senha AND u.excluido = :excluido") })
public class Usuario implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "Usuario.findAll";
	public static final String FIND_BY_ID = "Usuario.findByEmail";
	public static final String FIND_BY_EMAIL = "Usuario.findByEmail";
	public static final String FIND_BY_LOGIN = "Usuario.findByLogin";
	public static final String FIND_BY_LOGIN_SENHA = "Usuario.findByLoginSenha";
	public static final String FIND_BY_INTERVALO_DATA_CADASTRO = "Usuario.findByIntervaloDataCadastro";
	public static final String FIND_BY_USUARIO_CADASTRO_ID = "Usuario.findByUsuarioCadastroId";
	public static final String FIND_BY_USUARIO_EXCLUSAO = "Usuario.findByUsuarioExclusao";

	private Long id;

	private String email;
	private String login;
	private String senha;
	private String nomeUsuarioCadastro;
	private String nomeUsuarioExclusao;

	private Calendar dataCadastro;

	private boolean excluido;

	private int tipoUsuario;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID_USUARIO", nullable = false)
	public Long getId() {
		return this.id;
	}

	@Column(name = "DATA_CADASTRO")
	@Temporal(TemporalType.TIMESTAMP)
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

	@Column(name = "EMAIL")
	public String getEmail() {
		return this.email;
	}

	@Column(name = "LOGIN")
	public String getLogin() {
		return this.login;
	}

	@Column(name = "SENHA")
	public String getSenha() {
		return this.senha;
	}

	@Column(name = "EXCLUIDO", columnDefinition = "boolean default false")
	public boolean isExcluido() {
		return this.excluido;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public void setDataCadastroStr(String data) {
		Calendar calendar = DateUtils.parseToCalendar(data, false);
		this.setDataCadastro(calendar);
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

	/**
	 * 1 Administrador; 2 Secretaria; 3 Financeiro; 4 Aluno; 5 Professor
	 *
	 * @return int - tipoUsuario
	 */
	@Column(name = "TIPO_USUARIO")
	public int getTipoUsuario() {
		return this.tipoUsuario;
	}

	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

}
