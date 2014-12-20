package br.com.servico;

import java.util.List;

import br.com.controle.ProntuarioControle;
import br.com.modelo.Prontuario;

public class ProntuarioServico {

	private ProntuarioControle controller;

	private static ProntuarioServico instance = new ProntuarioServico();

	public static ProntuarioServico getInstance() {
		return instance;
	}

	public ProntuarioServico() {
		controller = new ProntuarioControle();
	}

	/*
	 * Métodos Falta gerar pkg controle e implementar os métodos aqui
	 */

	public void cadastrarProntuario(Prontuario pront) throws Exception {
		controller.create(pront);
	}

	public void editarProntuario(Prontuario pront) throws Exception {
		controller.edit(pront);
	}

	public void excluirProntuario(Prontuario pront) throws Exception {
		controller.destroy(pront);
	}

	public void findProntuarioById(Integer id) throws Exception {
		controller.recuperarPeloID(Prontuario.class, id);
	}

	public List<Prontuario> listarAllGrupo() throws Exception {
		return controller.list(Prontuario.class);
	}
	
}
