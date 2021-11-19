package br.com.agencia1615.sisalvo.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.faces.application.FacesMessage;

import br.com.agencia1615.sisalvo.util.FacesUtil;

/**
 *
 * @author Izri Miranda
 *
 */
public class FileUtils {

	/**
	 * Converte File to byte[]
	 *
	 * @param caminhoArquivo String
	 * @return byte[]
	 */
	public static byte[] getBytesFromFile(String caminhoArquivo) {
		byte[] bytes = null;

		try {

			File inputFile = new File(caminhoArquivo);

			FileInputStream inputStream = new FileInputStream(inputFile);

			bytes = new byte[(int) inputFile.length()];

			inputStream.read(bytes);

			inputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return bytes;
	}

	/**
	 * Converte byte[] to File
	 *
	 * @param bytes          caminhoArquivo
	 * @param caminhoArquivo
	 */
	public static void setBytesToFile(byte[] bytes, String caminhoArquivo, String tipoArquivo, String nomeArquivo) {

		String arquivoSalvo = caminhoArquivo + nomeArquivo + "." + tipoArquivo;

		File file = null;

		OutputStream ops = null;

		try {

			file = new File(arquivoSalvo);

			ops = new FileOutputStream(file);

			ops.write(bytes);

			ops.close();

			FacesUtil.setMensagemFaces(FacesMessage.SEVERITY_INFO, "Arquivo Salvo", arquivoSalvo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
