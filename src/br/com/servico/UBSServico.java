package br.com.servico;

import java.util.List;

import br.com.controle.UBSControle;
import br.com.modelo.UBS;

public class UBSServico {

	private UBSControle controller;

	private static UBSServico instance = new UBSServico();

	public static UBSServico getInstance() {
		return instance;
	}

	public UBSServico() {
		controller = new UBSControle();
	}

	// Métodos

	public void cadastrarUBS(UBS ubs) throws Exception {
		controller.create(ubs);
	}

	public void editarUBS(UBS ubs) throws Exception {
		controller.edit(ubs);
	}

	public void excluirUBS(UBS ubs) throws Exception {
		controller.destroy(ubs);
	}

	public List<UBS> findUBSByLocalidade(String localidade) throws Exception {
		return controller.findByLocalidade(localidade);
	}

	public List<UBS> findUBSByNome(String nome) throws Exception {
		return controller.findByNome(nome);
	}

	public UBS findUBSById(Integer id) throws Exception {
		return controller.recuperarPeloID(UBS.class, id);
	}

	public List<UBS> listarAllUBS() throws Exception {
		return controller.list(UBS.class);
	}

	public List<UBS> findBairro(String localidade) throws Exception {
		return controller.findBairro();
	}
	
	public List<UBS> findUBSFunc(int idFunc) throws Exception{
		return controller.findUBSFunc(idFunc);
	}
	
	public List<UBS> findNaoUBSFunc(int idFunc) throws Exception{
		return controller.findNaoUBSFunc(idFunc);
	}
}
