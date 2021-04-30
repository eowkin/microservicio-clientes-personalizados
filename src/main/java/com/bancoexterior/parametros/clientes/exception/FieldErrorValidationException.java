package com.bancoexterior.parametros.clientes.exception;

public class FieldErrorValidationException extends BadRequestUnprocessableException{
	
	public FieldErrorValidationException(String codigo) {
		super(codigo);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
