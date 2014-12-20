package br.com.servico;

import java.util.List;

import br.com.controle.PessoaControle;
import br.com.modelo.Pessoa;


public class PessoaServico {

	/*
	 * Se a classe n�o necessitar de selects espec�ficos, declarar :
	 * 	private GenericoDAOJPA controller;
	 */
	 private PessoaControle controller;

		private static PessoaServico instance = new PessoaServico();

		public static PessoaServico getInstance() {
			return instance;
		}

		public PessoaServico() {
			controller = new PessoaControle();
		}

		/* M�todos
		 * Falta gerar pkg controle e implementar os m�todos aqui
		 */

		public void cadastrarPessoa(Pessoa pessoa)
				throws Exception {
			controller.create(pessoa);
		}

		public void editarPessoa(Pessoa pessoa)
				throws Exception {
			controller.edit(pessoa);
		}

		public void excluirPessoa(Pessoa pessoa)
				throws Exception {
			controller.destroy(pessoa);
		}

		public void findPessoaById(Integer id) throws Exception {
			controller.recuperarPeloID(Pessoa.class, id);
		}

		public void listarAllPessoa() throws Exception {
			controller.list(Pessoa.class);
		}
		
		public List<Pessoa> findByNome(String nome)throws Exception {
			return controller.findByNome(nome);
		}
		public int findByCPF(String cpf)throws Exception {
			return controller.findByCPF(cpf);
		}
}
