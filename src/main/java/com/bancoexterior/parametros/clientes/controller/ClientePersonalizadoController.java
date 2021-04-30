package com.bancoexterior.parametros.clientes.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bancoexterior.parametros.clientes.config.Codigos.Constantes;
import com.bancoexterior.parametros.clientes.config.Codigos.Servicios;
import com.bancoexterior.parametros.clientes.dto.ClientesPersonalizadosDtoResponse;
import com.bancoexterior.parametros.clientes.dto.ClientesPersonalizadosDtoResponseActualizar;
import com.bancoexterior.parametros.clientes.dto.ClientesPersonalizadosRequestConsulta;
import com.bancoexterior.parametros.clientes.dto.ClientesPersonalizadosRequestCrear;
import com.bancoexterior.parametros.clientes.service.IClientesPersonalizadosService;
import com.bancoexterior.parametros.clientes.util.Utils;
import com.bancoexterior.parametros.clientes.validator.IClientesPersonalizadosValidator;

@RestController
@RequestMapping("${microservicio.path.pre}" + "${microservicio.ambiente}")
public class ClientePersonalizadoController {
	private static final Logger LOGGER = LogManager.getLogger(ClientePersonalizadoController.class);
	
	@Autowired
	private IClientesPersonalizadosValidator clientePersonalizadoValidator;
	
	@Autowired
	private IClientesPersonalizadosService clientePersonalizadoService;
	
	
	
	/**
	 * Nombre: listClientePersonalizado 
	 * Descripcion: Invocar metodo para listar Clientes Personalizados
	 * por los parametros enviados.
	 * 
	 * @param request     Objeto tipo ClientesPersonalizadosRequestConsulta   
	 * @param requestHTTP Objeto tipo HttpServletRequest
	 * @return ResponseEntity<Object>
	 * @version 1.0
	 * @author Eugenio Owkin
	 * @throws ApiUnprocessableEntity 
	 * @since 12/04/21
	 */
	@PostMapping(path =Servicios.CLIENTEPERSONALIZADOSURLV1+"/consultas", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> listClientePersonalizado(@RequestBody ClientesPersonalizadosRequestConsulta request, 
			HttpServletRequest requestHTTP){
		LOGGER.info(Servicios.CLIENTESPERSONALIZADOSCONTROLLERI);
		LOGGER.info(request);
		
		
	    
	    
		ClientesPersonalizadosDtoResponse response;
		HttpStatus estatusCM;
		
		response = clientePersonalizadoService.consultarClientesPer(request);
		estatusCM = Utils.getHttpStatus(response.getResultado().getCodigo().trim());
		
		LOGGER.info(estatusCM);
		LOGGER.info(response);
		LOGGER.info(Servicios.CLIENTESPERSONALIZADOSCONTROLLERF);
		if(response.getResultado().getCodigo().trim().substring(0, 1).equalsIgnoreCase(Constantes.SUBSTRING_COD_OK)) {
			return new ResponseEntity<>(response,estatusCM);
		}else {
		
			return new ResponseEntity<>(response.getResultado(),estatusCM);
		}
		
	}
	
	
	/**
	 * Nombre: crearClientePersonalizado 
	 * Descripcion: Invocar metodo para ingresar Cliente Personalizados nuevo
	 * 
	 * @param request     Objeto tipo ClientesPersonalizadosRequestCrear  
	 * @param result Objeto tipo BindingResult 
	 * @param requestHTTP Objeto tipo HttpServletRequest
	 * @return ResponseEntity<Object>
	 * @version 1.0
	 * @author Eugenio Owkin
	 * @throws ApiUnprocessableEntity 
	 * @since 12/04/21
	 */

	@PostMapping(path =Servicios.CLIENTEPERSONALIZADOSURLV1, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> crearClientePersonalizado(@Valid @RequestBody ClientesPersonalizadosRequestCrear request, BindingResult result, 
			HttpServletRequest requestHTTP){
		LOGGER.info(Servicios.CLIENTESPERSONALIZADOSCONTROLLERI);
		LOGGER.info(request);
		
		clientePersonalizadoValidator.validarCrear(request, result);
	    
	    
		ClientesPersonalizadosDtoResponseActualizar response;
		HttpStatus estatusCM;
		
		response = clientePersonalizadoService.crear(request, requestHTTP);
		estatusCM = Utils.getHttpStatus(response.getResultado().getCodigo().trim());
		
		LOGGER.info(estatusCM);
		LOGGER.info(response);
		LOGGER.info(Servicios.CLIENTESPERSONALIZADOSCONTROLLERF);
		if(response.getResultado().getCodigo().trim().substring(0, 1).equalsIgnoreCase(Constantes.SUBSTRING_COD_OK)) {
			return new ResponseEntity<>(response,estatusCM);
		}else {
		
			return new ResponseEntity<>(response.getResultado(),estatusCM);
		}
		
	}
	
	
	/**
	 * Nombre: actualizarClientePersonalizado 
	 * Descripcion: Invocar metodo para actualizar un Cliente Personalizado
	 * 
	 * @param request     Objeto tipo ClientesPersonalizadosRequestCrear  
	 * @param result      Objeto tipo BindingResult 
	 * @param requestHTTP Objeto tipo HttpServletRequest
	 * @return ResponseEntity<Object>
	 * @version 1.0
	 * @author Eugenio Owkin
	 * @throws ApiUnprocessableEntity 
	 * @since 12/04/21
	 */

	@PutMapping(path =Servicios.CLIENTEPERSONALIZADOSURLV1, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> actualizarClientePersonalizado(@Valid @RequestBody ClientesPersonalizadosRequestCrear request, BindingResult result, 
			HttpServletRequest requestHTTP){
		LOGGER.info(Servicios.CLIENTESPERSONALIZADOSCONTROLLERI);
		LOGGER.info(request);
		
		clientePersonalizadoValidator.validarActualizar(request, result);
	    
	    
		ClientesPersonalizadosDtoResponseActualizar response;
		HttpStatus estatusCM;
		
		response = clientePersonalizadoService.actualizar(request, requestHTTP);
		estatusCM = Utils.getHttpStatus(response.getResultado().getCodigo().trim());
		
		LOGGER.info(estatusCM);
		LOGGER.info(response);
		LOGGER.info(Servicios.CLIENTESPERSONALIZADOSCONTROLLERF);
		if(response.getResultado().getCodigo().trim().substring(0, 1).equalsIgnoreCase(Constantes.SUBSTRING_COD_OK)) {
			return new ResponseEntity<>(response,estatusCM);
		}else {
		
			return new ResponseEntity<>(response.getResultado(),estatusCM);
		}
		
	}
	
}
