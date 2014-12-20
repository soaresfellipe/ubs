package br.com.controle;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;

import br.com.dao.GenericoDAOJPA;
import br.com.modelo.Produto;

public class ProdutoControle extends GenericoDAOJPA<Produto> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(ProdutoControle.class.getName());

	@SuppressWarnings("unchecked")
	public List<Produto> findByNomeProd(String nome){
		logger.debug("Método que consulta um produto pelo nome");
		List<Produto> listaProd;
		Query query = em.createNamedQuery("Produto.findByNome");
		query.setParameter("nome","%"+nome+"%");
		logger.debug(query.toString());
		listaProd = query.getResultList();
		return listaProd;
	}
	
}
