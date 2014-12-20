package br.com.controle;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;

import br.com.dao.GenericoDAOJPA;
import br.com.modelo.Paciente;

public class PacienteControle extends GenericoDAOJPA<Paciente> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(PacienteControle.class.getName());

	@SuppressWarnings("unchecked")
	public int findByCODSUS(String cns) {
		logger.debug("Método de consulta de paciente pelo código do SUS");
		int paciente = 0;
		List<Paciente> result;
		Query query = em.createNamedQuery("Paciente.findByCODSUS");
		query.setParameter("cns", cns);
		logger.debug(query.toString());
		result = query.getResultList();
		if (result.isEmpty()) {
			paciente = 0;
		} else {
			if (result.size() == 1) {
				paciente = 1;
			}
		}
		return paciente;
	}

	public Paciente findByPessoa(int idPessoa) {
		logger.debug("Método que pesquisa um Paciente pelo ID da Pessoa");
		Paciente paciente;
		Query query = em.createNamedQuery("Paciente.findByPessoa");
		query.setParameter("idPessoa", idPessoa);
		logger.debug(query.toString());
		paciente = (Paciente) query.getSingleResult();
		return paciente;
	}

	@SuppressWarnings("unchecked")
	public List<Paciente> findByNome(String nome, int idmedico, Date data) {
		logger.debug("Método de pesquisa de Paciente pelo nome para agendar Paciente na consulta");
		List<Paciente> paciente;
		Query query = em.createNamedQuery("Paciente.findByNome");
		query.setParameter("nome", "%" + nome + "%");
		query.setParameter("idmedico", idmedico);
		query.setParameter("data", data);
		logger.debug(query.toString());
		paciente = query.getResultList();
		return paciente;
	}

	@SuppressWarnings("unchecked")
	public List<Paciente> findByNomeEncaixe(String nome, int idmedico,
			Date data, int idubs) {
		logger.debug("Método de pesquisa de Paciente pelo nome para agendar Paciente no Encaixe");
		List<Paciente> paciente;
		Query query = em.createNamedQuery("Paciente.findByNomeEncaixe");
		query.setParameter("nome", "%" + nome + "%");
		query.setParameter("idmedico", idmedico);
		query.setParameter("data", data);
		query.setParameter("idubs", idubs);
		logger.debug(query.toString());
		paciente = query.getResultList();

		return paciente;
	}
	
	@SuppressWarnings("unchecked")
	public List<Paciente> findPacienteByNome(String nome) {
		List<Paciente> paciente;
		Query query = em.createNamedQuery("Paciente.findPacienteByNome");
		query.setParameter("nome", "%" + nome + "%");
		paciente = query.getResultList();
		return paciente;
	}
}
