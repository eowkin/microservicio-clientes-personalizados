package com.bancoexterior.parametros.clientes.exception;

public class ClientePersonalizadoExistException extends BadRequestException{


	public ClientePersonalizadoExistException(String codigo) {
		super(codigo);
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
