package com.prova.mirante.exception;

import java.io.Serializable;

public class RestServiceException extends Exception implements
		Serializable {

	private static final long serialVersionUID = 1L;
	
	public RestServiceException() {
		super();
	}
	
	public RestServiceException(String msg) {
		super(msg);
	}
	
	public RestServiceException(String msg, Exception e) {
		super(msg, e);
	}

}