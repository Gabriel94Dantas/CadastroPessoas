package com.prova.mirante.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.json.JSONObject;

import com.prova.mirante.entity.Operador;
import com.prova.mirante.entity.Perfil;
import com.prova.mirante.producer.EntityManagerProducer;

@Named
@RequestScoped
public class OperadorService {
	
	@SuppressWarnings("unchecked")
	public Operador login(JSONObject credentials) {
		Operador operador = new Operador();
		try {
			EntityManager em = EntityManagerProducer.getEntityManager();
			em.getTransaction().begin();
			List<Operador> operadores = (List<Operador>) em.createNamedQuery("operador.login")
					.setParameter("login", credentials.get("login"))
					.setParameter("senha", credentials.get("senha"))
					.getResultList();
			operador = operadores.get(0);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return operador;
	}
	
	@SuppressWarnings("unchecked")
	public Operador getById(Long idOperador) {
		Operador operador = new Operador();
		try {
			EntityManager em = EntityManagerProducer.getEntityManager();
			em.getTransaction().begin();
			List<Operador> operadores = (List<Operador>) em.createNamedQuery("operador.id")
					.setParameter("idOperador", idOperador)
					.getResultList();
			operador = operadores.get(0);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return operador;
	}
	
	@SuppressWarnings("unchecked")
	public List<Operador> retornarTodos(){
		List<Operador> operadores = new ArrayList<Operador>();
		try {
			EntityManager em = EntityManagerProducer.getEntityManager();
			em.getTransaction().begin();
			operadores = (List<Operador>) em.createNamedQuery("operador.all")
					.getResultList();
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return operadores;
	}
	
	public void editar(Operador operador) {
		try {
			EntityManager em = EntityManagerProducer.getEntityManager();
			em.getTransaction().begin();
			operador.setPerfil(em.find(Perfil.class, operador.getPerfil().getIdPerfil()));
			em.merge(operador);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void salvar(Operador operador) {
		try {
			EntityManager em = EntityManagerProducer.getEntityManager();
			em.getTransaction().begin();
			operador.setDtRegistro(new Date());
			em.persist(operador);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(Operador operador) {
		try {
			EntityManager em = EntityManagerProducer.getEntityManager();
			em.getTransaction().begin();
			em.remove(em.getReference(Operador.class, operador.getIdOperador()));
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static JSONObject converter(Operador operador) {
		JSONObject jsonObject = new JSONObject();
		
		try {
			
			if (operador.getIdOperador() != null && !operador.getIdOperador().equals(0L)) {
				jsonObject.put("idOperador", operador.getIdOperador());
			}
			
			if (operador.getNome() != null && !operador.getNome().isEmpty()) {
				jsonObject.put("nome", operador.getNome());
			}
			
			if (operador.getLogin() != null && !operador.getLogin().isEmpty()) {
				jsonObject.put("login", operador.getLogin());
			}
			
			if (operador.getSenha() != null && !operador.getSenha().isEmpty()) {
				jsonObject.put("senha", operador.getSenha());
			}
			
			if (operador.getPerfil() != null && !operador.getPerfil().equals(new Perfil())) {
				JSONObject jsonObjectPerfil = new JSONObject();
				jsonObjectPerfil.put("descricao", operador.getPerfil().getDescricao());
				jsonObjectPerfil.put("idPerfil", operador.getPerfil().getIdPerfil());
				jsonObject.put("perfil", jsonObjectPerfil);
			}
			
			if (operador.getDtRegistro() != null) {
				jsonObject.put("dtRegistro", operador.getDtRegistro().getTime());
			}
			
			if (operador.getToken() != null && !operador.getToken().isEmpty()) {
				jsonObject.put("token", operador.getToken());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jsonObject;
	}
	
	public static Operador converter(JSONObject jsonObject) {
		Operador operador = new Operador();
		
		try {
			
			if (jsonObject.has("idOperador")) {
				operador.setIdOperador(jsonObject.getLong("idOperador"));
			}
			
			if (jsonObject.has("nome")) {
				operador.setNome(jsonObject.getString("nome"));
			}
			
			if (jsonObject.has("login")) {
				operador.setLogin(jsonObject.getString("login"));
			}
			
			if (jsonObject.has("senha")) {
				operador.setSenha(jsonObject.getString("senha"));
			}
			
			if (jsonObject.has("perfil")) {
				JSONObject jsonObjectPerfil = new JSONObject();
				jsonObjectPerfil = jsonObject.getJSONObject("perfil");
				Perfil perfil = new Perfil();
				perfil.setIdPerfil(jsonObjectPerfil.getLong("idPerfil"));
				if(jsonObjectPerfil.has("descricao")) {
					perfil.setDescricao(jsonObjectPerfil.getString("descricao"));
				}
				operador.setPerfil(perfil);
			}
			
			if (jsonObject.has("dtRegistro")) {
				operador.setDtRegistro(new Date(jsonObject.getLong("dtRegistro")));
			}
			
			if (jsonObject.has("token")) {
				operador.setToken(jsonObject.getString("token"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return operador;
	}
	
}
