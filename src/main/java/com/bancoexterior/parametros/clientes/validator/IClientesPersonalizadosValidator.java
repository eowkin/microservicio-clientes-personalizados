package com.bancoexterior.parametros.clientes.validator;

import org.springframework.validation.BindingResult;

import com.bancoexterior.parametros.clientes.dto.ClientesPersonalizadosRequestCrear;



public interface IClientesPersonalizadosValidator {

	public void validarCrear(ClientesPersonalizadosRequestCrear request, BindingResult result);
	
	public void validarActualizar(ClientesPersonalizadosRequestCrear request, BindingResult result);
}
