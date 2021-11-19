package br.com.agencia1615.sisalvo.controller;

import java.io.Serializable;
import java.util.Calendar;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.com.agencia1615.sisalvo.Main;
import br.com.agencia1615.sisalvo.model.UsuarioModel;
import br.com.agencia1615.sisalvo.model.dao.BaseDAO;
import br.com.agencia1615.sisalvo.model.entity.Aluno;
import br.com.agencia1615.sisalvo.model.entity.Turma;
import br.com.agencia1615.sisalvo.model.entity.Usuario;
import br.com.agencia1615.sisalvo.util.FacesUtil;
import br.com.agencia1615.sisalvo.util.MeuStringUtils;
import br.com.agencia1615.sisalvo.util.Security;

/**
 *
 * @author Izri Miranda
 *
 */
@Named
@SessionScoped
public class UsuarioController extends BaseDAO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2509055572604860630L;

	@Inject
	private UsuarioModel usuarioModel;

	private Usuario usuario;

	public String setPopUpConfirmarLogout() {
		System.out.println("SISALVO LOG | setPopUpConfirmarLogout()");
		PrimeFaces.current().executeScript("PF('botaoConfirmar').show()");
		return "";
	}

	public void exibirNomeTurmasPorAluno() {
		Aluno alunoRetorno = new Aluno();

		alunoRetorno = this.getAlunoDAO().buscarAlunoPorLogin("izrialuno");

		System.out.println(
				"SISALVO LOG | Método: exibirNomeTurmasPorAluno() - Nome Aluno: " + alunoRetorno.getNomeCompleto());
		System.out.println("SISALVO LOG | Tamanho lista: " + alunoRetorno.getListaTurma().size());
		for (Turma t : alunoRetorno.getListaTurma()) {
			System.out.println("SISALVO LOG | Dentro do for");
			System.out.println("SISALVO LOG | Nome Turma: " + t.getNomeTurma());
		}
	}

	public String salvarTeste() {
		new Main().salvarDisciplinaTeste();

		return "";
	}

	public Usuario getUsuarioSLogando() {
		Usuario usuario = new Usuario();

		usuario.setLogin(this.getUsuarioModel().getLogin());
		usuario.setSenha(this.getUsuarioModel().getSenha());
		usuario.setExcluido(false);

		return usuario;
	}

	public String efetuarLogin() {

		Usuario usuarioRetorno;

		if (this.isCamposValidos() == false) {

			FacesUtil.setMessagemLoginSenhaInvalidos();

			this.limparCamposLoginSenha();

			this.reloadFormularioLogin();
		} else {

			usuarioRetorno = this.getUsuarioDAO().validaUsuario(this.getUsuarioSLogando());

			if (usuarioRetorno == null) {
				FacesUtil.setMessagemLoginSenhaInvalidos();

				this.limparCamposLoginSenha();

				this.reloadFormularioLogin();
			} else {

				FacesContext facesContext = FacesContext.getCurrentInstance();

				facesContext.getExternalContext().getSessionMap().put("usuarioAutenticado", this.getUsuarioModel());

				return "/pages/sistema/home?faces-redirect=true";
			}
		}

		return null;
	}

	private boolean isCamposValidos() {

		System.out.println("SISALVO LOG | isCamposValidos()");

		boolean retorno = true;

		if (MeuStringUtils.isEmpty(this.getUsuarioModel().getLogin())
				|| MeuStringUtils.isEmpty(this.getUsuarioModel().getSenha())) {
			retorno = false;
		}

		if ((this.getUsuarioModel().getLogin() == null) || (this.getUsuarioModel().getSenha() == null)) {
			retorno = false;
		}

		System.out.println("SISALVO LOG | retorno = " + retorno);
		return retorno;
	}

	private void limparCamposLoginSenha() {
		this.getUsuarioModel().setLogin(null);
		this.getUsuarioModel().setSenha(null);
		// this.reload();
	}

	private void reloadFormularioLogin() {
		PrimeFaces.current().ajax().update("formLogin");
	}

	/**
	 * Faz o logoff da aplicação.
	 *
	 * @return login.xhtml
	 */
	public String doLogout() {
		System.out.println("SISALVO LOG | doLogout()");

		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		return "/pages/home/login?faces-redirect=true";
	}

	public void salvarNoBanco() {
		// this.getTransaction().begin();
		this.setUsuario();

		try {

			this.getEM().persist(this.getUsuario());
			this.getTransaction().commit();
			this.getEM().close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		this.verificaConexao();
	}

	public void setUsuario() {

		this.getUsuario().setLogin("izrimiranda");
		this.getUsuario().setEmail("izri@outlook.com");
		this.getUsuario().setSenha(Security.convertStringToMd5("1234"));
		this.getUsuario().setDataCadastro(Calendar.getInstance());
		this.getUsuario().setNomeUsuarioCadastro("Izrí Miranda");
	}

	public Usuario getUsuario() {
		if (this.usuario == null) {
			this.usuario = new Usuario();
		}

		return this.usuario;
	}

	public void verificaConexao() {
		if (this.getEM().isOpen()) {
			System.out.println("SISALVO LOG | Conexação com o banco ainda está aberta.");
		} else {
			System.out.println("SISALVO LOG | Conexação com o banco ainda está fechada.");
		}
	}

	public UsuarioModel getUsuarioModel() {
		if (this.usuarioModel == null) {
			this.usuarioModel = new UsuarioModel();
		}
		return this.usuarioModel;
	}

	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

}