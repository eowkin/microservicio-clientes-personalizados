package com.bancoexterior.parametros.clientes.interfase;

import com.bancoexterior.parametros.clientes.model.WSRequest;
import com.bancoexterior.parametros.clientes.model.WSResponse;

public interface  IWSService {
	WSResponse post(WSRequest request) ;
}
