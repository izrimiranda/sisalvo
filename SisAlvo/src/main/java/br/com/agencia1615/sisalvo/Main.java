package br.com.agencia1615.sisalvo;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.agencia1615.sisalvo.io.FileUtils;
import br.com.agencia1615.sisalvo.model.dao.BaseDAO;
import br.com.agencia1615.sisalvo.model.dao.CursoDAO;
import br.com.agencia1615.sisalvo.model.dao.EnderecoDAO;
import br.com.agencia1615.sisalvo.model.dao.ProfessorDAO;
import br.com.agencia1615.sisalvo.model.dao.TurmaDAO;
import br.com.agencia1615.sisalvo.model.dao.UsuarioDAO;
import br.com.agencia1615.sisalvo.model.entity.Aluno;
import br.com.agencia1615.sisalvo.model.entity.Atividade;
import br.com.agencia1615.sisalvo.model.entity.Curso;
import br.com.agencia1615.sisalvo.model.entity.DiarioAvaliacao;
import br.com.agencia1615.sisalvo.model.entity.DiarioPresenca;
import br.com.agencia1615.sisalvo.model.entity.Disciplina;
import br.com.agencia1615.sisalvo.model.entity.Endereco;
import br.com.agencia1615.sisalvo.model.entity.Professor;
import br.com.agencia1615.sisalvo.model.entity.Turma;
import br.com.agencia1615.sisalvo.model.entity.Usuario;
import br.com.agencia1615.sisalvo.util.DateUtils;
import br.com.agencia1615.sisalvo.util.EntityManagerUtil;
import br.com.agencia1615.sisalvo.util.Security;

public class Main extends BaseDAO {

	/**
	 *
	 */
	private static final long serialVersionUID = 8481827475556942615L;

	private UsuarioDAO usuarioDAO;
	private EnderecoDAO enderecoDAO;
	private CursoDAO cursoDAO;
	private TurmaDAO turmaDAO;
	private ProfessorDAO professorDAO;

	public static void main(String args[]) {
		new Main().salvarEnderecoTeste();
	}

	public void gerarBanco() {
		this.getEM();
	}

	public void salvarNoBanco() {
		this.salvarUsuarioAdm();
		// this.salvarDisciplina();
		// this.salvarAlunoTeste();
		// this.salvarUsuarioAluno();
	}

	public void salvarUsuarios() {
		this.salvarUsuarioAdm();
		this.salvarUsuarioAluno();
		this.salvarUsuarioProfessor();
	}

	public void salvarTodos() {
		this.salvarUsuarios();
		this.salvarEnderecoTeste();
		this.salvarAlunoTeste();
		this.salvarCursoTeste();
		this.salvarProfessorTeste();
		this.salvarTurmaTeste();
		this.salvarDisciplinaTeste();
	}

	public void salvarDisciplinaEmenta() {
		System.out.println("SISALVO LOG | Classe: Main - Método: salvarDisciplinaEmenta()");
		Disciplina disciplinaRetorno = null;

		InputStream caminhoArquivo = null;

		ByteArrayOutputStream bos = null;

		try {
			/**
			 * Faz uma busca da Disciplina pelo ID
			 */
			disciplinaRetorno = this.getDisciplinaDAO().buscarDisciplinaPorId("51");

//			caminhoArquivo = Context.
//
//					bos = new ByteArrayOutputStream();

			byte[] arquivoBytes = FileUtils
					.getBytesFromFile("C:\\Users\\Izri Miranda\\Desktop\\SisAlvo Teste\\ArquivoTesteUpload.pdf");

			disciplinaRetorno.setEmentaDoc(arquivoBytes);

			this.getEM().merge(disciplinaRetorno);

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("SISALVO LOG | Classe: Main - Método: salvarDisciplinaEmenta() | Emensa Salva!");
	}

	public void salvarCursoTeste() {
		this.getEM().persist(this.getCurso());
		System.out.println("SISALVO LOG | Método: salvarCursoTeste() - Curso Salvo com Sucesso!");
	}

	public Curso getCurso() {
		Curso curso = new Curso();

		curso.setNomeCurso("Técnico em Enfermagem");
		curso.setCargaHoraria(500);
		curso.setCargaHorariaEstagio(300);
		curso.setDataCadastro(Calendar.getInstance());
		curso.setNomeUsuarioCadatro("Izrí Miranda");
		curso.setTipoCurso("Regular");

		return curso;
	}

	public void salvarProfessorTeste() {
		this.getEM().persist(this.getProfessor());
		System.out.println("SISALVO LOG | Método: salvarProfessorTeste() - Professor Salvo com Sucesso.");
	}

	public Professor getProfessor() {
		Professor professor = new Professor();

		professor.setNomeCompleto("Izrí Miranda");
		professor.setContatoCelular("31988802212");
		professor.setContatoWhatsapp("31988802212");
		professor.setDataCadastro(Calendar.getInstance());
		professor.setEmail("izri@outlook.com");
		professor.setIdade(33);
		professor.setNomeUsuarioCadastro("Izrí Miranda");
		professor.setSexo("Masculino");
		professor.setStatus("Ativo");
		professor.setUsuario(this.getUsuarioDAO().buscarUsuarioPorLogin("izriprofessor"));
		professor.setEndereco(this.getEnderecoDAO().buscarEnderecoTeste());

		return professor;
	}

	public Turma getTurma() {
		Turma turma = new Turma();

		turma.setAno("2021");
		turma.setCapacidade(30);
		turma.setCurso(this.getCursoDAO().buscarCursoTeste());
		turma.setDataInicioStr("01/01/2021");
		turma.setDataTerminoStr("01/01/2023");
		turma.setNomeTurma("Enfermagem 01");
		turma.setPeriodo("Manhã");
		turma.setSerie(1);
		turma.setSiglaTurma("ENF01");

		return turma;
	}

	public Turma getTurma2() {
		Turma turma = new Turma();

		turma.setAno("2021");
		turma.setCapacidade(40);
		turma.setCurso(this.getCursoDAO().buscarCursoTeste());
		turma.setDataInicioStr("01/01/2021");
		turma.setDataTerminoStr("01/01/2023");
		turma.setNomeTurma("Enfermagem 02");
		turma.setPeriodo("Noturno");
		turma.setSerie(2);
		turma.setSiglaTurma("ENF02");

		return turma;
	}

	public void salvarTurmaTeste() {
		this.getEM().persist(this.getTurma2());
		System.out.println("SISALVO LOG | Método: salvarTurmaTeste() - Turma Teste Salva com Sucesso.");
	}

	public void salvarDisciplinaTeste() {

		Disciplina disciplina = new Disciplina();

		disciplina.setCurso(this.getCursoDAO().buscarCursoTeste());
		disciplina.setDataCadastro(Calendar.getInstance());
		disciplina.setNomeDisciplina("Enfermagem Aplicada");
		disciplina.setSerie(1);
		disciplina.setNomeUsuarioCadastro("Izrí Miranda");
		disciplina.setSiglaDisciplina("DEP");
		disciplina.setTurma(this.getTurmaDAO().buscarTurmaPorNome("Enfermagem 01"));
		disciplina.setProfessor(this.getProfessorDAO().buscarProfessorTeste());

		disciplina.setListaDiarioPresenca(this.getListaDiarioPresenca(disciplina));

		this.getEM().persist(disciplina);

	}

	public List<Atividade> getListaAtividade(Disciplina disciplina) {
		List<Atividade> listaAtividade = new ArrayList<Atividade>();

		Atividade a = new Atividade();

		a.setDataAtividade(DateUtils.parseToCalendar("10/10/2021", false));
		a.setNomeAtividade("Prova Avaliativa");
		a.setNomeUsuarioCad("izrialuno");
		a.setValorTotal(30.0);

		List<DiarioAvaliacao> listaDiario = new ArrayList<DiarioAvaliacao>();
		DiarioAvaliacao da = new DiarioAvaliacao();

		da.setAluno(this.getAlunoDAO().buscarAlunoPorLogin("izrialuno"));
		da.setDataAtividade(DateUtils.parseToCalendar("10/10/2021", false));
		da.setObservacao("Prova Avaliativa");
		da.setValorTotal(30.0);
		da.setValorObtido(20.0);

		listaDiario.add(da);

		a.getListaDiarioAvaliacao().addAll(listaDiario);

		listaAtividade.add(a);

		Atividade a2 = new Atividade();

		a2.setDataAtividade(DateUtils.parseToCalendar("10/10/2021", false));
		a2.setNomeAtividade("Prova Prática");
		a2.setNomeUsuarioCad("izrialuno");
		a2.setValorTotal(50.0);

		List<DiarioAvaliacao> listaDiario2 = new ArrayList<DiarioAvaliacao>();
		DiarioAvaliacao da2 = new DiarioAvaliacao();

		da2.setAluno(this.getAlunoDAO().buscarAlunoPorLogin("izrialuno"));
		da2.setDataAtividade(DateUtils.parseToCalendar("01/03/2021", false));
		da2.setObservacao("Prova Prática");
		da2.setValorTotal(50.0);
		da2.setValorObtido(50.0);

		listaDiario2.add(da2);

		a2.getListaDiarioAvaliacao().addAll(listaDiario2);

		listaAtividade.add(a2);

		return listaAtividade;
	}

	public List<DiarioAvaliacao> getListaDiarioAvaliacao() {
		List<DiarioAvaliacao> listaDiario = new ArrayList<DiarioAvaliacao>();
		DiarioAvaliacao da = new DiarioAvaliacao();

		da.setAluno(this.getAlunoDAO().buscarAlunoPorLogin("izrialuno"));
		da.setDataAtividade(DateUtils.parseToCalendar("10/10/2021", false));
		da.setObservacao("Prova Avaliativa");
		da.setValorTotal(30.0);
		da.setValorObtido(20.0);

		listaDiario.add(da);

		DiarioAvaliacao da2 = new DiarioAvaliacao();

		da2.setAluno(this.getAlunoDAO().buscarAlunoPorLogin("izrialuno"));
		da2.setDataAtividade(DateUtils.parseToCalendar("01/03/2021", false));
		da2.setObservacao("Prova Prática");
		da2.setValorTotal(50.0);
		da2.setValorObtido(50.0);

		listaDiario.add(da2);

		return listaDiario;
	}

	public List<DiarioPresenca> getListaDiarioPresenca(Disciplina disciplina) {
		List<DiarioPresenca> listaDiario = new ArrayList<DiarioPresenca>();
		DiarioPresenca dp = new DiarioPresenca();

		dp.setAluno(this.getAlunoDAO().buscarAlunoPorLogin("izrialuno"));
		dp.setDataAula(DateUtils.parseToCalendar("01/01/2021", false));
		dp.setDisciplina(disciplina);
		dp.setPresenca(true);

		listaDiario.add(dp);

		DiarioPresenca dp2 = new DiarioPresenca();

		dp2.setAluno(this.getAlunoDAO().buscarAlunoPorLogin("izrialuno"));
		dp2.setDataAula(DateUtils.parseToCalendar("02/01/2021", false));
		dp2.setDisciplina(disciplina);
		dp2.setPresenca(true);

		listaDiario.add(dp2);

		return listaDiario;

	}

	public void salvarUsuarioProfessor() {

		this.getEM().persist(this.getUsuarioProfessor());

		System.out.println("SISALVO LOG || Método: salvarUsuarioProfessor() - Usuário Professor Salvo!");
	}

	public Usuario getUsuarioProfessor() {
		Usuario usuario = new Usuario();

		usuario.setLogin("izriprofessor");
		usuario.setEmail("izri@outlook.com");
		usuario.setSenha(Security.convertStringToMd5("1234"));
		usuario.setDataCadastro(Calendar.getInstance());
		usuario.setNomeUsuarioCadastro("Izrí Miranda");
		usuario.setTipoUsuario(5);

		return usuario;
	}

	public Usuario getUsuarioAdm() {
		Usuario usuario = new Usuario();

		usuario.setLogin("izriadm");
		usuario.setEmail("izri@outlook.com");
		usuario.setSenha(Security.convertStringToMd5("1234"));
		usuario.setDataCadastro(Calendar.getInstance());
		usuario.setNomeUsuarioCadastro("Izrí Miranda");
		usuario.setTipoUsuario(1);

		return usuario;
	}

	public void salvarUsuarioAdm() {

		System.out.println("SISALVO LOG | salvarUsuarioAdm()");

//		if (this.getEM().isOpen()) {

		this.getEM().persist(this.getUsuarioAdm());

//		} else {
//			this.getTransaction().begin();
//
//			this.getUsuario().setLogin("izriadm");
//			this.getUsuario().setEmail("izri@outlook.com");
//			this.getUsuario().setSenha(Security.convertStringToMd5("1234"));
//			this.getUsuario().setDataCadastro(Calendar.getInstance());
//			this.getUsuario().setNomeUsuarioCadastro("Izrí Miranda");
//			this.getUsuario().setTipoUsuario(1);
//
//			this.getEM().persist(this.getUsuario());
//		}

		System.out.println("SISALVO LOG || Método: salvarUsuarioAdm() - Usuário ADM Salvo!");

	}

	public Usuario getUsuarioAluno() {
		Usuario usuario = new Usuario();

		usuario.setLogin("izrialuno");
		usuario.setEmail("izri@outlook.com");
		usuario.setSenha(Security.convertStringToMd5("1234"));
		usuario.setDataCadastro(Calendar.getInstance());
		usuario.setNomeUsuarioCadastro("Izrí Miranda");
		usuario.setTipoUsuario(4);

		return usuario;
	}

	public void salvarUsuarioAluno() {

		this.getEM().persist(this.getUsuarioAluno());

		System.out.println("SISALVO LOG || Método: salvarUsuarioAluno() - Usuario Aluno Salvo!");

	}

	public Endereco getEndereco() {
		Endereco endereco = new Endereco();

		endereco.setBairro("Campinho");
		endereco.setCEP("33600000");
		endereco.setCidade("Pedro Leopoldo");
		endereco.setComplemento("Bloco E/203");
		endereco.setLogradouro("Rua Agenor Teixeira da Costa");
		endereco.setNumero("425");
		endereco.setUF("MG");

		return endereco;
	}

	public void salvarEnderecoTeste() {

		this.getEM().persist(this.getEndereco());

		System.out.println("SISALVO LOG || Mètodo: salvarEnderecoTeste() - Endereço Salvo com Sucesso!");

	}

	public Aluno getAluno() {
		Aluno aluno = new Aluno();

		aluno.setContatoCelular("31988802212");
		aluno.setContatoWhatsapp("31988802212");
		aluno.setCPF("08636240654");
		aluno.setDataCadastro(Calendar.getInstance());
		aluno.setDataNascimentoStr("03/02/1988");
		aluno.setEmail("izri@outlook.com");
		aluno.setEndereco(this.getEnderecoDAO().buscarEnderecoTeste());
		aluno.setFiliacaoMaterna("Rosangela Dias Bruno Miranda");
		aluno.setFiliacaoPaterna("Luciano Vieira Miranda");
		aluno.setIdade(33);
		aluno.setNaturalidade("Carlos Chagas");
		aluno.setNomeCompleto("Izrí Bruno Miranda");
		aluno.setNomeUsuarioCadastro("izrimiranda");
		aluno.setNumIdentidade("15260976");
		aluno.setNumRegistroAluno(1);
		aluno.setPNE(false);
		aluno.setSexo("Masculiino");
		aluno.setStatus("Ativo");
		aluno.setUsuario(this.getUsuarioDAO().buscarUsuarioPorLogin("izrialuno"));

		return aluno;
	}

	public void salvarAlunoTeste() {

		// this.getTransaction().begin();
//		this.getEM().persist(this.getAluno().getUsuario());
//		this.getEM().persist(this.getAluno().getTurma().getCurso());
//		this.getEM().persist(this.getAluno().getTurma());
//		this.getEM().persist(this.getAluno().getEndereco());
		this.getEM().persist(this.getAluno());

		System.out.println("SISALVO LOG || Método: salvarAlunoTeste() - Aluno salvo!");

	}

	public void verificaConexao() {
		if (this.getEM().isOpen()) {
			System.out.println("SISALVO LOG | Conexação com o banco ainda está aberta.");
		} else {
			System.out.println("SISALVO LOG | Conexação com o banco ainda está fechada.");
		}
	}

	@Override
	public EntityTransaction getTransaction() {
		return EntityManagerUtil.JpaEntityManager().getTransaction();
	}

	@Override
	public EntityManager getEM() {
		return EntityManagerUtil.JpaEntityManager();
	}

	@Override
	public UsuarioDAO getUsuarioDAO() {
		if (this.usuarioDAO == null) {
			this.usuarioDAO = new UsuarioDAO();
		}
		return this.usuarioDAO;
	}

	@Override
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	@Override
	public EnderecoDAO getEnderecoDAO() {
		if (this.enderecoDAO == null) {
			this.enderecoDAO = new EnderecoDAO();
		}
		return this.enderecoDAO;
	}

	@Override
	public void setEnderecoDAO(EnderecoDAO enderecoDAO) {
		this.enderecoDAO = enderecoDAO;
	}

	@Override
	public CursoDAO getCursoDAO() {
		if (this.cursoDAO == null) {
			this.cursoDAO = new CursoDAO();
		}
		return this.cursoDAO;
	}

	@Override
	public void setCursoDAO(CursoDAO cursoDAO) {
		this.cursoDAO = cursoDAO;
	}

	@Override
	public TurmaDAO getTurmaDAO() {
		if (this.turmaDAO == null) {
			this.turmaDAO = new TurmaDAO();
		}
		return this.turmaDAO;
	}

	@Override
	public void setTurmaDAO(TurmaDAO turmaDAO) {
		this.turmaDAO = turmaDAO;
	}

	@Override
	public ProfessorDAO getProfessorDAO() {
		if (this.professorDAO == null) {
			this.professorDAO = new ProfessorDAO();
		}
		return this.professorDAO;
	}

	@Override
	public void setProfessorDAO(ProfessorDAO professorDAO) {
		this.professorDAO = professorDAO;
	}
}
