package br.com.servico;

import java.util.List;

import br.com.controle.DescricaoControle;
import br.com.modelo.Descricao;

public class DescricaoServico {


		private DescricaoControle controller;

		private static DescricaoServico instance = new DescricaoServico();

		public static DescricaoServico getInstance() {
			return instance;
		}

		public DescricaoServico() {
			controller = new DescricaoControle();
		}

		// Métodos

		public void cadastrarDescricao(Descricao desc) throws Exception {
			controller.create(desc);
		}

		public void editarDescricao(Descricao desc) throws Exception {
			controller.edit(desc);
		}

		public void excluirDescricao (Descricao desc) throws Exception {
			controller.destroy(desc);
		}
		
		public Descricao findById(int id) throws Exception{
			return controller.recuperarPeloID(Descricao.class, id);
		}
		
		public List<Descricao> findProntPac(int idPac){
			return controller.findProntPac(idPac);
		}

	
}
