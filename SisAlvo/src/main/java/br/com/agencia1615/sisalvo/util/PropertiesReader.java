package br.com.agencia1615.sisalvo.util;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class PropertiesReader {

	private final ResourceBundle bundle;

	public String getProperty(String key, Object... args) {
		try {

			return MessageFormat.format(this.bundle.getString(key), args);
		} catch (final MissingResourceException e) {
			System.err.println(e.getMessage());

			return null;
		}
	}

	public PropertiesReader(String bundleName) {
		this.bundle = ResourceBundle.getBundle(bundleName);
	}

}
