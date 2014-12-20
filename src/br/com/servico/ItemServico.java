package br.com.servico;

import java.util.List;

import br.com.controle.ItemControle;
import br.com.modelo.Item;

public class ItemServico {
	
		private ItemControle controller;

		private static ItemServico instance = new ItemServico();

		public static ItemServico getInstance() {
			return instance;
		}

		public ItemServico() {
			controller = new ItemControle();
		}

		// Métodos

		public void cadastrarItem(Item item) throws Exception {
			controller.create(item);
		}

		public void editarItem(Item item) throws Exception {
			controller.edit(item);
		}

		public void excluirItem(Item item) throws Exception {
			controller.destroy(item);
		}

		public Item findItemById(Integer id) throws Exception {
			return controller.recuperarPeloID(Item.class, id);
		}

		public List<Item> listarAllItem() throws Exception {
			return controller.list(Item.class);
		}
}
