package br.com.servico;

import java.util.List;

import br.com.controle.PedidoControle;
import br.com.modelo.Pedido;

public class PedidoServico {

	private PedidoControle controller;

	private static PedidoServico instance = new PedidoServico();

	public static PedidoServico getInstance() {
		return instance;
	}

	public PedidoServico() {
		controller = new PedidoControle();
	}

	// Métodos

	public void cadastrarPedido(Pedido pedido) throws Exception {
		controller.create(pedido);
	}

	public void editarPedido(Pedido pedido) throws Exception {
		controller.edit(pedido);
	}

	public void excluirPedido(Pedido pedido) throws Exception {
		controller.destroy(pedido);
	}

	public Pedido findItemById(Integer id) throws Exception {
		return controller.recuperarPeloID(Pedido.class, id);
	}

	public List<Pedido> listarAllPedido() throws Exception {
		return controller.list(Pedido.class);
	}
}
