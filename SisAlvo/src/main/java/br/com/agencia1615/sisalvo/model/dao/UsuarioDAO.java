package br.com.agencia1615.sisalvo.model.dao;

import java.io.Serializable;

import javax.persistence.TypedQuery;

import br.com.agencia1615.sisalvo.model.entity.Usuario;
import br.com.agencia1615.sisalvo.util.EntityManagerUtil;
import br.com.agencia1615.sisalvo.util.Security;

/**
 *
 * @author Izri Miranda
 *
 */
public class UsuarioDAO extends BaseDAO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -3209517134373370974L;

	public Usuario validaUsuario(Usuario usuario) {

		Usuario retornoBanco = null;

		TypedQuery<Usuario> query;

		try {
			query = EntityManagerUtil.JpaEntityManager().createNamedQuery(Usuario.FIND_BY_LOGIN_SENHA, Usuario.class);

			query.setParameter("login", usuario.getLogin());
			query.setParameter("senha", Security.convertStringToMd5(usuario.getSenha()));
			query.setParameter("excluido", false);

			if (!query.getResultList().isEmpty()) {
				retornoBanco = query.getSingleResult();
			} else {
				System.out.println("SISALVO LOG | Lista retorno banco vazia...");
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}

		return retornoBanco;
	}

}
