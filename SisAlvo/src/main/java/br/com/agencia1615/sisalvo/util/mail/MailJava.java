package br.com.agencia1615.sisalvo.util.mail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MailJava {

	public static final String TYPE_TEXT_PLAIN = "text/plain";
	public static final String TYPE_TEXT_HTML = "text/html";

	private String smtpHostMail;
	private String smtpPortMail;
	private String smtpAuth;
	private String smtpStarttls;
	private String fromNameMail;
	private String userMail;
	private String passMail;
	private String subjectMail;
	private String bodyMail;
	private Map<String, String> toMailsUsers;
	private List<String> fileMails;
	private String charsetMail;
	private String typeTextMail;

	public String getSmtpHostMail() {
		return this.smtpHostMail;
	}

	/**
	 * Indica qual sera o servidor de email(gmail, hotmail...)
	 *
	 * @return
	 */
	public void setSmtpHostMail(String smtpHostMail) {
		this.smtpHostMail = smtpHostMail;
	}

	public String getSmtpPortMail() {
		return this.smtpPortMail;
	}

	/**
	 * Indica a porta de acesso ao servidor.
	 *
	 * @return
	 */
	public void setSmtpPortMail(String smtpPortMail) {
		this.smtpPortMail = smtpPortMail;
	}

	public String getSmtpAuth() {
		return this.smtpAuth;
	}

	/**
	 * Indica que a necessidade de autenticacao no servidor(true ou false).
	 *
	 * @return
	 */
	public void setSmtpAuth(String smtpAuth) {
		this.smtpAuth = smtpAuth;
	}

	public String getSmtpStarttls() {
		return this.smtpStarttls;
	}

	/**
	 * Indica ao servidor que ele esta recebendo uma conexao segura
	 *
	 * @param smtpStarttls
	 */
	public void setSmtpStarttls(String smtpStarttls) {
		this.smtpStarttls = smtpStarttls;
	}

	public String getFromNameMail() {
		return this.fromNameMail;
	}

	/**
	 * Nome do remetente do email
	 *
	 * @param fromNameMail
	 */
	public void setFromNameMail(String fromNameMail) {
		this.fromNameMail = fromNameMail;
	}

	public String getUserMail() {
		return this.userMail;
	}

	/**
	 * Email do remetente.
	 *
	 * @param userMail
	 */
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getPassMail() {
		return this.passMail;
	}

	/**
	 * Senha do email do remetente
	 *
	 * @param passMail
	 */
	public void setPassMail(String passMail) {
		this.passMail = passMail;
	}

	public String getSubjectMail() {
		return this.subjectMail;
	}

	/**
	 * Assunto do email.
	 *
	 * @param subjectMail
	 */
	public void setSubjectMail(String subjectMail) {
		this.subjectMail = subjectMail;
	}

	public String getBodyMail() {
		return this.bodyMail;
	}

	/**
	 * Corpo do email, onde esta o texto da mensagem.
	 *
	 * @param bodyMail
	 */
	public void setBodyMail(String bodyMail) {
		this.bodyMail = bodyMail;
	}

	public Map<String, String> getToMailsUsers() {
		return this.toMailsUsers;
	}

	/**
	 * Lista com email e nome dos destinatarios.
	 *
	 * @param toMailsUsers
	 */
	public void setToMailsUsers(Map<String, String> toMailsUsers) {
		this.toMailsUsers = toMailsUsers;
	}

	public List<String> getFileMails() {
		if (this.fileMails == null) {
			this.fileMails = new ArrayList<String>();
		}
		return this.fileMails;
	}

	/**
	 * Lista contendo os arquivos anexos.
	 *
	 * @param fileMails
	 */
	public void setFileMails(List<String> fileMails) {
		this.fileMails = fileMails;
	}

	public String getCharsetMail() {
		return this.charsetMail;
	}

	/**
	 * Charset, no caso de html e necessario.
	 *
	 * @param charsetMail
	 */
	public void setCharsetMail(String charsetMail) {
		this.charsetMail = charsetMail;
	}

	public String getTypeTextMail() {
		return this.typeTextMail;
	}

	/**
	 * Tipo do formato da mensagem, texto ou html.
	 *
	 * @param typeTextMail
	 */
	public void setTypeTextMail(String typeTextMail) {
		this.typeTextMail = typeTextMail;
	}
}
