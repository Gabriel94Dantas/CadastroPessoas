package com.prova.mirante.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


@ApplicationScoped
public class EntityManagerProducer {
	
	public static EntityManager getEntityManager() {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProvaDB");
			return emf.createEntityManager();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
