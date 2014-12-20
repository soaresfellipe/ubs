package br.com.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class CargaTabelas {

	public static void main(String[] args) throws Exception {
		// Fabrica de Gerenciador de Entidades
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UBS_PU");
		
		// Gerenciador de Entidades
		EntityManager em = factory.createEntityManager();
		//em.getTransaction().begin();S
		//em.getTransaction().commit();

	}

}
