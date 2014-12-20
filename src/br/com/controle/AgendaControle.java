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
		logger.debug("M�todo que pesquisa hor�rios dispon�veis pelo ID do M�dico");
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
		logger.debug("M�todo que pesquisa hor�rios pelo ID do M�dico");
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
		logger.debug("M�todo de pesquisa de hor�rios por UBS, M�dico e Especialidade");
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
		logger.debug("M�todo de pesquisa de hor�rios livres por M�dico, Especialidade e UBS");
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
		logger.debug("M�todo que pesquisa hor�rios por data, m�dico e UBS");
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
		logger.debug("M�todo de pesquisa hor�rios livres por m�dico, especialidade, UBS e Paciente");
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
