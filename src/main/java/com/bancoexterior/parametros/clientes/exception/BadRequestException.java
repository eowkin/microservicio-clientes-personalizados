package com.bancoexterior.parametros.clientes.exception;

public class BadRequestException extends RuntimeException {

   
	public BadRequestException(String codigo) {
    	super(codigo);
    }

    /**
    * 
    */
	private static final long serialVersionUID = 1L;

}
