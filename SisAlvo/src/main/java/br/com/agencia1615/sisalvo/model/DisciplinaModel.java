package br.com.agencia1615.sisalvo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.agencia1615.sisalvo.model.entity.Curso;
import br.com.agencia1615.sisalvo.model.entity.DiarioAvaliacao;
import br.com.agencia1615.sisalvo.model.entity.DiarioPresenca;
import br.com.agencia1615.sisalvo.model.entity.Professor;
import br.com.agencia1615.sisalvo.model.entity.Turma;

/**
 *
 * @author Izri Miranda
 *
 */
public class DisciplinaModel implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2358549869955486394L;

	private Long id;

	private String nomeDisciplina;
	private String siglaDisciplina;
	private byte[] ementaDoc;
	private String nomeUsuarioCadastro;
	private String nomeUsuarioExclusao;
	private String tipoArquivoEmenta;
	private Calendar dataCadastro;

	private int serie;
	private boolean excluido;

	private Curso curso;
	private Turma turma;

	private List<DiarioAvaliacao> listaDiarioAvaliacao;
	private List<DiarioPresenca> listaDiarioPresenca;

	private Professor professor;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeDisciplina() {
		return this.nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public String getSiglaDisciplina() {
		return this.siglaDisciplina;
	}

	public void setSiglaDisciplina(String siglaDisciplina) {
		this.siglaDisciplina = siglaDisciplina;
	}

	public byte[] getEmentaDoc() {
		return this.ementaDoc;
	}

	public void setEmentaDoc(byte[] ementaDoc) {
		this.ementaDoc = ementaDoc;
	}

	public String getNomeUsuarioCadastro() {
		return this.nomeUsuarioCadastro;
	}

	public void setNomeUsuarioCadastro(String nomeUsuarioCadastro) {
		this.nomeUsuarioCadastro = nomeUsuarioCadastro;
	}

	public String getNomeUsuarioExclusao() {
		return this.nomeUsuarioExclusao;
	}

	public void setNomeUsuarioExclusao(String nomeUsuarioExclusao) {
		this.nomeUsuarioExclusao = nomeUsuarioExclusao;
	}

	public Calendar getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public int getSerie() {
		return this.serie;
	}

	public void setSerie(int serie) {
		this.serie = serie;
	}

	public boolean isExcluido() {
		return this.excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public Curso getCurso() {
		if (this.curso == null) {
			this.curso = new Curso();
		}
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Turma getTurma() {
		if (this.turma == null) {
			this.turma = new Turma();
		}
		return this.turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public List<DiarioAvaliacao> getListaDiarioAvaliacao() {
		if (this.listaDiarioAvaliacao == null) {
			this.listaDiarioAvaliacao = new ArrayList<DiarioAvaliacao>();
		}
		return this.listaDiarioAvaliacao;
	}

	public void setListaDiarioAvaliacao(List<DiarioAvaliacao> listaDiarioAvaliacao) {
		this.listaDiarioAvaliacao = listaDiarioAvaliacao;
	}

	public List<DiarioPresenca> getListaDiarioPresenca() {
		if (this.listaDiarioPresenca == null) {
			this.listaDiarioPresenca = new ArrayList<DiarioPresenca>();
		}
		return this.listaDiarioPresenca;
	}

	public void setListaDiarioPresenca(List<DiarioPresenca> listaDiarioPresenca) {
		this.listaDiarioPresenca = listaDiarioPresenca;
	}

	public Professor getProfessor() {
		if (this.professor == null) {
			this.professor = new Professor();
		}
		return this.professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public String getTipoArquivoEmenta() {
		return this.tipoArquivoEmenta;
	}

	public void setTipoArquivoEmenta(String tipoArquivoEmenta) {
		this.tipoArquivoEmenta = tipoArquivoEmenta;
	}

}
