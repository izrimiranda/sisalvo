package br.com.agencia1615.sisalvo.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.agencia1615.sisalvo.model.HomeModel;
import br.com.agencia1615.sisalvo.model.dao.AlunoDAO;
import br.com.agencia1615.sisalvo.model.dao.BaseDAO;
import br.com.agencia1615.sisalvo.model.dao.UsuarioDAO;
import br.com.agencia1615.sisalvo.model.entity.Aluno;
import br.com.agencia1615.sisalvo.model.entity.Usuario;

/**
 *
 * @author Izri Miranda
 *
 */
@Named
@SessionScoped
public class HomeController extends BaseDAO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6178399293168073133L;

	public HomeController() {
		System.out.println("SISALVO LOG | Class: HomeController()");
		this.setMenuHeader();
	}

	@Inject
	private UsuarioDAO usuarioDAO;

	@Inject
	private HomeModel homeModel;

	@Inject
	private AlunoDAO alunoDAO;

	private int menuHeader;

	private String nomeUsuario;

	public Aluno getAlunoBanco() {
		Aluno alunoRetorno = null;

		alunoRetorno = this.getAlunoDAO().buscarAlunoPorLogin(this.getUsuarioSession().getLogin().trim());

		return alunoRetorno;
	}

	public Usuario getUsuarioBanco() {

		Usuario usuarioBanco = null;

		usuarioBanco = this.getUsuarioDAO().validaUsuario(this.getUsuario());

		return usuarioBanco;
	}

	public int getMenuHeader() {

		System.out.println("SISALVO LOG | Class: HomeController - Method: getMenuHeader()");

		this.menuHeader = this.getUsuarioBanco().getTipoUsuario();

		System.out.println("SISALVO LOG | tipoUsuario: " + this.menuHeader);

		return this.menuHeader;
	}

	public void setMenuHeader() {

		System.out.println("SISALVO LOG | setMenuHeader()");

		String menu = "";

		int tipoUsuario = this.getUsuarioBanco().getTipoUsuario();

		System.out.println("SISALVO LOG | tipoUsuario: " + tipoUsuario);

		switch (tipoUsuario) {
		case 1:
			menu = "/pages/layout/headerPrincipal.xhtml";
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			menu = "/pages/layout/headerAluno.xhtml";
			break;
		case 5:
			menu = "/pages/layout/headerProfessor.xhtml";
			break;
		}

		System.out.println("SISALVO LOG | menu: " + menu);

		this.getHomeModel().setMenuHeader(menu);

	}

	public Usuario getUsuario() {
		Usuario usuario = new Usuario();

		usuario.setLogin(this.getUsuarioSession().getLogin());
		usuario.setSenha(this.getUsuarioSession().getSenha());

		return usuario;
	}

	@Override
	public UsuarioDAO getUsuarioDAO() {
		if (this.usuarioDAO == null) {
			this.usuarioDAO = new UsuarioDAO();
		}
		return this.usuarioDAO;
	}

	@Override
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public HomeModel getHomeModel() {
		if (this.homeModel == null) {
			this.homeModel = new HomeModel();
		}
		return this.homeModel;
	}

	public void setHomeModel(HomeModel homeModel) {
		this.homeModel = homeModel;
	}

	public String getNomeUsuario() {
		String nome = this.getAlunoBanco().getNomeCompleto();

		String nomeSplit[] = nome.split(" ", 2);

		this.nomeUsuario = nomeSplit[0];

		System.out.println("SISALVO LOG | Class: HomeController - Método: getNomeUsuario - Nome: " + this.nomeUsuario);
		return this.nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	@Override
	public AlunoDAO getAlunoDAO() {
		if (this.alunoDAO == null) {
			this.alunoDAO = new AlunoDAO();
		}
		return this.alunoDAO;
	}

	@Override
	public void setAlunoDAO(AlunoDAO alunoDAO) {
		this.alunoDAO = alunoDAO;
	}

}
