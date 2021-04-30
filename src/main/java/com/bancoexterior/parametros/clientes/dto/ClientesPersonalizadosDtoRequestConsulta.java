package com.bancoexterior.parametros.clientes.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.bancoexterior.parametros.clientes.config.Codigos.CodRespuesta;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


@Data
public class ClientesPersonalizadosDtoRequestConsulta implements Serializable{

	
	@JsonProperty("codIbs")
	@NotEmpty(message=CodRespuesta.CDE1004)
	@Size(min=1, max=10 , message = CodRespuesta.CDE1004)
	private String codIbs;
	
	@JsonProperty("nroIdCliente")
	@NotEmpty(message=CodRespuesta.CDE1005)
	@Size(min=1, max=16 , message = CodRespuesta.CDE1005)
	private String nroIdCliente;
	
	@JsonProperty("nombreRif")	
	@NotEmpty(message=CodRespuesta.CDE1006)
	private String nombreRif;
	
		
	@JsonProperty("flagActivo")
	@NotNull(message=CodRespuesta.CDE1007)
	private Boolean flagActivo;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
