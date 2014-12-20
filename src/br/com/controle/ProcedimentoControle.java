package br.com.controle;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;

import br.com.dao.GenericoDAOJPA;
import br.com.modelo.Procedimento;
import br.com.modelo.Variavel;

public class ProcedimentoControle extends GenericoDAOJPA<Procedimento> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6380918654043423292L;
	
	private static final Logger logger = Logger.getLogger(ProcedimentoControle.class.getName());
	
	@SuppressWarnings("unchecked")
	public List<Procedimento> findByNome(String nome) {
		logger.debug("Método que pesquisa um procedimento pelo nome");
		Query query = em.createNamedQuery("Procedimento.findByNome");
		query.setParameter("nome", "%" + nome + "%");
		logger.debug(query.toString());
		List<Procedimento> listaProcedimento = query.getResultList();
		return listaProcedimento;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Variavel> variaveisDoProc(int idproc) {
		logger.debug("Método que lista as variáveis de um determinado procedimento");
		Query query = em.createNamedQuery("Procedimento.variaveisDoProc");
		query.setParameter("idproc", idproc);
		logger.debug(query.toString());
		List<Variavel> listaVariavelDoProc = query.getResultList();
		return listaVariavelDoProc;
	}
	
	@SuppressWarnings("unchecked")
	public List<Variavel> variaveisNaoProc(int idproc) {
		logger.debug("Método que lista as variáveis que NÃO são deste procedimento");
		Query query = em.createNamedQuery("Procedimento.variaveisNaoProc");
		query.setParameter("idproc", idproc);
		logger.debug(query.toString());
		List<Variavel> listaVariavelNaoProc = query.getResultList();
		return listaVariavelNaoProc;
	}

}
