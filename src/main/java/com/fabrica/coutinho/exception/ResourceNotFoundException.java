package com.fabrica.coutinho.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 6434972751710854116L;

	public ResourceNotFoundException(String exception) {
		super(exception);
	}
}
