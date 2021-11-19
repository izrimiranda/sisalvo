package br.com.agencia1615.sisalvo.reader;

import br.com.agencia1615.sisalvo.util.PropertiesReader;

public class DadosEmailReader {

	private final PropertiesReader reader;

	private static DadosEmailReader instance;

	private DadosEmailReader() {
		this.reader = new PropertiesReader("messages_pt_BR");
	}

	protected String getProperty(String key, Object... args) {
		return this.reader.getProperty(key, args);
	}

	public static DadosEmailReader getInstance() {
		if (DadosEmailReader.instance == null) {
			DadosEmailReader.instance = new DadosEmailReader();
		}

		return instance;
	}

	public String getEmailRemetente() {
		return this.getProperty("DAE001");
	}

	public String getSenhaRemetente() {
		return this.getProperty("DAE002");
	}

	public String getSmtpHostGmail() {
		return this.getProperty("DAE003");
	}

	public String getSmtpPortGmail() {
		return this.getProperty("DAE004");
	}

	public String getCharsetMail() {
		return this.getProperty("DAE005");
	}

	public String getTypeTextMail() {
		return this.getProperty("DAE006");
	}

	public String getSubjectEmail(String nome) {
		return this.getProperty("DAE007", nome);
	}

}
