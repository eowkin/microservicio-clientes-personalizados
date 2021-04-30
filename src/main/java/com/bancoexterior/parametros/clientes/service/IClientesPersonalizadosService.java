package com.bancoexterior.parametros.clientes.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bancoexterior.parametros.clientes.dto.ClientesPersonalizadosDto;
import com.bancoexterior.parametros.clientes.dto.ClientesPersonalizadosDtoConsulta;
import com.bancoexterior.parametros.clientes.dto.ClientesPersonalizadosDtoResponse;
import com.bancoexterior.parametros.clientes.dto.ClientesPersonalizadosDtoResponseActualizar;
import com.bancoexterior.parametros.clientes.dto.ClientesPersonalizadosRequestConsulta;
import com.bancoexterior.parametros.clientes.dto.ClientesPersonalizadosRequestCrear;

public interface IClientesPersonalizadosService {
	
	public ClientesPersonalizadosDtoResponse consultarClientesPer(ClientesPersonalizadosRequestConsulta request);
	
	public ClientesPersonalizadosDtoResponseActualizar crear(ClientesPersonalizadosRequestCrear clientesPersonalizadosRequestCrear, HttpServletRequest requestHTTP);
	
	public ClientesPersonalizadosDtoResponseActualizar actualizar(ClientesPersonalizadosRequestCrear clientesPersonalizadosRequestCrear, HttpServletRequest requestHTTP);
	
	public boolean existsById(String codIbs);
	
	public ClientesPersonalizadosDto findById(String codIbs);
	
	public List<ClientesPersonalizadosDto> findAllDtoNativo(ClientesPersonalizadosDtoConsulta clientesPersonalizadosDtoConsulta);
	
}
