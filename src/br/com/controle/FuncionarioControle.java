package br.com.controle;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;

import br.com.dao.GenericoDAOJPA;
import br.com.modelo.Especialidade;
import br.com.modelo.Funcionario;
import br.com.modelo.Pessoa;

public class FuncionarioControle extends GenericoDAOJPA<Funcionario> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(FuncionarioControle.class.getName());

	@SuppressWarnings("unchecked")
	public int findAClinico(int crf,String uf) {
		logger.debug("Método que consulta um Analista Clínico pelo CRF/UF");
		int aclinico = 0;
		List<Funcionario> result;
		Query query = em.createNamedQuery("Funcionario.findAClinico");
		query.setParameter("crf", crf);
		query.setParameter("uf", uf);
		logger.debug(query.toString());
		result = query.getResultList();
		if (result.isEmpty()) {
			aclinico = 0;
		} else {
			if (result.size() == 1) {
				aclinico = 1;
			}
		}
		return aclinico;
	}
	
	@SuppressWarnings("unchecked")
	public int findAtendente(Pessoa idPessoa) {
		logger.debug("Método que pesquisa um atendente pelo ID da Pessoa");
		int atendente = 0;
		List<Funcionario> result;
		Query query = em.createNamedQuery("Funcionario.findAtendente");
		query.setParameter("idPessoa", idPessoa);
		logger.debug(query.toString());
		result = query.getResultList();
		if (result.isEmpty()) {
			atendente = 0;
		} else {
			if (result.size() == 1) {
				atendente = 1;
			}
		}
		return atendente;
	}
	
	@SuppressWarnings("unchecked")
	public int findEnfermeiro(int coren, String uf) {
		logger.debug("Método que pesquisa um enfermeiro pelo COREN");
		int enfermeiro = 0;
		List<Funcionario> result;
		Query query = em.createNamedQuery("Funcionario.findEnfermeiro");
		query.setParameter("coren", coren);
		query.setParameter("uf", uf);
		logger.debug(query.toString());
		result = query.getResultList();
		if (result.isEmpty()) {
			enfermeiro = 0;
		} else {
			if (result.size() == 1) {
				enfermeiro = 1;
			}
		}
		return enfermeiro;
	}
	
	@SuppressWarnings("unchecked")
	public int findEstoquista(Pessoa idPessoa) {
		logger.debug("Método que pesquisa um Estoquista pelo ID da Pessoa");
		int estoquista = 0;
		List<Funcionario> result;
		Query query = em.createNamedQuery("Funcionario.findEstoquista");
		query.setParameter("idPessoa", idPessoa);
		logger.debug(query.toString());
		result = query.getResultList();
		if (result.isEmpty()) {
			estoquista = 0;
		} else {
			if (result.size() == 1) {
				estoquista = 1;
			}
		}
		return estoquista;
	}
	
	@SuppressWarnings("unchecked")
	public int findFarmaceutico(int crf, String uf) {
		logger.debug("Método que pesquisa um Farmacêutico pelo CRF/UF");
		int farmaceutico = 0;
		List<Funcionario> result;
		Query query = em.createNamedQuery("Funcionario.findFarmaceutico");
		query.setParameter("crf", crf);
		query.setParameter("uf", uf);
		logger.debug(query.toString());
		result = query.getResultList();
		if (result.isEmpty()) {
			farmaceutico = 0;
		} else {
			if (result.size() == 1) {
				farmaceutico = 1;
			}
		}
		return farmaceutico;
	}
	
	@SuppressWarnings("unchecked")
	public int findMedico(int crm, String uf) {
		int medico = 0;
		List<Funcionario> result;
		Query query = em.createNamedQuery("Funcionario.findMedico");
		query.setParameter("crm", crm);
		query.setParameter("uf", uf);
		result = query.getResultList();
		if (result.isEmpty()) {
			medico = 0;
		} else {
			if (result.size() == 1) {
				medico = 1;
			}
		}
		return medico;
	}
	
	public Funcionario findFuncByPessoa(int idPessoa) {
		Funcionario funcionario;
		Query query = em.createNamedQuery("Funcionario.findFuncByPessoa");
		query.setParameter("idPessoa", idPessoa);
		funcionario = (Funcionario) query.getSingleResult();
		
		return funcionario;
	}
	                                                                                                                                             
	@SuppressWarnings("unchecked")
	public List<Especialidade> findEspFunc(int idUBS) {
		List<Especialidade> esp;
		Query query = em.createNamedQuery("Funcionario.findEspFunc");
		query.setParameter("idUBS", idUBS);
		esp = query.getResultList();
		System.out.println(query.toString());
		return esp;		
	}
	
	@SuppressWarnings("unchecked")
	public List<Funcionario> findMedicoByUBSEsp(int idubs,int idesp) {
		List<Funcionario> func;
		Query query = em.createNamedQuery("Funcionario.findMedicoByUBSEsp");
		query.setParameter("idubs", idubs);
		query.setParameter("idesp", idesp);
		func = query.getResultList();
		return func;		
	}
	
	@SuppressWarnings("unchecked")
	public List<Funcionario> findMedicoByEsp(int idesp){
		List<Funcionario> func;
		Query query = em.createNamedQuery("Funcionario.findMedicoByEsp");
		query.setParameter("idesp", idesp);
		func = query.getResultList();
		return func;		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Funcionario> findFunc(int idubs){
		List<Funcionario> func;
		Query query = em.createNamedQuery("Funcionario.findFunc");
		query.setParameter("idubs", idubs);
		func = query.getResultList();
		return func;		
	}	
}
