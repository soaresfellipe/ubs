package br.com.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import br.com.modelo.Agenda;
import br.com.modelo.Descricao;
import br.com.modelo.Especialidade;
import br.com.modelo.Exame;
import br.com.modelo.Funcionario;
import br.com.modelo.Paciente;
import br.com.modelo.Pessoa;
import br.com.modelo.Receita;
import br.com.modelo.UBS;
import br.com.servico.AgendaServico;
import br.com.servico.DescricaoServico;
import br.com.servico.ExameServico;
import br.com.servico.FuncionarioServico;
import br.com.servico.ReceitaServico;
import br.com.servico.UBSServico;

@ManagedBean
@ViewScoped
public class AgendaOnlineBean implements Serializable {

	// Atributos

	/**
	 * 
	 */
	private static final long serialVersionUID = 4773959020867241305L;
	
	private static final Logger logger = Logger.getLogger(AgendaOnlineBean.class
			.getName());

	private List<Agenda> listaHorariosLivresPaciente;
	private List<UBS> listaUBS;
	private List<Especialidade> listaEsp;
	private List<Funcionario> listaMed;
	private List<Descricao> listaDescProntuario;
	private List<Receita> listaRecProntuario;
	private List<Exame> listaExameProntuario;
	private UBS ubs;
	private Agenda agenda;
	private Especialidade esp;
	private Funcionario medico;
	private Paciente paciente;
	private Pessoa usuario;
	private Date data;
	private Date dataAtual;
	private boolean desmarcavel;
	private MensagensBean mensagem;

	// Métodos do Bean

	public AgendaOnlineBean() {

		listaHorariosLivresPaciente = new ArrayList<Agenda>();
		listaUBS = new ArrayList<UBS>();
		listaEsp = new ArrayList<Especialidade>();
		listaMed = new ArrayList<Funcionario>();
		listaDescProntuario = new ArrayList<Descricao>();
		listaRecProntuario = new ArrayList<Receita>();
		listaExameProntuario = new ArrayList<Exame>();
		ubs = new UBS();
		agenda = new Agenda();
		esp = new Especialidade();
		medico = new Funcionario();
		paciente = new Paciente();
		mensagem = new MensagensBean();
		
		Calendar c = Calendar.getInstance();
		dataAtual = c.getTime();

		try {
			listaUBS = UBSServico.getInstance().listarAllUBS();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Pego o usuário da sessão
		usuario = (Pessoa) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuario");
		
		// Se o usuário for paciente
		if (usuario.getInd_paciente() == 1) {
			paciente.setId(usuario.getPaciente().getId());
			
			// Carrego as listas de histórico
			listaDescProntuario = DescricaoServico.getInstance().findProntPac(
					usuario.getPaciente().getId());
			listaRecProntuario = ReceitaServico.getInstance().findRecPac(
					usuario.getPaciente().getId());
			listaExameProntuario = ExameServico.getInstance().findExamePac(
					usuario.getPaciente().getId());
		}

	}

	public void visualizarAgenda() throws Exception {
		logger.debug("Método Visualizar Agenda");
		listaEsp = FuncionarioServico.getInstance().findEspFunc(ubs.getId());
		if (esp != null) {
			listaMed = FuncionarioServico.getInstance().findMedicoByUBSEsp(
					ubs.getId(), esp.getId());
		}
	}

	public void horariosLivresPaciente() {

		listaHorariosLivresPaciente = AgendaServico.getInstance()
				.horariosLivresPaciente(data, medico.getId(), esp.getId(),
						ubs.getId(), paciente.getId());

	}

	public void agendaConsultaOnline() {
		if (agenda.getStatus().equals("Disponível")) {
			agenda.setStatus("Agendada");
			agenda.setPaciente(paciente);
			try {
				AgendaServico.getInstance().editarAgenda(agenda);
				mensagem.mensagemPacienteAgendado();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e);
			}
		} else {
			mensagem.mensagemPacienteNaoAgendado();
			logger.warn("Paciente não agendado.");
		}
	}

	public void desmarcaConsultaOnline() {
		String s = agenda.getStatus();

		Calendar horaIni = Calendar.getInstance();
		Calendar dataIni = Calendar.getInstance();
		Calendar horaAtual = Calendar.getInstance();

		horaIni.setTime(agenda.getHoraInicio());
		dataIni.setTime(agenda.getData());
		

		int h1 = horaIni.get(Calendar.HOUR_OF_DAY);
		int h2 = horaAtual.get(Calendar.HOUR_OF_DAY);
		int d1 = dataIni.get(Calendar.DAY_OF_YEAR);
		int d2 = horaAtual.get(Calendar.DAY_OF_YEAR);
		int h3 = h1 - h2;
		
		if (!(s.equals("Agendada"))) {
			mensagem.mensagemConsultaNaoDesmarcada();
		} else {
			if ((d1 > d2) || ((d1 == d2) && (h3 > 2))) {
				agenda.setStatus("Disponível");
				agenda.setPaciente(null);
				try {
					AgendaServico.getInstance().editarAgenda(agenda);
					mensagem.mensagemConsultaDesmarcada();
					logger.info("Consulta desmarcada");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error(e);
				}
			} else {
				mensagem.mensagemConsultaNaoDesmarcada();
				logger.warn("Consulta não desmarcada.");
			}
		}
	}

	// Getters and Setters

	public List<Agenda> getListaHorariosLivresPaciente() {
		return listaHorariosLivresPaciente;
	}

	public void setListaHorariosLivresPaciente(
			List<Agenda> listaHorariosLivresPaciente) {
		this.listaHorariosLivresPaciente = listaHorariosLivresPaciente;
	}

	public List<UBS> getListaUBS() {
		return listaUBS;
	}

	public void setListaUBS(List<UBS> listaUBS) {
		this.listaUBS = listaUBS;
	}

	public List<Especialidade> getListaEsp() {
		return listaEsp;
	}

	public void setListaEsp(List<Especialidade> listaEsp) {
		this.listaEsp = listaEsp;
	}

	public List<Funcionario> getListaMed() {
		return listaMed;
	}

	public void setListaMed(List<Funcionario> listaMed) {
		this.listaMed = listaMed;
	}

	public List<Descricao> getListaDescProntuario() {
		return listaDescProntuario;
	}

	public void setListaDescProntuario(List<Descricao> listaDescProntuario) {
		this.listaDescProntuario = listaDescProntuario;
	}

	public List<Receita> getListaRecProntuario() {
		return listaRecProntuario;
	}

	public void setListaRecProntuario(List<Receita> listaRecProntuario) {
		this.listaRecProntuario = listaRecProntuario;
	}

	public List<Exame> getListaExameProntuario() {
		return listaExameProntuario;
	}

	public void setListaExameProntuario(List<Exame> listaExameProntuario) {
		this.listaExameProntuario = listaExameProntuario;
	}

	public UBS getUbs() {
		return ubs;
	}

	public void setUbs(UBS ubs) {
		this.ubs = ubs;
	}

	public Pessoa getUsuario() {
		return usuario;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public Especialidade getEsp() {
		return esp;
	}

	public void setEsp(Especialidade esp) {
		this.esp = esp;
	}

	public Funcionario getMedico() {
		return medico;
	}

	public void setMedico(Funcionario medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(Date dataAtual) {
		this.dataAtual = dataAtual;
	}

	public boolean isDesmarcavel() {
		return desmarcavel;
	}

	public void setDesmarcavel(boolean desmarcavel) {
		this.desmarcavel = desmarcavel;
	}

}
