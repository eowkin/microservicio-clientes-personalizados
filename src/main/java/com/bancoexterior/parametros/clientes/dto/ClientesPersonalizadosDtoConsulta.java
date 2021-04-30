package com.bancoexterior.parametros.clientes.dto;

import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor @NoArgsConstructor
public class ClientesPersonalizadosDtoConsulta implements Serializable{

	@JsonProperty("codIbs")
	private String codIbs;
	
	@JsonProperty("nroIdCliente")
	private String nroIdCliente;
	
	@JsonProperty("nombreRif")
	private String nombreRif;
	
	@JsonProperty("flagActivo")
	private Boolean flagActivo;
	
	
	public ClientesPersonalizadosDtoConsulta(ClientesPersonalizadosRequestConsulta request) {
		super();
		this.codIbs = request.getClientesPersonalizadosDtoRequestConsulta().getCodIbs();
		this.nroIdCliente = request.getClientesPersonalizadosDtoRequestConsulta().getNroIdCliente();
		this.nombreRif = request.getClientesPersonalizadosDtoRequestConsulta().getNombreRif();
		this.flagActivo = request.getClientesPersonalizadosDtoRequestConsulta().getFlagActivo();
	}





	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
