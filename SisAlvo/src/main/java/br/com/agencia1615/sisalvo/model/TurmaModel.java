package br.com.agencia1615.sisalvo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.agencia1615.sisalvo.model.entity.Curso;
import br.com.agencia1615.sisalvo.model.entity.Disciplina;

/**
 *
 * @author Izri Miranda
 *
 */
public class TurmaModel implements Serializable {

	public TurmaModel() {
		System.out.println("SISALVO LOG | Classe: TurmaModel - Método: TurmaModel() | Inicializando Classe.");
	}

	/**
	 *
	 */
	private static final long serialVersionUID = -5640632073355074607L;

	private Long id;
	private String SiglaTurma;
	private String nomeTurma;
	private String ano;
	private String periodo;
	private int capacidade;
	private int serie;
	private String dataInicioStr;
	private String dataTerminoStr;

	private List<Disciplina> listaDisciplina;
	private Curso curso;

	public String getNomeTurma() {
		return this.nomeTurma;
	}

	public void setNomeTurma(String nomeTurma) {
		this.nomeTurma = nomeTurma;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSiglaTurma() {
		return this.SiglaTurma;
	}

	public void setSiglaTurma(String siglaTurma) {
		this.SiglaTurma = siglaTurma;
	}

	public String getAno() {
		return this.ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getPeriodo() {
		return this.periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public int getCapacidade() {
		return this.capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public int getSerie() {
		return this.serie;
	}

	public void setSerie(int serie) {
		this.serie = serie;
	}

	public String getDataInicioStr() {
		return this.dataInicioStr;
	}

	public void setDataInicioStr(String dataInicioStr) {
		this.dataInicioStr = dataInicioStr;
	}

	public String getDataTerminoStr() {
		return this.dataTerminoStr;
	}

	public void setDataTerminoStr(String dataTerminoStr) {
		this.dataTerminoStr = dataTerminoStr;
	}

	public List<Disciplina> getListaDisciplina() {
		if (this.listaDisciplina == null) {
			this.listaDisciplina = new ArrayList<Disciplina>();
		}
		return this.listaDisciplina;
	}

	public void setListaDisciplina(List<Disciplina> listaDisciplina) {
		this.listaDisciplina = listaDisciplina;
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

}
