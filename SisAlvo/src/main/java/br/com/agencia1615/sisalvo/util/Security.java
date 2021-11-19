package br.com.agencia1615.sisalvo.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Izr� Miranda
 *
 */
public class Security {

	public static String convertStringToMd5(String valor) {
		MessageDigest mDigest;

		try {
			// Instanciamos o nosso HASH MD5, poder�amos usar outro como
			// SHA, por exemplo, mas optamos por MD5.

			mDigest = MessageDigest.getInstance("MD5");
			// Convert a String valor para um array de bytes em MD5

			final byte[] valorMD5 = mDigest.digest(valor.getBytes("ISO-8859-1"));
			// Convertemos os bytes para hexadecimal, assim podemos salvar
			// no banco para posterior compara��o se senhas
			final StringBuffer sb = new StringBuffer();
			for (final byte b : valorMD5) {
				sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
			}

			return sb.toString();

		} catch (final NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		} catch (final UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
