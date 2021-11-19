package br.com.agencia1615.sisalvo.model.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import br.com.agencia1615.sisalvo.model.entity.Aluno;
import br.com.agencia1615.sisalvo.model.entity.Curso;
import br.com.agencia1615.sisalvo.model.entity.Disciplina;
import br.com.agencia1615.sisalvo.model.entity.Endereco;
import br.com.agencia1615.sisalvo.model.entity.Turma;
import br.com.agencia1615.sisalvo.util.EntityManagerUtil;

/**
 *
 * @author Izri Miranda
 *
 */
public class TurmaDAO extends BaseDAO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 8550594017897858125L;

	public Turma buscarTurmaPorNome(String nome) {
		Turma turma = null;
		TypedQuery<Turma> query = null;

		try {
			query = EntityManagerUtil.JpaEntityManager().createNamedQuery(Turma.FIND_BY_NOME_TURMA, Turma.class);
			query.setParameter("nomeTurma", nome);
			query.setParameter("excluido", false);

			turma = query.getSingleResult();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return turma;
	}

	public void salvarTurmaTeste() {

		List<Aluno> listaAluno = new ArrayList<Aluno>();
		List<Disciplina> listaDisciplina = new ArrayList<Disciplina>();

		Turma turma = new Turma();
		Endereco endereco = new Endereco();
		Aluno aluno = new Aluno();
		Curso curso = new Curso();

		curso.setId(Long.valueOf(108));
		aluno.setId(Long.valueOf(35));
		endereco.setId(Long.valueOf(34));

		listaDisciplina.addAll(this.getDisciplinaDAO().buscarTodasDisciplinas());
		listaAluno.add(this.getAlunoDAO().buscarAlunoPorId(aluno));

		turma.setAno("2021");
		turma.setCapacidade(30);
		turma.setCurso(this.getCursoDAO().buscarCursoPorId(curso));
		turma.setDataInicioStr("08/08/2021");
		turma.setDataTerminoStr("08/08/2022");
		turma.setListaAluno(listaAluno);
		turma.setListaDisciplina(listaDisciplina);
		turma.setNomeTurma("Turma Enfermagem Teste");
		turma.setNomeUsuarioCadastro("Izrí Miranda");
		turma.setPeriodo("Noturno");
		turma.setSerie(1);
		turma.setSiglaTurma("T1");

		this.getEM().persist(turma.getCurso());

		for (Aluno a : turma.getListaAluno()) {
			a.setTurma(turma);
			a.setEndereco(this.getEnderecoDAO().buscarEnderecoPorId(endereco));
			a.setUsuario(this.buscarUsuarioPorLogin("izrialuno"));

			System.out.println("SISALVO LOG | Endereço Rua: " + a.getEndereco().getLogradouro());
			this.getEM().persist(a.getUsuario());
			this.getEM().persist(a.getEndereco());
			this.getEM().persist(a.getTurma());
			this.getEM().persist(a);
		}

		for (Disciplina d : turma.getListaDisciplina()) {
			d.setTurma(turma);
			this.getEM().persist(d);
		}

		this.getEM().persist(turma);

		System.out.println("SISALVO LOG | Classe: TurmaDAO - Método: salvarTurmaTeste() | Turma Salva com sucesso.");
	}

}
