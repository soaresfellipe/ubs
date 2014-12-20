package br.com.controle;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;

import br.com.dao.GenericoDAOJPA;
import br.com.modelo.UBS;


public class UBSControle extends GenericoDAOJPA<UBS> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(UBSControle.class.getName());


	@SuppressWarnings("unchecked")
	public List<UBS> findByLocalidade(String localidade) {
		logger.debug("Método de pesquisa de UBS por localidade");
		Query query = em.createNamedQuery("UBS.findByLocalidade");
		query.setParameter("localidade", "%"+localidade+"%");
		logger.debug(query.toString());
		List<UBS> listaUBS = query.getResultList();
		return listaUBS;
	}
	
	@SuppressWarnings("unchecked")
	public List<UBS> findByNome(String nome) {
		logger.debug("Método de pesquisa de UBS por nome");
		Query query = em.createNamedQuery("UBS.findByNome");
		query.setParameter("nome", "%" + nome + "%");
		logger.debug(query.toString());
		List<UBS> listaUBS = query.getResultList();
		return listaUBS;
	}
	
	@SuppressWarnings("unchecked")
	public List<UBS> findBairro() {
		logger.debug("Método de pesquisa de UBS pelo bairro");
		Query query = em.createNamedQuery("UBS.findBairro");
		logger.debug(query.toString());
		List<UBS> listaUBS = query.getResultList();
		return listaUBS;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<UBS> findUBSFunc(int idFunc) {
		logger.debug("Método de pesquisa de UBS vinculada ao Funcionário");
		Query query = em.createNamedQuery("UBS.findUBSFunc");
		query.setParameter("idFunc", idFunc);
		logger.debug(query.toString());
		List<UBS> listaUBS = query.getResultList();
		return listaUBS;
	}
	
	@SuppressWarnings("unchecked")
	public List<UBS> findNaoUBSFunc(int idFunc) {
		logger.debug("Método de pesquisa de UBS NÃO vinculada ao Funcionário");
		Query query = em.createNamedQuery("UBS.findNaoUBSFunc");
		query.setParameter("idFunc", idFunc);
		logger.debug(query.toString());
		List<UBS> listaUBS = query.getResultList();
		return listaUBS;
	}
}
