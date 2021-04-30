package com.bancoexterior.parametros.clientes.exception;

public class ClientePersonalizadoNotExistException extends BadRequestException{


	public ClientePersonalizadoNotExistException(String codigo) {
		super(codigo);
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
