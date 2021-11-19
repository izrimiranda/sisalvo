package br.com.agencia1615.sisalvo.model;

import java.io.Serializable;

/**
 *
 * @author Izri Miranda
 *
 */
public class UsuarioModel implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 4031816564812727811L;

	private String login;
	private String senha;

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String testeModel() {
		System.out.println("SISALVO LOG | Teste Model!");

		return "";
	}
}
