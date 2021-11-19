package br.com.agencia1615.sisalvo.model.dao;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import br.com.agencia1615.sisalvo.model.UsuarioModel;
import br.com.agencia1615.sisalvo.model.entity.Aluno;
import br.com.agencia1615.sisalvo.model.entity.Usuario;
import br.com.agencia1615.sisalvo.util.EntityManagerUtil;

/**
 *
 * @author Izri Miranda
 *
 */
public class BaseDAO implements Serializable {

	public enum ENUNS {
		EXCLUIDO("excluido");

		private String enuns;

		private ENUNS(String enuns) {
			this.enuns = enuns;
		}
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 6893610133564423996L;

	private CursoDAO cursoDAO;
	private TurmaDAO turmaDAO;
	private AlunoDAO alunoDAO;
	private DisciplinaDAO disciplinaDAO;
	private UsuarioDAO usuarioDAO;
	private EnderecoDAO enderecoDAO;
	private ProfessorDAO professorDAO;

	public void setEnderecoDAO(EnderecoDAO enderecoDAO) {
		this.enderecoDAO = enderecoDAO;
	}

	public EnderecoDAO getEnderecoDAO() {
		if (this.enderecoDAO == null) {
			this.enderecoDAO = new EnderecoDAO();
		}
		return this.enderecoDAO;
	}

	public EntityTransaction getTransaction() {
		return EntityManagerUtil.JpaEntityManager().getTransaction();
	}

	public EntityManager getEM() {
		return EntityManagerUtil.JpaEntityManager();
	}

	public UsuarioModel getUsuarioSession() {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		return (UsuarioModel) facesContext.getExternalContext().getSessionMap().get("usuarioAutenticado");
	}

	public Usuario buscarUsuarioPorLogin(String login) {
		Usuario retornoBanco = null;

		TypedQuery<Usuario> query;

		try {
			query = EntityManagerUtil.JpaEntityManager().createNamedQuery(Usuario.FIND_BY_LOGIN, Usuario.class);
			query.setParameter("login", login);
			query.setParameter("excluido", false);

			retornoBanco = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return retornoBanco;
	}

	public Aluno buscarAlunoLogado() {
		UsuarioModel usuarioModel = this.getUsuarioSession();
		Aluno alunoRetorno = null;

		TypedQuery<Aluno> query;

		try {
			query = EntityManagerUtil.JpaEntityManager().createNamedQuery(Aluno.FIND_BY_USUARIO_LOGIN, Aluno.class);
			query.setParameter("login", usuarioModel.getLogin());
			query.setParameter("excluido", false);

			alunoRetorno = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alunoRetorno;
	}

	public CursoDAO getCursoDAO() {
		if (this.cursoDAO == null) {
			this.cursoDAO = new CursoDAO();
		}
		return this.cursoDAO;
	}

	public void setCursoDAO(CursoDAO cursoDAO) {
		this.cursoDAO = cursoDAO;
	}

	public TurmaDAO getTurmaDAO() {
		if (this.turmaDAO == null) {
			this.turmaDAO = new TurmaDAO();
		}
		return this.turmaDAO;
	}

	public void setTurmaDAO(TurmaDAO turmaDAO) {
		this.turmaDAO = turmaDAO;
	}

	public AlunoDAO getAlunoDAO() {
		if (this.alunoDAO == null) {
			this.alunoDAO = new AlunoDAO();
		}
		return this.alunoDAO;
	}

	public void setAlunoDAO(AlunoDAO alunoDAO) {
		this.alunoDAO = alunoDAO;
	}

	public DisciplinaDAO getDisciplinaDAO() {
		if (this.disciplinaDAO == null) {
			this.disciplinaDAO = new DisciplinaDAO();
		}
		return this.disciplinaDAO;
	}

	public void setDisciplinaDAO(DisciplinaDAO disciplinaDAO) {
		this.disciplinaDAO = disciplinaDAO;
	}

	public UsuarioDAO getUsuarioDAO() {
		if (this.usuarioDAO == null) {
			this.usuarioDAO = new UsuarioDAO();
		}
		return this.usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public ProfessorDAO getProfessorDAO() {
		if (this.professorDAO == null) {
			this.professorDAO = new ProfessorDAO();
		}
		return this.professorDAO;
	}

	public void setProfessorDAO(ProfessorDAO professorDAO) {
		this.professorDAO = professorDAO;
	}

}
