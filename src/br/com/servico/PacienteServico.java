package br.com.servico;

import java.util.Date;
import java.util.List;

import br.com.controle.PacienteControle;
import br.com.modelo.Paciente;

public class PacienteServico {
	
	/*
	 * Se a classe não necessitar de selects específicos, declarar :
	 * 	private GenericoDAOJPA controller;
	 */
	 private PacienteControle controller;

		private static PacienteServico instance = new PacienteServico();

		public static PacienteServico getInstance() {
			return instance;
		}

		public PacienteServico() {
			controller = new PacienteControle();
		}

		/* Métodos
		 * Falta gerar pkg controle e implementar os métodos aqui
		 */

		public void cadastrarPaciente(Paciente paciente)
				throws Exception {
			controller.create(paciente);
		}

		public void editarPaciente(Paciente paciente)
				throws Exception {
			controller.edit(paciente);
		}

		public void excluirPaciente(Paciente paciente)
				throws Exception {
			controller.destroy(paciente);
		}

		public Paciente findPacienteById(Integer id) throws Exception {
			return controller.recuperarPeloID(Paciente.class, id);
		}

		public List<Paciente> listarAllPaciente() throws Exception {
			return controller.list(Paciente.class);
		}
		
		public int findByCODSUS(String cns)throws Exception {
			return controller.findByCODSUS(cns);
		}
		
		public Paciente findByPessoa(int idPessoa)throws Exception {
			return controller.findByPessoa(idPessoa);
		}
		
		public List<Paciente> findByNome(String nome,int idmedico, Date data){
			return controller.findByNome(nome,idmedico,data);
		}
		
		public List<Paciente> findByNomeEncaixe(String nome,int idmedico, Date data,int idubs){
			return controller.findByNomeEncaixe(nome,idmedico,data,idubs);
		}
		public List<Paciente> findPacienteByNome(String nome){
			return controller.findPacienteByNome(nome);
		}
}
