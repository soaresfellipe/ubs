package br.com.servico;

import br.com.controle.ResultadoControle;
import br.com.modelo.Resultado;

public class ResultadoServico {

	/*
	 * Se a classe n�o necessitar de selects espec�ficos, declarar : private
	 * GenericoDAOJPA controller;
	 */
	private ResultadoControle controller;

	private static ResultadoServico instance = new ResultadoServico();

	public static ResultadoServico getInstance() {
		return instance;
	}

	public ResultadoServico() {
		controller = new ResultadoControle();
	}

	/*
	 * M�todos Falta gerar pkg controle e implementar os m�todos aqui
	 */

	public void cadastrarResultado(Resultado result) throws Exception {
		controller.create(result);
	}

	public void editarResultado(Resultado result) throws Exception {
		controller.edit(result);
	}

	public void excluirResultado(Resultado result) throws Exception {
		controller.destroy(result);
	}
}
