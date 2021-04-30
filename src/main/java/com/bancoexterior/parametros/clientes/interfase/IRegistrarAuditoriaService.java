package com.bancoexterior.parametros.clientes.interfase;

import com.bancoexterior.parametros.clientes.model.RegistrarAuditoriaRequest;

public interface IRegistrarAuditoriaService {
	
	void registrarAuditoria(RegistrarAuditoriaRequest auditoria,  String codigo, String mensaje, String errorAdicional);

}
