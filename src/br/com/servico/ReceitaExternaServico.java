package br.com.servico;

import br.com.controle.ReceitaExternaControle;
import br.com.modelo.ReceitaExterna;

public class ReceitaExternaServico {

	private ReceitaExternaControle controller;

	private static ReceitaExternaServico instance = new ReceitaExternaServico();

	public static ReceitaExternaServico getInstance() {
		return instance;
	}

	public ReceitaExternaServico() {
		controller = new ReceitaExternaControle();
	}

	// Métodos

	public void cadastrarReceitaExterna(ReceitaExterna receitaExterna)
			throws Exception {
		controller.create(receitaExterna);
	}

	public void editarReceitaExterna(ReceitaExterna ReceitaExterna)
			throws Exception {
		controller.edit(ReceitaExterna);
	}

	public void excluirReceitaExterna(ReceitaExterna ReceitaExterna)
			throws Exception {
		controller.destroy(ReceitaExterna);
	}

	public ReceitaExterna findById(int id) throws Exception {
		return controller.recuperarPeloID(ReceitaExterna.class, id);
	}
}
