package br.com.agencia1615.sisalvo.util.mail;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import br.com.agencia1615.sisalvo.controller.RecuperarSenhaController;
import br.com.agencia1615.sisalvo.model.dao.AlunoDAO;
import br.com.agencia1615.sisalvo.model.entity.Aluno;
import br.com.agencia1615.sisalvo.reader.DadosEmailReader;

public class EmailUtil implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -9151196327688703861L;

	public static final String TRUE = "true";

	private AlunoDAO alunoDAO;
	private Aluno alunoBanco;

	private RecuperarSenhaController recuperarController;

	public void enviarEmailResetSenha(String emailDestinatario) {

		/**
		 * Faz a busca do Aluno no banco de dados através do email informado.
		 *
		 * @param emailDestinatario
		 */
		this.buscarAlunoBanco(emailDestinatario);

		MailJava mj = new MailJava();

		/**
		 * Setando as configuracões de envio.
		 */
		mj.setSmtpHostMail(DadosEmailReader.getInstance().getSmtpHostGmail());
		mj.setSmtpPortMail(DadosEmailReader.getInstance().getSmtpPortGmail());
		mj.setSmtpAuth(EmailUtil.TRUE);
		mj.setSmtpStarttls(EmailUtil.TRUE);
		mj.setUserMail(DadosEmailReader.getInstance().getEmailRemetente());
		mj.setFromNameMail(this.getAlunoBanco().getNomeCompleto());
		mj.setPassMail(DadosEmailReader.getInstance().getSenhaRemetente());
		mj.setCharsetMail(DadosEmailReader.getInstance().getCharsetMail());
		mj.setSubjectMail(DadosEmailReader.getInstance().getSubjectEmail(this.getAlunoBanco().getNomeCompleto()));
		mj.setBodyMail(this.getHtmlMessage());
		mj.setTypeTextMail(DadosEmailReader.getInstance().getTypeTextMail());

		// sete quantos destinatarios desejar
		Map<String, String> map = new HashMap<String, String>();

		map.put(emailDestinatario, this.getAlunoBanco().getNomeCompleto());
		map.put("alvoescoladesaude@gmail.com", "Alvo Escola de Saúde");
		map.put("i.brunomiranda@gmail.com", "Izrí Gmail");

		mj.setToMailsUsers(map);

		// seta quatos anexos desejar
		// List<String> files = new ArrayList<String>();
		// files.add("C:\images\hover_prev.png");

		// mj.setFileMails(files);

		try {
			new MailJavaSender().senderMail(mj);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	private static String textMessage() {
		return "Leia o novo tutorial JavaMail do Programando com Java.n"
				+ "Saiba como enviar emails com anexo, em formato texto e html.n"
				+ "Envie seu email para mais de um destinatario.";
	}

	private String getHtmlMessage() {
		return "<html>" + "<body>" + "Foi solicitado em nosso sistema a alteração da senha do aluno: " + "<b>"
				+ this.getAlunoBanco().getNomeCompleto().toString() + "." + "</b>" + "<br></br>"
				+ "Caso não tenha sido você que solicitou o resete da senha, entre em contato com a Alvo." + "<br></br>"
				+ "Segue o link para resetar a senha: <a href=\"http://localhost:8080/sisalvo/pages/seguranca/registrarNovaSenha.faces?email="
				+ this.getAlunoBanco().getEmail() + "\"><b>Resetar Senha</b></a>" + "<br></br>" + "<br></br>"
				+ "<br></br>" + "Email enviado pelo <b>Sistema de Gestão Alvo</b>" + "<br></br>"
				+ "<img src=\"https://i.imgur.com/vDnFMde.png\"></img>" + "</body>" + "</html>";
	}

	private void buscarAlunoBanco(String email) {
		Aluno alunoBanco;

		alunoBanco = this.getAlunoDAO().buscarAlunoPorEmail(email);

		this.setAlunoBanco(alunoBanco);
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

	public RecuperarSenhaController getRecuperarController() {
		if (this.recuperarController == null) {
			this.recuperarController = new RecuperarSenhaController();
		}
		return this.recuperarController;
	}

	public void setRecuperarController(RecuperarSenhaController recuperarController) {
		this.recuperarController = recuperarController;
	}

	public Aluno getAlunoBanco() {
		if (this.alunoBanco == null) {
			this.alunoBanco = new Aluno();
		}
		return this.alunoBanco;
	}

	public void setAlunoBanco(Aluno alunoBanco) {
		this.alunoBanco = alunoBanco;
	}
}