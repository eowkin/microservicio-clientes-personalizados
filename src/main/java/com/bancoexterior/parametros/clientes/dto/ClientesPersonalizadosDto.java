package com.bancoexterior.parametros.clientes.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor @NoArgsConstructor
public class ClientesPersonalizadosDto implements Serializable{

	@JsonProperty("codIbs")
	private String codIbs;
	
	@JsonProperty("nroIdCliente")
	private String nroIdCliente;
	
	@JsonProperty("nombreRif")
	private String nombreRif;
	
	@JsonProperty("codUsuario")
	private String codUsuario;
	
	@JsonProperty("flagActivo")
	private Boolean flagActivo;
	
	@JsonProperty("fechaModificacion")
	private Date fechaModificacion;
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
