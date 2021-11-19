package br.com.agencia1615.sisalvo.reader;

import br.com.agencia1615.sisalvo.util.PropertiesReader;

public class Message {

	private final PropertiesReader reader;

	private static Message instance;

	private Message() {
		this.reader = new PropertiesReader("messages_pt_BR");
	}

	public static Message getInstance() {
		if (Message.instance == null) {
			Message.instance = new Message();
		}

		return Message.instance;
	}

	protected String getProperty(String key, Object... args) {
		return this.reader.getProperty(key, args);
	}

	/**
	 * Mensagem
	 *
	 * @return String: Email enviado com sucesso!
	 */
	public String getMensagemEmailEnviadoSucesso() {
		return this.getProperty("MPF009");
	}

	/**
	 * Mensagem
	 *
	 * @return String: "Mensagem de erro".
	 */
	public String getMensagemErro() {
		return this.getProperty("MPF001");
	}

	/**
	 * Mensagem
	 *
	 * @return String: Login e/ou senha inválidos!
	 */
	public String getMensagemLoginSenhaInvalidos() {
		return this.getProperty("MPF002");
	}

	/**
	 * Mensagem
	 *
	 * @return String: Email inválido. Verifique!
	 */
	public String getMensagemEmailInvalido() {
		return this.getProperty("MPF007");
	}

	/**
	 * Mensagem
	 *
	 * @return String: O email informado não existe na base de dados. Verifique!
	 */
	public String getMensagemEmailNaoExiste() {
		return this.getProperty("MPF008");
	}

	/**
	 * Mensagem
	 *
	 * @param caminho
	 * @return O arquivo foi salvo com sucesso! Caminho: {0}
	 */
	public String getMensagemArquivoSalvoComSucesso(String caminho) {
		return this.getProperty("MPF011");
	}
}
