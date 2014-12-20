package br.com.controle;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;

import br.com.dao.GenericoDAOJPA;
import br.com.modelo.Especialidade;


public class EspecialidadeControle extends GenericoDAOJPA<Especialidade> implements Serializable {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(EspecialidadeControle.class.getName());

		@SuppressWarnings("unchecked")
		public List<Especialidade> findByNome(String nome) {
			logger.debug("Método que pesquisa Especialidade pelo Nome");
	        Query query = em.createNamedQuery("Especialidade.findByName");
	        query.setParameter("nome", nome);
	        logger.debug(query.toString());
	        return query.getResultList();
		 }
}
