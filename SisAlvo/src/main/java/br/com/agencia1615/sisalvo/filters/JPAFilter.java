package br.com.agencia1615.sisalvo.filters;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/***
 * ESSE FILTER VAI SER CHAMADO TODA VEZ QUE FOR REALIZADO UMA REQUISI��O PARA O
 * FACES SERVLET.
 */
@WebFilter(servletNames = { "Faces Servlet" })
public class JPAFilter implements Filter {

	private EntityManagerFactory entityManagerFactory;

	private final String persistenceUnitName = "unitSisAlvo";

	public JPAFilter() {

	}

	@Override
	public void destroy() {

		this.entityManagerFactory.close();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		/* CRIANDO UM ENTITYMANAGER */
		final EntityManager entityManager = this.entityManagerFactory.createEntityManager();

		/* ADICIONANDO ELE NA REQUISI��O */
		request.setAttribute("entityManager", entityManager);

		/* INICIANDO UMA TRANSA��O */
		entityManager.getTransaction().begin();

		/* INICIANDO FACES SERVLET */
		chain.doFilter(request, response);

		try {

			/* SE N�O TIVER ERRO NA OPERA��O ELE EXECUTA O COMMIT */
			entityManager.getTransaction().commit();

		} catch (final Exception e) {

			/* SE TIVER ERRO NA OPERA��O � EXECUTADO O rollback */
			entityManager.getTransaction().rollback();
		} finally {

			/* DEPOIS DE DAR O COMMIT OU ROLLBACK ELE FINALIZA O entityManager */
			entityManager.close();
		}
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {

		/* CRIA O entityManagerFactory COM OS PAR�METROS DEFINIDOS NO persistence.xml */
		this.entityManagerFactory = Persistence.createEntityManagerFactory(this.persistenceUnitName);
	}

}