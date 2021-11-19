package br.com.agencia1615.sisalvo.view.vo;

public class LoginConfig {

	/*
	 * Nao eh uma entidade persistente. Um Value Object serve apenas para
	 * transportar dados entre as camadas.
	 */

	private String logoAlvo;
	private String textoRodaPe;
	private String icone;

	public String getLogoAlvo() {
		return this.logoAlvo;
	}

	public void setLogoAlvo(String logoAlvo) {
		this.logoAlvo = logoAlvo;
	}

	public String getTextoRodaPe() {
		return this.textoRodaPe;
	}

	public void setTextoRodaPe(String textoRodaPe) {
		this.textoRodaPe = textoRodaPe;
	}

	public String getIcone() {
		return this.icone;
	}

	public void setIcone(String icone) {
		this.icone = icone;
	}

}
