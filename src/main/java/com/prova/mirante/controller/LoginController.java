package com.prova.mirante.controller;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.prova.mirante.entity.Operador;
import com.prova.mirante.service.OperadorService;
import com.prova.mirante.util.JWTUtil;


@Stateless
@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginController {
		
	@Inject
	private OperadorService operadorService;
	
	public LoginController() {
		this.operadorService = new OperadorService();
	}
	
	@POST
	@Path("/logar")
	public Response login(String jsonOperador) {
		if (jsonOperador != null && !jsonOperador.isEmpty()) {
			try {
				JSONObject credentials = new JSONObject(jsonOperador);
				Operador operador = this.operadorService.login(credentials);
				if (operador != null && !operador.equals(new Operador())) {
					String token = JWTUtil.create(operador.getLogin());
					operador.setToken(token);
					JSONObject operadorReturn = OperadorService.converter(operador);
					return Response.ok().entity(operadorReturn.toString()).build();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Response.status(Response.Status.UNAUTHORIZED).build();
	}
	
}
