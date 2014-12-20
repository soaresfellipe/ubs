package br.com.servico;

import java.util.List;

import br.com.controle.FuncionarioControle;
import br.com.modelo.Especialidade;
import br.com.modelo.Funcionario;
import br.com.modelo.Pessoa;


public class FuncionarioServico {

	 private FuncionarioControle controller;

		private static FuncionarioServico instance = new FuncionarioServico();

		public static FuncionarioServico getInstance() {
			return instance;
		}

		public FuncionarioServico() {
			controller = new FuncionarioControle();
		}

		/* Métodos
		 * Falta gerar pkg controle e implementar os métodos aqui
		 */

		public void cadastrarFunc(Funcionario func)
				throws Exception {
			controller.create(func);
		}

		public void editarFunc(Funcionario func)
				throws Exception {
			controller.edit(func);
		}

		public void excluirFuncionario(Funcionario func)
				throws Exception {
			controller.destroy(func);
		}

		public void findFuncById(Integer id) throws Exception {
			controller.recuperarPeloID(Funcionario.class, id);
		}

		public List<Funcionario> listarAllFunc() throws Exception {
			return controller.list(Funcionario.class);
		}
		
		public int findAClinico (int crf,String uf)throws Exception {
			return controller.findAClinico(crf,uf);
		}
		
		public int findAtendente(Pessoa idPessoa)throws Exception{
			return controller.findAtendente(idPessoa);
		}
		
		public int findEnfermeiro(int coren, String uf)throws Exception{
			return controller.findEnfermeiro(coren, uf);
		}
		
		public int findEstoquista(Pessoa idPessoa)throws Exception{
			return controller.findEstoquista(idPessoa);
		}
		
		public int findMedico(int crm, String uf)throws Exception{
			return controller.findMedico(crm, uf);
		}
		
		public int findFarmaceutico(int crf,String uf)throws Exception {
			return controller.findFarmaceutico(crf,uf);
		}
		
		public Funcionario findFuncByPessoa(int idPessoa) throws Exception {
			return controller.findFuncByPessoa(idPessoa);
		}
		
		public List<Especialidade> findEspFunc(int idUBS){
			return controller.findEspFunc(idUBS);
		}
		
		public List<Funcionario> findMedicoByUBSEsp(int idubs,int idesp){
			return controller.findMedicoByUBSEsp(idubs, idesp);
		}
		
		public List<Funcionario> findMedicoByEsp(int idesp){
			return controller.findMedicoByEsp(idesp);
		}
		
		public List<Funcionario> findFunc(int idubs){
			return controller.findFunc(idubs);
		}
}

