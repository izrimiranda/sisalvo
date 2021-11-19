package br.com.agencia1615.sisalvo.view.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.agencia1615.sisalvo.view.vo.LoginConfig;

/**
 *
 * @author Izri Miranda
 *
 */
@Named
@SessionScoped
public class LoginConfigBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 4464583011191438252L;

	private LoginConfig loginConfig;
	private static final String PATH_IMAGES = "#{facesContext.externalContext.requestContextPath}/resources/images/";
	private static final String PATH_LOGOS = "#{facesContext.externalContext.requestContextPath}/resources/logos/";

	private void setConfigurações() {

		this.loginConfig = new LoginConfig();

		this.loginConfig.setLogoAlvo(LoginConfigBean.PATH_LOGOS + "logoAlvo.png"); // ....................Imagem
																					// logo-tipo da empresa.
		this.loginConfig.setIcone(LoginConfigBean.PATH_IMAGES + "iconeAlvo.ico"); // .......................Icone da
																					// Página.
		this.loginConfig.setTextoRodaPe(
				"Política de Privacidade | Agência 16:15 | Copyright \u00A9 2021 -" + " Todos os direitos reservados");
	}

	public LoginConfig getLoginConfig() {
		if (this.loginConfig == null) {
			this.setConfigurações();
		}
		return this.loginConfig;
	}

	public void setLoginConfig(LoginConfig loginConfig) {
		this.loginConfig = loginConfig;
	}
}
