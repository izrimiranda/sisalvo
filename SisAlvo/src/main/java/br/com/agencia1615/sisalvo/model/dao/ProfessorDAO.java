package br.com.agencia1615.sisalvo.model.dao;

import java.io.Serializable;

import javax.persistence.TypedQuery;

import br.com.agencia1615.sisalvo.model.entity.Professor;
import br.com.agencia1615.sisalvo.util.EntityManagerUtil;

public class ProfessorDAO extends BaseDAO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 830081604913526706L;

	public Professor buscarProfessorTeste() {
		Professor professor = null;
		TypedQuery<Professor> query = null;

		try {
			query = EntityManagerUtil.JpaEntityManager().createNamedQuery(Professor.FIND_ALL, Professor.class);
			query.setParameter("excluido", false);

			professor = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return professor;
	}

}
