package br.com.controle;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;

import br.com.dao.GenericoDAOJPA;
import br.com.modelo.Descricao;

public class DescricaoControle extends GenericoDAOJPA<Descricao> implements
		Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(DescricaoControle.class.getName());

	@SuppressWarnings("unchecked")
	public List<Descricao> findProntPac(int idPac) {
		logger.debug("Método que pesquisa Prontuário pelo ID do Paciente");
		List<Descricao> desc;
		Query query = em.createNamedQuery("Descricao.findProntPac");
		query.setParameter("idPac",idPac);
		logger.debug(query.toString());
		desc = query.getResultList();
		return desc;
	}

}
