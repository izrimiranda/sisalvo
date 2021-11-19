package br.com.agencia1615.sisalvo.model.dao;

import java.io.Serializable;

import javax.persistence.TypedQuery;

import br.com.agencia1615.sisalvo.model.entity.Endereco;
import br.com.agencia1615.sisalvo.util.EntityManagerUtil;

public class EnderecoDAO extends BaseDAO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -5690803017261621672L;

	public Endereco buscarEnderecoPorId(Endereco endereco) {
		Endereco retornoBanco = null;
		TypedQuery<Endereco> query = null;

		try {

			query = EntityManagerUtil.JpaEntityManager().createNamedQuery(Endereco.FIND_BY_ID, Endereco.class);
			query.setParameter("id", endereco.getId());
			query.setParameter("excluido", false);

			retornoBanco = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return retornoBanco;
	}

	public Endereco buscarEnderecoTeste() {
		Endereco endereco = null;
		TypedQuery<Endereco> query = null;

		try {
			query = EntityManagerUtil.JpaEntityManager().createNamedQuery(Endereco.FIND_ALL, Endereco.class);
			query.setParameter("excluido", false);

			endereco = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return endereco;
	}

}
