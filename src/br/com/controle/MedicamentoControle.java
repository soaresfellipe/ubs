package br.com.controle;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;

import br.com.dao.GenericoDAOJPA;
import br.com.modelo.Medicamento;

public class MedicamentoControle extends GenericoDAOJPA<Medicamento> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(MedicamentoControle.class.getName());

	@SuppressWarnings("unchecked")
	public List<Medicamento> findByNomeMed(String nome) {
		logger.debug("Método que pesquisa um medicamento pelo nome");
		List<Medicamento> listaMed;
		Query query = em.createNamedQuery("Medicamento.findByNomeMed");
		query.setParameter("nome", "%" + nome + "%");
		logger.debug(query.toString());
		listaMed = query.getResultList();
		return listaMed;
	}

	@SuppressWarnings("unchecked")
	public List<Medicamento> findMed(int idubs) {
		logger.debug("Método que pesquisa medicamentos de uma UBS");
		List<Medicamento> listaMed;
		Query query = em.createNamedQuery("Medicamento.findMed");
		query.setParameter("idubs", idubs);
		logger.debug(query.toString());
		listaMed = query.getResultList();
		return listaMed;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Medicamento> findMedOutrasUBS(int idubs) {
		logger.debug("Método que pesquisa medicamentos de outra UBS");
		List<Medicamento> listaMed;
		Query query = em.createNamedQuery("Medicamento.findMedOutrasUBS");
		query.setParameter("idubs", idubs);
		logger.debug(query.toString());
		listaMed = query.getResultList();
		return listaMed;
	}

	@SuppressWarnings("unchecked")
	public List<Medicamento> findByNomeMedUBS(String nome, int idubs) {
		logger.debug("Pesquisa de medicamentos de um medicamento de UBS pelo nome");
		List<Medicamento> listaMed;
		Query query = em.createNamedQuery("Medicamento.findByNomeMedUBS");
		query.setParameter("nome", "%" + nome + "%");
		query.setParameter("idubs", idubs);
		logger.debug(query.toString());
		listaMed = query.getResultList();
		return listaMed;
	}
}
