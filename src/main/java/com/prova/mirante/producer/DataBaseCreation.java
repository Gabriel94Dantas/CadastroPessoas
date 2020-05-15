package com.prova.mirante.producer;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.prova.mirante.entity.Operador;
import com.prova.mirante.entity.Perfil;

public class DataBaseCreation {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProvaDB");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Perfil perfilGerente = new Perfil();
		perfilGerente.setDescricao("GERENTE");
		Perfil perfilAnalista = new Perfil();
		perfilAnalista.setDescricao("ANALISTA");
		Perfil perfilAdministrador = new Perfil();
		perfilAdministrador.setDescricao("ADMINISTRADOR");
		
		em.persist(perfilAdministrador);
		em.persist(perfilAnalista);
		em.persist(perfilGerente);
		
		Operador operador = new Operador();
		operador.setDtRegistro(new Date());
		operador.setLogin("admin");
		operador.setNome("Administrador");
		operador.setPerfil(em.find(Perfil.class, 1L));
		operador.setSenha("admin1");
		
		em.persist(operador);
		
		em.getTransaction().commit();
		em.close();
		emf.close();
		System.out.println("RODEEEEEEIII");
	} 
	
}
