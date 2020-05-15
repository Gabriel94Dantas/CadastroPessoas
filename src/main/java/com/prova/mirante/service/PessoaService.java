package com.prova.mirante.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.json.JSONObject;

import com.prova.mirante.entity.Operador;
import com.prova.mirante.entity.Pessoa;
import com.prova.mirante.producer.EntityManagerProducer;

@Named
@RequestScoped
public class PessoaService {

	@SuppressWarnings("unchecked")
	public List<Pessoa> retornarTodos() {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		try {
			EntityManager em = EntityManagerProducer.getEntityManager();
			em.getTransaction().begin();
			pessoas = (List<Pessoa>) em.createNamedQuery("pessoa.all")
					.getResultList();
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pessoas;
	}
	
	@SuppressWarnings("unchecked")
	public Pessoa getById(Long idPessoa) {
		Pessoa pessoa = new Pessoa();
		try {
			EntityManager em = EntityManagerProducer.getEntityManager();
			em.getTransaction().begin();
			List<Pessoa> pessoas = (List<Pessoa>) em.createNamedQuery("pessoa.id")
					.setParameter("idPessoa", idPessoa)
					.getResultList();
			pessoa = pessoas.get(0);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pessoa;
	}
	
	public void editar(Pessoa pessoa) {
		try {
			EntityManager em = EntityManagerProducer.getEntityManager();
			em.getTransaction().begin();
			//pessoa.setOperador(em.find(Operador.class, pessoa.getOperador().getIdOperador()));
			em.merge(pessoa);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void salvar(Pessoa pessoa) {
		try {
			EntityManager em = EntityManagerProducer.getEntityManager();
			em.getTransaction().begin();
			pessoa.setDtRegistro(new Date());
			em.persist(pessoa);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(Pessoa pessoa) {
		try {
			EntityManager em = EntityManagerProducer.getEntityManager();
			em.getTransaction().begin();
			em.remove(em.getReference(Pessoa.class,pessoa.getIdPessoa()));
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static JSONObject converter(Pessoa pessoa) {
		JSONObject jsonObject = new JSONObject();
		try {
			
			if (pessoa.getIdPessoa() != null && !pessoa.getIdPessoa().equals(0L)) {
				jsonObject.put("idPessoa", pessoa.getIdPessoa());
			}
			
			if (pessoa.getNome() != null && !pessoa.getNome().isEmpty()) {
				jsonObject.put("nome", pessoa.getNome());
			}
			
			if (pessoa.getDocumento() != null && !pessoa.getDocumento().isEmpty()) {
				jsonObject.put("documento", pessoa.getDocumento());
			}
			
			if (pessoa.getDtNascimento() != null ) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
				jsonObject.put("dtNascimento", dateFormat.format(pessoa.getDtNascimento()));
			}
			
			if (pessoa.getNomeMae() != null && !pessoa.getNomeMae().isEmpty()) {
				jsonObject.put("nomeMae", pessoa.getNomeMae());
			}
			
			if (pessoa.getNomePai() != null && !pessoa.getNomePai().isEmpty()) {
				jsonObject.put("nomePai", pessoa.getNomePai());
			}
			
			if(pessoa.getDtRegistro() != null) {
				jsonObject.put("dtRegistro", pessoa.getDtRegistro().getTime());
			}
			
			if(pessoa.getOperador() != null && !pessoa.getOperador().equals(new Operador())) {
				JSONObject jsonOperador = OperadorService.converter(pessoa.getOperador());
				jsonObject.put("operador", jsonOperador);
			}
			
			if(pessoa.getTipo() != null) {
				jsonObject.put("tipo", pessoa.getTipo());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	public static Pessoa converter(JSONObject jsonPessoa) {
		Pessoa pessoa = new Pessoa();
		try {
			
			if (jsonPessoa.has("idPessoa")) {
				pessoa.setIdPessoa(jsonPessoa.getLong("idPessoa"));
			}
			
			if (jsonPessoa.has("nome")) {
				pessoa.setNome(jsonPessoa.getString("nome"));
			}
			
			if (jsonPessoa.has("documento")) {
				pessoa.setDocumento(jsonPessoa.getString("documento"));
			}
			
			if (jsonPessoa.has("dtNascimento")) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
				pessoa.setDtNascimento(dateFormat.parse(jsonPessoa.getString("dtNascimento")));
			}
			
			if (jsonPessoa.has("nomeMae")) {
				pessoa.setNomeMae(jsonPessoa.getString("nomeMae"));
			}
			
			if (jsonPessoa.has("nomePai")) {
				pessoa.setNomePai(jsonPessoa.getString("nomePai"));
			}
			
			if(jsonPessoa.has("dtRegistro")) {
				pessoa.setDtRegistro(new Date(jsonPessoa.getLong("dtRegistro")));
			}
			
			if(jsonPessoa.has("operador")) {
				JSONObject jsonOperador = jsonPessoa.getJSONObject("operador");
				Operador operador = OperadorService.converter(jsonOperador);
				pessoa.setOperador(operador);
			}
			
			if(jsonPessoa.has("tipo")) {
				pessoa.setTipo(jsonPessoa.getString("tipo"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pessoa;
	}
	
}
