package br.com.controle;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;

import br.com.dao.GenericoDAOJPA;
import br.com.modelo.Variavel;

@SuppressWarnings("serial")
public class VariavelControle extends GenericoDAOJPA<Variavel> implements Serializable {
	
	private static final Logger logger = Logger.getLogger(VacinacaoControle.class.getName());
	
	@SuppressWarnings("unchecked")
	public List<Variavel> findByNome(String nome) {
		logger.debug("Pesquisa de Variável pelo Nome");
		Query query = em.createNamedQuery("Variavel.findByName");
		query.setParameter("nome", nome);
		logger.debug(query.toString());
		return query.getResultList();
	}

}
