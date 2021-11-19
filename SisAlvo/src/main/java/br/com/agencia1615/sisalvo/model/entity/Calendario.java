package br.com.agencia1615.sisalvo.model.entity;

import java.beans.Transient;
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

import br.com.agencia1615.sisalvo.util.DateUtils;

/**
 * Entity implementation class for Entity: Calendario
 *
 */
@Entity
@Table(name = "TBL_CALENDARIO")
@NamedQueries({
		@NamedQuery(name = "Calendario.findAll", query = "SELECT c FROM Calendario c WHERE c.id = :id AND c.excluido = :excluido"),
		@NamedQuery(name = "Calendario.findByIntervaloDataAtividade", query = "SELECT c FROM Calendario c WHERE c.dataAtividade BETWEEN :dataIntervaloUm AND :dataIntervalorDois AND c.excluido = :excluido"),
		@NamedQuery(name = "Calendario.findByDescricao", query = "SELECT c FROM Calendario c WHERE c.descricao LIKE :descricao AND c.excluido = :excluido") })
public class Calendario implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "Calendario.findAll";
	public static final String FIND_BY_INTERVALO_DATA_ATIVIDADE = "Calendario.findByIntervaloDataAtividade";
	public static final String FIND_BY_DESCRICAO = "Calendario.findByDescricao";
	public static final String FIND_BY_USUARIO_CADASTRO_ID = "Calendario.findByUsuarioCadastroId";
	public static final String FIND_BY_USUARIO_DESCRICAO_ID = "Calendario.findByUsuarioExclusao";

	private Long id;

	private String nomeUsuarioCadastro;
	private String descricao;
	private String cor;
	private String nomeUsuarioExclusao;

	private Calendar dataAtividade;

	private boolean excluido;

	@Column(name = "COR")
	public String getCor() {
		return this.cor;
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

	@Column(name = "DESCRICAO")
	public String getDescricao() {
		return this.descricao;
	}

	@Id
	@Column(name = "ID_CALENDARIO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public Long getId() {
		return this.id;
	}

	@Column(name = "EXCLUIDO", columnDefinition = "boolean default false")
	public boolean isExcluido() {
		return this.excluido;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public void setDataAtividade(Calendar dataAtividade) {
		this.dataAtividade = dataAtividade;
	}

	public void setDataAtividadeStr(String data) {
		final Calendar calendar = DateUtils.parseToCalendar(data, false);
		this.setDataAtividade(calendar);
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public void setId(Long id) {
		this.id = id;
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
