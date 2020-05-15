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

import com.prova.mirante.entity.Operador;
import com.prova.mirante.service.OperadorService;

@Stateless
@Path("/operador")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OperadorController {
	
	@Inject
	private OperadorService operadorService;
	
	public OperadorController() {
		this.operadorService = new OperadorService();
	}
	
	@GET
	@Path("/listar")
	public Response listar() {
		try {
			List<Operador> operadores = this.operadorService.retornarTodos();
			JSONArray jsonArray = new JSONArray();
			if (operadores != null && !operadores.isEmpty()) {
				for (Operador operador : operadores) {
					JSONObject jsonObject  = OperadorService.converter(operador);
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
	@Path("/retornar/{idOperador}")
	public Response getById(@PathParam("idOperador") String idOperador) {
		try {
			Operador operador = this.operadorService.getById(new Long(idOperador));
			JSONObject jsonObject = OperadorService.converter(operador);
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
			Operador operador = OperadorService.converter(jsonObject);
			this.operadorService.editar(operador);
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
			Operador operador = OperadorService.converter(jsonObject);
			this.operadorService.salvar(operador);
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
			Operador operador = OperadorService.converter(jsonObject);
			this.operadorService.excluir(operador);
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.status(Response.Status.UNAUTHORIZED).build();
	}

}
