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
 * Entity implementation class for Entity: HistoricoContatoLead
 *
 * @author Izrí Miranda
 *
 *
 */
@Entity
@Table(name = "TBL_HISTORICO_CONTATO_LEAD")
@NamedQueries({
		@NamedQuery(name = "HistoricoContatoLead.findAll", query = "SELECT hcl FROM HistoricoContatoLead hcl WHERE hcl.excluido = :excluido"),
		@NamedQuery(name = "HistoricoContatoLead.findById", query = "SELECT hcl FROM HistoricoContatoLead hcl WHERE hcl.id = :id AND hcl.excluido = :excluido"),
		@NamedQuery(name = "HistoricoContatoLead.findByIntervaloDataContato", query = "SELECT hcl FROM HistoricoContatoLead hcl WHERE hcl.dataContato BETWEEN :dataIntervaloUm AND :dataIntervaloDois AND hcl.excluido = :excluido") })
public class HistoricoContatoLead implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "HistoricoContatoLead.findAll";
	public static final String FIND_BY_ID = "HistoricoContatoLead.findById";
	public static final String FIND_BY_INTERVALO_DATA_CONTATO = "HistoricoContatoLead.findByIntervaloDataContato";
	public static final String FIND_BY_USUARIO_CADASTRO_ID = "HistoricoContatoLead.findByUsuarioCadastroId";
	public static final String FIND_BY_USUARIO_EXCLUSAO_ID = "HistoricoContatoLead.findByUsuarioExclusaoId";

	private Long id;

	private String observacao;
	private String nomeUsuarioCadastro;
	private String nomeUsuarioExlusao;

	private Calendar dataContato;

	private boolean excluido;

	private Leads leads;

	@Column(name = "DATA_CADASTRO")
	@Temporal(TemporalType.TIMESTAMP)
	public Calendar getDataContato() {
		return this.dataContato;
	}

	@Transient
	public String getDataContatoStr() {
		String data = "";

		if (this.getDataContato() != null) {
			data = DateUtils.format(this.getDataContato(), DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN);
		}

		return data;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID_HISTORIO_CONTATO_LEAD", nullable = false)
	public Long getId() {
		return this.id;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "LEADS_ID")
	public Leads getLeads() {
		if (this.leads == null) {
			this.leads = new Leads();
		}
		return this.leads;
	}

	@Column(name = "OBSERVACAO")
	public String getObservacao() {
		return this.observacao;
	}

	@Column(name = "EXCLUIDO", columnDefinition = "boolean default false")
	public boolean isExcluido() {
		return this.excluido;
	}

	public void setDataContato(Calendar dataContato) {
		this.dataContato = dataContato;
	}

	public void setDataContatoStr(String data) {
		final Calendar calendar = DateUtils.parseToCalendar(data, false);
		this.setDataContato(calendar);
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLeads(Leads leads) {
		this.leads = leads;
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
	public String getNomeUsuarioExlusao() {
		return this.nomeUsuarioExlusao;
	}

	public void setNomeUsuarioExlusao(String nomeUsuarioExlusao) {
		this.nomeUsuarioExlusao = nomeUsuarioExlusao;
	}
}