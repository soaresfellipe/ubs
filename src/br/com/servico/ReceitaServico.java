package br.com.servico;

import java.util.Date;
import java.util.List;

import br.com.controle.ReceitaControle;
import br.com.modelo.Receita;

public class ReceitaServico {

	private ReceitaControle controller;

	private static ReceitaServico instance = new ReceitaServico();

	public static ReceitaServico getInstance() {
		return instance;
	}

	public ReceitaServico() {
		controller = new ReceitaControle();
	}

	// Métodos

	public void cadastrarReceita(Receita rec) throws Exception {
		controller.create(rec);
	}

	public void editarReceita(Receita rec) throws Exception {
		controller.edit(rec);
	}

	public void excluirReceita(Receita rec) throws Exception {
		controller.destroy(rec);
	}

	public Receita findById(int id) throws Exception {
		return controller.recuperarPeloID(Receita.class, id);
	}
	
	public List<Receita> findRecPac(int idPac){
		return controller.findRecPac(idPac);
	}
	
	public List<Receita> findRecUBSRetirar(int idUBS,Date dtValidade){
		return controller.findRecUBSRetirar(idUBS, dtValidade);
	}
	
	public List<Receita> findRecUBSAplicar(int idUBS,Date dtValidade){
		return controller.findRecUBSAplicar(idUBS, dtValidade);
	}
}
