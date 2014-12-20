package br.com.servico;

import java.util.List;

import br.com.controle.GrupoProcControle;
import br.com.modelo.GrupoProcedimento;

public class GrupoProcServico {

	/*
	 * Se a classe n�o necessitar de selects espec�ficos, declarar : private
	 * GenericoDAOJPA controller;
	 */
	private GrupoProcControle controller;

	private static GrupoProcServico instance = new GrupoProcServico();

	public static GrupoProcServico getInstance() {
		return instance;
	}

	public GrupoProcServico() {
		controller = new GrupoProcControle();
	}

	/*
	 * M�todos Falta gerar pkg controle e implementar os m�todos aqui
	 */

	public void cadastrarGrupo(GrupoProcedimento grupo) throws Exception {
		controller.create(grupo);
	}

	public void editarGrupo(GrupoProcedimento grupo) throws Exception {
		controller.edit(grupo);
	}

	public void excluirGrupo(GrupoProcedimento grupo) throws Exception {
		controller.destroy(grupo);
	}

	public void findGrupoById(Integer id) throws Exception {
		controller.recuperarPeloID(GrupoProcedimento.class, id);
	}

	public List<GrupoProcedimento> listarAllGrupo() throws Exception {
		return controller.list(GrupoProcedimento.class);
	}

}
