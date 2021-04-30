package com.bancoexterior.parametros.clientes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.bancoexterior.parametros.clientes.config.Codigos.Servicios;
import com.bancoexterior.parametros.clientes.dto.ClientesPersonalizadosDto;
import com.bancoexterior.parametros.clientes.dto.ClientesPersonalizadosDtoConsulta;
import com.bancoexterior.parametros.clientes.dto.ClientesPersonalizadosDtoRequestCrear;
import com.bancoexterior.parametros.clientes.dto.ClientesPersonalizadosDtoResponse;
import com.bancoexterior.parametros.clientes.dto.ClientesPersonalizadosDtoResponseActualizar;
import com.bancoexterior.parametros.clientes.dto.ClientesPersonalizadosRequestConsulta;
import com.bancoexterior.parametros.clientes.dto.ClientesPersonalizadosRequestCrear;
import com.bancoexterior.parametros.clientes.entities.ClientesPersonalizados;
import com.bancoexterior.parametros.clientes.model.RegistrarAuditoriaRequest;
import com.bancoexterior.parametros.clientes.repository.IClientesPersonalizadosRepository;
import com.bancoexterior.parametros.clientes.response.Resultado;
import com.bancoexterior.parametros.clientes.interfase.IRegistrarAuditoriaService;
import com.bancoexterior.parametros.clientes.config.Codigos.CodRespuesta;
import com.bancoexterior.parametros.clientes.config.Codigos.Constantes;

@Service
public class ClientesPersonalizadosServiceImpl implements IClientesPersonalizadosService{
	private static final Logger LOGGER = LogManager.getLogger(ClientesPersonalizadosServiceImpl.class);
	
	@Autowired
	private IClientesPersonalizadosRepository repo;

	@Autowired
	private IRegistrarAuditoriaService registrarA;
	
	@Autowired
	private Environment env;
	
	
	/**
	 * Nombre: existsById 
	 * Descripcion: Invocar metodo para buscar si existe o no 
	 * un Cliente Personalizado por id.
	 * @param codIbs String    
	 * @return boolean
	 * @version 1.0
	 * @author Eugenio Owkin
	 * @since 12/04/21
	 */
	@Override
	public boolean existsById(String codIbs) {
		return repo.existsById(codIbs);
	}
	
	/**
	 * Nombre: findById 
	 * Descripcion: Invocar metodo para una busqueda de un Cliente
	 * Personalizado por id.
	 *
	 * @param codIbs String    
	 * @return ClientesPersonalizadosDto
	 * @version 1.0
	 * @author Eugenio Owkin
	 * @since 12/04/21
	 */
	@Override
	public ClientesPersonalizadosDto findById(String codIbs) {
		ClientesPersonalizados clientesPersonalizados = repo.findById(codIbs).orElse(null);
		
		if(clientesPersonalizados != null) {
			ClientesPersonalizadosDto clientesPersonalizadosDto = new ClientesPersonalizadosDto();
			clientesPersonalizadosDto.setCodIbs(clientesPersonalizados.getCodIbs());
			clientesPersonalizadosDto.setNroIdCliente(clientesPersonalizados.getNroIdCliente());
			clientesPersonalizadosDto.setNombreRif(clientesPersonalizados.getNombreRif());
			clientesPersonalizadosDto.setCodUsuario(clientesPersonalizados.getCodUsuario());
			clientesPersonalizadosDto.setFlagActivo(clientesPersonalizados.getFlagActivo());
			clientesPersonalizadosDto.setFechaModificacion(clientesPersonalizados.getFechaModificacion());
			return clientesPersonalizadosDto;
		}else {
			return null;
		}
		
		
	}
	
	
	/**
	 * Nombre: findAllDtoNativo 
	 * Descripcion: Invocar metodo para una busqueda de un Cliente
	 * Personalizado por parametros.
	 *
	 * @param clientesPersonalizadosDtoConsulta ClientesPersonalizadosDtoConsulta     
	 * @return ClientesPersonalizadosDto
	 * @version 1.0
	 * @author Eugenio Owkin
	 * @since 12/04/21
	 */
	@Override
	public List<ClientesPersonalizadosDto> findAllDtoNativo(
			ClientesPersonalizadosDtoConsulta clientesPersonalizadosDtoConsulta) {

		String codIbs = Constantes.BLANK;
		String nroIdCliente = Constantes.BLANK;
		String nombreRif = Constantes.BLANK;
		String flag = Constantes.BLANK;
		boolean flagActivo = false;
		
		if(clientesPersonalizadosDtoConsulta.getCodIbs() != null) {
			codIbs = clientesPersonalizadosDtoConsulta.getCodIbs();
		}
		
		if(clientesPersonalizadosDtoConsulta.getNroIdCliente() != null) {
			nroIdCliente = clientesPersonalizadosDtoConsulta.getNroIdCliente();
		}
		
		if(clientesPersonalizadosDtoConsulta.getNombreRif() != null) {
			nombreRif = clientesPersonalizadosDtoConsulta.getNombreRif();
		}
		
		if(clientesPersonalizadosDtoConsulta.getFlagActivo() != null) {
			flag = "si";
			flagActivo = clientesPersonalizadosDtoConsulta.getFlagActivo();
		}
		
		List<ClientesPersonalizados> listClientesPersonalizados = repo.getClientesPersonalizadosByNativo(codIbs, nroIdCliente, nombreRif, flag, flagActivo);
		List<ClientesPersonalizadosDto> listClientesPersonalizadosDto = new ArrayList<>();
		
		for (ClientesPersonalizados clientesPersonalizados : listClientesPersonalizados) {
			ClientesPersonalizadosDto clientesPersonalizadosDto = new ClientesPersonalizadosDto();
			clientesPersonalizadosDto.setCodIbs(clientesPersonalizados.getCodIbs());
			clientesPersonalizadosDto.setNroIdCliente(clientesPersonalizados.getNroIdCliente());
			clientesPersonalizadosDto.setNombreRif(clientesPersonalizados.getNombreRif());
			clientesPersonalizadosDto.setCodUsuario(clientesPersonalizados.getCodUsuario());
			clientesPersonalizadosDto.setFlagActivo(clientesPersonalizados.getFlagActivo());
			clientesPersonalizadosDto.setFechaModificacion(clientesPersonalizados.getFechaModificacion());
			
			listClientesPersonalizadosDto.add(clientesPersonalizadosDto);
		}
		
		return listClientesPersonalizadosDto;
	}
	
	/**
	 * Nombre: consultarClientesPer 
	 * Descripcion: Invocar metodo para la gestion de consulta a realizar
	 * para la busqueda de los Clientes Personalizados 
	 * con los parametros enviados.
	 *
	 * @param request     Objeto tipo ClientesPersonalizadosRequestConsulta
	 * @return ClientesPersonalizadosDtoResponse
	 * @version 1.0
	 * @author Eugenio Owkin
	 * @since 12/04/21
	 */
	@Override
	public ClientesPersonalizadosDtoResponse consultarClientesPer(ClientesPersonalizadosRequestConsulta request) {
		LOGGER.info(Servicios.CLIENTESPERSONALIZADOSSERVICEICONSULTA);
		ClientesPersonalizadosDtoResponse response = new ClientesPersonalizadosDtoResponse();
		
		Resultado resultado = new Resultado();
		String errorCM = Constantes.BLANK;
		String codigo =  CodRespuesta.C0000;
		
		List<ClientesPersonalizadosDto> listClientesPersonalizados;
		ClientesPersonalizadosDtoConsulta clientesPersonalizadosDtoConsulta = new ClientesPersonalizadosDtoConsulta(request);  
		
		
		try {
			codigo = validaDatosConsulta(request);
			if(codigo.equalsIgnoreCase(CodRespuesta.C0000)) {
				//consulta BD
				listClientesPersonalizados = this.findAllDtoNativo(clientesPersonalizadosDtoConsulta);
				response.setListClientesPersonalizados(listClientesPersonalizados);
				
				//Validar Respuesta
				resultado = validaConsulta(listClientesPersonalizados);
				codigo = resultado.getCodigo();
				errorCM = resultado.getDescripcion();
			}
		} catch (Exception e) {
			LOGGER.error(e);
			codigo = CodRespuesta.CME6000;
			errorCM = Constantes.EXC+e;
		}
		
		response.getResultado().setCodigo(codigo);
		response.getResultado().setDescripcion(env.getProperty(Constantes.RES+codigo,codigo).replace(Constantes.ERROR, errorCM));
		
		LOGGER.info(response);
		LOGGER.info(Servicios.CLIENTESPERSONALIZADOSSERVICEFCONSULTA);
		return response;
	}
	
	
	/**
	 * Nombre: crear 
	 * Descripcion: Invocar metodo para crear el Cliente 
	 * Personalizado con los parametros enviados.
	 *
	 * @param request     Objeto tipo ClientesPersonalizadosRequestCrear
	 * @param requestHTTP Objeto tipo HttpServletRequest
	 * @return ClientesPersonalizadosDtoResponseActualizar
	 * @version 1.0
	 * @author Eugenio Owkin
	 * @since 12/04/21
	 */
	@Override
	public ClientesPersonalizadosDtoResponseActualizar crear(ClientesPersonalizadosRequestCrear request, HttpServletRequest requestHTTP) {
		LOGGER.info(Servicios.CLIENTESPERSONALIZADOSSERVICEICREAR);
		LOGGER.info(request);
		String microservicio = Servicios.CLIENTESPERSONALIZADOS;
		RegistrarAuditoriaRequest reAU = null;
		
		reAU = new RegistrarAuditoriaRequest(request, microservicio, requestHTTP);
		String errorM = Constantes.BLANK;
		String codigo =  CodRespuesta.C0000;
		
		ClientesPersonalizados obj = new ClientesPersonalizados();
		ClientesPersonalizadosDtoResponseActualizar response = new ClientesPersonalizadosDtoResponseActualizar();
		
		Resultado resultado = new Resultado();
		
		resultado.setCodigo(CodRespuesta.C0000);
		resultado.setDescripcion(env.getProperty(Constantes.RES+CodRespuesta.C0000,CodRespuesta.C0000).replace(Constantes.ERROR, Constantes.BLANK));
		
		try {
			ClientesPersonalizadosDtoRequestCrear dtoRequestCrear = request.getClientesPersonalizadosDtoRequestCrear();
			obj.setCodIbs(dtoRequestCrear.getCodIbs());
			obj.setNroIdCliente(dtoRequestCrear.getNroIdCliente());
			obj.setNombreRif(dtoRequestCrear.getNombreRif());
			obj.setCodUsuario(request.getCodUsuarioMR());
			obj.setFlagActivo(dtoRequestCrear.getFlagActivo());
			
			LOGGER.info(obj);
			repo.save(obj);
			response.setResultado(resultado);
			
		} catch (Exception e) {
			LOGGER.error(e);
			codigo = CodRespuesta.CME6001;
			errorM = Constantes.EXC+e;
			response.getResultado().setCodigo(CodRespuesta.CME6001);
			response.getResultado().setDescripcion(env.getProperty(Constantes.RES+CodRespuesta.CME6001,CodRespuesta.CME6001));
		}
		
		resultado.setCodigo(codigo);
		resultado.setDescripcion(env.getProperty(Constantes.RES+codigo,codigo).replace(Constantes.ERROR, errorM));
		
		if(reAU != null) {
			reAU.setIdCliente(Constantes.RIF);
			reAU.setCedula(Constantes.CEDULA);
			reAU.setTelefono(Constantes.TELEFONO);
			reAU.setIdCanal(request.getCanalCM());
			registrarAuditoriaBD(reAU, resultado, errorM);
		}
		
		LOGGER.info(Servicios.CLIENTESPERSONALIZADOSSERVICEFCREAR);
		return response;
	}
	
	
	/**
	 * Nombre: actualizar 
	 * Descripcion: Invocar metodo para actualizar el Cliente 
	 * Personalizado con los parametros enviados.
	 *
	 * @param request     Objeto tipo ClientesPersonalizadosRequestCrear
	 * @param requestHTTP Objeto tipo HttpServletRequest
	 * @return ClientesPersonalizadosDtoResponseActualizar
	 * @version 1.0
	 * @author Eugenio Owkin
	 * @since 12/04/21
	 */
	@Override
	public ClientesPersonalizadosDtoResponseActualizar actualizar(ClientesPersonalizadosRequestCrear request, HttpServletRequest requestHTTP) {
		LOGGER.info(Servicios.CLIENTESPERSONALIZADOSSERVICEIACTUALIZAR);
		LOGGER.info(request);
		String microservicio = Servicios.CLIENTESPERSONALIZADOSACTUALIZAR;
		RegistrarAuditoriaRequest reAU = null;
		
		reAU = new RegistrarAuditoriaRequest(request, microservicio, requestHTTP);
		String errorM = Constantes.BLANK;
		String codigo =  CodRespuesta.C0000;

		ClientesPersonalizados obj = new ClientesPersonalizados();
		ClientesPersonalizadosDtoResponseActualizar response = new ClientesPersonalizadosDtoResponseActualizar();
		
		Resultado resultado = new Resultado();
		resultado.setCodigo(CodRespuesta.C0000);
		resultado.setDescripcion(env.getProperty(Constantes.RES+CodRespuesta.C0000,CodRespuesta.C0000).replace(Constantes.ERROR, Constantes.BLANK));
		
		try {
			ClientesPersonalizadosDtoRequestCrear dtoRequestCrear = request.getClientesPersonalizadosDtoRequestCrear();
			
			ClientesPersonalizadosDto clientesPersonalizadosDto = this.findById(dtoRequestCrear.getCodIbs()); 
			
			obj.setCodIbs(clientesPersonalizadosDto.getCodIbs());
			obj.setNroIdCliente(clientesPersonalizadosDto.getNroIdCliente());
			obj.setNombreRif(dtoRequestCrear.getNombreRif());
			obj.setCodUsuario(request.getCodUsuarioMR());
			obj.setFlagActivo(dtoRequestCrear.getFlagActivo());
			obj.setFechaModificacion(clientesPersonalizadosDto.getFechaModificacion());
			
			LOGGER.info(obj);
			repo.save(obj);
			response.setResultado(resultado);
			
		} catch (Exception e) {
			LOGGER.error(e);
			codigo = CodRespuesta.CME6001;
			errorM = Constantes.EXC+e;
			response.getResultado().setCodigo(CodRespuesta.CME6001);
			response.getResultado().setDescripcion(env.getProperty(Constantes.RES+CodRespuesta.CME6001,CodRespuesta.CME6001));
		}
		
		resultado.setCodigo(codigo);
		resultado.setDescripcion(env.getProperty(Constantes.RES+codigo,codigo).replace(Constantes.ERROR, errorM));
		
		if(reAU != null) {
			reAU.setIdCliente(Constantes.RIF);
			reAU.setCedula(Constantes.CEDULA);
			reAU.setTelefono(Constantes.TELEFONO);
			reAU.setIdCanal(request.getCanalCM());
			registrarAuditoriaBD(reAU, resultado, errorM);
		}
		LOGGER.info(Servicios.CLIENTESPERSONALIZADOSSERVICEFACTUALIZAR);
		return response;
	}
	
	
	
	
	
	/**
     * Nombre:                 registrarAuditoriaBD
     * Descripcion:            Registrar Auditoria en Web Service
     *
     * @param  req  Objeto RegistrarAuditoriaRequest
     * @param  codigo   Codigo de respuesta
     * @param descripcion Descripcion del resultado
     * @version 1.0
     * @author Eugenio Owkin
	 * @since 12/04/21
	 */
	private void registrarAuditoriaBD(RegistrarAuditoriaRequest registrarAu,Resultado response, String errorAdicional) {
			
		        registrarA.registrarAuditoria(registrarAu, response.getCodigo(),response.getDescripcion(),errorAdicional);	
	}

	/**
     * Nombre:                  validaDatosConsulta
     * Descripcion:             Valida datos de entrada del metodo de consulta.
     *
     * @param  Objeto MonedasRequest
     * @return String  Codigo resultado de la evaluacion.
     * @version 1.0
     * @author Eugenio Owkin
	 * @since 12/04/21
	 */
	
	private String validaDatosConsulta(ClientesPersonalizadosRequestConsulta request) {
		LOGGER.info("dentro de validarDatosConsulta");
		LOGGER.info(request);
		String codigo = CodRespuesta.C0000;
		String codIbs;
		String nroIdCliente;
		String nombreRif;
		boolean flagActivo;
		
		
		codIbs = request.getClientesPersonalizadosDtoRequestConsulta().getCodIbs() == null ? "000":request.getClientesPersonalizadosDtoRequestConsulta().getCodIbs();
		nroIdCliente = request.getClientesPersonalizadosDtoRequestConsulta().getNroIdCliente() == null ? "000":request.getClientesPersonalizadosDtoRequestConsulta().getNroIdCliente();
		nombreRif = request.getClientesPersonalizadosDtoRequestConsulta().getNombreRif() == null ? "000":request.getClientesPersonalizadosDtoRequestConsulta().getNombreRif();
		flagActivo = request.getClientesPersonalizadosDtoRequestConsulta().getFlagActivo() == null ? Boolean.parseBoolean(Constantes.TRUE) : request.getClientesPersonalizadosDtoRequestConsulta().getFlagActivo();
		
		
		request.getClientesPersonalizadosDtoRequestConsulta().setCodIbs(codIbs);
		request.getClientesPersonalizadosDtoRequestConsulta().setNroIdCliente(nroIdCliente);
		request.getClientesPersonalizadosDtoRequestConsulta().setNombreRif(nombreRif);
		request.getClientesPersonalizadosDtoRequestConsulta().setFlagActivo(flagActivo);
		
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<ClientesPersonalizadosRequestConsulta>> errores = validator.validate(request);
		
	
			for (ConstraintViolation<ClientesPersonalizadosRequestConsulta> cv : errores) {
				
				if ( !cv.getMessage().equalsIgnoreCase(Constantes.BLANK)) {
					codigo = cv.getMessage();
					 break;
				}

			}

		
		return codigo;
	}

	/**
     * Nombre:                  validaConsulta
     * Descripcion:             Metodo para evaluar el resultado de la consulta de las monedas
     *
     * @param listLimitesGeneralesDto   Objeto List<LimitesGeneralesDto> 
     * @return Resultado                Objeto con la información de la evaluacion.
     * @version 1.0
     * @author Eugenio Owkin
	 * @since 12/04/21
	 */
    
	
	private Resultado validaConsulta(List<ClientesPersonalizadosDto> listClientesPersonalizadosDto) {
		Resultado resultado = new Resultado();
		resultado.setCodigo(CodRespuesta.C0000);
		resultado.setDescripcion(Constantes.BLANK);
		
		if(listClientesPersonalizadosDto.isEmpty()) {
			resultado.setCodigo(CodRespuesta.C0001);
			return resultado;
		}

	    
		LOGGER.info(resultado);
		return resultado;
		
	}

	

	



}
