package br.com.servico;

import java.util.List;

import br.com.controle.ProcedimentoControle;
import br.com.modelo.Procedimento;
import br.com.modelo.Variavel;

public class ProcedimentoServico {

	/*
	 * Se a classe não necessitar de selects específicos, declarar : private
	 * GenericoDAOJPA controller;
	 */
	
	private ProcedimentoControle controller;

	private static ProcedimentoServico instance = new ProcedimentoServico();

	public static ProcedimentoServico getInstance() {
		return instance;
	}

	public ProcedimentoServico() {
		controller = new ProcedimentoControle();
	}

	/*
	 * Métodos Falta gerar pkg controle e implementar os métodos aqui
	 */

	public void cadastrarProcedimento(Procedimento proc) throws Exception {
		controller.create(proc);
	}

	public void editarProcedimento(Procedimento proc) throws Exception {
		controller.edit(proc);
	}

	public void excluirProcedimento(Procedimento proc) throws Exception {
		controller.destroy(proc);
	}

	public Procedimento findProcedimentoById(Integer id) throws Exception {
		return controller.recuperarPeloID(Procedimento.class, id);
	}

	public List<Procedimento> listarAllProcedimento() throws Exception {
		return controller.list(Procedimento.class);
	}
	
	public List<Procedimento> findByNome(String nome) throws Exception {
		return controller.findByNome(nome);
	}
	
	public List<Variavel> variaveisDoProc(int idproc) throws Exception {
		return controller.variaveisDoProc(idproc);
	}
	
	public List<Variavel> variaveisNaoProc(int idproc) throws Exception {
		return controller.variaveisNaoProc(idproc);
	}

}
