package br.com.agencia1615.sisalvo.view.bean;

import java.io.Serializable;
import java.util.Locale;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.agencia1615.sisalvo.util.FacesUtil;

@Named
@SessionScoped
public class UserSessionInBean implements Serializable {

	private static final long serialVersionUID = -2765013996497920460L;

	// Dados de usuario comum do sistema
	private String pessoaLogged;
	private String userLogged;
	private boolean usuarioDevLogado;
	private Locale locale;

	public UserSessionInBean() {
		this.setLocale(FacesUtil.getLocale()); // Obtem o idioma local.
	}

	public Locale getLocale() {
		return this.locale;
	}

	/*--------
	 * get/set
	 --------*/
	public String getPessoaLogged() {
		return this.pessoaLogged;
	}

	public String getUserLogged() {
		return this.userLogged;
	}

	/*
	 * ********************* Licao 13 em diante... Utiliza o componente IdleMonitor
	 * do PrimeFaces para invalidar a sessão.
	 ***********************/
	public void idleListener() {
		FacesUtil.redirect("/pages/errorpages/viewExpired");
		FacesUtil.sessionInvalidate();
	}

	public boolean isUsuarioDevLogado() {
		return this.usuarioDevLogado;
	}

	/*
	 * Um botao de acao de uma pagina qualquer pode mudar o idioma, mas tem de
	 * passar um parametro para qual idioma a ser mudado.
	 * ------------------------------------------------------------
	 */
	public void mudarIdioma() {
		final String localeStr = FacesUtil.getParam("locale");

		if (localeStr.length() == 2) {
			this.locale = new Locale(localeStr);

		} else {
			this.locale = new Locale(localeStr.substring(0, 2), localeStr.substring(3));
		}

		FacesUtil.setLocale(this.locale);
		System.out.println(FacesUtil.getMessage("MGL064", new String[] { localeStr }));
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public void setPessoaLogged(String pessoaLogged) {
		this.pessoaLogged = pessoaLogged;
	}

	public void setUserLogged(String userLogged) {
		this.userLogged = userLogged;
	}

	public void setUsuarioDevLogado(boolean usuarioDevLogado) {
		this.usuarioDevLogado = usuarioDevLogado;
	}

}
