package br.com.controle;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;

import br.com.dao.GenericoDAOJPA;
import br.com.modelo.Agenda;

@SuppressWarnings("serial")
public class AgendaControle extends GenericoDAOJPA<Agenda> implements
		Serializable {

	private static final Logger logger = Logger.getLogger(AgendaControle.class
			.getName());

	@SuppressWarnings("unchecked")
	public boolean findHorarios(Date data, int idmedico) {
		logger.debug("Método que pesquisa horários disponíveis pelo ID do Médico");
		List<Agenda> listaAgenda;
		Query query = em.createNamedQuery("Agenda.findHorarios");
		query.setParameter("data", data);
		query.setParameter("idmed", idmedico);
		logger.debug(query.toString());
		listaAgenda = query.getResultList();
		boolean existe;
		if (listaAgenda.size() == 0) {
			existe = false;
		} else {
			existe = true;
		}
		return existe;
	}

	@SuppressWarnings("unchecked")
	public List<Agenda> findPorMedicoData(Date data, int idmedico) {
		logger.debug("Método que pesquisa horários pelo ID do Médico");
		List<Agenda> listaAgenda;
		Query query = em.createNamedQuery("Agenda.findHorarios");
		query.setParameter("data", data);
		query.setParameter("idmed", idmedico);
		logger.debug(query.toString());
		listaAgenda = query.getResultList();
		return listaAgenda;
	}

	@SuppressWarnings("unchecked")
	public List<Agenda> findHorariosMedicoUBSEsp(Date data, int idmedico,
			int idesp, int idubs) {
		logger.debug("Método de pesquisa de horários por UBS, Médico e Especialidade");
		List<Agenda> listaAgenda;
		Query query = em.createNamedQuery("Agenda.findHorariosMedicoUBSEsp");
		query.setParameter("data", data);
		query.setParameter("idmed", idmedico);
		query.setParameter("idesp", idesp);
		query.setParameter("idubs", idubs);
		logger.debug(query.toString());
		listaAgenda = query.getResultList();
		return listaAgenda;
	}

	@SuppressWarnings("unchecked")
	public List<Agenda> findHorariosLivres(Date data, int idmedico, int idesp,
			int idubs) {
		logger.debug("Método de pesquisa de horários livres por Médico, Especialidade e UBS");
		List<Agenda> listaAgenda;
		Query query = em.createNamedQuery("Agenda.findHorariosLivres");
		query.setParameter("data", data);
		query.setParameter("idmed", idmedico);
		query.setParameter("idesp", idesp);
		query.setParameter("idubs", idubs);
		logger.debug(query.toString());
		listaAgenda = query.getResultList();
		return listaAgenda;
	}

	@SuppressWarnings("unchecked")
	public boolean findHorariosByUBS(Date data, int idmedico, int idubs) {
		logger.debug("Método que pesquisa horários por data, médico e UBS");
		List<Agenda> listaAgenda;
		boolean existe = false;
		Query query = em.createNamedQuery("Agenda.findHorariosByUBS");
		query.setParameter("data", data);
		query.setParameter("idmed", idmedico);
		query.setParameter("idubs", idubs);
		logger.debug(query.toString());
		listaAgenda = query.getResultList();
		if (listaAgenda.size() == 0) {
			existe = false;
		} else {
			existe = true;
		}
		return existe;
	}

	@SuppressWarnings("unchecked")
	public List<Agenda> horariosLivresPaciente(Date data, int idmedico,
			int idesp, int idubs, int idpaciente) {
		logger.debug("Método de pesquisa horários livres por médico, especialidade, UBS e Paciente");
		List<Agenda> listaAgenda;
		Query query = em.createNamedQuery("Agenda.horariosLivresPaciente");
		query.setParameter("data", data);
		query.setParameter("idmed", idmedico);
		query.setParameter("idesp", idesp);
		query.setParameter("idubs", idubs);
		query.setParameter("idpaciente", idpaciente);
		logger.debug(query.toString());
		listaAgenda = query.getResultList();
		return listaAgenda;
	}
}
