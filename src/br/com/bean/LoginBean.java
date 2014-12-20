package br.com.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import br.com.modelo.Pessoa;
import br.com.servico.LoginServico;

@ManagedBean(name = "login")
@SessionScoped
public class LoginBean {

	private String perfil = "";
	private static boolean logado;
	private Pessoa usuario;
	
	private static final Logger logger = Logger.getLogger(LoginBean.class
			.getName());
	

	public String getPerfil() {
		
		int tipo, admin, indFunc, indPaciente = 0;
		admin = usuario.getInd_adm();
		indFunc = usuario.getInd_func();
		indPaciente = usuario.getInd_paciente();
		
		// Se usuário é ADMIN
		if ((admin == 1) && (indFunc == 0) && (indPaciente == 0)) {
			perfil = "Administrador";
		// Se usuário não é ADMIN
		} else if (admin == 0) {
			// Se usuário é Paciente
			if (indPaciente == 1) {
				perfil = "Paciente";			
			// Se usuário é Funcionário
			} else {
				tipo = usuario.getFunc().getTipoFuncionario();
				if (tipo == 1) {
					perfil = "Analista Clínico";
				} else if (tipo == 2) {
					perfil = "Atendente";
				} else if (tipo == 3) {
					perfil = "Enfermeiro";
				} else if (tipo == 4) {
					perfil = "Estoquista";
				} else if (tipo == 5) {
					perfil = "Farmacêutico";
				} else if (tipo == 6) {
					perfil = "Médico";
				}
			}
		}
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public static boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		LoginBean.logado = logado;
	}

	public Pessoa getUsuario() {
		return usuario;
	}

	public void setUsuario(Pessoa usuario) {
		this.usuario = usuario;
	}

	/*
	 * @Fellipe Procedimentos específicos do Bean
	 */

	FacesContext fc = FacesContext.getCurrentInstance();
	ExternalContext ec = fc.getExternalContext();
	HttpSession session = (HttpSession) ec.getSession(true);

	public LoginBean() {
		usuario = new Pessoa();
	}

	public String validar() {

		logger.info("Iniciando validação de login");
		LoginServico servico = new LoginServico();
		// Função para validar o Login
		try {
			usuario = servico.validaLogin(usuario.getCpf(), usuario.getSenha(), usuario.getDtNascimento());
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e);
		}

		if (usuario.getId() > 0) {
			session.setAttribute("usuario", usuario);
			logger.info("Logando e colocando usuário na sessão...");
			logado = true;
			return "/p/principal?faces-redirect=true";
		} else {
			if (session != null) {
				session.invalidate();
			}
			FacesContext.getCurrentInstance().addMessage(
					"",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!",
							"Usuário e/ou senha incorretas!"));
			logado = false;
			return "/index?faces-redirect=true";
		}
	}

	public String logout() {
		logger.info("Saindo e derrubando a sessão do usuário");
		if (session != null) {
			session.invalidate();
			// session.setMaxInactiveInterval(3);
		}
		return "/index?faces-redirect=true";
	}
	
	public void timeout() {
		logger.info("Encerrada a sessão por inatividade do usuário");
		if (session != null) {
			session.invalidate();
		}
	}
}