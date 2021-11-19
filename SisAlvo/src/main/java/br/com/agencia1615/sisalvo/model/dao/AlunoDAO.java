package br.com.agencia1615.sisalvo.model.dao;

import java.io.Serializable;

import javax.persistence.TypedQuery;

import br.com.agencia1615.sisalvo.model.entity.Aluno;
import br.com.agencia1615.sisalvo.util.EntityManagerUtil;

/**
 *
 * @author Izri Miranda
 *
 */
public class AlunoDAO extends BaseDAO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 4782572003264475498L;

	public Aluno buscarAlunoPorId(Aluno aluno) {
		Aluno retornoBanco = null;
		TypedQuery<Aluno> query = null;

		try {
			retornoBanco = new Aluno();

			query = EntityManagerUtil.JpaEntityManager().createNamedQuery(Aluno.FIND_BY_ID, Aluno.class);
			query.setParameter("id", aluno.getId());
			query.setParameter("excluido", false);

			retornoBanco = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return retornoBanco;
	}

	public Aluno buscarAlunoPorLogin(String login) {
		Aluno retornoBanco = null;

		TypedQuery<Aluno> query;

		try {
			query = EntityManagerUtil.JpaEntityManager().createNamedQuery(Aluno.FIND_BY_USUARIO_LOGIN, Aluno.class);
			query.setParameter("login", login.trim());
			query.setParameter("excluido", false);

			retornoBanco = query.getResultList().get(0);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return retornoBanco;
	}

	public Aluno buscarAlunoPorEmail(String email) {
		Aluno retornoBanco = null;

		TypedQuery<Aluno> query;

		try {
			query = EntityManagerUtil.JpaEntityManager().createNamedQuery(Aluno.FIND_BY_EMAIL, Aluno.class);
			query.setParameter("email", email);
			query.setParameter("excluido", false);

			if (!query.getResultList().isEmpty()) {
				retornoBanco = query.getSingleResult();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return retornoBanco;
	}

	public boolean isEmailExiste(String email) {
		System.out.println("SISALVO LOG >>> isEmailExiste(String email)");
		boolean verificador = false;

		TypedQuery<Aluno> query;

		try {
			query = EntityManagerUtil.JpaEntityManager().createNamedQuery(Aluno.FIND_BY_EMAIL, Aluno.class);

			query.setParameter("email", email);
			query.setParameter("excluido", false);

			System.out.println("SISALVO LOG >>> Lista Retorno: " + query.getResultList().isEmpty());
			if (!query.getResultList().isEmpty()) {
				System.out.println("SISALVO LOG >>> Lista retorno banco vazia!");
				verificador = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return verificador;
	}

}
