package br.com.controle;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;

import br.com.dao.GenericoDAOJPA;
import br.com.modelo.Paciente;
import br.com.modelo.Vacinacao;

public class VacinacaoControle extends GenericoDAOJPA<Vacinacao> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3151063918415242670L;
	
	private static final Logger logger = Logger.getLogger(VacinacaoControle.class.getName());

	@SuppressWarnings("unchecked")
	public List<Vacinacao> cadernetaDoPaciente(int idpaciente) {
		logger.debug("Método que carrega caderneta de vacinação do Paciente");
		Query q = em.createNamedQuery("Vacinacao.cadernetaDoPaciente");
		q.setParameter("idpaciente", idpaciente);
		logger.debug(q.toString());
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Paciente> findPacientePorNome(String nome) {
		logger.debug("Método que carrega Paciente por Nome");
		Query q = em.createNamedQuery("Vacinacao.findPacientePorNome");
		q.setParameter("nome", "%"+nome+"%");
		logger.debug(q.toString());
		return q.getResultList();
	}

}