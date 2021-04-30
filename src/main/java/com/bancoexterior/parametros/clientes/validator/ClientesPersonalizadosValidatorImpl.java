package com.bancoexterior.parametros.clientes.validator;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.bancoexterior.parametros.clientes.config.Codigos.CodRespuesta;
import com.bancoexterior.parametros.clientes.dto.ClientesPersonalizadosRequestCrear;
import com.bancoexterior.parametros.clientes.exception.ClientePersonalizadoExistException;
import com.bancoexterior.parametros.clientes.exception.ClientePersonalizadoNotExistException;
import com.bancoexterior.parametros.clientes.exception.FieldErrorValidationException;
import com.bancoexterior.parametros.clientes.service.IClientesPersonalizadosService;

@Component
public class ClientesPersonalizadosValidatorImpl implements IClientesPersonalizadosValidator{

	@Autowired
	private IClientesPersonalizadosService clienteService;
	
	
	
	/**
	 * Nombre: validarCrear 
	 * Descripcion: Invocar metodo para realizar validacion
	 * de los parametros de entrada y demas validaciones 
	 * antes de procesar al endPoint crear Cliente Personalizado.
	 *
	 * @param request     Objeto tipo ClientesPersonalizadosRequestCrear
	 * @param result     Objeto tipo BindingResult  
	 * @return void
	 * @version 1.0
	 * @author Eugenio Owkin
	 * @since 12/04/21
	 */
	@Override
	public void validarCrear(ClientesPersonalizadosRequestCrear request, BindingResult result) {
		//Validando los valores de entrada
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(FieldError::getDefaultMessage)
					.collect(Collectors.toList());
			throw new FieldErrorValidationException(errors.get(0));			
		}
		
		String codIbs = request.getClientesPersonalizadosDtoRequestCrear().getCodIbs();
		
		if(clienteService.existsById(codIbs)) {
			throw new ClientePersonalizadoExistException(CodRespuesta.CME2001);
		}
	}


	/**
	 * Nombre: validarActualizar 
	 * Descripcion: Invocar metodo para realizar validacion
	 * de los parametros de entrada y demas validaciones 
	 * antes de procesar al endPoint actualizar Cliente Personalizado.
	 *
	 * @param request     Objeto tipo ClientesPersonalizadosRequestCrear
	 * @param result     Objeto tipo BindingResult  
	 * @return void
	 * @version 1.0
	 * @author Eugenio Owkin
	 * @since 12/04/21
	 */
	@Override
	public void validarActualizar(ClientesPersonalizadosRequestCrear request, BindingResult result) {
		//Validando los valores de entrada
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(FieldError::getDefaultMessage)
					.collect(Collectors.toList());
			throw new FieldErrorValidationException(errors.get(0));			
		}
				
		String codIbs = request.getClientesPersonalizadosDtoRequestCrear().getCodIbs();
				
		if(!clienteService.existsById(codIbs)) {
			throw new ClientePersonalizadoNotExistException(CodRespuesta.CME2000);
		}
	}

}
