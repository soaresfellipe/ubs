package br.com.servico;

import java.util.List;

import br.com.controle.VacinaControle;
import br.com.modelo.Vacina;

public class VacinaServico {

	private VacinaControle controller;

	private static VacinaServico instance = new VacinaServico();

	public static VacinaServico getInstance() {
		return instance;
	}

	public VacinaServico() {
		controller = new VacinaControle();
	}

	// Métodos

	public void cadastrarVacina(Vacina vacina) throws Exception {
		controller.create(vacina);
	}

	public void editarVacina(Vacina vacina) throws Exception {
		controller.edit(vacina);
	}

	public void excluirVacina(Vacina vacina) throws Exception {
		controller.destroy(vacina);
	}
	
	public List<Vacina> listarAllVacina(){
		return controller.list(Vacina.class);
	}
	
	public Vacina findUBSById(Integer id) throws Exception {
		return controller.recuperarPeloID(Vacina.class, id);
	}
}
