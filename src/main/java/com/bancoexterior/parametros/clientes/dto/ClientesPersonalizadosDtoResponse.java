package com.bancoexterior.parametros.clientes.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.bancoexterior.parametros.clientes.response.Resultado;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


@Data
public class ClientesPersonalizadosDtoResponse implements Serializable{

	private Resultado resultado;
	
	@JsonProperty("clientesPersonalizados")
	private List<ClientesPersonalizadosDto> listClientesPersonalizados;
	
	
	
	
	public ClientesPersonalizadosDtoResponse() {
		super();
		this.resultado = new Resultado();
		this.listClientesPersonalizados = new ArrayList<>();
	}




	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
