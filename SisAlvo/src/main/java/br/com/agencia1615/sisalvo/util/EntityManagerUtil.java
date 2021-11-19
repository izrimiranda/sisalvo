package br.com.agencia1615.sisalvo.util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Izri Miranda
 *
 */
public class EntityManagerUtil {

	public static EntityManager JpaEntityManager() {

		final FacesContext facesContext = FacesContext.getCurrentInstance();

		final ExternalContext externalContext = facesContext.getExternalContext();

		final HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

		return (EntityManager) request.getAttribute("entityManager");
	}

}
