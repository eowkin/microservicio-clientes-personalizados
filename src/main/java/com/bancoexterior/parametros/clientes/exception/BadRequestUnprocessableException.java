package com.bancoexterior.parametros.clientes.exception;

public class BadRequestUnprocessableException extends RuntimeException {
	
	public BadRequestUnprocessableException(String codigo) {
		super(codigo);
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
