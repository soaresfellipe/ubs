package br.com.controle;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;

import br.com.dao.GenericoDAOJPA;
import br.com.modelo.Exame;
import br.com.modelo.Paciente;
import br.com.modelo.Resultado;

public class ExameControle extends GenericoDAOJPA<Exame> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -932604835374678523L;
	
	private static final Logger logger = Logger.getLogger(ExameControle.class.getName());

	@SuppressWarnings("unchecked")
	public List<Exame> findExamesDoPaciente(int idpaciente, String status) {
		logger.debug("Método que pesquisa um Exame pelo ID do Paciente");
		Query q = em.createNamedQuery("Exame.findExamesDoPaciente");
		q.setParameter("idpaciente", idpaciente);
		q.setParameter("status", status);
		logger.debug(q.toString());
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Resultado> listaVariavelResultado(int idexame) {
		logger.debug("Método que lista resultados das variáveis pelo ID do Exame");
		Query q = em.createNamedQuery("Exame.listaVariavelResultado");
		q.setParameter("idexame", idexame);
		logger.debug(q.toString());
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Paciente> findPacientesComExame(String nome) {
		Query q = em.createNamedQuery("Exame.findPacientesPorNome");
		q.setParameter("nome", "%" + nome + "%");
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Exame> findExamePac(int idpac) {
		Query q = em.createNamedQuery("Exame.findExamePac");
		q.setParameter("idPac", idpac);
		return q.getResultList();
	}
	
	
}
