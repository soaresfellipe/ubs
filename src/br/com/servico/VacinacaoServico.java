package br.com.servico;

import java.util.List;

import br.com.controle.VacinacaoControle;
import br.com.modelo.Paciente;
import br.com.modelo.Vacinacao;

public class VacinacaoServico {
	
	private VacinacaoControle controller;

	private static VacinacaoServico instance = new VacinacaoServico();

	public static VacinacaoServico getInstance() {
		return instance;
	}

	public VacinacaoServico() {
		controller = new VacinacaoControle();
	}

	// Métodos

	public void cadastrarVacinacao(Vacinacao vacinacao) throws Exception {
		controller.create(vacinacao);
	}

	public void editarVacinacao(Vacinacao vacinacao) throws Exception {
		controller.edit(vacinacao);
	}

	public void excluirVacinacao(Vacinacao vacinacao) throws Exception {
		controller.destroy(vacinacao);
	}

	public Vacinacao findVacinacaoById(Integer id) throws Exception {
		return controller.recuperarPeloID(Vacinacao.class, id);
	}

	public List<Vacinacao> listarAllVacinacao() throws Exception {
		return controller.list(Vacinacao.class);
	}
	
	public List<Vacinacao> cadernetaDoPaciente(int idpaciente) throws Exception {
		return controller.cadernetaDoPaciente(idpaciente);
	}
	
	public List<Paciente> findPacientePorNome(String nome) throws Exception {
		return controller.findPacientePorNome(nome);
	}
}
