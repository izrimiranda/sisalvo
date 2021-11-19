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
 * Entity implementation class for Entity: Leads
 *
 * @author Izri Miranda
 */
@Entity
@Table(name = "TBL_LEADS")
@NamedQueries({ @NamedQuery(name = "Leads.findAll", query = "SELECT l FROM Leads l WHERE l.excluido = :excluido"),
		@NamedQuery(name = "Leads.findById", query = "SELECT l FROM Leads l WHERE l.id = :id AND l.excluido = :excluido"),
		@NamedQuery(name = "Leads.findByContatoWhatsapp", query = "SELECT l FROM Leads l WHERE l.contatoWhatsapp LIKE :contatoWhatsapp AND l.excluido = :excluido"),
		@NamedQuery(name = "Leads.findByContatoCelular", query = "SELECT l FROM Leads l WHERE l.contatoCelular LIKE :contatoCelular AND l.excluido = :excluido"),
		@NamedQuery(name = "Leads.findByNomeCompleto", query = "SELECT l FROM Leads l WHERE l.nomeCompleto LIKE :nomeCompleto AND l.excluido = :excluido"),
		@NamedQuery(name = "Leads.findByEmail", query = "SELECT l FROM Leads l WHERE l.email LIKE :email AND l.excluido = :excluido"),
		@NamedQuery(name = "Leads.findByIntervaloDataCadastro", query = "SELECT l FROM Leads l WHERE l.dataCadastro BETWEEN :dataIntervaloUm AND :dataIntervaloDois AND l.excluido = :excluido") })
public class Leads implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "Leads.findAll";
	public static final String FIND_BY_ID = "Leads.findById";
	public static final String FIND_BY_NOME_COMPLETO = "Leads.findByNomeCompleto";
	public static final String FIND_BY_EMAIL = "Leads.findByEmail";
	public static final String FIND_BY_INTERVALO_DATA_CADASTRO = "Leads.findByIntervaloDataCadastro";
	public static final String FIND_BY_USUARIO_CADASTRO_ID = "Leads.findByUsuarioCadastroId";
	public static final String FIND_BY_CONTATO_WHATSAPP = "Leads.findByContatoWhatsapp";
	public static final String FIND_BY_CONTATO_CELULAR = "Leads.findByContatoCelular";

	private Long id;

	private String nomeCompleto;
	private String contatoWhatsapp;
	private String contatoCelular;
	private String email;
	private String formaProspeccao;
	private String observacao;
	private String nomeUsuarioCadastro;
	private String nomeUsuarioExclusao;

	private Calendar dataCadastro;
	private boolean excluido;

	private List<HistoricoContatoLead> historicoContatoLead;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID_LEADS", nullable = false)
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

	@Column(name = "FORMA_PROSPECCAO")
	public String getFormaProspeccao() {
		return this.formaProspeccao;
	}

	@OneToMany(targetEntity = HistoricoContatoLead.class, cascade = {
			CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "leads")
	public List<HistoricoContatoLead> getHistoricoContatoLead() {
		if (this.historicoContatoLead == null) {
			this.historicoContatoLead = new ArrayList<HistoricoContatoLead>();
		}
		return this.historicoContatoLead;
	}

	@Column(name = "NOME_COMPLETO")
	public String getNomeCompleto() {
		return this.nomeCompleto;
	}

	@Column(name = "OBSERVACAO")
	public String getObservacao() {
		return this.observacao;
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

	public void setDataCadastroStr(String data) {
		final Calendar calendar = DateUtils.parseToCalendar(data, false);
		this.setDataCadastro(calendar);
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public void setFormaProspeccao(String formaProspeccao) {
		this.formaProspeccao = formaProspeccao;
	}

	public void setHistoricoContatoLead(List<HistoricoContatoLead> historicoContatoLead) {
		this.historicoContatoLead = historicoContatoLead;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
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

}
