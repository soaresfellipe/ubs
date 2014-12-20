package br.com.servico;

import java.util.List;

import br.com.controle.ExameControle;
import br.com.modelo.Exame;
import br.com.modelo.Paciente;
import br.com.modelo.Resultado;

public class ExameServico {

	/*
	 * Se a classe não necessitar de selects específicos, declarar : private
	 * GenericoDAOJPA controller;
	 */
	
	private ExameControle controller;

	private static ExameServico instance = new ExameServico();

	public static ExameServico getInstance() {
		return instance;
	}

	public ExameServico() {
		controller = new ExameControle();
	}

	/*
	 * Métodos Falta gerar pkg controle e implementar os métodos aqui
	 */

	public void cadastrarExame(Exame exame) throws Exception {
		controller.create(exame);
	}

	public void editarExame(Exame exame) throws Exception {
		controller.edit(exame);
	}

	public void excluirExame(Exame exame) throws Exception {
		controller.destroy(exame);
	}
	
	public List<Exame> findExamesDoPaciente(int idexame, String status) throws Exception {
		return controller.findExamesDoPaciente(idexame, status);
	}
	
	public List<Paciente> findPacientesPorNome(String nome) throws Exception {
		return controller.findPacientesComExame(nome);
	}
	
	public List<Resultado> listaVariavelResultado(int idexame) throws Exception {
		return controller.listaVariavelResultado(idexame);
	}

	public List<Exame> findExamePac(int idpac){
		return controller.findExamePac(idpac);
	}
}
