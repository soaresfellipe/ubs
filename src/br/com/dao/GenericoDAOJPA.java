package br.com.dao;

import java.util.List;

import javax.persistence.EntityManager;


public class GenericoDAOJPA<E> implements GenericoDAO<E> {

	//private static Logger log = Logger.getLogger(GenericoDAOJPA.class);
	protected EntityManager em = UtilJPA.getInstance().getEntityManager();

	public E create(E entity) throws Exception {

		try {
			if  (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}
			em.persist(entity);
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			throw new Exception("Não foi possível inserir a entidade.", ex);
		}
		return entity;
	}

	public E edit(E entity) throws Exception {
		try {
			if  (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}
			entity = em.merge(entity);
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			throw new Exception("Não foi possível alterar a entidade.", ex);
		}
		return entity;
	}

	public void destroy(E entity) throws Exception {
		try {
			if  (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}
			em.remove(entity);
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			throw new Exception("Não foi possível remover a entidade.", ex);
		}
	}

	public E recuperarPeloID(Class<E> persistentClass, Integer id) throws Exception {
		return em.find(persistentClass, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<E> list(Class<E> persistentClass) {
		return em.createQuery("SELECT e FROM " + persistentClass.getName() + " e").getResultList();
	}

	
	
	
}
