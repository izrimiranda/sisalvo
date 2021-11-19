package br.com.agencia1615.sisalvo.util;

import java.util.List;

public class MeuStringUtils {

	public static boolean isEmpty(String str) {
		if ((str == null) || str.trim().equals("")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNotEmpty(String str) {
		if (MeuStringUtils.isEmpty(str)) {
			return false;
		} else {
			return true;
		}
	}

	public static String ListToString(List<String> lista) {
		StringBuilder retorno = new StringBuilder();

		for (String s : lista) {
			retorno.append(s);
			retorno.append(", ");
		}

		return retorno.delete(retorno.length() - 2, retorno.length()).toString();
	}

//	public static String montaPathFoto(String path) {
//
//		String novoPath = "";
//
//		char[] barraChar = VisualizaFotoBean.BARRA.toCharArray();
//
//		for (char c : path.toCharArray()) {
//
//			if (c == barraChar[0]) {
//				novoPath = novoPath + c + VisualizaFotoBean.BARRA;
//			} else {
//				novoPath = novoPath + c;
//			}
//		}
//		return novoPath;
//	}
}
