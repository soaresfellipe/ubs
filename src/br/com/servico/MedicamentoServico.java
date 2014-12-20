package br.com.servico;

import java.util.List;

import br.com.controle.MedicamentoControle;
import br.com.modelo.Medicamento;

public class MedicamentoServico {

	private MedicamentoControle controller;

	private static MedicamentoServico instance = new MedicamentoServico();

	public static MedicamentoServico getInstance() {
		return instance;
	}

	public MedicamentoServico() {
		controller = new MedicamentoControle();
	}

	// Métodos

	public void cadastrarMedicamento(Medicamento med) throws Exception {
		controller.create(med);
	}

	public void editarMedicamento(Medicamento med) throws Exception {
		controller.edit(med);
	}

	public void excluirMedicamento(Medicamento med) throws Exception {
		controller.destroy(med);
	}

	public List<Medicamento> findByNomeMed(String nome) {
		return controller.findByNomeMed(nome);
	}

	public Medicamento findById(int id) throws Exception {
			return controller.recuperarPeloID(Medicamento.class, id);
	}
	
	public List<Medicamento> findMed(int idubs){
		return controller.findMed(idubs);
	}
	
	public List<Medicamento> findMedOutrasUBS(int idubs){
		return controller.findMedOutrasUBS(idubs);
	}
	
	public List<Medicamento> findByNomeMedUBS(String nome, int idubs){
		return controller.findByNomeMedUBS(nome, idubs);
	}
	
}
