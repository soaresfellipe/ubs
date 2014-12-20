package br.com.controle;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;

import br.com.dao.GenericoDAOJPA;
import br.com.modelo.GrupoProcedimento;

@SuppressWarnings("serial")
public class GrupoProcControle extends GenericoDAOJPA<GrupoProcedimento> implements Serializable {
	
	private static final Logger logger = Logger.getLogger(GrupoProcControle.class.getName());
	
	@SuppressWarnings("unchecked")
	public List<GrupoProcedimento> findByNome(String nome) {
		logger.debug("Método que pesquisa o nome do Grupo de Procedimento pelo nome");
		Query query = em.createNamedQuery("GrupoProcedimento.findByName");
		query.setParameter("nome", nome);
		logger.debug(query.toString());
		return query.getResultList();
	}

}
