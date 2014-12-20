package br.com.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class UtilJPA {
	
	private static UtilJPA instance;
	private EntityManager em;
	private EntityTransaction et;
	EntityManagerFactory factory;
	
	private UtilJPA () {
	
		//Obtém o factory a partir da unidade de persistência
		factory = Persistence.createEntityManagerFactory("UBS_PU");
		this.em = factory.createEntityManager();
		this.et = getEntityManager().getTransaction();
	}
	
	protected static UtilJPA getInstance() {
		if (instance == null) {
			instance = new UtilJPA();
		}
		return instance;
	}
	
	public EntityManager getEntityManager() {
		return em;
	}

	public EntityTransaction getEntityTransaction(){
		return et;
	}	

	// Inicia transacao de banco de dados 
	public void beginTrans() {
		em.getTransaction().begin();
	}

	// Persiste a transacao no banco de dados
	public void commitTrans() {
		em.getTransaction().commit();
	}

	// Cancela a transacao no banco de dados 
	public void rollbackTrans() {
		if (et.isActive()) {
			et.rollback();
		}
	}	

	//Fecha o factory para liberar os recursos utilizados
	public void closeFactory() {
		factory.close();
	}	
}
