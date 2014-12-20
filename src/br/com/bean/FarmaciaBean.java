package br.com.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import br.com.modelo.Medicamento;
import br.com.modelo.Paciente;
import br.com.modelo.Receita;
import br.com.modelo.ReceitaExterna;
import br.com.servico.MedicamentoServico;
import br.com.servico.PacienteServico;
import br.com.servico.ReceitaExternaServico;
import br.com.servico.ReceitaServico;

@ManagedBean
@ViewScoped
public class FarmaciaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(FarmaciaBean.class
			.getName());
	
	private List<Receita> listaReceitas;
	private Receita receitaSelecionada;
	private Medicamento medicamento;
	private String medico;
	private int crm;
	private int quant;
	private Date dtSolicitacao;
	private Date dtRetirada;
	private String local;
	private String nome;
	private List<Paciente> listaPaciente;
	private List<Medicamento> listaMedicamento;
	private String medicamentoNome;
	private ReceitaExterna receitaExterna;
	private Paciente paciente;
	private MensagensBean mensagem;

	public FarmaciaBean() {
		mensagem = new MensagensBean();
		medicamentoNome = "";
		listaPaciente = new ArrayList<Paciente>();
		listaMedicamento = new ArrayList<Medicamento>();
		nome = "";
		listaReceitas = new ArrayList<Receita>();
		receitaSelecionada = new Receita();
		medicamento = new Medicamento();
		paciente = new Paciente();
		medico = "";
		quant = 0;
		local = "";
		receitaExterna = new ReceitaExterna();
	}

	public void atualizaLista() {
		logger.debug("Atualiza Lista da Farmácia");
		listaReceitas = ReceitaServico.getInstance().findRecUBSRetirar(2,
				new Date());
	}

	public void limpaCampos() {
		logger.debug("limpaCampos");
		medicamentoNome = "";
		listaPaciente = new ArrayList<Paciente>();
		listaMedicamento = new ArrayList<Medicamento>();
		nome = "";
		listaReceitas = new ArrayList<Receita>();
		receitaSelecionada = new Receita();
		medicamento = new Medicamento();
		paciente = new Paciente();
		medico = "";
		quant = 0;
		local = "";
		crm = 0;

	}

	public void salvarRetiradaReceitaExterna() throws Exception {

		if (quant <= medicamento.getQuantAtual()) {
						
			System.out.println("Retirando medicamento");
			receitaExterna.setDtRetirada(new Date());
			receitaExterna.setDtSolicitacao(dtSolicitacao);
			receitaExterna.setLocal(local);
			receitaExterna.setMedicamento(medicamento);
			receitaExterna.setMedico(medico);
			receitaExterna.setPaciente(paciente);
			receitaExterna.setQuant(quant);
			receitaExterna.setCrm(crm);

			ReceitaExternaServico.getInstance().cadastrarReceitaExterna(
					receitaExterna);
			
			int quantAtual;
			
			quantAtual = medicamento.getQuantAtual() - quant;
			
			medicamento.setQuantAtual(quantAtual);
			
			MedicamentoServico.getInstance().editarMedicamento(medicamento);
			
			mensagem.mensagemReceitaExternaCadastroSucesso();
			logger.info("Receita Externa cadastrada");

		} else {
			mensagem.mensagemFaltaQuantidadeSolicitada();
			logger.warn("Erro: Falta a Quantidade Solicitada");
		}

	}

	public void retirarMedicamentoSemReceita() throws Exception {
		logger.debug("Começou a retirada");

		receitaSelecionada = listaReceitas.get(0);
		receitaSelecionada.setStatus("Concluído");

		medicamento = MedicamentoServico.getInstance().findById(
				receitaSelecionada.getMedicamento().getId());

		int quantAtual = medicamento.getQuantAtual()
				- receitaSelecionada.getQuant();

		medicamento.setQuantAtual(quantAtual);

		ReceitaServico.getInstance().editarReceita(receitaSelecionada);
		MedicamentoServico.getInstance().editarMedicamento(medicamento);
		mensagem.mensagemRetiradaSucesso();
		logger.info("Medicamento "+ medicamento.getNome() + " editado");
	}

	public List<Receita> getListaReceitas() {
		return listaReceitas;
	}

	public void setListaReceitas(List<Receita> listaReceitas) {
		this.listaReceitas = listaReceitas;
	}

	public Receita getReceitaSelecionada() {
		return receitaSelecionada;
	}

	public void setReceitaSelecionada(Receita receitaSelecionada) {
		this.receitaSelecionada = receitaSelecionada;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getMedico() {
		return medico;
	}

	public void setMedico(String medico) {
		this.medico = medico;
	}

	public int getCrm() {
		return crm;
	}

	public void setCrm(int crm) {
		this.crm = crm;
	}

	public int getQuant() {
		return quant;
	}

	public void setQuant(int quant) {
		this.quant = quant;
	}

	public Date getDtSolicitacao() {
		return dtSolicitacao;
	}

	public void setDtSolicitacao(Date dtSolicitacao) {
		this.dtSolicitacao = dtSolicitacao;
	}

	public Date getDtRetirada() {
		return dtRetirada;
	}

	public void setDtRetirada(Date dtRetirada) {
		this.dtRetirada = dtRetirada;
	}

	public String getLocal() {
		return local;
	}

	public MensagensBean getMensagem() {
		return mensagem;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Paciente> getListaPaciente() {
		return listaPaciente;
	}

	public void setListaPaciente(List<Paciente> listaPaciente) {
		this.listaPaciente = listaPaciente;
	}

	public String getMedicamentoNome() {
		return medicamentoNome;
	}

	public void setMedicamentoNome(String medicamentoNome) {
		this.medicamentoNome = medicamentoNome;
	}

	public List<Medicamento> getListaMedicamento() {
		return listaMedicamento;
	}

	public void setListaMedicamento(List<Medicamento> listaMedicamento) {
		this.listaMedicamento = listaMedicamento;
	}

	public ReceitaExterna getReceitaExterna() {
		return receitaExterna;
	}

	public void setReceitaExterna(ReceitaExterna receitaExterna) {
		this.receitaExterna = receitaExterna;
	}

	public void buscaPaciente() {
		listaPaciente = PacienteServico.getInstance().findPacienteByNome(nome);
	}

	public void buscaMedicamento() {
		listaMedicamento = MedicamentoServico.getInstance().findByNomeMedUBS(
				medicamentoNome, 2);
	}
}
