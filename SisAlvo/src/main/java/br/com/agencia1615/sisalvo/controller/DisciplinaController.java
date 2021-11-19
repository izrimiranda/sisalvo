package br.com.agencia1615.sisalvo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.agencia1615.sisalvo.io.FileUtils;
import br.com.agencia1615.sisalvo.model.DisciplinaModel;
import br.com.agencia1615.sisalvo.model.TurmaModel;
import br.com.agencia1615.sisalvo.model.dao.BaseDAO;
import br.com.agencia1615.sisalvo.model.entity.Curso;
import br.com.agencia1615.sisalvo.model.entity.Disciplina;
import br.com.agencia1615.sisalvo.model.entity.Professor;
import br.com.agencia1615.sisalvo.model.entity.Turma;
import br.com.agencia1615.sisalvo.reader.Labels;

/**
 *
 * @author Izri Miranda
 *
 */
@Named
@SessionScoped
public class DisciplinaController extends BaseDAO implements Serializable {

	/**
	 * t
	 */
	private static final long serialVersionUID = 8465955910683154546L;

	private String nomeTurma;

	private String nomeDisciplina;

	private Map<String, String> nomeTurmas = new HashMap<>();

	@Inject
	transient private DisciplinaModel disciplinaModel;

	@Inject
	private TurmaModel turmaModel;

	@Produces
	private List<DisciplinaModel> listaDisciplina;

	@Produces
	private List<TurmaModel> listaTurmaModel;

	private DisciplinaModel disciplinaSelecionada;

	private boolean isLinhaSelecionada;

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

		for (Disciplina d : this.getTurmaModel().getListaDisciplina()) {
			System.out.println(d.getNomeDisciplina());
		}

		return "";
	}

	public String onChangeTurma() {

		System.out.println("SISALVO LOG | Classe: DisciplinaController - Método: onChangeTurma() | Nome Turma: "
				+ this.getNomeTurma());
		Turma turma = this.getTurmaDAO().buscarTurmaPorNome(this.getNomeTurma());

		System.out.println(
				"SISALVO LOG | Classe: DisciplinaController - Método: onChangeTurma() | Mudou o valor do SelectOneMenu.");

		this.setDisciplinaSelecionada(null);
		/**
		 * Limpando a lista de disciplinas antes de setar o novos valores.
		 */
		this.getListaDisciplina().clear();

		/**
		 * Populando a lista de Disciplinas a partir da Turma Selecionada.
		 */
		for (Disciplina d : turma.getListaDisciplina()) {

			DisciplinaModel dm = new DisciplinaModel();

			dm.setCurso(d.getCurso());
			dm.setDataCadastro(d.getDataCadastro());
			dm.setExcluido(d.isExcluido());
			dm.setId(d.getId());
			dm.setListaDiarioAvaliacao(d.getListaDiarioAvaliacao());
			dm.setListaDiarioPresenca(d.getListaDiarioPresenca());
			dm.setNomeDisciplina(d.getNomeDisciplina());
			dm.setNomeUsuarioCadastro(d.getNomeUsuarioCadastro());
			dm.setProfessor(d.getProfessor());
			dm.setSerie(d.getSerie());
			dm.setSiglaDisciplina(d.getSiglaDisciplina());
			dm.setTurma(d.getTurma());
			dm.setTipoArquivoEmenta(d.getTipoArquivoEmenta());
			dm.setEmentaDoc(d.getEmentaDoc());

			this.getListaDisciplina().add(dm);
		}

		System.out.println(
				"SISALVO LOG | Classe: DisciplinaController - Método: onChangeTurma() | Nome Disciplina Selecionada: "
						+ this.getDisciplinaSelecionada().getNomeDisciplina());

		return "";
	}

	public void popularDisciplinas(List<Disciplina> listaDisciplina) {

		DisciplinaModel dm = new DisciplinaModel();

		Curso c = new Curso();
		c.setNomeCurso("Curso Teste");
		dm.setCurso(c);

		dm.setNomeDisciplina("Disciplina Teste");
		dm.setSerie(1);
		dm.setSiglaDisciplina("DIS1");

		Turma t = new Turma();
		t.setNomeTurma("Turma Teste");
		dm.setTurma(t);

		Professor p = new Professor();
		p.setNomeCompleto("Izrí Miranda");
		dm.setProfessor(p);

		this.getListaDisciplina().add(dm);
	}

	@PostConstruct
	public void init() {
		System.out.println("SISALVO LOG | Classe: DisciplinaController | init() ");
		this.setTurmasAlunos();
		// this.popularDisciplinas();
	}

	public void setTurmasAlunos() {

		List<Turma> listaTurma = new ArrayList<Turma>();

		listaTurma = this.getAlunoDAO().buscarAlunoLogado().getListaTurma();

		for (Turma turma : listaTurma) {
			this.getNomeTurmas().put(turma.getNomeTurma(), turma.getNomeTurma());
		}

//		for (Turma t : listaTurma) {
//			TurmaModel tm = new TurmaModel();
//
//			tm.setId(t.getId());
//			tm.setAno(t.getAno());
//			tm.setCapacidade(t.getCapacidade());
//			tm.setCurso(t.getCurso());
//			tm.setDataInicioStr(t.getDataInicioStr());
//			tm.setDataTerminoStr(t.getDataTerminoStr());
//			tm.setListaDisciplina(t.getListaDisciplina());
//			tm.setNomeTurma(t.getNomeTurma());
//			tm.setSerie(t.getSerie());
//			tm.setSiglaTurma(t.getSiglaTurma());
//
//			for (Disciplina d : tm.getListaDisciplina()) {
//
//				Disciplina disciplina = new Disciplina();
//
//				System.out.println(
//						"SISALVO LOG | Classe: DisciplinaController - Método: setTurmasAlunos() | Nome Disciplinas:"
//								+ d.getNomeDisciplina());
//
//				disciplina.setCurso(d.getCurso());
//				disciplina.setDataCadastroStr(d.getDataCadastroStr());
//				disciplina.setId(d.getId());
//				disciplina.setListaDiarioAvaliacao(d.getListaDiarioAvaliacao());
//				disciplina.setListaDiarioPresenca(d.getListaDiarioPresenca());
//				disciplina.setProfessor(d.getProfessor());
//				disciplina.setTurma(d.getTurma());
//			}

		// listaTurmaModel.add(tm);

		// }

		// this.getListaTurmaModel().addAll(listaTurmaModel);
	}

	public void buscarDisciplinasAluno() {

	}

	public void removerDisciplina() {
		this.listaDisciplina.remove(this.getDisciplinaSelecionada());
		this.setDisciplinaSelecionada(null);
	}

	public DisciplinaModel getDisciplinaModel() {
		if (this.disciplinaModel == null) {
			this.disciplinaModel = new DisciplinaModel();
		}
		return this.disciplinaModel;
	}

	public void setDisciplinaModel(DisciplinaModel disciplinaModel) {
		this.disciplinaModel = disciplinaModel;
	}

	public List<DisciplinaModel> getListaDisciplina() {
		if (this.listaDisciplina == null) {
			this.listaDisciplina = new ArrayList<DisciplinaModel>();
		}
		return this.listaDisciplina;
	}

	public void setListaDisciplina(List<DisciplinaModel> listaDisciplina) {
		this.listaDisciplina = listaDisciplina;
	}

	public TurmaModel getTurmaModel() {
		if (this.turmaModel == null) {
			this.turmaModel = new TurmaModel();
		}
		return this.turmaModel;
	}

	public void setTurmaModel(TurmaModel turmaModel) {
		this.turmaModel = turmaModel;
	}

	public List<TurmaModel> getListaTurmaModel() {
		if (this.listaTurmaModel == null) {
			this.listaTurmaModel = new ArrayList<TurmaModel>();
		}
		return this.listaTurmaModel;
	}

	public void setListaTurmaModel(List<TurmaModel> listaTurmaModel) {
		this.listaTurmaModel = listaTurmaModel;
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

	public DisciplinaModel getDisciplinaSelecionada() {
		System.out.println("SISALVO LOG | Classe: DisciplinaController - Método: getDisciplinaSelecionada()");
		if (this.disciplinaSelecionada == null) {
			this.disciplinaSelecionada = new DisciplinaModel();
		}

		System.out.println(
				"SISALVO LOG | Classe: DisciplinaController - Método: getDisciplinaSelecionada() | Nome Disciplia Selecionada: "
						+ this.disciplinaSelecionada.getNomeDisciplina());
		return this.disciplinaSelecionada;
	}

	public void setDisciplinaSelecionada(DisciplinaModel disciplinaSelecionada) {
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

}
