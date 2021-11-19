package br.com.agencia1615.sisalvo.model;

import java.io.Serializable;

/**
 *
 * @author Izri Miranda
 *
 */
public class HomeModel implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6204440233124808383L;

	private String menuHeader;

	public String getMenuHeader() {
		System.out.println("SISALVO LOG | Class: HomeModel - Method: getMenuHeader()");
		return this.menuHeader;
	}

	public void setMenuHeader(String menuHeader) {
		this.menuHeader = menuHeader;
	}
}
