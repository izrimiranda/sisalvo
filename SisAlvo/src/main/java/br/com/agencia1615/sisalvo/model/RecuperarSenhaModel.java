package br.com.agencia1615.sisalvo.model;

import java.io.Serializable;

/**
 *
 * @author Izri Miranda
 *
 */
public class RecuperarSenhaModel implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 8406657451568382314L;

	private String email;
	private String senha;
	private String novaSenha;

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNovaSenha() {
		return this.novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

}
