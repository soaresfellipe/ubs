package br.com.controle;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;

import br.com.dao.GenericoDAOJPA;
import br.com.modelo.Receita;

public class ReceitaControle extends GenericoDAOJPA<Receita> implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(ReceitaControle.class.getName());

	@SuppressWarnings("unchecked")
	public List<Receita> findRecPac(int idPac) {
		logger.debug("Método de pesquisa de Receitas do Paciente");
		List<Receita> rec;
		Query query = em.createNamedQuery("Receita.findRecPac");
		query.setParameter("idPac", idPac);
		rec = query.getResultList();
		logger.debug(query.toString());
		return rec;
	}
	
	@SuppressWarnings("unchecked")
	public List<Receita> findRecUBSRetirar(int idUBS,Date dtValidade) {
		logger.debug("Método que lista receitas a serem retiradas");
		List<Receita> rec;
		Query query = em.createNamedQuery("Receita.findRecUBSRetirar");
		query.setParameter("idUBS", idUBS);
		query.setParameter("dtValidade", dtValidade);
		rec = query.getResultList();
		logger.debug(query.toString());
		return rec;
	}
	
	@SuppressWarnings("unchecked")
	public List<Receita> findRecUBSAplicar(int idUBS,Date dtValidade) {
		logger.debug("Método que lista receitas a serem aplicadas");
		List<Receita> rec;
		Query query = em.createNamedQuery("Receita.findRecUBSAplicar");
		query.setParameter("idUBS", idUBS);
		query.setParameter("dtValidade", dtValidade);
		logger.debug(query.toString());
		rec = query.getResultList();		
		return rec;
	}

}