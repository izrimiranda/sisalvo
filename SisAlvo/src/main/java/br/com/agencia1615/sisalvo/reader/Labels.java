package br.com.agencia1615.sisalvo.reader;

import br.com.agencia1615.sisalvo.util.PropertiesReader;

/**
 *
 * @author Izri Miranda
 *
 */
public class Labels {

	private final PropertiesReader reader;

	private static Labels instance;

	private Labels() {
		this.reader = new PropertiesReader("messages_pt_BR");
	}

	public static Labels getInstance() {
		if (Labels.instance == null) {
			Labels.instance = new Labels();
		}

		return Labels.instance;
	}

	protected String getProperty(String key, Object... args) {
		return this.reader.getProperty(key, args);
	}

	/**
	 * Label "Nome Disciplina"
	 *
	 * @param nomeDisciplina
	 * @return String "Nome Disciplina"
	 */
	public String getNomeDisciplina(String nomeDisciplina) {
		return this.getProperty("mm_keyLabel_nomeDisciplina", nomeDisciplina);
	}

}
