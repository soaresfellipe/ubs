package br.com.servico;

import java.util.List;

import br.com.controle.ProdutoControle;
import br.com.modelo.Produto;

public class ProdutoServico {

	private ProdutoControle controller;

	private static ProdutoServico instance = new ProdutoServico();

	public static ProdutoServico getInstance() {
		return instance;
	}

	public ProdutoServico() {
		controller = new ProdutoControle();
	}

	// Métodos

	public void cadastrarProduto(Produto prod) throws Exception {
		controller.create(prod);
	}

	public void editarProduto(Produto prod) throws Exception {
		controller.edit(prod);
	}

	public void excluirProduto (Produto prod) throws Exception {
		controller.destroy(prod);
	}
	
	public Produto findById(int id) throws Exception{
		return controller.recuperarPeloID(Produto.class, id);
	}
	
	public List<Produto> findByNomeProd(String nome){
		return controller.findByNomeProd(nome);		
	}

}
