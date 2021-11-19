package br.com.agencia1615.sisalvo.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javax.el.MethodExpression;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import br.com.agencia1615.sisalvo.reader.Message;

public class FacesUtil {

	private static List<String> listaBeansInSession;

	/*
	 * Obtem o tipo de mensagem lancada pelo FacesMessage.
	 * ---------------------------------------------------
	 */
	public static boolean containsMsg(String key) {
		final List<FacesMessage> list = FacesContext.getCurrentInstance().getMessageList();

		for (final FacesMessage fm : list) {
			if (fm.getSummary().contains(key) && fm.getSeverity().toString().contains("WARN")) {
				return true;
			}
		}

		return false;
	}

	/*
	 * Redireciona para uma pagina qualquer (forward).
	 * -----------------------------------------------
	 */
	public static void forward(String url) {
		final FacesContext ctx = FacesContext.getCurrentInstance();
		ctx.getApplication().getNavigationHandler().handleNavigation(ctx, null, url);
		FacesContext.getCurrentInstance().responseComplete();
	}

	/*
	 * Retorna o nome do contexto precedido da barra "/"
	 * -------------------------------------------------
	 */
	public static String getContextName() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
	}

	/*
	 * Retorna o caminho completo de algum recurso. Recebe o caminho do contexto
	 * web: Este param pode ser algo do tipo:"/WEB-INF/classes", por exemplo, e
	 * devolve algo do tipo: "C:/cursoJava/blankapp/webapp/WEB-INF/classes
	 * ----------------------------------------------------------------------
	 */
	public static String getFullPath(String path) {
		final ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
				.getContext();

		return servletContext.getRealPath(path);
	}

	/*
	 * Retorna a key de alguma string de mensagem.
	 * -------------------------------------------
	 */
	public static String getKey(String message) {
		final ResourceBundle rs = ResourceBundle.getBundle("messages",
				FacesContext.getCurrentInstance().getViewRoot().getLocale());
		final Enumeration<?> keys = rs.getKeys();
		while (keys.hasMoreElements()) {
			final String key = (String) keys.nextElement();
			if (rs.getString(key).equals(message)) {
				return key;
			}
		}
		return null;
	}

	/*
	 * Recuperando o idioma ---------------------
	 */
	public static Locale getLocale() {
		return FacesContext.getCurrentInstance().getViewRoot().getLocale();
	}

	/*
	 * Obtem uma lista de todos os arquivos messages.properties do sistema. Esses
	 * arquivos teem de estar sob o diretorio source da app
	 * ----------------------------------------------------------
	 */
	public static List<String> getLocales() {
		final List<String> locales = new ArrayList<String>();
		final FacesContext ctx = FacesContext.getCurrentInstance();
		final Iterator<?> it = ctx.getApplication().getSupportedLocales();
		locales.add(ctx.getApplication().getDefaultLocale().toString());

		while (it.hasNext()) {
			locales.add(it.next().toString());
		}
		return locales;
	}

	/*
	 * Obtem o tipo de mensagem do FacesMessage.
	 * -----------------------------------------
	 */
	public static Severity getMaximumSeverity() {
		return FacesContext.getCurrentInstance().getMaximumSeverity();
	}

	/*
	 * MENSAGENS SEM PARAMETROS Utilizado intensivamente no processo de
	 * internacionalizacao. Obtem o valor da mensagem do arquivo properties passando
	 * a chave. -----------------------------------------------------------------
	 */
	public static String getMessage(String key) {
		final ResourceBundle rs = ResourceBundle.getBundle("messages",
				FacesContext.getCurrentInstance().getViewRoot().getLocale());

		if (rs.containsKey(key)) {
			return rs.getString(key);
		}
		return key + ": invalid key";
	}

	/*
	 * MENSAGENS COM PARAMETROS Utilizado intensivamente no processo de
	 * internacionalizacao. Obtem o valor da mensagem do arquivo properties,
	 * passando a chave e uma lista de parametros. ----------------------------
	 * ---------------------------------------------
	 */
	public static String getMessage(String key, String[] params) {
		final ResourceBundle rs = ResourceBundle.getBundle("messages",
				FacesContext.getCurrentInstance().getViewRoot().getLocale());
		String msg;
		if (rs.containsKey(key)) {
			msg = rs.getString(key);

		} else {
			msg = key + ": invalid key";
		}

		for (int i = 0; i < params.length; i++) {
			final String param = params[i];
			final String regex = "{" + i + "}";
			msg = msg.replace(regex, param);
		}
		return msg;
	}

	/*
	 * Obtem todas as mensagens setadas no FacesMessage.
	 * -------------------------------------------------
	 */
	public static List<FacesMessage> getMessageList() {
		return FacesContext.getCurrentInstance().getMessageList();
	}

	/*
	 * Utilizado para setar uma action em algum componente da pagina, dinamicamente.
	 * -----------------------------------------------------------------------------
	 */
	public static MethodExpression getMethodExpression(String action) {
		final FacesContext ctx = FacesContext.getCurrentInstance();
		final MethodExpression methodExpression = FacesContext.getCurrentInstance().getApplication()
				.getExpressionFactory().createMethodExpression(ctx.getELContext(), action, String.class, new Class[0]);

		return methodExpression;
	}

	/*
	 * recuperar um objeto da sessao -------------------------
	 */
	public static Object getObjectFromSession(String key) {
		final FacesContext ctx = FacesContext.getCurrentInstance();
		final HttpSession session = (HttpSession) ctx.getExternalContext().getSession(true);
		final Object value = session.getAttribute(key);
		session.removeAttribute(key);
		return value;
	}

	/*
	 * retorna o parametro existente no request (lancado pela pagina).
	 * ---------------------------------------------------------------
	 */
	public static String getParam(String param) {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(param);
	}

	/*
	 * Obtem o tipo de mensagem lancada pelo FacesMessage.
	 * ---------------------------------------------------
	 */
	public static String getSeverity() {
		final Severity severity = FacesContext.getCurrentInstance().getMaximumSeverity();
		if (severity != null) {
			return severity.toString();
		}
		return null;
	}

	/*
	 * Obtem a url renderizada no momento. -----------------------------------
	 */
	public static String getUrlInView() {
		final FacesContext ctx = FacesContext.getCurrentInstance();

		return ctx.getViewRoot().getViewId().substring(0, ctx.getViewRoot().getViewId().indexOf(".xhtml"));
	}

	/*
	 * Retorna o viewRoot (recurso sendo visualizado no momento).
	 * ----------------------------------------------------------
	 */
	public static String getViewRoot() {
		return FacesContext.getCurrentInstance().getViewRoot().getViewId();
	}

	/*
	 * Lista todos os beans que estao gerenciados em sessao.
	 * -----------------------------------------------------
	 */
	public static List<String> listarBeansInSession() {
		listaBeansInSession = new ArrayList<String>();
		Map<String, Object> beanMap = new HashMap<String, Object>();
		beanMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		final Iterator<?> it = beanMap.entrySet().iterator();
		while (it.hasNext()) {
			@SuppressWarnings("unchecked")
			final Map.Entry<String, Object> par = (Entry<String, Object>) it.next();
			listaBeansInSession.add(par.getKey());
		}
		return listaBeansInSession;
	}

	/*
	 * guardar um objeto na sessao --------------------------
	 */
	public static void putObjectInSession(String key, Object value) {
		final FacesContext ctx = FacesContext.getCurrentInstance();
		final HttpSession session = (HttpSession) ctx.getExternalContext().getSession(true);
		session.setAttribute(key, value);
	}

	/*
	 * Redireciona para uma pagina qualquer. -------------------------------------
	 */
	public static void redirect(String url) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(getContextName() + url + ".faces");
			FacesContext.getCurrentInstance().responseComplete();

		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * apagar um objeto da sessao ----------------------
	 */
	public static void removeObjectTheSession(String key) {
		final FacesContext ctx = FacesContext.getCurrentInstance();

		final HttpSession session = (HttpSession) ctx.getExternalContext().getSession(true);
		session.removeAttribute(key);
	}

	/*
	 * Retira todos os beans da sessao, exceto beanNotReset, o que não serah
	 * resetado.
	 * -----------------------------------------------------------------------------
	 * --
	 */
	public static void resetBeans(String beanNotReset) {

		final Map<String, Object> beanMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		final Iterator<?> it = beanMap.entrySet().iterator();

		while (it.hasNext()) {
			@SuppressWarnings("unchecked")
			final Map.Entry<String, Object> par = (Entry<String, Object>) it.next();

			// Nao resetar o bean "marcado para nao ser resetado" e o menu
			if ((beanNotReset != null) && par.getKey().toLowerCase().contains(beanNotReset.toLowerCase())) {
				continue;
			}
			if (par.getKey().toLowerCase().contains("menubean")) {
				continue;
			}

			// Nao resetar os beans default do EJB
			if (par.getKey().contains("ConversationIdGenerator")) {
				continue;
			}
			if (par.getKey().contains("conversations")) {
				continue;
			}
			if (par.getKey().contains("charset")) {
				continue;
			}
			if (par.getKey().contains("LockStore")) {
				continue;
			}
			if (par.getKey().contains("activeViewContexts")) {
				continue;
			}
			if (par.getKey().contains("activeViewMaps")) {
				continue;
			}
			if (par.getKey().contains("LogicalViewMap")) {
				continue;
			}
			if (par.getKey().contains("WELD")) {
				continue;
			}

			beanMap.remove(par.getKey());
			beanMap.remove(par.getValue());
		}
	}

	/*
	 * Invalidar a sessao. -------------------
	 */
	public static void sessionInvalidate() {
		final HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
				.getSession(false);

		session.invalidate();
	}

	/*
	 * Mudando o idioma ----------------
	 */
	public static void setLocale(Locale locale) {
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	}

	/*
	 * Adiciona a mensagem no contexto. Recebe FacesMessage como parametro.
	 * --------------------------------------------------------------------
	 */
	public static void setMessage(FacesMessage msg) {
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public static void setMessagemLoginSenhaInvalidos() {
		FacesUtil.setMessage(new FacesMessage(FacesMessage.SEVERITY_WARN, Message.getInstance().getMensagemErro(),
				Message.getInstance().getMensagemLoginSenhaInvalidos()));
	}

	public static void setMensagemFaces(Severity severity, String label, String mensagem) {
		FacesUtil.setMessage(new FacesMessage(severity, label, mensagem));
	}

}