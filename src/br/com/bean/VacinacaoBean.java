package br.com.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import br.com.modelo.Funcionario;
import br.com.modelo.Paciente;
import br.com.modelo.Pessoa;
import br.com.modelo.Vacinacao;
import br.com.servico.VacinacaoServico;

@ManagedBean
@ViewScoped
public class VacinacaoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Logger
	private static final Logger logger = Logger.getLogger(VacinacaoBean.class
			.getName());

	private Vacinacao vacinacao;
	private Pessoa usuario;
	private Paciente paciente;
	private Funcionario enfermeiro;
	private String nome;
	private boolean situacao;
	private List<Vacinacao> cadernetaVacinacao;
	private List<Paciente> listaPaciente;
	private MensagensBean mensagem;

	public VacinacaoBean() {
		vacinacao = new Vacinacao();
		paciente = new Paciente();
		mensagem = new MensagensBean();
		cadernetaVacinacao = new ArrayList<Vacinacao>();
		listaPaciente = new ArrayList<Paciente>();
	}

	// Getters and Setters

	public Vacinacao getVacinacao() {
		return vacinacao;
	}

	public void setVacinacao(Vacinacao vacinacao) {
		this.vacinacao = vacinacao;
	}

	public Pessoa getUsuario() {
		return usuario;
	}

	public void setUsuario(Pessoa usuario) {
		this.usuario = usuario;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public Funcionario getEnfermeiro() {
		return enfermeiro;
	}

	public void setEnfermeiro(Funcionario enfermeiro) {
		this.enfermeiro = enfermeiro;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public List<Paciente> getListaPaciente() {
		return listaPaciente;
	}

	public void setListaPaciente(List<Paciente> listaPaciente) {
		this.listaPaciente = listaPaciente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public MensagensBean getMensagem() {
		return mensagem;
	}

	public boolean isSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}

	public List<Vacinacao> getCadernetaVacinacao() {
		return cadernetaVacinacao;
	}

	public void setCadernetaVacinacao(List<Vacinacao> cadernetaVacinacao) {
		this.cadernetaVacinacao = cadernetaVacinacao;
	}

	// Métodos do Bean

	public void findPacientePorNome() {
		if (nome.equals("")) {
			listaPaciente = null;
		} else {
			try {
				logger.debug("Iniciada listagem de pacientes com a letra "
						+ nome);
				listaPaciente = VacinacaoServico.getInstance()
						.findPacientePorNome(nome);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
		}
	}

	public void visualizaCaderneta() {
		// Puxando dados de vacinação do Paciente
		try {
			cadernetaVacinacao = VacinacaoServico.getInstance()
					.cadernetaDoPaciente(paciente.getId());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Erro ao listar vacinas do paciente: "+ paciente.getPessoa().getNome());
			logger.error(e);
		}
	}

	public void editar() {

		logger.info("Edição de Vacina em Caderneta de Vacinação");

		// Pego a sessão do usuário

		usuario = (Pessoa) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuario");
		Calendar c = Calendar.getInstance();
		vacinacao.setDtHrAplicacao(c.getTime());
		vacinacao.setStatus("Concluído");
		if (usuario.getFunc().getId() == 1) {
			enfermeiro = new Funcionario();
			enfermeiro.setId(usuario.getFunc().getId());
			vacinacao.setEnfermeiro(enfermeiro);
		}
		try {
			VacinacaoServico.getInstance().editarVacinacao(vacinacao);
			mensagem.mensagemVacinaAplicadaSucesso();
			logger.info("Vacina aplicada - Paciente: "
					+ vacinacao.getPaciente().getPessoa().getNome()
					+ "Vacina: " + vacinacao.getVacina().getNome());
		} catch (Exception e) {
			mensagem.mensagemEdicaoFalha();
			e.printStackTrace();
			logger.error("Erro na aplicação da vacina - "+ e);
		}
	}
}
