package br.com.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;

import br.com.modelo.Agenda;
import br.com.modelo.Descricao;
import br.com.modelo.Especialidade;
import br.com.modelo.Exame;
import br.com.modelo.Funcionario;
import br.com.modelo.Medicamento;
import br.com.modelo.Paciente;
import br.com.modelo.Pessoa;
import br.com.modelo.Procedimento;
import br.com.modelo.Prontuario;
import br.com.modelo.Receita;
import br.com.modelo.Resultado;
import br.com.modelo.UBS;
import br.com.modelo.Variavel;
import br.com.servico.AgendaServico;
import br.com.servico.DescricaoServico;
import br.com.servico.EspecialidadeServico;
import br.com.servico.ExameServico;
import br.com.servico.FuncionarioServico;
import br.com.servico.MedicamentoServico;
import br.com.servico.PacienteServico;
import br.com.servico.ProcedimentoServico;
import br.com.servico.ProntuarioServico;
import br.com.servico.ReceitaServico;
import br.com.servico.ResultadoServico;
import br.com.servico.UBSServico;

@ManagedBean
@ViewScoped
public class AgendaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nome;
	private String localidade;
	private boolean desabilitaBuscaPorLoc;
	private Agenda agenda;
	private List<UBS> listaUBS;
	private List<Especialidade> listEsp;
	private UBS ubs;
	private Especialidade esp;
	private Funcionario medico;
	private List<Funcionario> listaMed;
	private String nomeMedico;
	private Date data;
	private Date horaInicio;
	private Date horaFim;
	private int hora;
	private int minutos;
	private int intervalo;
	private Date horaInicioIntervalo;
	private Date horaFimIntervalo;
	private Date horaInicioAux;
	private boolean fim;
	private int horaAux;
	private List<Agenda> listaAgendaDia;
	private int i = 0;
	private Date horaFimAux = new Date();
	private Agenda agendaAux;
	private Date comparaI = new Date();
	private Date comparaF = new Date();
	private List<Agenda> listAgenda;
	private Date data2;
	private Date horainicio2;
	private Date horafim2;
	private List<Especialidade> listEspBusca;
	private List<UBS> listaUBSBusca;
	private boolean existe;
	private Agenda agendaSelecionada;
	private List<Paciente> listPaciente;
	private Paciente paciente;
	private String nomePaciente;
	private List<Agenda> agendaMedico;
	private String just;
	private String justExclusao;
	private String desc;
	private boolean habilitaP1;
	private boolean habilitaP2;
	private List<Descricao> listaDescProntuario;
	private Descricao descricao;
	private List<Receita> listaRecProntuario;
	private Receita receita;
	private List<Exame> listaExameProntuario;
	private Exame exame;
	private Medicamento medicamento;
	private List<Medicamento> listaMedicamento;
	private int quant;
	private String unidadeMedida;
	private String tipoAtend;
	private String descNew;
	private boolean habilitaCombo;
	private List<Medicamento> listaMedicamentoOutrasUBS;
	private boolean habilitaTipoAtd;
	private int activeTab;
	private List<Procedimento> listaProc;
	private Procedimento proc;
	private List<Variavel> listaVar;
	private Resultado result;
	private Prontuario prontuario;
	private MensagensBean mensagem;
	Pessoa usuario = (Pessoa) FacesContext.getCurrentInstance()
			.getExternalContext().getSessionMap().get("usuario");

	public AgendaBean() {

		mensagem = new MensagensBean();
		listaAgendaDia = new ArrayList<Agenda>();
		prontuario = new Prontuario();
		listaVar = new ArrayList<Variavel>();
		result = new Resultado();
		try {
			listaProc = ProcedimentoServico.getInstance()
					.listarAllProcedimento();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		proc = new Procedimento();
		activeTab = 0;
		habilitaTipoAtd = false;
		listaMedicamentoOutrasUBS = new ArrayList<Medicamento>();
		habilitaCombo = false;
		descNew = "";
		tipoAtend = "";
		habilitaP1 = true;
		habilitaP2 = false;
		desc = "";
		justExclusao = "";
		just = "";
		try {
			agendaMedico = AgendaServico.getInstance().findPorMedicoData(
					new Date(), usuario.getFunc().getId());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		paciente = new Paciente();
		listPaciente = new ArrayList<Paciente>();
		agenda = new Agenda();
		agendaSelecionada = new Agenda();
		agendaAux = new Agenda();
		horaInicioAux = new Date();
		nome = "";
		localidade = "";
		desabilitaBuscaPorLoc = false;
		listaUBS = new ArrayList<UBS>();
		ubs = new UBS();
		listEsp = new ArrayList<Especialidade>();
		try {
			listEspBusca = EspecialidadeServico.getInstance()
					.listarAllEspecialidades();
			listaUBSBusca = UBSServico.getInstance().listarAllUBS();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		esp = new Especialidade();
		medico = new Funcionario();
		listaMed = new ArrayList<Funcionario>();
		nomeMedico = "";
		fim = false;
		comparaF = new Date();
		comparaI = new Date();
		listAgenda = new ArrayList<Agenda>();
		data2 = new Date();
		horainicio2 = new Date();
		horafim2 = new Date();
		descricao = new Descricao();
		listaDescProntuario = new ArrayList<Descricao>();
		receita = new Receita();
		listaRecProntuario = new ArrayList<Receita>();
		exame = new Exame();
		listaExameProntuario = new ArrayList<Exame>();
		medicamento = new Medicamento();
		quant = 0;
		unidadeMedida = "";
		listaMedicamento = new ArrayList<Medicamento>();
		
		// Pego o usuário da sessão
		usuario = (Pessoa) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuario");
		
		// Seto o médico, se o usuário da sessão for do tipo médico
		if (usuario.getInd_func() == 1) {
			if (usuario.getFunc().getTipoFuncionario() == 6) {
				medico.setId(usuario.getFunc().getId());
			}
		}		
	}

	public void desmarcarConsulta() throws Exception {
		Calendar cDataAtual = Calendar.getInstance();
		Calendar cHoraInicio = Calendar.getInstance();
		Calendar cDataInicio = Calendar.getInstance();

		cDataAtual.setTime(new Date());
		cDataInicio.setTime(agenda.getData());
		cHoraInicio.setTime(agenda.getHoraInicio());

		int idataAtual = cDataAtual.get(Calendar.HOUR_OF_DAY);
		int idataInicio = cHoraInicio.get(Calendar.HOUR_OF_DAY);

		int diaAnoInicio = cDataInicio.get(Calendar.DAY_OF_YEAR);
		int diaAnoAtual = cDataAtual.get(Calendar.DAY_OF_YEAR);

		// Subtração das Horas
		int result = idataInicio - idataAtual;

		Date dataAtual = new Date();

		if ((agenda.getData().after(dataAtual))
				|| (diaAnoAtual == diaAnoInicio)) {
			if ((agenda.getStatus().equals("Agendada")) && (result >= 2)) {
				agenda.setStatus("Disponível");
				agenda.setPaciente(null);

				AgendaServico.getInstance().editarAgenda(agenda);
				mensagem.mensagemConsultaDesmarcada();
			} else {
				mensagem.mensagemConsultaNaoDesmarcada();
			}
		} else {
			mensagem.mensagemConsultaNaoDesmarcada();
		}
	}

	public void salvarExame() throws Exception {

		proc = ProcedimentoServico.getInstance().findProcedimentoById(
				proc.getId());
		exame.setAgenda(agenda);
		exame.setDtValidade(data);
		exame.setPaciente(agenda.getPaciente());
		exame.setProcedimento(proc);
		exame.setStatus("Pendente");
		exame.setDtCriacao(new Date());

		ExameServico.getInstance().cadastrarExame(exame);

		listaVar = ProcedimentoServico.getInstance().variaveisDoProc(
				proc.getId());

		for (int i = 0; i < listaVar.size(); i++) {
			result.setExame(exame);
			result.setFuncionario(usuario.getFunc());
			result.setValor(0);
			result.setVariavel(listaVar.get(i));

			ResultadoServico.getInstance().cadastrarResultado(result);
			result = new Resultado();
		}

		data = null;
		exame = new Exame();

		mensagem.mensagemCadastroSucesso();
	}

	public void salvarReceita() throws Exception {
		if (medicamento.getId() == 0) {
			mensagem.mensagemPreencherCamposFalha();
		} else {
			if (activeTab == 0) {
				receita.setAgenda(agenda);
				receita.setDescricao(descNew);
				receita.setDtCriacao(new Date());
				receita.setDtValidade(data);
				receita.setFuncionario(usuario.getFunc());
				receita.setPaciente(agenda.getPaciente());
				receita.setStatus("Pendente");

				ReceitaServico.getInstance().cadastrarReceita(receita);

				data = new Date();
				descNew = "";

				mensagem.mensagemCadastroSucesso();

			} else if ((activeTab == 1) || (activeTab == 2)) {

				receita.setAgenda(agenda);
				receita.setDtCriacao(new Date());
				receita.setDtValidade(data);
				receita.setPaciente(agenda.getPaciente());
				receita.setStatus("Pendente");
				receita.setFuncionario(usuario.getFunc());
				receita.setPaciente(agenda.getPaciente());
				receita.setDescricao(descNew);
				receita.setMedicamento(medicamento);
				receita.setQuant(quant);
				receita.setTipoAtendimento(tipoAtend);

				ReceitaServico.getInstance().cadastrarReceita(receita);
				mensagem.mensagemCadastroSucesso();
			}

			data = new Date();
			medicamento = new Medicamento();
			unidadeMedida = "";
			quant = 0;
			tipoAtend = "";
			descNew = "";

		}
	}

	public void cancelarReceita() {
		data = new Date();
		medicamento = new Medicamento();
		unidadeMedida = "";
		quant = 0;
		tipoAtend = "";
		descNew = "";
	}

	public void onChange() throws Exception {
		System.out.println(medicamento.getId());
		medicamento = MedicamentoServico.getInstance().findById(
				medicamento.getId());
		unidadeMedida = medicamento.getUnidadeMedida();

	}

	public void onSelect() {
		habilitaTipoAtd = true;
	}

	@SuppressWarnings("deprecation")
	public void cancelar() throws Exception {
		Date dataLocal = new Date();
		if ((agenda.getStatus().equals("Agendada"))
				&& (agenda.getPaciente() != null)
				&& (dataLocal.getHours() > agenda.getHoraInicio().getHours())
				&& (dataLocal.getMinutes() > agenda.getHoraInicio()
						.getMinutes()) && (dataLocal.after(agenda.getData()))) {
			agenda.setStatus("Falta");
			agenda.setJustCancelamento(just);
			AgendaServico.getInstance().editarAgenda(agenda);
			paciente = PacienteServico.getInstance().findPacienteById(
					agenda.getPaciente().getId());
			paciente.setQuantFalta(paciente.getQuantFalta() + 1);
			PacienteServico.getInstance().editarPaciente(paciente);
			mensagem.mensagemFaltaSucesso(paciente);
		} else {
			mensagem.mensagemFaltaFalha();
		}
	}

	public void habilitaPanel() throws Exception {
		if (agenda.getStatus().equals("Agendada")) {
			habilitaP1 = false;
			habilitaP2 = true;
		} else {
			habilitaP1 = true;
			habilitaP2 = false;
		}
	}

	public void onTabChange(TabChangeEvent event) {
		TabView tabView = (TabView) event.getComponent();
		activeTab = tabView.getChildren().indexOf(event.getTab());

		System.out.println(activeTab + "aqui");
		listaMedicamento = MedicamentoServico.getInstance().findMed(
				agenda.getUbs().getId());
		System.out.println("ubs" + agenda.getUbs().getId());
		listaMedicamentoOutrasUBS = MedicamentoServico.getInstance()
				.findMedOutrasUBS(agenda.getId());
	}

	public void populaMedicamento() {
		listaMedicamento = MedicamentoServico.getInstance().findMed(
				agenda.getUbs().getId());
		listaMedicamentoOutrasUBS = MedicamentoServico.getInstance()
				.findMedOutrasUBS(agenda.getId());
	}

	public void populaMedicamentoLocal() {
		listaMedicamento = MedicamentoServico.getInstance().findMed(
				agenda.getUbs().getId());
	}

	public void buscaHistorico() {
		listaDescProntuario = DescricaoServico.getInstance().findProntPac(
				agenda.getPaciente().getId());
		listaRecProntuario = ReceitaServico.getInstance().findRecPac(
				agenda.getPaciente().getId());
		listaExameProntuario = ExameServico.getInstance().findExamePac(
				agenda.getPaciente().getId());
	}

	public String encerrarConsulta() throws Exception {
		listaDescProntuario = DescricaoServico.getInstance().findProntPac(
				agenda.getPaciente().getId());

		if (desc.equals("")) {
			mensagem.mensagemPreencherCamposFalha();
			return "";
		} else {
			if (listaDescProntuario.size() == 0) {
				prontuario.setDtInicioTratamento(new Date());
				prontuario.setPaciente(agenda.getPaciente());

				ProntuarioServico.getInstance().cadastrarProntuario(prontuario);

				descricao.setData(new Date());
				descricao.setDescricao(desc);
				descricao.setProntuario(prontuario);

				DescricaoServico.getInstance().cadastrarDescricao(descricao);

				agenda.setStatus("Finalizado");

				AgendaServico.getInstance().editarAgenda(agenda);

				mensagem.mensagemCadastroSucesso();

			} else {

				descricao.setData(new Date());
				descricao.setDescricao(desc);
				descricao.setProntuario(listaDescProntuario.get(0)
						.getProntuario());

				DescricaoServico.getInstance().cadastrarDescricao(descricao);
				System.out.println("Cadastrou apenas descricao");

				agenda.setStatus("Finalizado");

				AgendaServico.getInstance().editarAgenda(agenda);
				mensagem.mensagemCadastroSucesso();
			}
			return "/agenda-medico.xhtml?faces-redirect=true";
		}
	}

	@SuppressWarnings("deprecation")
	public void excluir() throws Exception {
		Date dataLocal = new Date();
		if (((dataLocal.getDate() == agenda.getData().getDate()) || (dataLocal
				.before(agenda.getData())))
				&& (agenda.getStatus() != "Excluído")) {
			agenda.setStatus("Excluído");
			agenda.setJustExclusao(justExclusao);
			if (agenda.getPaciente() != null) {
				mensagem.mensagemEntreEmContato(agenda);
			}
			agenda.setPaciente(null);
			AgendaServico.getInstance().editarAgenda(agenda);
			mensagem.mensagemEdicaoSucesso();
		}
	}

	public void gerarAgendaEncaixe() {
		listaAgendaDia = AgendaServico.getInstance().findHorariosLivres(data,
				medico.getId(), esp.getId(), ubs.getId());

		existe = AgendaServico.getInstance().findHorariosByUBS(data,
				medico.getId(), ubs.getId());

		if ((existe == true) && (listaAgendaDia.size() == 0)) {
			try {
				agenda.setData(data);
				agenda.setPaciente(paciente);
				agenda.setMedico(medico);
				agenda.setUbs(ubs);
				agenda.setEspecialidade(esp);
				agenda.setHoraInicio(horaInicio);
				agenda.setHoraFim(horaFim);
				agenda.setStatus("Encaixe - Agendado");
				AgendaServico.getInstance().cadastrarAgenda(agenda);
				mensagem.mensagemCadastroSucesso();
			} catch (Exception e) {
				mensagem.mensagemCadastroFalha();
				e.printStackTrace();
			}
		} else {
			mensagem.mensagemAgendaEncaixeFalha();
		}
	}

	public void findUBS() {
		if (localidade.equals(null)) {
			listaUBS = null;
		} else {
			try {
				listaUBS = UBSServico.getInstance().findUBSByLocalidade(
						localidade);
				System.out.println(localidade);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				listEsp = FuncionarioServico.getInstance().findEspFunc(
						ubs.getId());
				System.out.println(ubs.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				listaMed = FuncionarioServico.getInstance().findMedicoByUBSEsp(
						ubs.getId(), esp.getId());
				nomeMedico = medico.getPessoa().getNome();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public void buscaPaciente() throws Exception {
		System.out.println(nomePaciente);
		System.out.println(agenda.getId());
		listPaciente = PacienteServico.getInstance().findByNome(nomePaciente,
				agenda.getMedico().getId(), agenda.getData());
	}

	public void buscaPacienteEncaixe() throws Exception {
		System.out.println(nomePaciente);
		listPaciente = PacienteServico.getInstance().findByNomeEncaixe(
				nomePaciente, medico.getId(), data, ubs.getId());
	}

	public void associarPaciente() throws Exception {
		Calendar c = Calendar.getInstance();
		Calendar aux = Calendar.getInstance();
		
		aux.setTime(agenda.getData());

		if ((agenda.getStatus().equals("Disponível"))
				&& ((aux.get(Calendar.DAY_OF_YEAR)) >=(c.get(Calendar.DAY_OF_YEAR)))) {
			agenda.setStatus("Agendada");
			agenda.setPaciente(paciente);
			AgendaServico.getInstance().editarAgenda(agenda);
			mensagem.mensagemPacienteAgendado();
		} else {
			mensagem.mensagemPacienteNaoAgendado();
		}
	}

	public void gerarAgenda() throws Exception {
		// Se o retorno da query for false, não existe horário agendado para o
		// médico selecionado, no dia solicitado.
		existe = AgendaServico.getInstance().findHorarios(data, medico.getId());

		// Variáveis auxiliares:
		Calendar cHoraInicioAuX = Calendar.getInstance();
		Calendar cHoraFimAuX = Calendar.getInstance();
		Calendar cData = Calendar.getInstance();
		Calendar cLocal = Calendar.getInstance();

		// Hora inicial escolhida pelo atendente
		cHoraInicioAuX.setTime(horaInicio);
		cHoraFimAuX.setTime(horaInicio);

		Calendar comparaIni = Calendar.getInstance();
		Calendar comparaFim = Calendar.getInstance();
		Calendar comparaIniInter = Calendar.getInstance();
		Calendar comparaFimInter = Calendar.getInstance();

		comparaIni.setTime(horaInicio);
		comparaFim.setTime(horaFim);
		comparaIniInter.setTime(horaInicioIntervalo);
		comparaFimInter.setTime(horaFimIntervalo);

		cData.set(Calendar.HOUR, comparaIni.get(Calendar.HOUR));

		if ((cData.get(Calendar.DAY_OF_YEAR)) < (cLocal
				.get(Calendar.DAY_OF_YEAR))) {
			mensagem.mensagemAgendaNaoCadastrada();
		} else {
			if (existe == false) {

				// Intervalo é escolhido na tela, pelo usuário, pode assumir
				// valor 1 (30 em 30 minutos) ou 2 (1 em 1 hora)
				if (intervalo == 1) {
					cHoraFimAuX.add(Calendar.MINUTE, 30);
				} else if (intervalo == 2) {
					cHoraFimAuX.add(Calendar.HOUR, 1);
				}

				while (fim == false) {
					agenda = new Agenda();

					if (((cHoraInicioAuX.after(comparaIni)) || (cHoraInicioAuX
							.compareTo(comparaIni) == 0))
							&& ((cHoraFimAuX.before(comparaFim)) || (cHoraFimAuX
									.compareTo(comparaFim) == 0))) {

						if (((cHoraInicioAuX.after(comparaIniInter)) || (cHoraInicioAuX
								.compareTo(comparaIniInter) == 0))
								&& ((cHoraFimAuX.before(comparaFimInter)) || (cHoraFimAuX
										.compareTo(comparaFimInter) == 0))) {

							System.out.println("Horário de " + cHoraInicioAuX
									+ " até " + cHoraFimAuX
									+ " não será cadastrado!");

							if (intervalo == 1) {
								cHoraInicioAuX.add(Calendar.MINUTE, 30);
								cHoraFimAuX.add(Calendar.MINUTE, 30);
							} else if (intervalo == 2) {
								cHoraInicioAuX.add(Calendar.HOUR, 1);
								cHoraFimAuX.add(Calendar.HOUR, 1);
							}

						} else {

							agenda.setHoraInicio(cHoraInicioAuX.getTime());
							agenda.setData(data);
							agenda.setHoraFim(cHoraFimAuX.getTime());
							agenda.setMedico(medico);
							agenda.setEspecialidade(esp);
							agenda.setUbs(ubs);
							agenda.setStatus("Disponível");// disponível
							agenda.setTagOnline(false);

							AgendaServico.getInstance().cadastrarAgenda(agenda);

							if (intervalo == 1) {
								cHoraInicioAuX.add(Calendar.MINUTE, 30);
								cHoraFimAuX.add(Calendar.MINUTE, 30);
							} else if (intervalo == 2) {
								cHoraInicioAuX.add(Calendar.HOUR, 1);
								cHoraFimAuX.add(Calendar.HOUR, 1);
							}
						}
					} else {
						fim = true;
					}
				}
				mensagem.mensagemCadastroSucesso();
			} else {
				mensagem.mensagemAgendaLotada();
			}
		}
	}

	public void visualizarAgenda() throws Exception {
		listEsp = FuncionarioServico.getInstance().findEspFunc(ubs.getId());
		if (esp != null) {
			listaMed = FuncionarioServico.getInstance().findMedicoByUBSEsp(
					ubs.getId(), esp.getId());
		}
	}

	public void atualizaConsultasMedico() {
		agendaMedico = AgendaServico.getInstance().findPorMedicoData(data, medico.getId());
	}

	public void visualizarAgenda2() {
		listaAgendaDia = new ArrayList<Agenda>();
		listaAgendaDia = AgendaServico.getInstance().findHorariosMedicoUBSEsp(
				data, medico.getId(), esp.getId(), ubs.getId());
	}

	public int getHoraAux() {
		return horaAux;
	}

	public void setHoraAux(int horaAux) {
		this.horaAux = horaAux;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public boolean isDesabilitaBuscaPorLoc() {
		return desabilitaBuscaPorLoc;
	}

	public void setDesabilitaBuscaPorLoc(boolean desabilitaBuscaPorLoc) {
		this.desabilitaBuscaPorLoc = desabilitaBuscaPorLoc;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public List<UBS> getListaUBS() {
		return listaUBS;
	}

	public void setListaUBS(List<UBS> listaUBS) {
		this.listaUBS = listaUBS;
	}

	public UBS getUbs() {
		return ubs;
	}

	public void setUbs(UBS ubs) {
		this.ubs = ubs;
	}

	public List<Especialidade> getListEsp() {
		return listEsp;
	}

	public void setListEsp(List<Especialidade> listEsp) {
		this.listEsp = listEsp;
	}

	public Especialidade getEsp() {
		return esp;
	}

	public void setEsp(Especialidade esp) {
		this.esp = esp;
	}

	public void setIdUBSBusca(boolean idUBSBusca) {
	}

	public Funcionario getMedico() {
		return medico;
	}

	public void setMedico(Funcionario medico) {
		this.medico = medico;
	}

	public List<Funcionario> getListaMed() {
		return listaMed;
	}

	public void setListaMed(List<Funcionario> listaMed) {
		this.listaMed = listaMed;
	}

	public String getNomeMedico() {
		return nomeMedico;
	}

	public void setNomeMedico(String nomeMedico) {
		this.nomeMedico = nomeMedico;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Date horaFim) {
		this.horaFim = horaFim;
	}

	public int getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(int intervalo) {
		this.intervalo = intervalo;
	}

	public Date getHoraInicioIntervalo() {
		return horaInicioIntervalo;
	}

	public void setHoraInicioIntervalo(Date horaInicioIntervalo) {
		this.horaInicioIntervalo = horaInicioIntervalo;
	}

	public Date getHoraFimIntervalo() {
		return horaFimIntervalo;
	}

	public void setHoraFimIntervalo(Date horaFimIntervalo) {
		this.horaFimIntervalo = horaFimIntervalo;
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public boolean isFim() {
		return fim;
	}

	public void setFim(boolean fim) {
		this.fim = fim;
	}

	public Date getHoraInicioAux() {
		return horaInicioAux;
	}

	public void setHoraInicioAux(Date horaInicioAux) {
		this.horaInicioAux = horaInicioAux;
	}

	public List<Agenda> getListaAgendaDia() {
		return listaAgendaDia;
	}

	public void setListaAgendaDia(List<Agenda> listaAgendaDia) {
		this.listaAgendaDia = listaAgendaDia;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public Date getHoraFimAux() {
		return horaFimAux;
	}

	public void setHoraFimAux(Date horaFimAux) {
		this.horaFimAux = horaFimAux;
	}

	public Agenda getAgendaAux() {
		return agendaAux;
	}

	public void setAgendaAux(Agenda agendaAux) {
		this.agendaAux = agendaAux;
	}

	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}

	public Date getComparaI() {
		return comparaI;
	}

	public void setComparaI(Date comparaI) {
		this.comparaI = comparaI;
	}

	public Date getComparaF() {
		return comparaF;
	}

	public void setComparaF(Date comparaF) {
		this.comparaF = comparaF;
	}

	public List<Agenda> getListAgenda() {
		return listAgenda;
	}

	public void setListAgenda(List<Agenda> listAgenda) {
		this.listAgenda = listAgenda;
	}

	public Date getData2() {
		return data2;
	}

	public void setData2(Date data2) {
		this.data2 = data2;
	}

	public Date getHorainicio2() {
		return horainicio2;
	}

	public void setHorainicio2(Date horainicio2) {
		this.horainicio2 = horainicio2;
	}

	public Date getHorafim2() {
		return horafim2;
	}

	public void setHorafim2(Date horafim2) {
		this.horafim2 = horafim2;
	}

	public List<Especialidade> getListEspBusca() {
		return listEspBusca;
	}

	public void setListEspBusca(List<Especialidade> listEspBusca) {
		this.listEspBusca = listEspBusca;
	}

	public List<UBS> getListaUBSBusca() {
		return listaUBSBusca;
	}

	public void setListaUBSBusca(List<UBS> listaUBSBusca) {
		this.listaUBSBusca = listaUBSBusca;
	}

	public boolean isExiste() {
		return existe;
	}

	public void setExiste(boolean existe) {
		this.existe = existe;
	}

	public Agenda getAgendaSelecionada() {
		return agendaSelecionada;
	}

	public void setAgendaSelecionada(Agenda agendaSelecionada) {
		this.agendaSelecionada = agendaSelecionada;
	}

	public List<Paciente> getListPaciente() {
		return listPaciente;
	}

	public void setListPaciente(List<Paciente> listPaciente) {
		this.listPaciente = listPaciente;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public List<Agenda> getAgendaMedico() {
		return agendaMedico;
	}

	public void setAgendaMedico(List<Agenda> agendaMedico) {
		this.agendaMedico = agendaMedico;
	}

	public String getJust() {
		return just;
	}

	public void setJust(String just) {
		this.just = just;
	}

	public String getJustExclusao() {
		return justExclusao;
	}

	public void setJustExclusao(String justExclusao) {
		this.justExclusao = justExclusao;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public boolean isHabilitaP1() {
		return habilitaP1;
	}

	public void setHabilitaP1(boolean habilitaP1) {
		this.habilitaP1 = habilitaP1;
	}

	public boolean isHabilitaP2() {
		return habilitaP2;
	}

	public void setHabilitaP2(boolean habilitaP2) {
		this.habilitaP2 = habilitaP2;
	}

	public List<Descricao> getListaDescProntuario() {
		return listaDescProntuario;
	}

	public void setListaDescProntuario(List<Descricao> listaDescProntuario) {
		this.listaDescProntuario = listaDescProntuario;
	}

	public Descricao getDescricao() {
		return descricao;
	}

	public void setDescricao(Descricao descricao) {
		this.descricao = descricao;
	}

	public List<Receita> getListaRecProntuario() {
		return listaRecProntuario;
	}

	public void setListaRecProntuario(List<Receita> listaRecProntuario) {
		this.listaRecProntuario = listaRecProntuario;
	}

	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public List<Exame> getListaExameProntuario() {
		return listaExameProntuario;
	}

	public void setListaExameProntuario(List<Exame> listaExameProntuario) {
		this.listaExameProntuario = listaExameProntuario;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public List<Medicamento> getListaMedicamento() {
		return listaMedicamento;
	}

	public void setListaMedicamento(List<Medicamento> listaMedicamento) {
		this.listaMedicamento = listaMedicamento;
	}

	public int getQuant() {
		return quant;
	}

	public void setQuant(int quant) {
		this.quant = quant;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public String getTipoAtend() {
		return tipoAtend;
	}

	public void setTipoAtend(String tipoAtend) {
		this.tipoAtend = tipoAtend;
	}

	public String getDescNew() {
		return descNew;
	}

	public void setDescNew(String descNew) {
		this.descNew = descNew;
	}

	public boolean isHabilitaCombo() {
		return habilitaCombo;
	}

	public void setHabilitaCombo(boolean habilitaCombo) {
		this.habilitaCombo = habilitaCombo;
	}

	public List<Medicamento> getListaMedicamentoOutrasUBS() {
		return listaMedicamentoOutrasUBS;
	}

	public void setListaMedicamentoOutrasUBS(
			List<Medicamento> listaMedicamentoOutrasUBS) {
		this.listaMedicamentoOutrasUBS = listaMedicamentoOutrasUBS;
	}

	public int getActiveTab() {
		return activeTab;
	}

	public void setActiveTab(int activeTab) {
		this.activeTab = activeTab;
	}

	public boolean isHabilitaTipoAtd() {
		return habilitaTipoAtd;
	}

	public void setHabilitaTipoAtd(boolean habilitaTipoAtd) {
		this.habilitaTipoAtd = habilitaTipoAtd;
	}

	public void setListaProc(List<Procedimento> listaProc) {
		this.listaProc = listaProc;
	}

	public void setProc(Procedimento proc) {
		this.proc = proc;
	}

	public List<Procedimento> getListaProc() {
		return listaProc;
	}

	public Procedimento getProc() {
		return proc;
	}

	public List<Variavel> getListaVar() {
		return listaVar;
	}

	public void setListaVar(List<Variavel> listaVar) {
		this.listaVar = listaVar;
	}

	public Resultado getResult() {
		return result;
	}

	public void setResult(Resultado result) {
		this.result = result;
	}

	public Prontuario getProntuario() {
		return prontuario;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}

	public Pessoa getUsuario() {
		return usuario;
	}

}
