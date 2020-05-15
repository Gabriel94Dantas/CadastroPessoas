package com.prova.mirante.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RestServiceExceptionHandler implements ExceptionMapper<RestServiceException> {

	@Override
	public Response toResponse(final RestServiceException exception) {
		
		return Response.status(Status.OK)
				.entity(new ErrorMessage(exception.getMessage()))
				.type(MediaType.APPLICATION_JSON).build();
	}

}