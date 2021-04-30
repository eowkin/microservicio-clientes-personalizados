package com.bancoexterior.parametros.clientes.dto;

import java.io.Serializable;

import com.bancoexterior.parametros.clientes.response.Resultado;

import lombok.Data;

@Data
public class ClientesPersonalizadosDtoResponseActualizar implements Serializable{

	
	private Resultado resultado;
	
	
	
	public ClientesPersonalizadosDtoResponseActualizar() {
		super();
		this.resultado = new Resultado();
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
