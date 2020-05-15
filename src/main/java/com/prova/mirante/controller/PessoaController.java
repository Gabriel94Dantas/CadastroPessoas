package com.prova.mirante.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import com.prova.mirante.entity.Pessoa;
import com.prova.mirante.service.PessoaService;

@Stateless
@Path("/pessoa")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PessoaController {

	@Inject
	private PessoaService pessoaService;
	
	public PessoaController() {
		this.pessoaService = new PessoaService();
	}
	
	@GET
	@Path("/listar")
	public Response listar() {
		try {
			List<Pessoa> pessoas = this.pessoaService.retornarTodos();
			JSONArray jsonArray = new JSONArray();
			if (pessoas != null && !pessoas.isEmpty()) {
				for (Pessoa pessoa : pessoas) {
					JSONObject jsonObject  = PessoaService.converter(pessoa);
					jsonArray.put(jsonObject);
				}
			}
			return Response.ok().entity(jsonArray.toString()).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.status(Response.Status.UNAUTHORIZED).build();
	}
	
	@GET
	@Path("/retornar/{idPessoa}")
	public Response getById(@PathParam("idPessoa") String idPessoa) {
		try {
			Pessoa pessoa = this.pessoaService.getById(new Long(idPessoa));
			JSONObject jsonObject = PessoaService.converter(pessoa);
			return Response.ok().entity(jsonObject.toString()).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.status(Response.Status.UNAUTHORIZED).build();
	}
	
	@PUT
	@Path("/editar")
	public Response editar(String jsonRecebido) {
		try {
			JSONObject jsonObject = new JSONObject(jsonRecebido);
			Pessoa pessoa = PessoaService.converter(jsonObject);
			this.pessoaService.editar(pessoa);
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.status(Response.Status.UNAUTHORIZED).build();
	}
	
	@POST
	@Path("/salvar")
	public Response salvar(String jsonRecebido) {
		try {
			JSONObject jsonObject = new JSONObject(jsonRecebido);
			Pessoa pessoa = PessoaService.converter(jsonObject);
			this.pessoaService.salvar(pessoa);
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.status(Response.Status.UNAUTHORIZED).build();
	}
	
	@DELETE
	@Path("/excluir")
	public Response excluir(String jsonRecebido) {
		try {
			JSONObject jsonObject = new JSONObject(jsonRecebido);
			Pessoa pessoa = PessoaService.converter(jsonObject);
			this.pessoaService.excluir(pessoa);
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.status(Response.Status.UNAUTHORIZED).build();
	}
	
}
