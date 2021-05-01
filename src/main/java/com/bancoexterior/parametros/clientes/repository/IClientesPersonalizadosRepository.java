package com.bancoexterior.parametros.clientes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bancoexterior.parametros.clientes.config.Codigos.SQLUtils;
import com.bancoexterior.parametros.clientes.entities.ClientesPersonalizados;

public interface IClientesPersonalizadosRepository extends JpaRepository<ClientesPersonalizados, String>{
	
	@Query(value = SQLUtils.SELECTCLIENTESPERSONALIZADOS, nativeQuery = true)
	public List<ClientesPersonalizados> getClientesPersonalizadosByNativo(String codIbs, String nroIdCliente, String nombreRif, String flag, boolean flagActivo);
}
