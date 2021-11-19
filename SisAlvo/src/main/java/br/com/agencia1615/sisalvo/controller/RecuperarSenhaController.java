package br.com.agencia1615.sisalvo.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.com.agencia1615.sisalvo.model.RecuperarSenhaModel;
import br.com.agencia1615.sisalvo.model.dao.AlunoDAO;
import br.com.agencia1615.sisalvo.reader.Message;
import br.com.agencia1615.sisalvo.util.FacesUtil;
import br.com.agencia1615.sisalvo.util.mail.EmailUtil;

/**
 *
 * @author Izri Miranda
 *
 */
@Named
@SessionScoped
public class RecuperarSenhaController implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 8968277606600982184L;

	public static final String ERRO = "Erro";

	@Inject
	private EmailUtil emailUtil;

	@Inject
	private AlunoDAO alunoDAO;

	@Inject
	private RecuperarSenhaModel recuperarSenhaModel;

	private Integer progress;

	public void onComplete() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(Message.getInstance().getMensagemEmailEnviadoSucesso()));
	}

	public String salvarNovaSenha() {
		this.isSenhasValidas();
		return "";
	}

	private boolean isEmailValido() {
		boolean verificador = false;

		if (this.getRecuperarSenhaModel().getEmail().isEmpty()
				|| !this.getRecuperarSenhaModel().getEmail().contains("@")) {
			System.out.println("SISALVO LOG | Campo email vazio!");
			FacesUtil.setMensagemFaces(FacesMessage.SEVERITY_WARN, RecuperarSenhaController.ERRO,
					Message.getInstance().getMensagemEmailInvalido());
		} else {
			System.out.println("SISALVO LOG >>> Email válido!");
			if (this.getAlunoDAO().isEmailExiste(this.getRecuperarSenhaModel().getEmail())) {
				System.out.println("SISALVO LOG >>> Email existe na base de dados.");
				verificador = true;
			}
		}

		return verificador;
	}

	public String getEmailBanco() {
		String emailRetorno = "";

		return emailRetorno;
	}

	public String enviarEmailRecuperarSenhaTeste() {

		return "";
	}

	public String enviarEmailRecuperarSenha() {
		System.out.println("SISALVO LOG | enviarEmailRecuperarSenha()");

		if (this.isEmailValido()) {
			this.getEmailUtil().enviarEmailResetSenha(this.getRecuperarSenhaModel().getEmail());
			System.out.println("SISALGO LOG | Email Enviado!");
		}

		this.limparCampoEmail();

		return "";
	}

	private void limparCampoEmail() {
		System.out.println("SISALVO LOG | Limpar campo email!");

		this.getRecuperarSenhaModel().setEmail(null);

		PrimeFaces.current().resetInputs("idFormEmail");

		System.out.println("SISALVO LOG | Campo Email: " + this.getRecuperarSenhaModel().getEmail());

	}

	public boolean isSenhasValidas() {
		boolean verificador = false;

		if (this.getRecuperarSenhaModel().getSenha().toString()
				.equals(this.getRecuperarSenhaModel().getNovaSenha().toString())) {
			System.out.println("SISALVO LOG | As senha são iguais!");
		} else {
			System.out.println("SISALVO LOG | As senhas não são iguais!");
		}

		return verificador;
	}

	private Integer updateProgress(Integer progress) {
		if (progress == null) {
			progress = 0;
		} else {
			progress = progress + (int) (Math.random() * 35);

			if (progress > 100) {
				progress = 100;
			}
		}

		return progress;
	}

	public Integer getProgress() {
		this.progress = this.updateProgress(this.progress);
		return this.progress;
	}

	public EmailUtil getEmailUtil() {
		if (this.emailUtil == null) {
			this.emailUtil = new EmailUtil();
		}
		return this.emailUtil;
	}

	public void setEmailUtil(EmailUtil emailUtil) {
		this.emailUtil = emailUtil;
	}

	public RecuperarSenhaModel getRecuperarSenhaModel() {
		if (this.recuperarSenhaModel == null) {
			this.recuperarSenhaModel = new RecuperarSenhaModel();
		}
		return this.recuperarSenhaModel;
	}

	public void setRecuperarSenhaModel(RecuperarSenhaModel recuperarSenhaModel) {
		this.recuperarSenhaModel = recuperarSenhaModel;
	}

	public AlunoDAO getAlunoDAO() {
		if (this.alunoDAO == null) {
			this.alunoDAO = new AlunoDAO();
		}
		return this.alunoDAO;
	}

	public void setAlunoDAO(AlunoDAO alunoDAO) {
		this.alunoDAO = alunoDAO;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

}
