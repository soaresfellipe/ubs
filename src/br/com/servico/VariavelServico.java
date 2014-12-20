package br.com.servico;

import java.util.List;

import br.com.controle.VariavelControle;
import br.com.modelo.Variavel;

public class VariavelServico {


	/*
	 * Se a classe não necessitar de selects específicos, declarar :
	 * 	private GenericoDAOJPA controller;
	 */
	// private VariavelControle controller;
	
		private VariavelControle controller;

		private static VariavelServico instance = new VariavelServico();

		public static VariavelServico getInstance() {
			return instance;
		}

		public VariavelServico() {
			controller = new VariavelControle();
		}

		/* Métodos
		 * Falta gerar pkg controle e implementar os métodos aqui
		 */

		public void cadastrarVariavel(Variavel variavel) throws Exception {
			controller.create(variavel);
		}

		public void editarVariavel(Variavel variavel) throws Exception {
			controller.edit(variavel);
		}

		public void excluirVariavel(Variavel variavel) throws Exception {
			controller.destroy(variavel);
		}		

		public void findVariavelById(Integer id) throws Exception {
			controller.recuperarPeloID(Variavel.class, id);
		}

		public List<Variavel> listarAllVariavel() throws Exception {
			return controller.list(Variavel.class);
		}

		
}
