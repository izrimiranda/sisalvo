package br.com.agencia1615.sisalvo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import br.com.agencia1615.sisalvo.io.FileUtils;
import br.com.agencia1615.sisalvo.model.dao.BaseDAO;
import br.com.agencia1615.sisalvo.model.entity.Atividade;
import br.com.agencia1615.sisalvo.model.entity.DiarioAvaliacao;
import br.com.agencia1615.sisalvo.model.entity.Disciplina;
import br.com.agencia1615.sisalvo.model.entity.Turma;
import br.com.agencia1615.sisalvo.reader.Labels;

/**
 *
 * @author Izri Miranda
 *
 */
@Named
@SessionScoped
public class visualizarNotasAlunoController extends BaseDAO implements Serializable {

	/**
	 * t
	 */
	private static final long serialVersionUID = 8465955910683154546L;

	private String nomeTurma;

	private String nomeDisciplina;

	private Map<String, String> nomeTurmas = new HashMap<>();

	@Produces
	private List<Disciplina> listaDisciplina;

	private Disciplina disciplinaSelecionada;

	private boolean isLinhaSelecionada;

	private List<DiarioAvaliacao> listaDiarioAvaliacao;

	public String habilitarBotaoDownload() {
		System.out.println("SISALVO LOG | Classe: DisciplinaController - Método: habilitarBotaoDownload()");
		this.setLinhaSelecionada(false);
		return "";
	}

	public String exportarEmenta() {
		System.out.println("SISALVO LOG | Classe: DisciplinaController - Método: exportarEmenta()");

		System.out.println("SISALVO LOG | Classe: DisciplinaController - Método: exportarEmenta() - "
				+ this.getDisciplinaSelecionada().getTipoArquivoEmenta());

		String caminhoArquivo = "C:\\Users\\Izri Miranda\\Desktop\\SisAlvo Teste\\Download\\";
		String nomeArquivo = "Ementa Primeiros Socorros";
		String tipoArquivo = this.getDisciplinaSelecionada().getTipoArquivoEmenta();

		FileUtils.setBytesToFile(this.getDisciplinaSelecionada().getEmentaDoc(), caminhoArquivo, tipoArquivo,
				nomeArquivo);

		return "";
	}

	public String popularTabelaDisciplinas() {

		this.onChangeTurma();

		return "";
	}

	public String onChangeTurma() {

		System.out.println("SISALVO LOG | Classe: DisciplinaController - Método: onChangeTurma() | Nome Turma: "
				+ this.getNomeTurma());
		Turma turma = this.getTurmaDAO().buscarTurmaPorNome(this.getNomeTurma());

		this.setDisciplinaSelecionada(null);
		/**
		 * Limpando a lista de disciplinas antes de setar o novos valores.
		 */
		this.getListaDisciplina().clear();

		/**
		 * Populando a lista de Disciplinas a partir da Turma Selecionada.
		 */
		this.getListaDisciplina().addAll(turma.getListaDisciplina());

		return "";
	}

//	public void popularDisciplinas(List<Disciplina> listaDisciplina) {
//
//		DisciplinaModel dm = new DisciplinaModel();
//
//		Curso c = new Curso();
//		c.setNomeCurso("Curso Teste");
//		dm.setCurso(c);
//
//		dm.setNomeDisciplina("Disciplina Teste");
//		dm.setSerie(1);
//		dm.setSiglaDisciplina("DIS1");
//
//		Turma t = new Turma();
//		t.setNomeTurma("Turma Teste");
//		dm.setTurma(t);
//
//		Professor p = new Professor();
//		p.setNomeCompleto("Izrí Miranda");
//		dm.setProfessor(p);
//
//		this.getListaDisciplina().add(dm);
//	}

	@PostConstruct
	public void init() {
		System.out.println("SISALVO LOG | Classe: DisciplinaController | init() ");
		this.setTurmasAlunos();
		// this.popularDisciplinas();
	}

	public void setTurmasAlunos() {

		List<Turma> listaTurma = new ArrayList<Turma>();

		listaTurma = this.getAlunoDAO().buscarAlunoLogado().getListaTurma();

		System.out.println("SISALVO LOG | Lista: " + listaTurma.isEmpty());

		for (Turma turma : listaTurma) {
			this.getNomeTurmas().put(turma.getNomeTurma(), turma.getNomeTurma());
		}
	}

	public void buscarDisciplinasAluno() {

	}

	public void removerDisciplina() {
		this.listaDisciplina.remove(this.getDisciplinaSelecionada());
		this.setDisciplinaSelecionada(null);
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

	public String getNomeTurma() {
		return this.nomeTurma;
	}

	public void setNomeTurma(String nomeTurma) {
		this.nomeTurma = nomeTurma;
	}

	public Map<String, String> getNomeTurmas() {
		return this.nomeTurmas;
	}

	public void setNomeTurmas(Map<String, String> nomeTurmas) {
		this.nomeTurmas = nomeTurmas;
	}

	public Disciplina getDisciplinaSelecionada() {
		System.out.println("SISALVO LOG | Classe: DisciplinaController - Método: getDisciplinaSelecionada()");
		if (this.disciplinaSelecionada == null) {
			this.disciplinaSelecionada = new Disciplina();
			System.out.println("SISALVO LOG | this.getDisciplinaSelecionada().getListaAtividade().isEmpty(): "
					+ this.getDisciplinaSelecionada().getListaAtividade().isEmpty());

			if (this.getDisciplinaSelecionada().getListaAtividade().isEmpty() == false) {
				for (Atividade a : this.getDisciplinaSelecionada().getListaAtividade()) {
					for (DiarioAvaliacao da : a.getListaDiarioAvaliacao()) {
						if (da.getAluno().getUsuario().getLogin().equals(this.getUsuarioSession().getLogin())) {
							this.getListaDiarioAvaliacao().add(da);
						}
					}
				}
			}
		}

		return this.disciplinaSelecionada;
	}

	public void setDisciplinaSelecionada(Disciplina disciplinaSelecionada) {
		this.disciplinaSelecionada = disciplinaSelecionada;
	}

	public String getNomeDisciplina() {
		return Labels.getInstance().getNomeDisciplina(this.getDisciplinaSelecionada().getNomeDisciplina());
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public boolean getIsLinhaSelecionada() {
		return this.isLinhaSelecionada;
	}

	public void setLinhaSelecionada(boolean isLinhaSelecionada) {
		this.isLinhaSelecionada = isLinhaSelecionada;
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

}
