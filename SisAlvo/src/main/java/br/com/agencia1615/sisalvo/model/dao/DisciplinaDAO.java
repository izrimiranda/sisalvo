package br.com.agencia1615.sisalvo.model.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import br.com.agencia1615.sisalvo.model.entity.Disciplina;
import br.com.agencia1615.sisalvo.util.EntityManagerUtil;

/**
 *
 * @author Izri Miranda
 *
 */
public class DisciplinaDAO extends BaseDAO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 4892023536095671345L;

	public Disciplina buscarDisciplinaPorId(String id) {
		Disciplina disciplinaRetorno = null;
		TypedQuery<Disciplina> query = null;

		try {
			query = EntityManagerUtil.JpaEntityManager().createNamedQuery(Disciplina.FIND_BY_ID, Disciplina.class);
			query.setParameter("id", Long.parseLong(id));
			query.setParameter("excluido", false);

			disciplinaRetorno = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return disciplinaRetorno;
	}

	public List<Disciplina> buscarTodasDisciplinas() {
		List<Disciplina> listaDisciplinaRetorno = null;
		TypedQuery<Disciplina> query = null;

		try {
			listaDisciplinaRetorno = new ArrayList<Disciplina>();
			query = EntityManagerUtil.JpaEntityManager().createNamedQuery(Disciplina.FIND_ALL, Disciplina.class);
			query.setParameter("excluido", false);

			listaDisciplinaRetorno.addAll(query.getResultList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaDisciplinaRetorno;
	}

	public List<Disciplina> buscarListaDisciplinaPorAluno() {
		List<Disciplina> listaRetornoBanco = null;
		TypedQuery<Disciplina> query = null;

		try {
			// query = EntityManagerUtil.JpaEntityManager().createNamedQuery(null, null)
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
