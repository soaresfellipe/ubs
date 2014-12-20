package br.com.controle;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;

import br.com.dao.GenericoDAOJPA;
import br.com.modelo.Pessoa;


public class PessoaControle extends GenericoDAOJPA<Pessoa> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(PessoaControle.class.getName());

	@SuppressWarnings("unchecked")
	public List<Pessoa> findByNome(String nome) {
		logger.debug("Método que consulta uma pessoa pelo nome");
		Query query = em.createNamedQuery("Pessoa.findByNome");
		query.setParameter("nome", "%" + nome + "%");
		logger.debug(query.toString());
		List<Pessoa> pessoas = query.getResultList();
		return pessoas;
	}
	
	@SuppressWarnings("unchecked")
	public int findByCPF(String cpf) {
		logger.debug("Método que pesquisa uma pessoa pelo seu CPF");
		int pessoa = 0;
		List<Pessoa> result;
		Query query = em.createNamedQuery("Pessoa.findByCPF");
		query.setParameter("cpf", cpf);
		logger.debug(query.toString());
		result = query.getResultList();
		
		if (result.isEmpty()) {
			pessoa = 0;
		} else {
			if (result.size() == 1) {
				pessoa = (int) query.getSingleResult();
				System.out.println("ok");
			}
		}
		return pessoa;
	}

}