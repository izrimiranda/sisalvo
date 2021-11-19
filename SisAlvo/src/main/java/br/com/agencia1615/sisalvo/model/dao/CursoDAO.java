package br.com.agencia1615.sisalvo.model.dao;

import java.io.Serializable;

import javax.persistence.TypedQuery;

import br.com.agencia1615.sisalvo.model.entity.Curso;
import br.com.agencia1615.sisalvo.util.EntityManagerUtil;

public class CursoDAO extends BaseDAO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 4752122057505302306L;

	public Curso buscarCursoPorId(Curso curso) {
		Curso cursoRetorno = null;
		TypedQuery<Curso> query = null;

		try {
			query = EntityManagerUtil.JpaEntityManager().createNamedQuery(Curso.FIND_BY_ID, Curso.class);
			query.setParameter("id", curso.getId());
			query.setParameter("excluido", false);

			cursoRetorno = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cursoRetorno;
	}

	public Curso buscarCursoTeste() {
		Curso curso = null;
		TypedQuery<Curso> query = null;

		try {
			query = EntityManagerUtil.JpaEntityManager().createNamedQuery(Curso.FIND_ALL, Curso.class);
			query.setParameter("excluido", false);

			curso = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return curso;
	}

}
